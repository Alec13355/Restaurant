package com.example.shane.shaneconnect;

import com.android.volley.Response;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Created by shane on 3/7/17.
 */

public class TableCache {

    private ArrayList<PriorityQueue<TableReservation>> list;

    private ShaneConnect connect;

    public TableCache(ShaneConnect connect){
        list = new ArrayList<PriorityQueue<TableReservation>>();
        this.connect=connect;
    }

    public void insertReservation(TableReservation res){
        if(list.get(res.getID()) == null){
            list.add(res.getID(),new PriorityQueue<TableReservation>());
        }
        list.get(res.getID()).add(res);
        connect.placeReservation(res.getDesc(),res.get_table_ID(),res.getStatus(),new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {

            }
        });

    }






}
