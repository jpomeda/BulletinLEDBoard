package com.upm.etsit.b105.jpomeda.bulletinledboard;

/**
 * Created by Javi on 28/6/16.
 */

import android.bluetooth.*;
import android.os.Handler;
import android.util.Log;


import java.io.IOException;
import java.util.*;
import java.lang.*;

public class ConnectThread extends Thread {


        private final BluetoothSocket mmSocket;
        private final BluetoothDevice mmDevice;





        Handler h;



        ConnectedThread mConnectedThread;



        private final static UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

        private final String TAG = "ConnectThread";

        public ConnectThread(BluetoothDevice device, Handler h) {
            // Use a temporary object that is later assigned to mmSocket,
            // because mmSocket is final
            BluetoothSocket tmp = null;
            this.h = h;

            mmDevice = device;

            Log.d(TAG, mmDevice.toString());

            // Debemos pasar un device (sacado a partir de la MAC) desde la clase Bluetooth


            // Get a BluetoothSocket to connect with the given BluetoothDevice
            try {
                // MY_UUID is the app's UUID string, also used by the server code
                Log.d(TAG, "Creado socket");
                tmp = mmDevice.createInsecureRfcommSocketToServiceRecord(MY_UUID);
            } catch (IOException e) { }

            mmSocket = tmp;

        }

        public void run() {


            try {


                /*if ((mmSocket != null) && (mConnectedThread != null)) {

                    mmSocket.close(); // Por si está abierto antes
                    mConnectedThread.interrupt();
                }*/


                // Connect the device through the socket. This will block
                // until it succeeds or throws an exception
                Log.d(TAG, "entra en try");
                mmSocket.connect();
                Boolean b = mmSocket.isConnected();
                Log.d(TAG, "Conectado: "+b.toString());
            } catch (IOException connectException) {
                // Unable to connect; close the socket and get out
                try {
                    Log.d(TAG, "Imposible conectar");
                    mmSocket.close();
                } catch (IOException closeException) { }
                return;
            }

            mConnectedThread = new ConnectedThread(mmSocket, h);
            mConnectedThread.start();

        }

        /** Will cancel an in-progress connection, and close the socket */
        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) { }
        }

}
