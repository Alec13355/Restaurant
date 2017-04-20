package com.example.alec.positive_eating.CustomerLogin;

/**
 * Created by shane on 4/20/17.
 */

public class AtChecker implements  EmailSyntaxChecker {
    @Override
    public boolean test(String input) {
        if(input.contains("@")){
            return true;
        }
        return false;
    }
}
