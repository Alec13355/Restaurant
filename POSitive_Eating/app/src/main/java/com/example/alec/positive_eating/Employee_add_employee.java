/**
 * @author Alec
 */

package com.example.alec.positive_eating;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;

import org.json.JSONObject;

import shaneconnect.ShaneConnect;

import static com.example.alec.positive_eating.Singleton_ShaneConnect_Factory.getShaneConnect;
/**
 *
 * Simple on create for the page add employee. This java file is used to add new employees or users to the system.
 * To allow people access to the POS terminal
 */
public class Employee_add_employee extends AppCompatActivity {
Button add;
//This is Christian's comment.
//This is another comment.
//LogInButton = (Button) findViewById(R.id.logInButton);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);
        add= (Button) findViewById(R.id.Add_Person);
        add.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view){

                        String firstName=((EditText)findViewById(R.id.firstName)).getText().toString() ;
                        String lastName= ((EditText)findViewById(R.id.lastName)).getText().toString();
                        String Social= ((EditText)findViewById(R.id.Social)).getText().toString();
                        String bankRoute= ((EditText)findViewById(R.id.routingNumber)).getText().toString();
                        String bankAccount= ((EditText)findViewById(R.id.bankNumber)).getText().toString();
                        String Pass= ((EditText)findViewById(R.id.Pass)).getText().toString();
                        String Pay= ((EditText)findViewById(R.id.Pay)).getText().toString();
                        String PhoneNum= ((EditText)findViewById(R.id.Phone)).getText().toString();
                        String Position= ((EditText)findViewById(R.id.Position)).getText().toString();
                        String Avail = ((EditText)findViewById(R.id.Shift)).getText().toString();
                       add_User(firstName,lastName,Social,bankRoute,bankAccount,Pass,Pay,PhoneNum,Position,Avail);

                    }
                }
        );

    }

    /**
     *
     * @param first
     * @param last
     * @param social
     * @param bankroute
     * @param bankAccount
     * @param Pass
     * @param Pay
     * @param PhoneNumber
     * @param Position
     * Takes in the parameters given in the text boxes to create a new employee user on the server. This also will set their roles
     */
    private void add_User(String first, String last, String social, String bankroute,String bankAccount,String Pass, String Pay,String PhoneNumber,String Position,String Avail ){
        ShaneConnect vista = getShaneConnect();
        vista.createAccount(last,first,"DontKnow",bankroute,social,bankAccount,Integer.parseInt(Position),Pass,Avail,"Not relevent",PhoneNumber,Integer.parseInt(Pay),new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
            }
        });
}}
