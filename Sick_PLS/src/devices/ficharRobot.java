/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devices;

/**
 *
 * @author Macarena
 */
public class ficharRobot {

    long tiempo;
    String UID;
    String MAC;
    
    public ficharRobot(){}
    public ficharRobot(long t, String u, String m){
        this.tiempo=t;
        this.MAC=m;
        this.UID=u;
    }

    public long getTiempo() {
        return tiempo;
    }

    public void setTiempo(long tiempo) {
        this.tiempo = tiempo;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getMAC() {
        return MAC;
    }

    public void setMAC(String MAC) {
        this.MAC = MAC;
    }
}
