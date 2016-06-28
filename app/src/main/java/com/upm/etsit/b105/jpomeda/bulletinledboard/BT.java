package com.upm.etsit.b105.jpomeda.bulletinledboard;

/**
 * Created by Javi on 28/6/16.
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.bluetooth.*;
import android.content.Intent;
import java.util.*;


public class BT implements BluetoothHandler {

    public static final String raspBT = "98:D3:31:B3:9C:48";
    private final BluetoothServerSocket btSS = null;

    BluetoothAdapter btAdapter;
    BluetoothDevice device;

    public void openConnection(){

        btAdapter = BluetoothAdapter.getDefaultAdapter();
        device =  btAdapter.getRemoteDevice(raspBT);

        try{
            btSS = device.createRfcommSocketToServiceRecord(MY_UUID);
        }


    }

    public void write(){

    }

    public void read(){

    }



}
