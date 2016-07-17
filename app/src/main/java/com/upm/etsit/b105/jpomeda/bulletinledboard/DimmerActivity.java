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
import android.widget.*;

/**
 * Created by Javi on 7/7/16.
 */
public class DimmerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    private final String TAG = "DimmerActivity";


    private Button botonEnviar;

    private final String PRUEBA = "Hola";

    private BluetoothService btService;
    boolean mBound = false;

    private String trama = "bzzzzz";

    private char color1;
    private char duracion1;
    private char opcion1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dimmer);


        botonEnviar = (Button) findViewById(R.id.botonEnviaBlink);


            /* SPINNERS VIEW */

        Spinner spinnerc1 = (Spinner) findViewById(R.id.spinnerColor1);
        ArrayAdapter<CharSequence> adapterc1 = ArrayAdapter.createFromResource(this, R.array.colors_array, android.R.layout.simple_spinner_item);
        adapterc1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerc1.setAdapter(adapterc1);

        Spinner spinnerd1 = (Spinner) findViewById(R.id.spinnerDuracion1);
        ArrayAdapter<CharSequence> adapterd1 = ArrayAdapter.createFromResource(this, R.array.duracion_array, android.R.layout.simple_spinner_item);
        adapterd1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerd1.setAdapter(adapterd1);

        Spinner spinnero1 = (Spinner) findViewById(R.id.spinnerDimmer1);
        ArrayAdapter<CharSequence> adaptero1 = ArrayAdapter.createFromResource(this, R.array.dimmer_array, android.R.layout.simple_spinner_item);
        adaptero1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnero1.setAdapter(adaptero1);



            /* SPINNERS ONCLICK */

        spinnerc1.setOnItemSelectedListener(this);
        spinnerd1.setOnItemSelectedListener(this);
        spinnero1.setOnItemSelectedListener(this);


    }

    @Override
    protected void onStart(){
        super.onStart();

        Intent intent = new Intent(this, BluetoothService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

        botonEnviar.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v) {
                try {
                    generaTrama();
                    Toast.makeText(DimmerActivity.this, "Enviando: " +trama, Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Enviando..."+trama);
                    if (mBound){
                        btService.conexion.mConnectedThread.write(trama);
                    }
                } catch (Exception e) {
                    //Toast.makeText(IntercalarActivity.this, "No se pudo enviar", Toast.LENGTH_SHORT).show();
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

    public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
        Spinner spinner = (Spinner) parent;
        if (spinner.getId() == R.id.spinnerColor1) {
            switch (pos) {
                case 0:
                    color1 = 'b';
                    break;
                case 1:
                    color1 = 'r';
                    break;
                case 2:
                    color1 = 'g';
                    break;
                case 3:
                    color1 = 'y';
                    break;
                case 4:
                    color1 = 'o';
                    break;
                case 5:
                    color1 = 'p';
                    break;
                case 6:
                    color1 = 'w';
                    break;
            }

        } else if (spinner.getId() == R.id.spinnerDuracion1) {
            switch (pos) {
                case 0:
                    duracion1 = '1';
                    break;
                case 1:
                    duracion1 = '2';
                    break;
                case 2:
                    duracion1 = '3';
                    break;
                case 3:
                    duracion1 = '4';
                    break;
                case 4:
                    duracion1 = '5';
                    break;
            }

        } else if (spinner.getId() == R.id.spinnerDimmer1) {
            switch (pos) {
                case 0:
                    opcion1 = 'a'; //DimmerUpAll
                    break;
                case 1:
                    opcion1 = 'b'; //DimmerDownAll
                    break;
            }

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    public void generaTrama (){
        trama = ""+'c'+color1+'z'+duracion1+'z'+opcion1;
    }

}