/**
 * @author Alec
 */
package com.example.alec.positive_eating;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.alec.positive_eating.Singleton_ShaneConnect_Factory.getShaneConnect;

/**
 * Setting a variable for a Button and 2 edit texts for user name password and login button
 */
public class LoginWindow extends AppCompatActivity {

    Button LogInButton;
    EditText userNumber;
    EditText password;
    EditText Firstname;
    EditText Lastname;
    boolean usermanager;
    boolean userwaitstaff;
    boolean cook;
    int counter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_window);

        startService(new Intent(this, ShaneConnectService.class));

        LogInButton = (Button) findViewById(R.id.logInButton);
        password = (EditText) findViewById(R.id.password);
        userNumber = (EditText) findViewById(R.id.editText);
        Firstname  = (EditText) findViewById(R.id.Firstnamelog);
        Lastname  = (EditText) findViewById(R.id.lastnamelog);
        counter=0;
        /**
         * When it's clicked it will compare what is given to fake data.
         */
        LogInButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
<<<<<<< HEAD

=======
//                        Intent myIntent = new Intent(LoginWindow.this, MainScreen.class); /** Class name here */
//                        LoginWindow.this.startActivity(myIntent);
>>>>>>> f9199b2efe848cc321c6a01194f2ffce66b5c64f

                        String Userdata= Lastname.getText().toString()+"_"+Firstname.getText().toString()+"_"+userNumber.getText().toString();
                        Clockin(Userdata);
                        checkuser(Userdata,password.getText().toString());


                    }
                });
    }

    public void checkuser (String a, final String b){
        shaneconnect.ShaneConnect vista = getShaneConnect();
        usermanager=false;
        userwaitstaff=false;
        cook=false;
        vista.getAccountData(a,new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                try {
                    correct(response.get("pass").toString(),b, response.get("status").toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                    if(usermanager){
                        Intent myIntent = new Intent(LoginWindow.this, MainScreen.class); /** Class name here */
                        LoginWindow.this.startActivity(myIntent);
                    }
                    if(userwaitstaff){
                        Intent myIntent = new Intent(LoginWindow.this, Menu.class); /** Class name here */
                        LoginWindow.this.startActivity(myIntent);
                    }
                    if(cook){
                        Intent myIntent = new Intent(LoginWindow.this, OrderStatus.class); /** Class name here */
                        LoginWindow.this.startActivity(myIntent);
                    }

            }

        });
    }
    public void correct(String a,String b,String c){
        if(c.equals("1")){
        if(a.equals(b)){
            usermanager=true;
        }}
        else if(c.equals("2")){
            if(a.equals(b)){
                userwaitstaff=true;
            }
        }
        else if(c.equals("3")){
            if(a.equals(b)){
                cook=true;
            }
        }
    }
    public void Clockin(String a){
        shaneconnect.ShaneConnect vista = getShaneConnect();
        vista.newEmployeeLog(a,1,new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

            }

        });
    }

}

