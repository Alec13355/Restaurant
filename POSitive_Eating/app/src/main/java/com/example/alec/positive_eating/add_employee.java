package com.example.alec.positive_eating;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.shaneconnect.ShaneConnect;

import static com.example.alec.positive_eating.Singleton_ShaneConnect_Factory.getShaneConnect;

public class add_employee extends AppCompatActivity {
Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);
        add= (Button)(findViewById(R.id.Add_Employee));
        add.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view){
                       add_User();
                    }
                }
        );

    }
    private void add_User(){
        ShaneConnect vista = getShaneConnect();
        String firstName=((EditText)findViewById(R.id.firstName)).getText().toString() ;
        String lastName= ((EditText)findViewById(R.id.lastName)).getText().toString();
        String Social= ((EditText)findViewById(R.id.Social)).getText().toString();
        String bankRoute= ((EditText)findViewById(R.id.routingNumber)).getText().toString();
        String bankAccount= ((EditText)findViewById(R.id.bankNumber)).getText().toString();
        //ShaneConnect vista = new ShaneConnect("http://proj-309-yt-4.cs.iastate.edu:1234", this);
//        vista.createAccount(,new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
//            }
//        });
}}
