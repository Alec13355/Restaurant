package com.example.alec.positive_eating.editMenu;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.alec.positive_eating.R;
import com.example.alec.positive_eating.editMenu.addFood.AddFoodMenu;

import java.util.ArrayList;

import shaneconnect.ShaneConnect;

import static com.example.alec.positive_eating.Singleton_ShaneConnect_Factory.getShaneConnect;



public class EditFoodMenu extends AppCompatActivity {

    private static int starting = 0;

    private LayoutBuilder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_food_menu);



        android.app.FragmentManager manager = getFragmentManager();

        ShaneConnect shane = getShaneConnect();

        builder = new LayoutBuilder(getLayouts(),shane,manager,this);

        Button prev = (Button) findViewById(R.id.prev);

        final Context cont = this;
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                starting--;
                if(starting<0){
                    starting=0;
                }
                builder.clear();
                ShaneConnect shane = getShaneConnect();
                android.app.FragmentManager manager = getFragmentManager();
                builder = new LayoutBuilder(getLayouts(),shane,manager,cont);
                builder.loadList(starting);

            }
        });

        Button next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                starting++;
                builder.clear();

                builder.clear();
                ShaneConnect shane = getShaneConnect();
                android.app.FragmentManager manager = getFragmentManager();
                builder = new LayoutBuilder(getLayouts(),shane,manager,cont);
                builder.loadList(starting);
            }
        });

        builder.loadList(starting);
        Button b = (Button) findViewById(R.id.button4);

        final Intent i = new Intent(this,AddFoodMenu.class);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);

            }
        });

    }


    private ArrayList<LinearLayout> getLayouts(){
        ArrayList<LinearLayout> list = new ArrayList<LinearLayout>();

        LinearLayout t = (LinearLayout) findViewById(R.id.list);
        list.add(t);

        t = (LinearLayout) findViewById(R.id.list2);
        list.add(t);

        t = (LinearLayout) findViewById(R.id.list3);
        list.add(t);

        t = (LinearLayout) findViewById(R.id.list4);
        list.add(t);

        t = (LinearLayout) findViewById(R.id.list5);
        list.add(t);

        t = (LinearLayout) findViewById(R.id.list6);
        list.add(t);

        t = (LinearLayout) findViewById(R.id.list7);
        list.add(t);

        t = (LinearLayout) findViewById(R.id.list8);
        list.add(t);

        return list;
    }

}

