package com.example.alec.positive_eating;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.example.shaneconnect.ShaneConnect;

import org.json.JSONObject;

import static com.example.alec.positive_eating.Singleton_ShaneConnect_Factory.getShaneConnect;

public class LoginWindow extends AppCompatActivity {
Button LogInButton;
    EditText userName;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_window);
        setupService();

        LogInButton=(Button)findViewById(R.id.logInButton);
        password=(EditText)findViewById(R.id.password);
        userName=(EditText)findViewById(R.id.userName);
        ShaneConnect username1= getShaneConnect();
        username1.createAccount("Harrison","Alec","0",0,"abc","8675309",8675309,8675309,new Response.Listener<JSONObject>(){

                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                }});

        LogInButton.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        Log.v("EditText", userName.getText().toString());
                        if(userName.getText().toString().equals("alec")){
                            if(password.getText().toString().equals("abc123")){
                                Intent myIntent = new Intent(LoginWindow.this, MainScreen.class); /** Class name here */
                                LoginWindow.this.startActivity(myIntent);
                            }
                        }
                    }
                });
    }
    private void setupService() {
        startService(new Intent(this, ShaneConnectService.class));
    }
}
