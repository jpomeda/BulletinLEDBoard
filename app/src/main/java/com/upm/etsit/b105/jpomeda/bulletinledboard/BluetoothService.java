package com.upm.etsit.b105.jpomeda.bulletinledboard;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;

import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by jpomeda on 01/07/2016.
 */

/*
 * Echar un ojo a IBinder. Se utiliza para comunicación con el servicio.
 * Este servicio así como está no nos valdría. ¿Cómo nos comunicamos¿
 */
public class BluetoothService extends Service {

    //Buscar en google bluetooth service example y seguir.


    public static String address = "98:D3:31:B3:9C:48";

    public BluetoothAdapter btAdapter;
    public BluetoothDevice device;
    Handler h;
    public ConnectThread conexion;

    private static final String TAG = BluetoothService.class.getSimpleName();

    public class LocalBinder extends Binder {
        BluetoothService getService(){
            return BluetoothService.this;
        }
    }

    public void onCreate() {
        this.h = h;
        btAdapter = BluetoothAdapter.getDefaultAdapter();
        device = btAdapter.getRemoteDevice(address);

    }

    public int onStartCommand(Intent i, int flags, int id){
        if (btAdapter == null) {
            Log.d(TAG, "No hay adaptador Bluetooth");
            stopSelf();
            return 0;
        } else {
            Log.d(TAG, "servicio empezado");
            conexion = new ConnectThread(device, h);
            conexion.start();
            return START_STICKY;
        }
        String stopService = i.getStringExtra("stopservice");
        if (stopService != null && stopService.length() > 0){
            stop();
        }
    }

    public void onDestroy(){
        conexion.cancel();
    }

    private final IBinder mBinder = new LocalBinder();

    public IBinder onBind(Intent i){
        return mBinder;
    };



}