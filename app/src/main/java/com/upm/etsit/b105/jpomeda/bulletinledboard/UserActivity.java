package com.upm.etsit.b105.jpomeda.bulletinledboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class UserActivity extends AppCompatActivity {

    private TextView usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        usuario = (TextView)findViewById(R.id.holaUser); // 'enlazamos' el control con el cuadro de texto

        Bundle bundle = this.getIntent().getExtras();
        usuario.setText("Bienvenido " + bundle.getString("Usuario"));
    }
}
