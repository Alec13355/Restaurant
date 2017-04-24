package com.example.alec.positive_eating.editMenu.addFood;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.alec.positive_eating.CustomerLogin.ShaneConnectCustomerLoginAdapter;
import com.example.alec.positive_eating.R;

import shaneconnect.ShaneConnect;

import static com.example.alec.positive_eating.Singleton_ShaneConnect_Factory.getShaneConnect;

public class AddFoodMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final Context context = this;

        /*Desert*/
        final CheckBox check = (CheckBox) findViewById(R.id.checkBox4);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox check1 = (CheckBox) findViewById(R.id.checkBox2);
                CheckBox check2 = (CheckBox) findViewById(R.id.checkBox);
                CheckBox check3 = (CheckBox) findViewById(R.id.checkBox3);
                check1.setChecked(false);
                check2.setChecked(false);
                check3.setChecked(false);
            }
        });

        /*Main Entre*/
        CheckBox check1 = (CheckBox) findViewById(R.id.checkBox2);
        check1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox check1 = (CheckBox) findViewById(R.id.checkBox4);
                CheckBox check2 = (CheckBox) findViewById(R.id.checkBox);
                CheckBox check3 = (CheckBox) findViewById(R.id.checkBox3);
                check1.setChecked(false);
                check2.setChecked(false);
                check3.setChecked(false);
            }
        });

        /*Sides*/
        CheckBox check2 = (CheckBox) findViewById(R.id.checkBox);
        check2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox check1 = (CheckBox) findViewById(R.id.checkBox2);
                CheckBox check2 = (CheckBox) findViewById(R.id.checkBox4);
                CheckBox check3 = (CheckBox) findViewById(R.id.checkBox3);
                check1.setChecked(false);
                check2.setChecked(false);
                check3.setChecked(false);
            }
        });

        /*Drinks*/
        CheckBox check3 = (CheckBox) findViewById(R.id.checkBox3);
        check3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox check1 = (CheckBox) findViewById(R.id.checkBox2);
                CheckBox check2 = (CheckBox) findViewById(R.id.checkBox);
                CheckBox check3 = (CheckBox) findViewById(R.id.checkBox4);
                check1.setChecked(false);
                check2.setChecked(false);
                check3.setChecked(false);
            }
        });


        /*Submit*/
        Button fab = (Button) findViewById(R.id.button5);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText t = (EditText)findViewById(R.id.editText3);
                EditText price = (EditText) findViewById(R.id.editText4);
                EditText quain = (EditText) findViewById(R.id.editText5);
                EditText t2 = (EditText) findViewById(R.id.editText6);
                EditText t3 = (EditText) findViewById(R.id.editText2);
                CheckBox ch1 = (CheckBox) findViewById(R.id.checkBox4);
                CheckBox ch2 = (CheckBox) findViewById(R.id.checkBox2);
                CheckBox ch3 = (CheckBox) findViewById(R.id.checkBox);
                CheckBox ch4 = (CheckBox) findViewById(R.id.checkBox3);

                TypeOfFood type = new TypeOfFood(ch1,ch2,ch3,ch4);
                FoodBuilder builder = new FoodBuilder(t,price,quain,t2,t3,type);
                ShaneConnectFoodAdatpter con = new ShaneConnectFoodAdatpter(getShaneConnect(),context);
                con.submitFood(builder.buildFood());

            }
        });
    }

}
