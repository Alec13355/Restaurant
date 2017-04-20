package com.example.alec.positive_eating.CustomerLogin;

/**
 * Created by shane on 4/20/17.
 */

public class State {

    private boolean state;

    public State(){
        state = true;
    }

    public void setState(boolean val){
        this.state=val;
    }

    public boolean getState(){
        return this.state;
    }
}
