package com.example.alec.positive_eating;
import com.example.shaneconnect.ShaneConnect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;
import android.view.View;
import android.content.Intent;

public class MainScreen extends AppCompatActivity {
Button Seating,Menu,Status,schedule,Edit_users,Payroll;//Delares the button variables
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
        //Initilizes the buttons.

        Edit_users.setOnClickListener(
            new View.OnClickListener()
             {
                public void onClick(View view){
                  Intent myIntent = new Intent(MainScreen.this, EditStaff.class); /** Class name here */
                   MainScreen.this.startActivity(myIntent);
                }
        }
        );
        Payroll.setOnClickListener(
        new View.OnClickListener()
        {
            public void onClick(View view){
                Intent myIntent = new Intent(MainScreen.this, Payroll.class); /** Class name here */
                MainScreen.this.startActivity(myIntent);
            }
        }
        );
        schedule.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view){
                        Intent myIntent = new Intent(MainScreen.this, Schedule.class); /** Class name here */
                        MainScreen.this.startActivity(myIntent);
                    }
                }
        );
        Status.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {


                        Intent myIntent = new Intent(MainScreen.this, OrderStatus.class); /** Class name here */
                        MainScreen.this.startActivity(myIntent);
                    }
                }

                 );
        Menu.setOnClickListener(

                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {


                        Intent myIntent = new Intent(MainScreen.this, Menu.class); /** Class name here */
                        MainScreen.this.startActivity(myIntent);
                    }
                }

        );
        Seating.setOnClickListener(

                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {


                        Intent myIntent = new Intent(MainScreen.this, Seating.class); /** Class name here */
                        MainScreen.this.startActivity(myIntent);
                    }
                }

        );



    }
}
