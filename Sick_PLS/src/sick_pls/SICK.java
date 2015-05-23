/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sick_pls;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import sick_pls.GUI;

/**
 *
 * @author Angel Miguel
 */
public class SICK implements Runnable {
    
    /* Attributos de la cabecera de los mensajes */
    public static final byte STX = 0x02;
    public static final byte ACK = 0x06;
    public static final byte STATUS = 0x02;
    public static final byte ADDR = (byte) 0x80;
    public static final byte[] HEADER_DATA = {STX, ADDR,(byte)0xD6, (byte)0x02, (byte)0xB0, (byte)0x69, (byte)0x01};
    public static final byte[] SENDING_DATA_ACK = {ACK, STX, ADDR, (byte)0x03, (byte)0x00, (byte)0xA0, (byte)0x00, (byte) 0x00, (byte) 0x06, (byte) 0x0A};
    public static final byte[] CHANGED_BAUD_ACK = {ACK, STX, ADDR, (byte)0x03, (byte)0x00, (byte)0xA0, (byte)0x01, (byte) 0x00, (byte) 0x04, (byte) 0x0B};
    
    
    /* Valores para el envio de ciertos mensajes */
    public static final byte[] STATUS_REQUEST = {0x02, 0x00, 0x01, 0x00, 0x31, 0x15, 0x12};
    public static final byte[] CHANGE_TO_9600_BAUD = {0x02, 0x00, 0x02, 0x00, 0x20, 0x42, 0x52, 0x08};
    public static final byte[] CHANGE_TO_38400_BAUD = {0x02, 0x00, 0x02, 0x00, 0x20, 0x40, (byte)0x50, 0x08}; // El checksum da en los ultimos valores 0x80 y 0x08. Habría que probar con ellos.
    public static final byte[] STOP_SENDING_DATA = {0x02, 0x00, 0x02, 0x00, 0x20, 0x25, 0x35, 0x08};
    public static final byte[] START_SENDING_DATA = {0x02, 0x00, 0x02, 0x00, 0x20, 0x24, 0x34, 0x08};
    
    public static final byte[] CAMARA_APAGAR = {(byte) 0x81, 0x01, 0x04, 0x00, 0x03, (byte) 0xFF};
    public static final byte[] CAMARA_MOVER_ARRIBA = {(byte) 0x81, 0x01, 0x06, 0x01, 0x18, (byte) 0x14, 0x03,0x01,(byte)0xFF};
    public static final byte[] CAMARA_MOVER_ABAJO_DER = {(byte) 0x81, 0x01, 0x06, 0x01, 0x18, (byte) 0x14, 0x02,0x02,(byte)0xFF};
    
    /* Parametros del laser */
    public static  final byte[] PASSWORD = "SICK_PLS".getBytes();
    public static final int NUM_REGISTROS = 361; // 361 registros de distancia para la resolución por defecto (de 0º a 180º) con paso de 0,5º
    
    
    /* El puerto serie de donde escribiremos */
    public  PuertoSerie serialPort;
 
    public boolean reinit = false;
    
    /* 
    Mandar informacion al robot PRUEBA
    */
    
    public  final byte[] frenosRobot = {(byte) 0x1B, (byte) 0x02, (byte) 0x02, (byte) 0x00, (byte) 0x0B, (byte) 0x00, (byte) 0x09, (byte) 0x1B, (byte) 0x03};
  
    /* Array con los valores de distancia al laser. 
    Medición en centimetros de 0º a 180º con paso de 0.5º
    Visto desde la planta. El angulo 0 se encuentra a la derecha, y el 180 a la izquierda.
    */
    private static int[] medidas;
    
   private String puerto;
   private int baud;
   
    public SICK(String puerto, int baud){
        this.puerto = puerto;
        this.baud = baud;
    }
    
    /**
     * Initialice the serial port and send the signal to start send data
     * @param portName The name of the serial port ("COM3")
     * @param baud  The baud rate
     */
    public void initializePort(String portName, int baud){
        serialPort = new PuertoSerie();
        serialPort.initialize(portName, baud) ;
        medidas = new int[NUM_REGISTROS];
        serialPort.sendData(START_SENDING_DATA);
      
    }
    

    /**
     * Checks if a queue has the header of the ACK of start send
     * @param cabecera
     * @return 
     */
    public static boolean cabeceraACK(ArrayList<Byte> cabecera){
        if(cabecera.size() >= SENDING_DATA_ACK.length){
        for(int i = 0; i < SENDING_DATA_ACK.length; i++){
            if(cabecera.get(i) != SENDING_DATA_ACK[i]) return false;
        }
            return true;
        } else { 
            return false;
        }
    }
    
    public  boolean baudACK(ArrayList<Byte> cabecera){
        if(cabecera.size() >= CHANGED_BAUD_ACK.length){
        for(int i = 0; i < CHANGED_BAUD_ACK.length; i++){
            if(cabecera.get(i) != CHANGED_BAUD_ACK[i]) return false;
        }
         
        return true;
        } else{
            return false;
        }
    }
    
    public static boolean cabeceraDATA(ArrayList<Byte> data){
        if(data.size() != HEADER_DATA.length) return false;
        for(int i = 0; i < HEADER_DATA.length; i++){
            if(data.get(i) != HEADER_DATA[i]) return false;
        }
        return true;
    }
    
    public static void addMeasure(int pos, int data){
        medidas[pos] = data;
    }
    
    public static void printMeasures(int pos){
        // Get minimum value for every 10º
    
      int min = Integer.MAX_VALUE;
            for(int j = 0; j < 20; j++){
                if(medidas[20 * pos + pos] < min)
                    min = medidas[20 * pos + pos];
            }
    
        
        GUI.setMeasures(min, pos);
    }
    
    public static void printMeasures(){
        // Get minimum value for every 10º
      for(int i = 0; i < 18; i++){    
      int min = Integer.MAX_VALUE;
            for(int j = 0; j < 20; j++){
                if(medidas[20 * i + j] < min)
                    min = medidas[20 * i + j];
            }
    
        
        GUI.setMeasures(min, i);
      }
    }

   
    @Override
    public void run() {
        initializePort(puerto , baud);
        while(true){
            Thread.yield();
            if(serialPort.reinit){
                try {
                    reinit = false;
                    serialPort.sendData(STOP_SENDING_DATA);
                    serialPort.close();
                    Thread.sleep(100);
                    serialPort.initialize(puerto, baud);
                    serialPort.sendData(START_SENDING_DATA);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SICK.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
