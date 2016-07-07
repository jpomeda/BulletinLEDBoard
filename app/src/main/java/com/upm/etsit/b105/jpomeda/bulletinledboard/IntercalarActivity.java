package com.upm.etsit.b105.jpomeda.bulletinledboard;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Javi on 7/7/16.
 */
public class IntercalarActivity extends AppCompatActivity {


        private final String TAG = "IntercalarActivity";

        private EditText color;

        private Button botonEnviar;
        private Button botonCancelar;

        public Bluetooth BT;
        private Handler handler;

        private String envioColor;
        private final String PRUEBA = "Hola";

        private BluetoothService btService;
        boolean mBound = false;



        @Override
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_intercalar);


            botonEnviar = (Button) findViewById(R.id.botonEnviaBlink);
            color = (EditText) findViewById(R.id.colorTextBlink);

            //BT = new Bluetooth (handler);



            botonEnviar.setOnClickListener(new View.OnClickListener(){
                public void onClick (View v) {
                    try {
                        Log.d(TAG, "Enviando..."+color.getText().toString());
                        //BT.conexion.mConnectedThread.write(color.getText().toString());
                    } catch (Exception e) {
                        Toast.makeText(IntercalarActivity.this, "No se pudo enviar", Toast.LENGTH_SHORT).show();
                    }

                }

            });

        }

        @Override
        protected void onStart(){
            super.onStart();

            Intent intent = new Intent(this, BluetoothService.class);
            bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

            botonEnviar.setOnClickListener(new View.OnClickListener(){
                public void onClick (View v) {
                    try {
                        Log.d(TAG, "Enviando..."+color.getText().toString());
                        if (mBound){
                            btService.conexion.mConnectedThread.write(color.getText().toString());
                        }
                        //BT.conexion.mConnectedThread.write(color.getText().toString());
                    } catch (Exception e) {
                        Toast.makeText(IntercalarActivity.this, "No se pudo enviar", Toast.LENGTH_SHORT).show();
                    }

                }

            });
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

    }
