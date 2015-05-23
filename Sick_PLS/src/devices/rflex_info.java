    /*  RFLEX ROS Node constants
 *  David Lu!! - 2/2010
 *  Modified from Player Driver
 *
 *  Player - One Hell of a Robot Server
 *  Copyright (C) 2000
 *     Brian Gerkey, Kasper Stoy, Richard Vaughan, & Andrew Howard
 *
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 */
package devices;

public class rflex_info {

    // Protocol information
    static final int PROTOCOL_SIZE = 9;
    static final int PACKET_CRC_START = 2;
    static final int PACKET_CRC_OFFSET = 4;
    // Packet data slots
    static final int PACKET_PORT_BYTE = 2;
    static final int PACKET_ID_BYTE = 3;
    static final int PACKET_OPCODE_BYTE = 4;
    static final int PACKET_SIZE_BYTE = 5;
    static final int PACKET_DATA_START_BYTE = 6;

    static final int MAX_COMMAND_LENGTH = 256;
    static final int BUFFER_SIZE = 1024;

    static final int STD_TRANS_TORQUE = 30000;
    static final int STD_ROT_ACC = 100000;
    static final int STD_ROT_TORQUE = 35000;

    static final int SYS_PORT = 1;
    static final int MOT_PORT = 2;
    static final int JSTK_PORT = 3;
    static final int SONAR_PORT = 4;
    static final int DIO_PORT = 5;
    static final int IR_PORT = 6;

    static final int SYS_LCD_DUMP = 0;
    static final int SYS_STATUS = 1;

    static final int MOT_AXIS_GET_SYSTEM = 0;
    static final int MOT_AXIS_GET_MODEL = 1;
    static final int MOT_AXIS_GET_TARGET = 2;
    static final int MOT_AXIS_SET_LIMITS = 3;
    static final int MOT_AXIS_GET_LIMITS = 4;
    static final int MOT_AXIS_SET_POS_LIMITS = 5;
    static final int MOT_AXIS_GET_POS_LIMITS = 6;
    static final int MOT_AXIS_SET_DIR = 7;
    static final int MOT_AXIS_SET_POS = 8;
    static final int MOT_AXIS_GET_MODE = 9;
    static final int MOT_SET_DEFAULTS = 10;
    static final int MOT_BRAKE_SET = 11;
    static final int MOT_BRAKE_RELEASE = 12;
    static final int MOT_SYSTEM_REPORT = 33;
    static final int MOT_SYSTEM_REPORT_REQ = 34;
    static final int MOT_GET_NAXES = 65;
    static final int MOT_SET_GEARING = 66;
    static final int MOT_GET_GEARING = 67;
    static final int MOT_MOTOR_SET_MODE = 68;
    static final int MOT_MOTOR_GET_MODE = 69;
    static final int MOT_MOTOR_SET_PARMS = 70;
    static final int MOT_MOTOR_GET_PARMS = 71;
    static final int MOT_MOTOR_SET_LIMITS = 72;
    static final int MOT_MOTOR_GET_LIMITS = 73;
    static final int MOT_MOTOR_GET_DATA = 74;
    static final int MOT_AXIS_SET_PARMS = 75;
    static final int MOT_AXIS_GET_PARMS = 76;
    static final int MOT_AXIS_SET_PWM_LIMIT = 77;
    static final int MOT_AXIS_GET_PWM_LIMIT = 78;
    static final int MOT_AXIS_SET_PWM = 79;
    static final int MOT_AXIS_GET_PWM = 80;

    static final int SONAR_RUN = 0;
    static final int SONAR_GET_UPDATE = 1;
    static final int SONAR_REPORT = 2;

    static final int DIO_REPORTS_REQ = 0;
    static final int DIO_REPORT = 1;
    static final int DIO_GET_UPDATE = 2;
    static final int DIO_UPDATE = 3;
    static final int DIO_SET = 4;

    static final int IR_RUN = 0;
    static final int IR_REPORT = 1;

    static final int JSTK_GET_STATE = 0;

    static final int HEADING_HOME_ADDRESS = 0x31;
    static final int BUMPER_ADDRESS = 0x40;

    static final int SONAR_MAX_COUNT = 224;

    // Escape codes used in the data packets
    static final char NUL = 0;
    static final char SOH = 1;
    static final char STX = 2;
    static final char ETX = 3;
    static final char ESC = 27;
}
