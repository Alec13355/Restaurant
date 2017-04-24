package com.example.alec.positive_eating;

import com.example.alec.positive_eating.customerRegisteration.Customer;

/**
 * Created by cshin on 4/14/2017.
 */

public class Singleton_CustomerObject_Factory {
    private static Customer c = null;

    public static Customer getCustomer() {
        return c;
    }

    public void setCustomer(Customer set) {
        c = new Customer(set.getUserName(), set.getEmail(), set.getPassword());
    }

    public void clearCustomer() {
        c = null;
    }
}
