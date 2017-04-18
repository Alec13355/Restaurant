package com.example.alec.positive_eating.payrole;

import android.content.Context;
import android.widget.LinearLayout;

/**
 * Created by shane on 4/11/17.
 */

public abstract class Builder1 {

    protected LinearLayout display;

    protected Context con;

    public Builder1(LinearLayout d, Context con){
        display = d;
        this.con = con;
    }


    public abstract void buildPart(Employee_Payment s);

}
