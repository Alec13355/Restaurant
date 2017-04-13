package com.example.alec.positive_eating.payrole;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by shane on 4/11/17.
 */

public class ConcreteListViewBuilder extends Builder {
    public ConcreteListViewBuilder(LinearLayout d, Context con) {
        super(d,con);
    }


    @Override
    public void buildPart(Employee_Payment s) {
        TextView text = new TextView(this.con);
        text.setText("Employee " + s.getUserName() + ":" + s.getAmountDue());
        display.addView(text);
    }

    public void clear(){
        display.removeAllViews();
    }

}
