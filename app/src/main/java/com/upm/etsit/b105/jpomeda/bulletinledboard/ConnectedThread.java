package com.upm.etsit.b105.jpomeda.bulletinledboard;

/**
 * Created by Javi on 28/6/16.
 */

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.bluetooth.*;
import android.util.Log;
import android.content.Intent;
import java.util.*;
import java.io.*;
import android.os.Handler;



public class ConnectedThread extends Thread{

    private final BluetoothSocket mmSocket;
    private final InputStream mmInStream;
    private final OutputStream mmOutStream;

    final int MESSAGE_READ = 1;

    Handler h;
    private final String TAG = "ConnectedThread";


    public ConnectedThread (BluetoothSocket socket, Handler h){

        mmSocket = socket;
        InputStream tmpIn = null;
        OutputStream tmpOut = null;

        this.h = h;
        // Get the input and output streams, using temp objects because
        // member streams are final
        try {
            tmpIn = socket.getInputStream();
            tmpOut = socket.getOutputStream();
        } catch (IOException e) { }

        mmInStream = tmpIn;
        mmOutStream = tmpOut;

    }

    public void run() {
        byte[] buffer = new byte[1024];  // buffer store for the stream
        int bytes; // bytes returned from read()

        // Keep listening to the InputStream until an exception occurs
        while (true) {
            try {
                // Read from the InputStream
                bytes = mmInStream.read(buffer);
                // Send the obtained bytes to the UI activity
                h.obtainMessage(MESSAGE_READ, bytes, -1, buffer).sendToTarget();
            } catch (IOException e) {
                break;
            }
        }
    }

    public void write(byte[] bytes) {
        try {
            mmOutStream.write(bytes);
            Log.d(TAG, "Bytes enviados "+bytes);
        } catch (IOException e) { }
    }

    public void write(String message) {
        Log.d(TAG, "Datos a enviar" + message + "...");
        byte[] msgBuffer = message.getBytes();
        try {
            mmOutStream.write(msgBuffer);
        } catch (IOException e) {
            Log.d(TAG, "...Error data send: " + e.getMessage() + "...");
        }
    }

    /* Call this from the main activity to shutdown the connection */
    public void cancel() {
        try {
            mmSocket.close();
        } catch (IOException e) { }
    }





}
