package com.example.alec.positive_eating.editMenu;

import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.LinearLayout;

import com.android.volley.Response;

import org.json.JSONObject;

import java.util.HashMap;

import shaneconnect.ShaneConnect;

/**
 * Created by shane on 4/23/17.
 */

public class LayoutBuilder {

    private LinearLayout list;

    private ShaneConnect connection;

    private FragmentManager manager;

    private HashMap<Integer,Food> hash;

    public LayoutBuilder(LinearLayout list, ShaneConnect shane,FragmentManager manager){
        this.list=list;
        this.connection = shane;
        this.manager = manager;
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
                    hash.put(f.getId(),f);
                    FoodGUI frag = FoodGUI.newInstance(f);
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.add(list.getId(),frag,"fragment" + f.getId());
                    transaction.commit();
                    loadList(index+1);
                }
            }
        });
    }



}
