package com.example.alec.positive_eating.CustomerLogin;

import com.android.volley.Response;
import com.example.alec.positive_eating.customerRegisteration.Customer;

import org.json.JSONException;
import org.json.JSONObject;

import shaneconnect.ShaneConnect;
/**
 * Created by shane on 4/20/17.
 */


public class ShaneConnectCustomerLoginAdapter {

   private ShaneConnect connection;

    private Object lock;

    private State state;

    public ShaneConnectCustomerLoginAdapter(ShaneConnect shane,Object lock,State state){
        connection = shane;
        this.lock = lock;
        this.state = state;

    }

    public void checkCredentials(final Customer customer){

            new Thread(new Runnable() {
                @Override
                public void run() {
                    credentials(customer);
                }
            }).run();


    }


    private void credentials(final Customer customer) {

        synchronized (state) {
            connection.getCustomer(customer.getEmail(), new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    if (!response.has("none")) {
                        try {
                            String correctpass = (String) response.get("pass");

                            if (customer.getPassword().equals(correctpass)) {
                                state.setState(true);
                                state.notify();
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
