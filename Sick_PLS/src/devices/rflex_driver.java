/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devices;

import static java.lang.Math.abs;
import static devices.rflex_info.*;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import sick_pls.PuertoSerie;

public class rflex_driver implements Runnable {

    private byte readBuffer[] = new byte[BUFFER_SIZE];
    private byte writeBuffer[] = new byte[BUFFER_SIZE];
    private char[] auxBuffer = new char[BUFFER_SIZE];

    private boolean found;
    private int offset;

    protected PuertoSerie serialPort;
    protected boolean brake;
    protected int numIr;

    protected int distance;         ///< Raw translational odometry
    protected int bearing;          ///< Raw rotational odometry
    protected int transVelocity;    ///< Raw translational velocity
    protected int rotVelocity;      ///< Raw rotational velocity  
    protected int voltage;          ///< Raw voltage reading
    protected int sonar_ranges[];   ///< Raw Sonar readings (including unconnected ports)

    protected int lcdX, lcdY;
    protected byte lcdData[];
    protected int odomReady;

    public rflex_driver() {
        readBuffer = new byte[BUFFER_SIZE];
        writeBuffer = new byte[BUFFER_SIZE];

        sonar_ranges = new int[SONAR_MAX_COUNT];

        distance = bearing = transVelocity = rotVelocity = 0;
        voltage = 0;
        offset = 0;
        odomReady = 0;
        found = false;
        brake = true;

        // initialise the LCD dump array
        lcdData = new byte[320 * 240 / 8];
        if (lcdData != null) {
            lcdX = 320 / 8;
            lcdY = 240;
        }
    }

    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void initialize(String portName, int baud) {
        try {
            serialPort = new PuertoSerie();
            serialPort.initialize(portName, baud);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
//finds the sign of a value

    static long sgn(long val) {
        if (val < 0) {
            return 0;
        } else {
            return 1;
        }
    }

    static short getInt16(final byte[] bytes, int pos) {
        return ByteBuffer.wrap(bytes).getShort(pos);
    }

    static int getInt32(final byte[] bytes, int pos) {
        return ByteBuffer.wrap(bytes).getInt(pos);
    }

    static void putInt8(int i, byte[] bytes, int pos) {
        ByteBuffer.wrap(bytes).put(pos, (byte) i);
    }

    static void putInt32(int l, byte[] bytes, int pos) {
        ByteBuffer.wrap(bytes).putInt(pos, l);
    }

    /**
     * Configure the sonar parameters and send message to RFlex. \param
     * echoDelay Echo Delay \param pingDelay Ping Delay \param setDelay Set
     * Delay \param val Unknown
     *
     * @param echoDelay
     * @param pingDelay
     * @param setDelay
     * @param val
     * @todo Figure out unknown value's purpose.
     */
    public void configureSonar(final int echoDelay, final int pingDelay,
            final int setDelay, final int val) {
        byte data[] = new byte[MAX_COMMAND_LENGTH];
        putInt32(echoDelay, data, 0);
        putInt32(pingDelay, data, 4);
        putInt32(setDelay, data, 8);
        putInt8(val, data, 12);
        sendCommand(SONAR_PORT, 4, SONAR_RUN, 13, data);
    }

    /**
     * Turn Brake on or off Note: Brake on means the controller cannot move the
     * robot and external forces CAN move it. \param power true for on, false
     * for off
     *
     * @param on
     */
    public void setBrakePower(final boolean on) {
        int brk;
        if (on) {
            brk = MOT_BRAKE_SET;
        } else {
            brk = MOT_BRAKE_RELEASE;
        }

        sendCommand(MOT_PORT, 0, brk, 0, null);
    }

    /**
     * Sends a set motion defaults message to the device.
     */
    public void motionSetDefaults() {
        sendCommand(MOT_PORT, 0, MOT_SET_DEFAULTS, 0, null);
    }

    /**
     * Gets brake power \return True if brake is engaged
     *
     * @return
     */
    public boolean getBrakePower() {
        return brake;
    }

    /**
     * Gets the number of IR sensors \return Number of IR sensors
     *
     * @return
     */
    public int getIrCount() {
        return numIr;
    }

    /**
     * Sets the velocity \param transVelocity Translational velocity in
     * arbitrary units \param rotVelocity Rotational velocity in arbitrary units
     * \param acceleration Acceleration (also in arbitrary units)
     *
     * @param transVelocity
     * @param rotVelocity
     * @param acceleration
     */
    public void setVelocity(final long transVelocity, final long rotVelocity, final long acceleration) {
        int utvel = (int) abs(transVelocity);
        int urvel = (int) abs(rotVelocity);
        //char data[] = new char[MAX_COMMAND_LENGTH];
        byte data[] = new byte[MAX_COMMAND_LENGTH];
        // ** workaround for stupid hardware bug, cause unknown, but this works
        // ** with minimal detriment to control
        // ** avoids all values with 1b in highest or 3'rd highest order byte

        // 0x1b is part of the packet terminating string
        // which is most likely what causes the bug
        // ** if 2'nd order byte is 1b, round to nearest 1c, or 1a
        if ((urvel & 0xff00) == 0x1b00) {
            // ** if lowest order byte is>127 round up, otherwise round down
            urvel = (urvel & 0xffff0000) | ((urvel & 0xff) > 127 ? 0x1c00 : 0x1aff);
        }

        // ** if highest order byte is 1b, round to 1c, otherwise round to 1a
        if ((urvel & 0xff000000) == 0x1b000000) {
            // ** if 3'rd order byte is>127 round to 1c, otherwise round to 1a
            urvel = (urvel & 0x00ffffff) | (((urvel & 0xff0000) >> 16) > 127 ? 0x1c000000 : 0x1aff0000);
        }

        putInt8(0, data, 0);                            /* forward motion */

        putInt32((int) utvel, data, 1);                 /* abs trans velocity*/

        putInt32((int) acceleration, data, 5);          /* trans acc */

        putInt32(STD_TRANS_TORQUE, data, 9);            /* trans torque */

        putInt8((int) sgn(transVelocity), data, 13);    /* trans direction */

        sendCommand(MOT_PORT, 0, MOT_AXIS_SET_DIR, 14, data);

        putInt8(1, data, 0);                     /* rotational motion */

        putInt32(urvel, data, 1);                /* abs rot velocity  */
        /* 0.275 rad/sec * 10000 */

        putInt32(STD_ROT_ACC, data, 5);          /* rot acc */

        putInt32(STD_ROT_TORQUE, data, 9);       /* rot torque */

        putInt8((int) sgn(rotVelocity), data, 13);            /* rot direction */

        sendCommand(MOT_PORT, 0, MOT_AXIS_SET_DIR, 14, data);

    }

    /**
     * Sends a system status command to the device. Updates the brake and
     * battery status.
     */
    public void sendSystemStatusCommand() {
        sendCommand(SYS_PORT, 0, SYS_STATUS, 0, null);
    }

    private void parseMotReport(final byte buffer[]) {
        int rv, timeStamp, acc, trq;
        byte axis;
        //printf("-----------\n");
        //printf("buffer: %u\n", *buffer);
        //cout << "buffer " << buffer << "\n";
        switch (buffer[PACKET_OPCODE_BYTE]) {
            case MOT_SYSTEM_REPORT:
                rv = getInt32(buffer, 6);
                timeStamp = getInt32(buffer, 10);
                axis = buffer[14];
                if (axis == 0) {
                    distance = getInt32(buffer, 15);
                    //printf("rflex_distance %d\n", distance);
                    transVelocity = getInt32(buffer, 19);
                    //printf("rflex_transVelocity %d\n", transVelocity);
                    odomReady = odomReady | 1;
                } else if (axis == 1) {
                    bearing = getInt32(buffer, 15);
                    //printf("rflex_bearing %d\n", bearing);
                    rotVelocity = getInt32(buffer, 19);
                    //printf("rflex_rot_velocity %d\n", rotVelocity);
                    odomReady = odomReady | 2;
                }
                acc = getInt32(buffer, 23);
                //printf("rflex_acc %d\n", acc);
                trq = getInt32(buffer, 27);
                //printf("rflex_trq %d\n", trq);
                //printf("\n");
                break;
            default:
                break;
        }
    }

    private void parseSysReport(final byte[] buffer) {
        long timeStamp;
        byte length = buffer[5];

        switch (buffer[PACKET_OPCODE_BYTE]) {
            case SYS_LCD_DUMP:
                // currently designed for 320x240 screen on b21r
                // stored in packed format
                if (length < 6) {
                    System.err.println("Got bad Sys packet (lcd)");
                    break;
                }

                byte lcd_length,
                 row;
                timeStamp = getInt32(buffer, 6);
                row = buffer[10];
                lcd_length = buffer[11];
                if (row > lcdY || lcd_length > lcdX) {
                    System.err.println("LCD Data Overflow");
                    break;
                }

//                memcpy( & lcdData[row * lcdX],  & buffer[12], lcd_length);

                break;

            case SYS_STATUS:
                if (length < 9) {
                    System.err.println("Got bad Sys packet (status)");
                    break;
                }
                timeStamp = getInt32(buffer, 6);
                // raw voltage measurement...needs calibration offset added
                voltage = getInt32(buffer, 10);
                brake = buffer[14] != 0;
                break;

            default:
                System.err.println("Unknown sys opcode recieved");
        }
    }

    //processes a sonar packet from the rflex
    private void parseSonarReport(final byte[] buffer) {
        int retval, timeStamp, count;
        byte dlen = (byte) getUnsignedInt(buffer[5]);

        switch (buffer[PACKET_OPCODE_BYTE]) {
            case SONAR_REPORT:
                retval = getInt32(buffer, 6);
                timeStamp = getInt32(buffer, 10);
                count = 0;
                while ((8 + count * 3 < dlen) && (count < 256) && (count < SONAR_MAX_COUNT)) {
                    int sid = (int) getUnsignedInt(buffer[14 + count * 3]);
                    sonar_ranges[sid] = getInt16(buffer, (14 + count * 3 + 1));
                    count++;
                }
                break;
            default:
                break;
        }
    }

    //parses a packet from the rflex, and decides what to do with it
    private void parsePacket(final byte[] buffer) {
        switch (buffer[PACKET_PORT_BYTE]) {
            case SYS_PORT:
                parseSysReport(buffer);
                break;
            case MOT_PORT:
                parseMotReport(buffer);
                break;
            case SONAR_PORT:
                parseSonarReport(buffer);
                break;
            default:
                break;
        }
    }

 
    private void sendCommand(final int port, final int id, final int opcode, final int length, byte[] data) {

        StringBuilder sb = new StringBuilder();

        // Header
        writeBuffer[0] = ESC;
        writeBuffer[1] = STX;
        writeBuffer[PACKET_PORT_BYTE] = (byte) port;
        writeBuffer[PACKET_ID_BYTE] = (byte) id;
        writeBuffer[PACKET_OPCODE_BYTE] = (byte) opcode;
        writeBuffer[PACKET_SIZE_BYTE] = (byte) length;
        for (int i = 0; i < length; i++) {
            writeBuffer[6 + i] = data[i];
        }
        // Footer
        writeBuffer[length + PACKET_DATA_START_BYTE] = (byte) computeCRC(writeBuffer, 2, length + PACKET_CRC_OFFSET);
        writeBuffer[length + PACKET_DATA_START_BYTE + 1] = ESC;
        writeBuffer[length + PACKET_DATA_START_BYTE + 2] = ETX;

        for (int i = 0; i < length + 9; i++) {
            sb.append(String.format("%02X ", writeBuffer[i]));
        }
        try {
            serialPort.sendData(writeBuffer);
            Thread.sleep(250);
        } catch (InterruptedException ex) {
            Logger.getLogger(rflex_driver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private byte computeCRC(final byte[] buffer, final int pos, final int n) {
        int crc = buffer[pos];
        for (int i = pos+1; i < n + 2; ++i) {
            crc ^= buffer[i];
        }
        return (byte) getUnsignedInt(crc);
    }

    public static long getUnsignedInt(int x) {
        return x & 0x00ffL;
    }

}
