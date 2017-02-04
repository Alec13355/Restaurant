package com.example.shane.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //authGetRequest();


        ShaneConnect d = new ShaneConnect("http://10.0.2.2:3004");
        //d.getTestData();
        //v.setText("Test");
    }

    public void authGetRequest() {

        RequestQueue queue = Volley.newRequestQueue(this);
        final TextView v = (TextView) findViewById(R.id.display);
// prepare the Auth Get Request
        String url = "http://10.0.2.2:3004/getTestData";
        //String url ="http://www.google.com";
        JsonObjectRequest lastFMAuthRequest = new JsonObjectRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        v.setText(response.toString());
                    }
                },new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.print(error.networkResponse);

            }

        });

// add it to the RequestQueue
        queue.add(lastFMAuthRequest);
    }
}

