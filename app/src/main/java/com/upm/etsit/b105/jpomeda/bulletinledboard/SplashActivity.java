package com.upm.etsit.b105.jpomeda.bulletinledboard;

/**
 * Created by Javi on 2/7/16.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

    public class SplashActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);



            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

