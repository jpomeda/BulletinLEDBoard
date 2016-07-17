package com.upm.etsit.b105.jpomeda.bulletinledboard;

/**
 * Created by Javi on 28/6/16.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.*;
import android.bluetooth.*;
import android.content.*;

import java.io.IOException;
import java.util.*;
import android.view.*;
import android.os.Handler;

public class BlinkActivity extends AppCompatActivity{

    private final String TAG = "BlinkActivity";

    private String trama = "zzzzzz";

    private EditText color;

    private Button botonEnviar;

    private Button botonNegro;
    private Button botonVerde;
    private Button botonRojo;
    private Button botonAzul;
    private Button botonMorado;
    private Button botonAmarillo;
    private Button botonBlanco;
    private Button botonNaranja;

    public Bluetooth BT;
    private Handler handler;

    private String envioColor;
    private final String PRUEBA = "Hola";

    private BluetoothService btService;
    boolean mBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blink);


        botonEnviar = (Button) findViewById(R.id.botonEnviaBlink);

        botonVerde = (Button) findViewById(R.id.buttonGreen);
        botonRojo = (Button) findViewById(R.id.buttonRed);
        botonAzul = (Button) findViewById(R.id.buttonBlue);
        botonMorado = (Button) findViewById(R.id.buttonPurple);
        botonBlanco = (Button) findViewById(R.id.buttonWhite);
        botonNaranja = (Button) findViewById(R.id.buttonOrange);
        botonAmarillo = (Button) findViewById(R.id.buttonYellow);
        botonNegro = (Button) findViewById(R.id.buttonBlack);

        //color = (EditText) findViewById(R.id.colorTextBlink);

        //BT = new Bluetooth (handler);

    }

    @Override
    protected void onStart(){
        super.onStart();

        Intent intent = new Intent(this, BluetoothService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

        /* * BOTONES DE COLORES * */

        asignaColor("arzzzz", botonRojo);
        asignaColor("abzzzz", botonAzul);
        asignaColor("agzzzz", botonVerde);
        asignaColor("awzzzz", botonBlanco);
        asignaColor("ayzzzz", botonAmarillo);
        asignaColor("aozzzz", botonNaranja);
        asignaColor("apzzzz", botonMorado);
        asignaColor("axzzzz", botonNegro);

    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            BluetoothService.LocalBinder binder = (BluetoothService.LocalBinder) service;
            btService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG, "onServiceDisconnected");
            mBound = false;
        }
    };

    /* Envia el color respectivo al botón */

    private void asignaColor (final String t, final Button b){
        b.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v) {
                try {
                    Log.d(TAG, "Enviando..."+ b.getText());
                    if (mBound){
                        trama = t;
                        Log.d(TAG, "Trama: "+trama);
                        btService.conexion.mConnectedThread.write(trama);
                    }
                    //BT.conexion.mConnectedThread.write(color.getText().toString());
                } catch (Exception e) {
                    Toast.makeText(BlinkActivity.this, "No se pudo enviar", Toast.LENGTH_SHORT).show();
                }

            }

        });
    }





}