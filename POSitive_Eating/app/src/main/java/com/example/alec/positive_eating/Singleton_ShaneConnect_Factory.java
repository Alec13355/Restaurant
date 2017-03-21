package com.example.alec.positive_eating;

import android.content.Context;
import com.example.shane.shaneconnect.ShaneConnect;

/**
 * @author Shane/Christian
 */
public class Singleton_ShaneConnect_Factory {

    private static ShaneConnect sc = null;

    private static final int PORT_NUMBER = 2223;

    public static ShaneConnect getShaneConnect(String URL, Context context) {
        if (sc == null) {
            sc = new ShaneConnect(URL+PORT_NUMBER, context);
        }
        return sc;
    }

    public static ShaneConnect getShaneConnect() {
        return sc;
    }
}