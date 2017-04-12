package com.example.alec.positive_eating.payrole;

import android.content.Context;
import android.widget.ListView;
import android.widget.ScrollView;

import java.util.List;

/**
 * Created by shane on 4/11/17.
 */

public abstract class Builder {

   protected ScrollView display;

    protected Context con;

    public Builder(ScrollView d, Context con){
        display = d;
        this.con = con;
    }


    public abstract void buildPart(Employee_Payment s);

}
