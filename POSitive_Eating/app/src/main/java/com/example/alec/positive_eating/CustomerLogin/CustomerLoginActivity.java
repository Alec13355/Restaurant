package com.example.alec.positive_eating.CustomerLogin;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.example.alec.positive_eating.Employee_LoginWindow;
import com.example.alec.positive_eating.Employee_MainScreen;
import com.example.alec.positive_eating.Launch_Screen;
import com.example.alec.positive_eating.R;
import com.example.alec.positive_eating.Reservations.CustomerReservations;

import java.util.List;

import shaneconnect.ShaneConnect;

import static com.example.alec.positive_eating.CustomerMainMenu.RESERVATION_CODE;
import static com.example.alec.positive_eating.Singleton_ShaneConnect_Factory.getShaneConnect;

/**
 * @author Christian Shinkle
 * The CustomerLoginActivity handles logging in for the customer. It includes fucntionality for
 * checking if the email address password meet the requirements, such as the email address having
 * a '@' or the password having at least 5 characters.
 */
public class CustomerLoginActivity extends AppCompatActivity {

    private static final int REQUEST_READ_CONTACTS = 0;
    private static String[] DUMMY_CREDENTIALS;
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;


    /**
     * Creates the activity, sets up views, including email view, password view, sign-in button,
     * and register button.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);

        Button b = (Button)findViewById(R.id.email_sign_in_button);

        final Context cont = this;
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = ((EditText) findViewById(R.id.password)).getText().toString();
                String email = ((EditText) findViewById(R.id.email)).getText().toString();
                CustomerBuilder builder = new CustomerBuilder();
                builder.addEmail(email);
                builder.addPassword(password);
                State state = new State();
                state.setState(false);
                Object lock = new Object();
                ShaneConnectCustomerLoginAdapter adapter = new ShaneConnectCustomerLoginAdapter(getShaneConnect(),lock,state,cont);
                adapter.credentials(builder.getCustomer());

            }
        });
    }


    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(CustomerLoginActivity.this, Launch_Screen.class);
        this.finishActivity(0);
        CustomerLoginActivity.this.startActivity(myIntent);
    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(CustomerLoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }


}

