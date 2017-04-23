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
public class Employee_LoginWindow extends AppCompatActivity {

    Button LogInButton;
    EditText userNumber;
    EditText password;
    EditText Firstname;
    EditText Lastname;
    boolean userExists;
    boolean cook;
    int counter;

    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(Employee_LoginWindow.this, Launch_Screen.class);
        this.finishActivity(0);
        Employee_LoginWindow.this.startActivity(myIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_window);


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

//                        Intent myIntent = new Intent(Employee_LoginWindow.this, Employee_MainScreen.class); /** Class name here */
//                        Employee_LoginWindow.this.startActivity(myIntent);


                        String Userdata= Lastname.getText().toString()+"_"+Firstname.getText().toString()+"_"+userNumber.getText().toString();
                        Clockin(Userdata);
                        checkuser(Userdata,password.getText().toString());

                    }
                });
    }

    public void checkuser (String a, final String b){
        shaneconnect.ShaneConnect vista = getShaneConnect();
        userExists=false;
        cook=false;
        vista.getAccountData(a,new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                //Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                try {
                    employee e = new employee(response.getString("first"), response.getString("last"), response.getInt("emp_id"), response.getString("address"), response.getString("phone"), response.getInt("rate"), response.getString("pass"), response.getInt("status"), response.getString("social"), response.getString("bank_num"), response.getString("routing"));
                    Singleton_Current_Employee.getEInstance().setEmployee(e);
                    correct(response.get("pass").toString(),b);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                    if(userExists){
                        Intent myIntent = new Intent(Employee_LoginWindow.this, Employee_MainScreen.class); /** Class name here */
                        Employee_LoginWindow.this.startActivity(myIntent);
                    }

            }

        });
    }
    public void correct(String a,String b){

        if(a.equals(b)) {
            userExists = true;
        }else{
            Toast.makeText(Employee_LoginWindow.this, String.valueOf("Invalid Password"), Toast.LENGTH_SHORT);
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

