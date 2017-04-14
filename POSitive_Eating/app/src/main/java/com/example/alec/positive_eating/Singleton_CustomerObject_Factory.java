package com.example.alec.positive_eating;

import com.example.alec.positive_eating.customerRegisteration.Customer;

/**
 * Created by cshin on 4/14/2017.
 */

public class Singleton_CustomerObject_Factory {
    private static Customer c = new Customer("sdrafahl", "sdrafahl@iastate.edu", "jimbob");

    public static Customer getCustomer() {
        return c;
    }
}
