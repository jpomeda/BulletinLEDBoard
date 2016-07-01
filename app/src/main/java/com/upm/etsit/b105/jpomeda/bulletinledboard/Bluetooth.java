package com.upm.etsit.b105.jpomeda.bulletinledboard;

import android.bluetooth.BluetoothAdapter;
import android.os.Handler;
import android.util.Log;

/**
 * Created by jpomeda on 01/07/2016.
 */
public class Bluetooth {

    public static String address = "98:D3:31:B3:9C:48";

    public BluetoothAdapter btAdapter;
    Handler h;
    public ConnectThread conexion;

    private static final String TAG = Bluetooth.class.getSimpleName();

    public Bluetooth (Handler h){
        this.h = h;
        btAdapter = BluetoothAdapter.getDefaultAdapter();

        if (btAdapter == null){
            Log.d(TAG, "No hay adaptador Bluetooth");
        } else {
            conexion = new ConnectThread();
            //añadir aquí la apertura del hilo de ejecución
        }
    }

}
