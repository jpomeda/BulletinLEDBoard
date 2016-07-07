package com.upm.etsit.b105.jpomeda.bulletinledboard;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.os.Handler;

import android.os.IBinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.bluetooth.*;
import android.content.*;
import java.util.*;
import android.view.*;
import android.util.Log;


public class UserActivity extends AppCompatActivity {

    /* BINDING */

    BluetoothService mService;
    boolean mBound = false;

    /* * * * * */

    private TextView usuario;
    private final static int REQUEST_ENABLE_BT = 1;

    private Button botonBlink;
    private Button botonIntercalar;
    private Button btTest;

    private Handler handler;
    private BluetoothService btService;

    private final String TAG = "UserActivity";

    /* TRAMA A ENVIAR A LA RASPBERRY */

    public String trama = "";

    private enum Efecto {BLINK, INTERCALAR, DIMMER}
    private enum Color {ROJO, AZUL, VERDE, BLANCO, NEGRO}
    private int duración;

    /* * * * * * * * * * * * * * * * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        botonBlink = (Button) findViewById(R.id.btnBlink);
        botonIntercalar = (Button) findViewById(R.id.btnIntercalar);

        usuario = (TextView)findViewById(R.id.holaUser); // 'enlazamos' el control con el cuadro de texto

        Bundle bundle = this.getIntent().getExtras();
        usuario.setText("Bienvenido " + bundle.getString("Usuario"));

        botonBlink.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                Intent intent = new Intent(UserActivity.this, BlinkActivity.class);
                Log.d(TAG, "Intent creado. Iniciando blinkActivity");
                startActivity(intent);
            }
        });

        botonIntercalar.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                Intent intent = new Intent(UserActivity.this, IntercalarActivity.class);
                Log.d(TAG, "Intent creado. Iniciando IntercalarActivity");
                startActivity(intent);
            }
        });


        /* Creamos un BluetoothAdapter, cogiendo el de por defecto del teléfono (normalmente
        sólo tenemos ese).

         */

    }



    /* Al empezar la actividad:
     * Activamos Bluetooth.
     * Si Bluetooth está activado, iniciamos el servicio.
     */
    @Override
    protected void onStart(){
        super.onStart();
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            throw new NullPointerException(){

            };
        }

        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }


        if (mBluetoothAdapter.isEnabled()){
            Log.d(TAG, "Iniciando servicio...");
            Intent i = new Intent(UserActivity.this, BluetoothService.class);
            startService(i);
        }

    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        stopService(new Intent(UserActivity.this, BluetoothService.class));
    }
}
