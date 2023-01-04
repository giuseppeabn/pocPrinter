package com.dantsu.thermalprinter.async;
import android.util.Log;

import com.dantsu.escposprinter.connection.DeviceConnection;
import com.dantsu.escposprinter.exceptions.EscPosConnectionException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidParameterException;

import android_serialport_api.SerialPort;

public class SerialConnection extends DeviceConnection {

    private SerialPort mSerialPort;
    private FileOutputStream stream;
    @Override
    public DeviceConnection connect() throws EscPosConnectionException {
        Log.d("btn", "0");

        if (this.isConnected()) {
            Log.d("btn", "isConnected");
            return this;
        }
        try {
            mSerialPort = DeliveryApp.getPrintSerialPort();
            Log.d("btn", "1 SerialConnection");
            stream = (FileOutputStream) mSerialPort.getOutputStream();
            Log.d("btn", "2 SerialConnection");
            this.data = new byte[0];
        } catch (IOException e) {
            Log.d("btn", "3 error");

            e.printStackTrace();
            this.stream = null;
            throw new EscPosConnectionException("Unable to connect to Serial device.");
        }
        return this;
    }

    @Override
    public DeviceConnection disconnect() {
        this.data = new byte[0];
        if (this.isConnected()) {
            try {
                this.stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.stream = null;
        }
        return this;
    }
}