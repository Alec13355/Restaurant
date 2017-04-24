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



    private ShaneConnect connection;

    private FragmentManager manager;

    private Context cont;

    private FragmentTransaction transaction;

    ArrayList<Fragment> listoffrags;

    ArrayList<LinearLayout> listofLayouts;

    private static int count = 0;


    public LayoutBuilder(ArrayList<LinearLayout> list,ShaneConnect shane, FragmentManager manager, Context cont){
        this.listofLayouts = list;
        this.connection = shane;
        this.manager = manager;
        this.cont = cont;
        this.transaction = manager.beginTransaction();
        this.listoffrags = new ArrayList<Fragment>();
    }





    public void loadList(final int index){

        connection.getFoodByIndex(index, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if(!response.has("none") && count<=8){
                    Food f = new Food(response);
                    FoodGUI frag = FoodGUI.newInstance(f,cont);
                    selectFragLocation(index,frag);
                    listoffrags.add(frag);
                    loadList(index+1);
                    count++;

                }else{
                        count=0;
                        transaction.commit();



                }
            }
        });
    }

    public void clear(){
        for(int x=0;x<listofLayouts.size();x++){
            listofLayouts.get(x).removeAllViews();

        }
        for(int x=0;x<listoffrags.size();x++){
            transaction.remove(listoffrags.get(x));
            listoffrags.get(x).onDestroy();
        }
    }

    private void selectFragLocation(int index,FoodGUI g){
        switch(index % 8){
            case 0:
                transaction.add(R.id.list, g);
                break;
            case 1:
                transaction.add(R.id.list2, g);
                break;
            case 2:
                transaction.add(R.id.list3, g);
                break;
            case 3:
                transaction.add(R.id.list4, g);
                break;
            case 4:
                transaction.add(R.id.list5, g);
                break;
            case 5:
                transaction.add(R.id.list6, g);
                break;
            case 6:
                transaction.add(R.id.list7, g);
                break;
            case 7:
                transaction.add(R.id.list8, g);
                break;

        }
    }



}
