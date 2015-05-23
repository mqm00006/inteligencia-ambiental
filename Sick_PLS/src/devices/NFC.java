/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devices;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import sick_pls.*;
import java.io.*;
import java.util.*;
import gnu.io.*;

/**
 *
 * @author Macarena
 */
public class NFC implements Runnable {

    //Variables para la comunicación con Arduino

    private OutputStream salida = null;
    private InputStream entrada = null;
    SerialPort puertoSerie;

    //Fichero donde está almacenada la información relacionada con las etiquetas
    String FICHERO_ETIQUETAS = "etiquetas.txt";
    //Variable donde iremos almacenando los distintos UID de las tarjetas que pasaremos por el escáner
    String uidEtiqueta = "";
    //Vector con Objetos de tipo fichar Robot que sirve para almacenar los datos relativos a los robots
    // y la hora a la que pasan por las etiquetas, lo ideal es que en un futuro donde sean muchos los robots
    //esto se almacene en una Base de Datos
    ArrayList<ficharRobot> fichar = new ArrayList<ficharRobot>();
    AtomicInteger contador = new AtomicInteger(0);
    //String donde se almacena la información que se mostrará por pantalla a través de la GUI
    String mostrar = "";

    private String puerto;
    private int baudRate;
    private final int TIEMPO_ESPERA = 2000;
    public boolean cerrar;

    /**
     *
     * Este método se encarga de mostrar por pantalla la información que hay
     * almacenada en el fichero de las etiquetas, mostrando la información
     * relacionada con la etiqueta correspondiente.
     *
     * @param m. String que contiene la MAC del robot o dispositivo que está
     * leyendo la tarjeta y donde se está ejecutando el programa
     *
     */
    public void Mostrar_Informacion(String m) throws FileNotFoundException, IOException {
        //Para ello creamos las instancia de las clases para la lectura del fichero
        FileReader fe = new FileReader(FICHERO_ETIQUETAS);
        BufferedReader be = new BufferedReader(fe);

        String cadena2, informacion;

        //En cadena2 vamos almacenando las lineas que vamos leyendo del fichero
        while ((cadena2 = be.readLine()) != null) {
            //En dir se obtiene una subcadena que contiene los 20 primeros caracteres y que contienen la dirección MAC
            String dir = cadena2.substring(0, 20);
            //En info almacenamos la línea de fichero separada por ;. Debemos de recordar que en el fichero los datos se 
            //encuentran separados por ; Es decir, tenemos la MAC;INFORMACION
            String[] info = cadena2.split(";");
            //Si coincide que alguna de las MAC del fichero coincide con la que manda Arduino, de la etiqueta que acaba 
            // de escanear mostramos la info
            if (dir.equals(uidEtiqueta)) {
                //Mostraremos la segunda parte de el vector puesto que es la que contiene la INFORMACION
                informacion = info[1];
                mostrar += "El robot con MAC: " + m + " " + informacion + "\n";
                //pantalla.setText( mostrar);
                GUI.printNFC(mostrar);
                
               
                //MOSTRAR EN TEXTAREA CON METODO QUE ME DE ANGEL
            }
        }
        be.close();
    }

    SerialPortEventListener evento = new SerialPortEventListener() {
        @Override
        /*
         * Este método se llama cada vez que se produce un Evento en el puerto Serie.
         * @param spe Es el evento que ocurre y que debemos capturar
         * 
         */
        public synchronized void serialEvent(SerialPortEvent spe) {

            //Si el evento que se produce es del tipo que nos mandan datos, es decir, se lee una etiqueta
            //realizamos lo siguiente
            if (spe.getEventType() == SerialPortEvent.DATA_AVAILABLE) {

                //Esta variable sirve para controlar que si ya tenemos una entrada en el vector fichar, con ese Robot y 
                //esa etiqueta que no se vuelva a crear, sino que se renueve el tiempo. Es decir, si tengo una entrada en el 
                //vector que me dice que un Robot ha pasado por la etiqueta Amarilla, por ejemplo, pues que no se cree otra
                //entrada para ese mismo Robot y esa misma etiqueta, sino que se modifique la que ya tenemos
                boolean esta = false;

                //Creamos una instancia para leer el flujo de entrada del Puerto Serie
                BufferedReader reader = new BufferedReader(new InputStreamReader(entrada));
                String line = "";

                try {
                    //Leemos lo que se nos manda por el puerto serie. Almacenamos en un string las cadenas que nos vayan llegando
                    //hasta que se lea un ; que me indica que se ha leido al completo el UID de la tarjeta. Con esto garantizamos
                    //que no se leen ráfagas de datos, sino el dato al completo.
                    line = reader.readLine();
                    if (!line.contains(";")) {
                        line += line;
                    }

                    reader.close();
                } catch (IOException ex) {
                }

                //Con esto obtengo la MAC del dispositivo donde se ejecuta el programa, es decir, del Robot.
                InetAddress ip;
                NetworkInterface network;
                byte[] mac = null;
                try {
                    ip = InetAddress.getLocalHost();
                    network = NetworkInterface.getByInetAddress(ip);
                    mac = network.getHardwareAddress();
                } catch (UnknownHostException | SocketException ex) {
                    System.out.println("ERROR");
                }

                //La convierto a String
                String dirMAC = "";
                for (Integer i = 0; i < mac.length; i++) {
                    dirMAC += mac[i];
                }

                //Con esto consigo eliminar el ; final que tenía el UID de la etiqueta y lo almaceno en la variable uid
                String linea[] = line.split(";");
                String uid = linea[0];

                //Para que no me almacene valores vacíos o el mismo
                if (!uid.equals("") && !uid.equals(uidEtiqueta)) {
                    uidEtiqueta = uid;
                }
                try {
                    for (int i = 0; i < fichar.size(); i++) {

                        //Si exite en el vector un dato de tipo ficharRobot que ya contiene la MAC del robot y el UID de la 
                        //etiqueta quiere decir que ya se ha leido esa etiqueta con ese robot. Debemos comprobar que no se haya 
                        //producido hace menos de 5 minutos
                        if (dirMAC.equals(fichar.get(i).getMAC()) && uidEtiqueta.equals(fichar.get(i).getUID())) {
                            esta = true;
                            long tiempo = fichar.get(i).getTiempo();
                            //En el caso de que se supere los 5 minutos se muestra la información relacionada con la etiqueta
                            //por pantalla y se actualiza el valor de lectura del tiempo
                            if (System.currentTimeMillis() - tiempo >= 300000) {
                                Mostrar_Informacion(fichar.get(i).getMAC());
                                fichar.get(i).setTiempo(System.currentTimeMillis());

                            }
                        }
                    }
                    //Si este dispositivo o Robot no ha leido con anterioridad  esta etiqueta se añade al vector de registros
                    //y se muestra la información

                    if (esta == false) {
                        //Realizo esta comprobación para evitar valores basura que me puedan llegar, ya que los UID comienzan todos
                        //así
                         
                        if (uidEtiqueta.substring(0, 3).equals("04 ")) {
                            ficharRobot f = new ficharRobot(System.currentTimeMillis(), uidEtiqueta, dirMAC);
                            fichar.add(f);
                            Mostrar_Informacion(dirMAC);
                        }
                    }

                } catch (Exception e) {
                }
            }
        }

    };

    public NFC(String portName, int baud) {
        puerto = portName;
        baudRate = baud;
        cerrar = false;
    }

    @Override
    public void run() {
        // Inicializar el puerto.
        iniciarConexion(puerto, baudRate);
        while (!cerrar) {

        }

    }

    public void iniciarConexion(String portName, int DATA_RATE) {
        System.setProperty("gnu.io.rxtx.SerialPorts", portName);
        CommPortIdentifier portId = null;
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

        //First, Find an instance of serial port as set in PORT_NAMES.
        while (portEnum.hasMoreElements()) {
            CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();

            if (currPortId.getName().equals(portName)) {
                portId = currPortId;
                break;
            }

        }
        if (portId == null) {
            System.out.println("Could not find COM port.");
            return;
        }

        try {
            // open serial port, and use class name for the appName.
            puertoSerie = (SerialPort) portId.open(this.getClass().getName(),
                    TIEMPO_ESPERA);

            // set port parameters
            puertoSerie.setSerialPortParams(DATA_RATE,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);

//              serialPort.setSerialPortParams(dataRate,
//                    SerialPort.DATABITS_8,
//                    SerialPort.STOPBITS_1,
//                    SerialPort.PARITY_NONE);
            // open the streams
            entrada = puertoSerie.getInputStream();
            salida = puertoSerie.getOutputStream();

            // add event listeners
            puertoSerie.addEventListener(evento);
            puertoSerie.notifyOnDataAvailable(true);
        } catch (Exception e) {
            //Por si hay algún error
            System.out.println("Error al abrir el puerto");

        }
    }

}
