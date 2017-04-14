package com.example.alec.positive_eating.customerRegisteration;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Response;

import org.json.JSONObject;

import shaneconnect.ShaneConnect;

import static com.example.alec.positive_eating.Singleton_ShaneConnect_Factory.getShaneConnect;

/**
 * Created by shane on 4/13/17.
 */

public class ShaneConnectAdapter {

    private ShaneConnect connect;

    private Context cont;


    public ShaneConnectAdapter(Context con){
        this.connect = getShaneConnect();
        this.cont = con;
    }

    public void register(final Customer cust){
        final ShaneConnect s = this.connect;
        this.connect.getCustomer(cust.getUserName(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if(response.has("none")){
                    s.addCustomer(cust.getUserName(),cust.getEmail(),cust.getPassword(), new Response.Listener<JSONObject>(){

                        @Override
                        public void onResponse(JSONObject response) {

                            if(response.has("success")){
                                Toast.makeText(cont,"success",Toast.LENGTH_LONG).show();
                            }

                        }
                    });
                }else{
                    Toast.makeText(cont,"Username already exists",Toast.LENGTH_LONG).show();
                }
            }
        });



    }



}
