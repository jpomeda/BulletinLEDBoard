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
    private Button botonDimmer;
    private Button botonTest;

    private BluetoothService btService;

    private final String TAG = "UserActivity";


    /* * * * * * * * * * * * * * * * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        botonBlink = (Button) findViewById(R.id.btnBlink);
        botonIntercalar = (Button) findViewById(R.id.btnIntercalar);
        botonDimmer = (Button) findViewById(R.id.btnDimmer);
        botonTest = (Button) findViewById(R.id.btnTest);

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

        botonDimmer.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                Intent intent = new Intent(UserActivity.this, DimmerActivity.class);
                Log.d(TAG, "Intent creado. Iniciando DimmerActivity");
                startActivity(intent);
            }
        });

        botonTest.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                if(btService.conexion.isAlive()){
                    Toast.makeText(UserActivity.this, "Conectado con Raspberry Pi", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UserActivity.this, "No conectado con Raspberry Pi", Toast.LENGTH_SHORT).show();
                }
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

        Intent intent = new Intent(this, BluetoothService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

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
