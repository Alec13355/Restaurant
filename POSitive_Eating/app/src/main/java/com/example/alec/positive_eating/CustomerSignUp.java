package com.example.alec.positive_eating;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CustomerSignUp extends AppCompatActivity {

    private View firstName, lastName, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firstName = (EditText) findViewById(R.id.first_name);


        Button b = (Button) findViewById(R.id.register_confirm);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(isValidInput()){

                finish();
            }
            finish();
            }
        });
    }

    private void addAccount() {

    }

    private boolean isValidInput() {

        return true;
    }
}
