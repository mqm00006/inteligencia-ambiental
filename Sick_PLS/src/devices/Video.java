/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devices;

/**
 *
 * @author portatil
 */
import java.util.logging.Level;
import java.util.logging.Logger;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.VideoCapture;

public class Video implements Runnable{

    /**
     * @param args the command line arguments
     */
    int id;
    public Video(int i)
    {
        this.id=i;
    }

    @Override
    public void run() {
         System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
        
         
         
        VideoCapture cap = new VideoCapture(this.id);
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Video.class.getName()).log(Level.SEVERE, null, ex);
        }
            
		// Check if video capturing is enabled
		if (!cap.isOpened()) {
                    System.out.println("peta");
                    System.exit(-1);
		}

		// Matrix for storing image
		Mat image = new Mat();
		// Frame for displaying image
		MyFrame frame = new MyFrame();
		frame.setVisible(true);

		// Main loop
		while (true) {
			// Read current camera frame into matrix
			cap.read(image);
			// Render frame if the camera is still acquiring images
			if (image != null) {
				frame.render(image);
			} else {
				System.out.println("No captured frame -- camera disconnected");
				break;
			}
		}
    }
    
}
