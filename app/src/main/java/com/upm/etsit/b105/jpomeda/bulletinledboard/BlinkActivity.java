package com.upm.etsit.b105.jpomeda.bulletinledboard;

/**
 * Created by Javi on 28/6/16.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
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

    private EditText color;

    private Button botonEnviar;
    private Button botonCancelar;

    public Bluetooth BT;
    private Handler handler;

    private String envioColor;
    private final String PRUEBA = "Hola";



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blink);


        botonEnviar = (Button) findViewById(R.id.botonEnviaBlink);
        color = (EditText) findViewById(R.id.colorTextBlink);

        //BT = new Bluetooth (handler);

        botonEnviar.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v) {
                try {
                    Log.d(TAG, "Enviando..."+color.getText().toString());
                    BT.conexion.mConnectedThread.write(color.getText().toString());
                } catch (Exception e) {
                    Toast.makeText(BlinkActivity.this, "No se pudo enviar", Toast.LENGTH_SHORT).show();
                }

            }

        });

    }





}