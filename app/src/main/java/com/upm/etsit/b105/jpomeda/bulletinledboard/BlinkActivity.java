package com.upm.etsit.b105.jpomeda.bulletinledboard;

/**
 * Created by Javi on 28/6/16.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.bluetooth.*;
import android.content.*;
import java.util.*;
import android.view.*;

public class BlinkActivity extends AppCompatActivity{

    private EditText color;

    private Button botonEnviar;
    private Button botonCancelar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blink);



        botonEnviar.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v) {


            }

        });

    }





}