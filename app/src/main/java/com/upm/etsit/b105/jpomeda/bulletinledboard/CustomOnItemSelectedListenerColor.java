package com.upm.etsit.b105.jpomeda.bulletinledboard;


import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

/**
 * Created by Javi on 15/7/16.
 */
public class CustomOnItemSelectedListenerColor implements AdapterView.OnItemSelectedListener{

    char posicion;


    public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
        switch (pos) {
            case 0: posicion = 'b'; break;
            case 1: posicion = 'r'; break;
            case 2: posicion = 'g'; break;
            case 3: posicion = 'y'; break;
            case 4: posicion = 'o'; break;
            case 5: posicion = 'p'; break;
            case 6: posicion = 'w'; break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}


/*<item>Azul</item>
<item>Rojo</item>
<item>Verde</item>
<item>Amarillo</item>
<item>Naranja</item>
<item>Morado</item>
<item>Blanco</item>*/
