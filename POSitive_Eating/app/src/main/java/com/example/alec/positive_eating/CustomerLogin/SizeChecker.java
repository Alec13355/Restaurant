package com.example.alec.positive_eating.CustomerLogin;

/**
 * Created by shane on 4/20/17.
 */

public class SizeChecker implements EmailSyntaxChecker {

    @Override
    public boolean test(String input) {
        for(int x=0;x<input.length();x++){
            if(input.charAt(x)=='@'){
                if(x>0){
                    if(input.length()!=(x+1)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
