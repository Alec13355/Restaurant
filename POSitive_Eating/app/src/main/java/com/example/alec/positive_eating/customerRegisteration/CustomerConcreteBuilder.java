package com.example.alec.positive_eating.customerRegisteration;

import android.view.View;
import android.widget.EditText;

/**
 * Created by shane on 4/13/17.
 */

public class CustomerConcreteBuilder implements CustomerBuilder {

    Customer customer;

    public CustomerConcreteBuilder(){
        customer = new Customer();
    }

    @Override
    public void addUser(EditText user) {
        customer.setUserName(user.getText().toString());
    }

    @Override
    public boolean addEmail(EditText email) {
        String input = email.getText().toString();
        if(input.contains("@")){
            customer.setEmail(input);
            return true;
        }
        return false;

    }

    @Override
    public void addPassword(EditText password) {
        customer.setPassword(password.getText().toString());
    }

    @Override
    public Customer getProduct() {
        return this.customer;
    }
}
