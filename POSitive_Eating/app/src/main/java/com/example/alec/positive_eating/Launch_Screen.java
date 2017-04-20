package com.example.alec.positive_eating;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.alec.positive_eating.CustomerLogin.*;

import static android.view.Gravity.CENTER;
import static android.view.Gravity.CENTER_HORIZONTAL;

import com.example.alec.positive_eating.CustomerLogin.CustomerLoginActivity;

public class Launch_Screen extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_screen);

        startService(new Intent(this, ShaneConnectService.class));
        Button customer = (Button) findViewById(R.id.Customer);
        Button employee = (Button) findViewById(R.id.Employee);
        customer.setOnClickListener(this);
        employee.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.Customer:
                startActivity(new Intent(this, CustomerLoginActivity.class));
                break;
            case R.id.Employee:
                //startActivity(new Intent(Launch_Screen.this, Employee_MainScreen.class)); break;
                startActivity(new Intent(this, Employee_LoginWindow.class));
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        TextView text = new TextView(Launch_Screen.this);
        text.setText(String.valueOf("PRESS EXIT TO EXIT"));
        text.setGravity(CENTER);

        AlertDialog.Builder builder = new AlertDialog.Builder(Launch_Screen.this);
        builder.setTitle(String.valueOf("WARNING"));

        builder.setView(text);

        builder.setPositiveButton(String.valueOf("EXIT"), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
}
