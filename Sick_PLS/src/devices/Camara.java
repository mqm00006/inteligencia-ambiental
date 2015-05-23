/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devices;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import sick_pls.PuertoSerie;
import static sick_pls.SICK.CAMARA_MOVER_ARRIBA;
import static sick_pls.SICK.NUM_REGISTROS;

/**
 *
 * @author uni
 */
public class Camara implements Runnable{
   // public static final byte[] _APAGAR = {(byte) 0x81, 0x01, 0x04, 0x00, 0x03, (byte) 0xFF};
    public static final byte[] _MOVER_ARRIBA = {(byte)0x81,0x01,0x06,0x03,0x18,0x14,0x00,0x00,0x00,0x00,0x00,0x00,0x04,0x00,(byte)0xFF};
    public static final byte[] _MOVER_ABAJO = {(byte)0x81,0x01,0x06,0x03,0x18,0x14,0x00,0x00,0x00,0x00,0x0F,0x0f,0x0c,0x00,(byte)0xFF};
    
    
    public static final byte[] _izquierda={(byte)0x81,0x01,0x06,0x03,0x18,0x14,0x0F,0x0f,0x0c,0x00,0x00,0x00,0x00,0x00,(byte)0xFF};
    public static final byte[] _derecha={(byte)0x81,0x01,0x06,0x03,0x18,0x14,0x00,0x00,0x04,0x00,0x00,0x00,0x00,0x00,(byte)0xFF};
    public static final byte[] _home={(byte)0x81,0x01,0x06,0x04,(byte)0xff};
    public static final byte[] _reset={(byte)0x81,0x01,0x06,0x05,(byte)0xFF};
     private  PuertoSerie serialPort;
     
     private String port;
     private int speed;
     
     
     public Camara(String portName,int baud)
     {
         this.port=portName;
         this.speed=baud;
         this.serialPort=new PuertoSerie();
         this.serialPort.initialize(this.port, this.speed);
     }
    public void enviar(byte[] instruccion)            
    {
        
        
        try{
            this.serialPort.sendData(instruccion);
            Thread.sleep(0);
        } catch (InterruptedException ex) {
            Logger.getLogger(Camara.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run() {
        
        
        while(true)
        {
            
        }
    }
    
}
