/**
 * @author Alec
 */
package com.example.alec.positive_eating;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.alec.positive_eating.payrole.Employee_Payroll;

/**
 * This class is the main landing page and will change views depending on what button is pressed.
 */
public class Employee_MainScreen extends AppCompatActivity {
Button Seating,Menu,Status,schedule,Edit_users,Payroll,addTableMap,viewEmployeeList;//Delares the button variables
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        Seating=(Button)findViewById(R.id.Seating_Button);
        Menu=(Button)findViewById(R.id.Ordering_Menu);
        Status=(Button)findViewById(R.id.OrderStatButton);
        schedule=(Button)findViewById(R.id.Schedule);
        Edit_users=(Button)findViewById(R.id.Edit_Users);
        Payroll=(Button)findViewById(R.id.Payroll);
        addTableMap=(Button)findViewById(R.id.addTableMap);
        viewEmployeeList=(Button)findViewById(R.id.viewEmployeeList);
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
        Seating.setOnClickListener(
            new View.OnClickListener()
            {
                public void onClick(View view)
                {
                    Intent myIntent = new Intent(Employee_MainScreen.this, Employee_Seating.class); /** Class name here */
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
}
