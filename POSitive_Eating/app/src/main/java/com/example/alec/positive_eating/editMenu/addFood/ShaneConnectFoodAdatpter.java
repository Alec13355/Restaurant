package com.example.alec.positive_eating.editMenu.addFood;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Response;
import com.example.alec.positive_eating.CustomerLogin.ShaneConnectCustomerLoginAdapter;
import com.example.alec.positive_eating.editMenu.Food;

import org.json.JSONObject;

import shaneconnect.ShaneConnect;

/**
 * Created by shane on 4/23/17.
 */

public class ShaneConnectFoodAdatpter {

    private ShaneConnect connection;

    Context cont;

    public ShaneConnectFoodAdatpter(ShaneConnect connect, Context cont){
        this.connection=connect;
        this.cont=cont;
    }

    public void submitFood(Food food){
        connection.addFood(food.getName(), food.getIntPrice(), food.getDesc(), food.getQuantity(), food.getOptions(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if(response.has("added")){
                    Toast.makeText(cont,"success",Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}
