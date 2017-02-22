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
        final TextView v = (TextView) findViewById(R.id.display);
        //authGetRequest();
        final ShaneConnect d = new ShaneConnect("http://10.0.2.2:3019", this);
        ArrayList<String> s = new ArrayList<String>();
        test(0,d,v);
        /*
        d.placeOrder("Raw Cow", s, 40, "table 9", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }

        });
    }
    */
  /*      d.getFood("burger", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                v.setText(response.toString());
            }

        });


    }*/

        /*class Disp implements Response.Listener<JSONException> {

            TextView v;

            Disp(TextView v){
                super();
                this.v = v;
            }

            @Override
            public void onResponse(JSONException response) {
                v.setText(v.getText()+response.toString());
                d.getOrders();
            }


        }
        */

    }
    public void test(final int index, final ShaneConnect s, final TextView v) {
        s.getOrders(index, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                    if(response.length()>0){
                        v.setText(response.toString() + v.getText());
                        test(index+1,s,v);
                    }

            }

        });
    }


}
