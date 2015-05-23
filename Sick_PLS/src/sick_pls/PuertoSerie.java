/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sick_pls;

import java.io.*;
import java.util.*;
import gnu.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Angel Miguel
 */
public class PuertoSerie implements SerialPortEventListener {

    SerialPort serialPort;
   
    /**
     * A BufferedReader which will be fed by a InputStreamReader converting the
     * bytes into characters making the displayed results codepage independent
     */
    private BufferedReader input;
    /**
     * The output stream to the port
     */
    private OutputStream output;
    /**
     * Milliseconds to block while waiting for port open
     */
    private static final int TIME_OUT = 2000;
    /**
     * Default bits per second for COM port.
     */
    private static final int DATA_RATE = 9600; // 9600
    
    /**
     * Auxiliar variables for reading and parsing the received data
     */
    public int charCounter = 0, dataCounter = 0, pos = 0;
    
    /**
     * We store the data in a queue for manage headers of start/stop and data packages
     */
    private ArrayList<Byte> cabecera_start, cabecera_data; // Para obtener la cabecera de comienzo de envío de datos del sensor, y para la cabecera del paquete de datos con información sobre el barrido que realiza el laser para obtener la medicion
    
    private boolean data; // ¿Estamos leyendo datos?
    
    private String hexString;
    private int count;
    public boolean reinit ;
    
    /**
     * Initializaces the specified serial port at the specified data rate
     * @param portName name of the port (i.e. COM3)
     * @param dataRate data rate in bauds(i.e. 9600)
     */
    public void initialize(String portName, int dataRate) {
        // the next line is for Raspberry Pi and 
        // gets us into the while loop and was suggested here was suggested http://www.raspberrypi.org/phpBB3/viewtopic.php?f=81&t=32186
        System.setProperty("gnu.io.rxtx.SerialPorts", portName);
        hexString = new String();
        cabecera_start = new ArrayList<Byte>();
        cabecera_data = new ArrayList<Byte>();
        count = 0;
        charCounter = 0;
        dataCounter = 0;
        pos = 0; 
        reinit = false;
        data = false;
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
            serialPort = (SerialPort) portId.open(this.getClass().getName(),
                    TIME_OUT);

            // set port parameters
            serialPort.setSerialPortParams(dataRate,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_ODD);
         
//              serialPort.setSerialPortParams(dataRate,
//                    SerialPort.DATABITS_8,
//                    SerialPort.STOPBITS_1,
//                    SerialPort.PARITY_NONE);

            // open the streams
            input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
            output = serialPort.getOutputStream();

            // add event listeners
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);

            // send to sick status request
//                        System.out.println("Mandando STATUS REQUEST");
//                        sendData(SICK.STATUS_REQUEST);
//                        System.out.println("Mandado");
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    /**
     * This should be called when you stop using the port. This will prevent
     * port locking on platforms like Linux.
     */
    public synchronized void close() {
        if (serialPort != null) {
            serialPort.removeEventListener();
            serialPort.close();
        }
    }

    /**
     * Handle an event on the serial port. Read the data and print it.
     */
    public synchronized void serialEvent(SerialPortEvent oEvent) {
        if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            try {
                byte read = (byte) serialPort.getInputStream().read();
                String dat2 = String.format("%02X", read);
//               System.out.printf("%02X", read);
//                System.out.println();
//             
                
                if(data){
                  String dat = String.format("%02X", read);
                    
                    if( (dataCounter % 2) == 0){
                        hexString = dat;
                    } else {
                       hexString = dat + hexString;
                       int value = Integer.parseInt(hexString, 16);
                       value = (value << 3) % 0xffff; // Quitamos los 3 primeros bits que no valen
                       value = value >> 3;
                       
                       SICK.addMeasure(pos, value);
                       pos++;
                    }
                     
                     if(pos % 20 == 0 && pos != 0){
                         SICK.printMeasures((int) pos / 20 - 1 );
                     }
                    dataCounter++;
                    if(dataCounter >= 361 * 2){
                        GUI.printLaserStatus("WAITING FOR DATA HEADER");
                        data = false;
                        dataCounter = 0;
                        pos = 0;
                       reinit = true;
//                        SICK.printMeasures();
                       
                     this.notifyAll();
                        
                    }
                }
            
                
                if (charCounter < 10) {
                    cabecera_start.add((byte) read);
                } else {
                    cabecera_start.remove(0);
                    cabecera_start.add((byte) read);
                }

                if (charCounter < 7) {
                    cabecera_data.add((byte) read);
                } else {
                    cabecera_data.remove(0);
                    cabecera_data.add((byte) read);
                    
                }

                charCounter++;

                if (SICK.cabeceraACK(cabecera_start)) {
                   GUI.printLaserStatus("Signal of start/stop received.");
                }
              
                
                
                
                if(SICK.cabeceraDATA(cabecera_data)){ // Procesar datos del laser
                    GUI.printLaserStatus("Receiving data...");
                    data = true;
                    
                }
                
              

                
            } catch (Exception e) {
                System.err.println(e.toString());
            }
        }
        
        // Ignore all the other eventTypes, but you should consider the other ones.
    }
   
    /**
     * Send a String 
     * @param data  the data to send
     */
    public synchronized void sendData(String data) {
        BufferedWriter b = new BufferedWriter(new OutputStreamWriter(output));
        try {
            b.write(data);
            b.flush();
        } catch (IOException ex) {
            Logger.getLogger(PuertoSerie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Send a byte vector of data to the serial port
     * @param data 
     */
    public synchronized void sendData(byte[] data) {
        try {
            output.write(data);

        } catch (IOException ex) {
            Logger.getLogger(PuertoSerie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * This function verifies the existence of the specified port name.
     * @param portName  The name of the port (COM3 for example)
     * @return 
     */
    public boolean verifyPort(String portName){
        CommPortIdentifier portId = null;
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

         while (portEnum.hasMoreElements()) {
            CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
      
                if (currPortId.getName().equals(portName)) {
                    portId = currPortId;
                    break;
                }
            
        }
        if (portId == null) {
            System.out.println("Could not find COM port " + portName);
            return false;
        } else {
            return true;
        }
    }

}
