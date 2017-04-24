package com.example.alec.positive_eating.editMenu;

import android.app.Fragment;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.example.alec.positive_eating.R;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import shaneconnect.ShaneConnect;

/**
 * Created by shane on 4/23/17.
 */

public class LayoutBuilder {

    private LinearLayout list;

    private ShaneConnect connection;

    private FragmentManager manager;

    private Context cont;

    private FragmentTransaction transaction;

    ArrayList<FrameLayout> listoffrags;


    public LayoutBuilder(LinearLayout list, ShaneConnect shane, FragmentManager manager, Context cont){
        this.list=list;
        this.connection = shane;
        this.manager = manager;
        this.cont = cont;
        this.transaction = manager.beginTransaction();
        this.listoffrags = new ArrayList<FrameLayout>();
    }


    public void loadList(){
        loadList(0);
    }


    private void loadList(final int index){

        connection.getFoodByIndex(index, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if(!response.has("none")){
                    Food f = new Food(response);
                    FoodGUI frag = FoodGUI.newInstance(f,cont);
                    transaction.add(R.id.list, frag);
                    loadList(index+1);
                }else{
                    transaction.commit();
                }
            }
        });
    }



}
