package com.upm.etsit.b105.jpomeda.bulletinledboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.bluetooth.*;
import android.content.Intent;

public class UserActivity extends AppCompatActivity {

    private TextView usuario;
    private final static int REQUEST_ENABLE_BT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        usuario = (TextView)findViewById(R.id.holaUser); // 'enlazamos' el control con el cuadro de texto

        Bundle bundle = this.getIntent().getExtras();
        usuario.setText("Bienvenido " + bundle.getString("Usuario"));


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
