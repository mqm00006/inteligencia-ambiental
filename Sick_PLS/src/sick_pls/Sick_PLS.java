/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sick_pls;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Angel Miguel
 */
public class Sick_PLS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//       Controlador a = new Controlador();
        ExecutorService executo = Executors.newCachedThreadPool();
        SICK a = new SICK("COM6", 115200);
        executo.execute(a);
    
    }
    
}
