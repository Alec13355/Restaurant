/**
 * @author Alec
 */
package com.example.alec.positive_eating;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Response;
import com.example.alec.positive_eating.payrole.Employee_Payroll;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.alec.positive_eating.Singleton_Current_Employee.getInstance;
import static com.example.alec.positive_eating.Singleton_Employee_List.getListInstance;
import static com.example.alec.positive_eating.Singleton_ShaneConnect_Factory.getShaneConnect;

/**
 * This class is the main landing page and will change views depending on what button is pressed.
 */
public class Employee_MainScreen extends AppCompatActivity {
    Button Seating,Menu,Status,schedule,Edit_users,Payroll,addTableMap,viewEmployeeList;//Delares the button variables

    List<employee>  eList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        Menu=(Button)findViewById(R.id.Ordering_Menu);
        Status=(Button)findViewById(R.id.OrderStatButton);
        schedule=(Button)findViewById(R.id.Schedule);
        Edit_users=(Button)findViewById(R.id.Edit_Users);
        Payroll=(Button)findViewById(R.id.Payroll);
        addTableMap=(Button)findViewById(R.id.addTableMap);
        viewEmployeeList=(Button)findViewById(R.id.viewEmployeeList);

        Menu.setVisibility(View.INVISIBLE);
        Status.setVisibility(View.INVISIBLE);
        schedule.setVisibility(View.INVISIBLE);
        Edit_users.setVisibility(View.INVISIBLE);
        Payroll.setVisibility(View.INVISIBLE);
        addTableMap.setVisibility(View.INVISIBLE);
        viewEmployeeList.setVisibility(View.INVISIBLE);

        shaneconnect.ShaneConnect vista = getShaneConnect();
        getEmployeeList(0, vista);

        eList = new ArrayList<>();
        //Initilizes the buttons.

        Edit_users.setOnClickListener(
            new View.OnClickListener()
             {
                public void onClick(View view){
                    Intent myIntent = new Intent(Employee_MainScreen.this, Employee_EditStaff.class); /** Class name here */
                    Employee_MainScreen.this.startActivity(myIntent);
                }
        }
        );
        Payroll.setOnClickListener(
            new View.OnClickListener()
            {
                public void onClick(View view){
                    Intent myIntent = new Intent(Employee_MainScreen.this, Employee_Payroll.class); /** Class name here */
                    Employee_MainScreen.this.startActivity(myIntent);
                }
            }
        );
        schedule.setOnClickListener(
            new View.OnClickListener()
            {
                public void onClick(View view){
                    Intent myIntent = new Intent(Employee_MainScreen.this, Employee_Schedule.class); /** Class name here */
                    Employee_MainScreen.this.startActivity(myIntent);
                }
            }
        );
        Status.setOnClickListener(
            new View.OnClickListener()
            {
                public void onClick(View view)
                {
                    Intent myIntent = new Intent(Employee_MainScreen.this, CookOrderList.class); /** Class name here */
                    Employee_MainScreen.this.startActivity(myIntent);
                }
            }
        );
        Menu.setOnClickListener(
            new View.OnClickListener()
            {
                public void onClick(View view)
                {
                    Intent myIntent = new Intent(Employee_MainScreen.this, Employee_Menu.class); /** Class name here */
                    Employee_MainScreen.this.startActivity(myIntent);
                }
            }
        );
        addTableMap.setOnClickListener(
            new View.OnClickListener()
            {
                public void onClick(View view)
                {
                    Intent myIntent = new Intent(Employee_MainScreen.this, tableMap.class); /** Class name here */
                    Employee_MainScreen.this.startActivity(myIntent);
                }
            }
        );
        viewEmployeeList.setOnClickListener(
            new View.OnClickListener()
            {
                public void onClick(View view)
                {
                    Intent myIntent = new Intent(Employee_MainScreen.this, ViewEmployeeList.class); /** Class name here */
                    Employee_MainScreen.this.startActivity(myIntent);
                }
            }
        );



    }

    private void getEmployeeList(final int index, final shaneconnect.ShaneConnect s) {
        s.getEmployees(index, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    //employee(String first, String last, int ID, String availability, String phone, int rate, String pass){
                    employee temp = new employee(response.getString("first"), response.getString("last"), response.getInt("emp_id"), response.getString("address"), response.getString("phone"), response.getInt("rate"), response.getString("pass"), response.getInt("status"));
                    eList.add(temp);
                    getEmployeeList(index + 1, s);
                    
                } catch (JSONException e) {
                    getListInstance().setEList(eList);
                    Menu.setVisibility(View.VISIBLE);
                    Status.setVisibility(View.VISIBLE);
                    schedule.setVisibility(View.VISIBLE);
                    Edit_users.setVisibility(View.VISIBLE);
                    Payroll.setVisibility(View.VISIBLE);
                    addTableMap.setVisibility(View.VISIBLE);
                    viewEmployeeList.setVisibility(View.VISIBLE);
                    return;
                }
            }

        });
    }
}
