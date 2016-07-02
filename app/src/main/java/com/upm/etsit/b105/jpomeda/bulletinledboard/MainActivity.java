package com.upm.etsit.b105.jpomeda.bulletinledboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    private EditText txtUsuario;
    private Button botonAceptar;
    private EditText txtPass;

    private final String PASSWORD = "070715";
    private String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUsuario = (EditText)findViewById(R.id.txtNombre);
        txtPass = (EditText)findViewById(R.id.mainEditPass);
        botonAceptar = (Button)findViewById(R.id.btnAceptar);




        botonAceptar.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){

                pass = txtPass.getText().toString();
                Log.d(TAG, "Contraseña introducida:" +pass);

                if (pass.equals(PASSWORD)) {

                    Intent intent = new Intent(MainActivity.this, UserActivity.class);
                    Bundle b = new Bundle();
                    b.putString("Usuario", txtUsuario.getText().toString());

                    intent.putExtras(b);

                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    //hola que tal
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
