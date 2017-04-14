package com.example.alec.positive_eating.customerRegisteration;

import android.view.View;
import android.widget.EditText;

/**
 * Created by shane on 4/13/17.
 */

public interface CustomerBuilder {

    public void addUser(EditText user);

    public void addEmail(EditText email);

    public void addPassword(EditText password);

    public Customer getProduct();

}
