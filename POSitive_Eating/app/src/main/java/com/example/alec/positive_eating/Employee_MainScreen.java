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

import static com.example.alec.positive_eating.Singleton_Current_Employee.getEInstance;
import static com.example.alec.positive_eating.Singleton_Employee_List.getListInstance;
import static com.example.alec.positive_eating.Singleton_ShaneConnect_Factory.getShaneConnect;
import static com.example.alec.positive_eating.Singleton_Table_List.getTableListInstance;

/**
 * This class is the main landing page and will change views depending on what button is pressed.
 */
public class Employee_MainScreen extends AppCompatActivity {
    Button Status,schedule,Edit_users,Payroll,addTableMap,viewEmployeeList;//Delares the button variables

    List<employee>  eList;
    private List<Table> allTheTables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(Employee_MainScreen.this, "LOADING", Toast.LENGTH_SHORT).show();
        setContentView(R.layout.activity_main_screen);
        Status=(Button)findViewById(R.id.OrderStatButton);
        schedule=(Button)findViewById(R.id.Schedule);
//        if(getEInstance().getEmployee().getPermissions() == 0) {
//            Edit_users = (Button) findViewById(R.id.Edit_Users);
//            Edit_users.setVisibility(View.INVISIBLE);
//        }
        Edit_users = (Button) findViewById(R.id.Edit_Users);
        Edit_users.setVisibility(View.INVISIBLE);
        Payroll=(Button)findViewById(R.id.Payroll);
        addTableMap=(Button)findViewById(R.id.addTableMap);
        viewEmployeeList=(Button)findViewById(R.id.viewEmployeeList);


        Status.setVisibility(View.INVISIBLE);
        schedule.setVisibility(View.INVISIBLE);

        Payroll.setVisibility(View.INVISIBLE);
        addTableMap.setVisibility(View.INVISIBLE);
        viewEmployeeList.setVisibility(View.INVISIBLE);

        eList = new ArrayList<>();
        allTheTables = new ArrayList<>();

        shaneconnect.ShaneConnect vista = getShaneConnect();
        getEmployeeList(0, vista);

        //Initializes the buttons.
        if(getEInstance().getEmployee().getPermissions() == 0) {
            Edit_users.setOnClickListener(
                    new View.OnClickListener() {
                        public void onClick(View view) {
                            Intent myIntent = new Intent(Employee_MainScreen.this, Employee_add_employee.class); /** Class name here */
                            Employee_MainScreen.this.startActivity(myIntent);
                        }
                    }
            );
        }
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

    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(Employee_MainScreen.this, Employee_LoginWindow.class);
        this.finishActivity(0);
        Employee_MainScreen.this.startActivity(myIntent);
    }

    private void getEmployeeList(final int index, final shaneconnect.ShaneConnect s) {
        s.getEmployees(index, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    //employee(String first, String last, int ID, String availability, String phone, int rate, String pass){
                    employee temp = new employee(response.getString("first"), response.getString("last"), response.getInt("emp_id"), response.getString("address"), response.getString("phone"), response.getInt("rate"), response.getString("pass"), response.getInt("status"), response.getString("social"), response.getString("bank_num"), response.getString("routing"));
                    eList.add(temp);
                    getEmployeeList(index + 1, s);

                } catch (JSONException e) {
                    getListInstance().setEList(eList);
                    retrieveTables(0, s);
                    return;
                }
            }

        });
    }

    public void retrieveTables(final int index, final shaneconnect.ShaneConnect s) {
        s.getTables(index, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    //Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                    //if(userPermission == server) then check to see if employeeID matches singleton employeeID, otherwise discard table
                    Table temp = new Table(response.getString("name"), response.getInt("x_coord"), response.getInt("y_coord"), response.getInt("status"), response.getInt("employee_id"), " ", response.getInt("number_seats"), eList, Employee_MainScreen.this, null);
//                    if(getEInstance().getEmployee().getPermissions() == 1){
//                        if(getEInstance().getEmployee().getID() == temp.getEmployeeID()){
//                            allTheTables.add(temp);
//                        }
//                    }else{
//                        allTheTables.add(temp);
//                    }
                    allTheTables.add(temp);
                    retrieveTables(index+1,s);
                } catch (JSONException e) {
                    getTableListInstance().setTList(allTheTables);
                    Status.setVisibility(View.VISIBLE);
                    schedule.setVisibility(View.VISIBLE);
                    addTableMap.setVisibility(View.VISIBLE);
                    viewEmployeeList.setVisibility(View.VISIBLE);
                    if(getEInstance().getEmployee().getPermissions() == 0) {
                        Edit_users.setVisibility(View.VISIBLE);
                        Payroll.setVisibility(View.VISIBLE);
                    }
                    return;
                }
            }

        });
    }
}
