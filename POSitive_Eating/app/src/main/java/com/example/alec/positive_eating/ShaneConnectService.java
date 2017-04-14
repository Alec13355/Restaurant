package com.example.alec.positive_eating;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import static com.example.alec.positive_eating.Singleton_ShaneConnect_Factory.getShaneConnect;
import static com.example.alec.positive_eating.Singleton_TableCache_Factory.getTableCache;

/**
 * @author Shane
 * Shane Connect java file
 */
public class ShaneConnectService extends Service {

    private final IBinder myBinder = new MyLocalBinder();

    @Override
    public void onCreate() {
        getShaneConnect("http://proj-309-yt-4.cs.iastate.edu:", this);
        getTableCache(getShaneConnect());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    public class MyLocalBinder extends Binder {
        ShaneConnectService getService() {
            return ShaneConnectService.this;
        }
    }
}
