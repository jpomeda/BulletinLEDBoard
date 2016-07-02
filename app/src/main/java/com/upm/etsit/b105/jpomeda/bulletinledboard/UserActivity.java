package com.upm.etsit.b105.jpomeda.bulletinledboard;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.bluetooth.*;
import android.content.*;
import java.util.*;
import android.view.*;
import android.util.Log;


public class UserActivity extends AppCompatActivity {

    private TextView usuario;
    private final static int REQUEST_ENABLE_BT = 1;

    private Button botonBlink;
    private Button botonIntercalar;

    private final String TAG = "UserActivity";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        botonBlink = (Button) findViewById(R.id.btnBlink);
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


        /* Creamos un BluetoothAdapter, cogiendo el de por defecto del teléfono (normalmente
        sólo tenemos ese).

         */

        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            throw new NullPointerException(){

            };
        }

        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }







    }
}
