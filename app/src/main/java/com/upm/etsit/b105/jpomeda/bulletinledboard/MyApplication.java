package com.upm.etsit.b105.jpomeda.bulletinledboard;

import android.app.Application;
import android.os.Handler;

/**
 * Created by Javi on 7/7/16.
 */
public class MyApplication extends Application {


    Handler.Callback realCallback = null;
    Handler h = new Handler(){
        public void handleMessage(android.os.Message msg){
            if (realCallback != null){
                realCallback.handleMessage(msg);
            }
        };
    };


    public Handler getHandler(){
        return h;
    }

    public void setCallback(Handler.Callback callback){
        this.realCallback = callback;
    }


    @Override
    public void onCreate(){
        super.onCreate();






    }

}
