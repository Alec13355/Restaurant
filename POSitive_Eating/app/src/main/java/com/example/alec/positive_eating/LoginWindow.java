package com.example.alec.positive_eating;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;
import android.view.View;
import android.content.Intent;

public class LoginWindow extends AppCompatActivity {
Button LogInButton;
    EditText userName;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_window);

        LogInButton=(Button)findViewById(R.id.logInButton);
        password=(EditText)findViewById(R.id.password);
        userName=(EditText)findViewById(R.id.userName);
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
}
