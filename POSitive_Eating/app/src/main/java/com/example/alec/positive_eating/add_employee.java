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
import com.example.shane.shaneconnect.ShaneConnect;

import org.json.JSONObject;

import static com.example.alec.positive_eating.Singleton_ShaneConnect_Factory.getShaneConnect;

public class add_employee extends AppCompatActivity {
Button add;

    /**
     *
     * Simple on create for the page add employee. This java file is used to add new employees or users to the system.
     * To allow people access to the POS terminal
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);
        add= (Button)(findViewById(R.id.Add_Employee));
        add.setOnClickListener(
                new View.OnClickListener()
                {
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
                       add_User(firstName,lastName,Social,bankRoute,bankAccount,Pass,Pay,PhoneNum,Position);
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
    private void add_User(String first, String last, String social, String bankroute,String bankAccount,String Pass, String Pay,String PhoneNumber,String Position ){
        ShaneConnect vista = getShaneConnect();
        vista.createAccount(last,first,"Don'tKnow", Integer.parseInt(Position),Pass,"Not relevant",PhoneNumber,"Not relevent",Integer.parseInt(Pay),new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
            }
        });
//        ShaneConnect vista = new ShaneConnect("http://proj-309-yt-4.cs.iastate.edu:1234", this);
//        vista.createAccount(,new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
//            }
//        });
}}
