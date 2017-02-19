package com.example.shane.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import com.example.shane.shaneconnect.ShaneConnect;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * This is for testing only do not use
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //authGetRequest();
        ShaneConnect d = new ShaneConnect("http://10.0.2.2:3019",this);
        ArrayList<JSONObject> l;
        l = d.getEmployeeLog("Smith_Bob_1");
        TextView v = (TextView)findViewById(R.id.display);
        String disp="";
        for(int x=0;x<l.size();x++){
            disp+=l.get(x).toString();
        }
        v.setText(disp);



    }

}

