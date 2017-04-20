package com.example.alec.positive_eating.CustomerLogin;

import com.example.alec.positive_eating.customerRegisteration.Customer;

import java.util.ArrayList;

/**
 * Created by shane on 4/20/17.
 */

public class CustomerBuilder {

    private Customer c;

    ArrayList<SyntaxChecker> checkers;

    public CustomerBuilder(){
        c = new Customer();
        checkers = new ArrayList<SyntaxChecker>();
        checkers.add(new AtChecker());
        checkers.add(new SizeChecker());
    }

    public boolean addEmail(String email){

            for(int x=0;x<checkers.size();x++){
                if(!checkers.get(x).test(email)){
                    return false;
                }
            }
        c.setEmail(email);
        return true;
    }

    public boolean addPassword(String pass){
        c.setPassword(pass);
        return true;
    }

    public Customer getCustomer(){
        return this.c;
    }



}
