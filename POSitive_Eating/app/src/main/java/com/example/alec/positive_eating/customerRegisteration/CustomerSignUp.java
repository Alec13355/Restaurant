package com.example.alec.positive_eating.customerRegisteration;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.alec.positive_eating.R;

public class CustomerSignUp extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*General Setup*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final CustomerConcreteBuilder builder = new CustomerConcreteBuilder();

        EditText usr = (EditText) findViewById(R.id.first_name);
        EditText email = (EditText) findViewById(R.id.register_email);
        EditText pass = (EditText) findViewById(R.id.register_password);

        builder.addEmail(email);
        builder.addUser(usr);
        builder.addPassword(pass);

        Button b = (Button) findViewById(R.id.register_confirm);
        final Context context = this;
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShaneConnectAdapter shane = new ShaneConnectAdapter(context);
                shane.register(builder.getProduct());
            }
    });

    }


}
