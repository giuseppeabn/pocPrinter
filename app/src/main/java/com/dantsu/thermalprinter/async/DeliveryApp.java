package com.dantsu.thermalprinter.async;

import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;

import android_serialport_api.SerialPort;

public class DeliveryApp {


    public static SerialPort getPrintSerialPort() throws SecurityException, IOException, InvalidParameterException {

        SerialPort mSerialPort = null;
        if (mSerialPort == null) {
            /* Open the serial port */
            mSerialPort = new SerialPort(new File("/dev/ttyS1"), 115200, 0, true);
        }
        return mSerialPort;
    }
}
