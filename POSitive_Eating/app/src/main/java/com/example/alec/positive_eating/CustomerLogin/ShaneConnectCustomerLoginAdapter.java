package com.example.alec.positive_eating.CustomerLogin;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.Response;
import com.example.alec.positive_eating.Employee_MainScreen;
import com.example.alec.positive_eating.customerRegisteration.Customer;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import shaneconnect.ShaneConnect;
/**
 * Created by shane on 4/20/17.
 */


public class ShaneConnectCustomerLoginAdapter {

   private ShaneConnect connection;

    private Object lock;

    private State state;

    private Context cont;

    public ShaneConnectCustomerLoginAdapter(ShaneConnect shane,Object lock,State state,Context cont){
        connection = shane;
        this.lock = lock;
        this.state = state;
        this.cont = cont;

    }




    public void credentials(final Customer customer) {

        synchronized (state) {
            state.notify();
            connection.getCustomer(customer.getEmail(), new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    if (!response.has("none")) {
                        try {
                            String correctpass = (String) response.get("pass");

                            if (customer.getPassword().equals(correctpass)) {
                                Toast.makeText(cont,"success",Toast.LENGTH_LONG).show();
                                Intent myIntent = new Intent(cont, Employee_MainScreen.class); /** Class name here */
                                cont.startActivity(myIntent);

                            }else{
                                Toast.makeText(cont,"success",Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

        }
    }


}
