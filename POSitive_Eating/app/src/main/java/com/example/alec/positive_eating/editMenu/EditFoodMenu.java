package com.example.alec.positive_eating.editMenu;


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

import shaneconnect.ShaneConnect;

import static com.example.alec.positive_eating.Singleton_ShaneConnect_Factory.getShaneConnect;

public class EditFoodMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_food_menu);



        LinearLayout layout = (LinearLayout) findViewById(R.id.list);
        android.app.FragmentManager manager = getFragmentManager();

        ShaneConnect shane = getShaneConnect();

        LayoutBuilder builder = new LayoutBuilder(layout,shane,manager,this);

        builder.loadList();
        Button b = (Button) findViewById(R.id.button4);

        final Intent i = new Intent(this,AddFoodMenu.class);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);

            }
        });

    }

}

