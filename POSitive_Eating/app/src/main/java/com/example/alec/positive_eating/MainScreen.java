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
Button Seating,Menu,Status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        Seating=(Button)findViewById(R.id.Seating_Button);
        Menu=(Button)findViewById(R.id.Ordering_Menu);
        Status=(Button)findViewById(R.id.OrderStatButton);

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


//        LogInButton=(Button)findViewById(R.id.logInButton);
//        password=(EditText)findViewById(R.id.password);
//        userName=(EditText)findViewById(R.id.userName);
//        LogInButton.setOnClickListener(
//                new View.OnClickListener()
//                {
//                    public void onClick(View view)
//                    {
//                        Log.v("EditText", userName.getText().toString());
//                        if(userName.getText().toString().equals("alec")){
//                            if(password.getText().toString().equals("abc123")){
//                                Intent myIntent = new Intent(LoginWindow.this, MainScreen.class); /** Class name here */
//                                LoginWindow.this.startActivity(myIntent);
//                            }
//                        }
//                    }
//                });
    }
}
