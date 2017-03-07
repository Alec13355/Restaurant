package com.example.alec.positive_eating;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.example.shaneconnect.ShaneConnect;

import org.json.JSONObject;

import static com.example.alec.positive_eating.Singleton_ShaneConnect_Factory.getShaneConnect;

public class Edit_menu extends AppCompatActivity {
    EditText food,price,descriton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_menu);
        Button add = (Button) findViewById(R.id.Add_to_menu);
         food= (EditText)findViewById(R.id.FoodName);
         price= (EditText)findViewById(R.id.PriceofFood);
         descriton= (EditText)findViewById(R.id.Description);
        //Initilizes the buttons.

        add.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view){
                        ChangeFood(food.getText().toString(),price.getText().toString(),descriton.getText().toString());
                    }
                }
        );
    }
    }

    private void ChangeFood(String name, String price, String disc){
        ShaneConnect vista = getShaneConnect();
        //ShaneConnect vista = new ShaneConnect("http://proj-309-yt-4.cs.iastate.edu:1234", this);
        int a = Integer.parseInt(price);
        vista.addFood(name, a, disc, 1, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
