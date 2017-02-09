package com.example.shane.test;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.support.annotation.Nullable;
import android.util.JsonWriter;
import android.view.Display;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * The latest in Shane Drafahl Industries® has led to the development in a way to modulate your server needs into one convenient way to communicate with
 * a server you own.
 */
public class ShaneConnect {


    /**
     * String address of where the server
     * and example might be
     */
    private String url;

    /**
     * maind is what stores the mainactivity that can put on operation in a query for the OS
     */
    private MainActivity maind;

    /**
     *
     * @param url is a String object that holds the location of the server you want to connect to and example may be http://10.0.2.2:3019 do not enter the address of the post request
     * @param d is the MainActivity that controls the runtime of the app, for example to give an argument of this from the MainActivity class is just to type "this".
     */
    public ShaneConnect(String url, MainActivity d) {
        this.url=url;
        maind=d;
    }


    /**
     * getAccountData will make a call to the server to get a json object of the representing account user given as an argument.
     * @param account_name this is the name of the account you want data from, for example "SMITH_BOB_1" would be an argument. The convention is that the last name, first name, and a unique ID are concatenated together with underscores to create the username.
     * @param s is a asynchronous callback function
     */
    public void getAccountData(String account_name,Response.Listener<JSONObject> s ) {
        RequestQueue queue = Volley.newRequestQueue(maind); //not sure if this will work
        JSONObject out = new JSONObject();
        try {
            out.put("name",account_name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest lastFMAuthRequest = new JsonObjectRequest(Request.Method.POST, url + "/getAccount", out, s, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.print(error.networkResponse);

            }

        });
        queue.add(lastFMAuthRequest);
    }


}
