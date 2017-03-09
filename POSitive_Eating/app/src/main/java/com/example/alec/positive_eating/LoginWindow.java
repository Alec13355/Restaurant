package com.example.alec.positive_eating;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * @author Alec
 */
public class LoginWindow extends AppCompatActivity {
    /**
     * Setting a variable for a Button and 2 edit texts for user name password and login button
     */
    Button LogInButton;
    EditText userName;
    EditText password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_window);

        startService(new Intent(this, ShaneConnectService.class));

        LogInButton = (Button) findViewById(R.id.logInButton);
        password = (EditText) findViewById(R.id.password);
        userName = (EditText) findViewById(R.id.userName);

        /**
         * When it's clicked it will compare what is given to fake data.
         */
        LogInButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        Log.v("EditText", userName.getText().toString());
                        if (userName.getText().toString().equals("alec")) {
                            if (password.getText().toString().equals("abc123")) {
                                Intent myIntent = new Intent(LoginWindow.this, MainScreen.class); /** Class name here */
                                LoginWindow.this.startActivity(myIntent);
                            }
                        }
                    }
                });
    }




}

