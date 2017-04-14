package com.example.alec.positive_eating.Reservations;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Response;
import com.example.alec.positive_eating.Table;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import shaneconnect.ShaneConnect;
import shaneconnect.TableCache;
import shaneconnect.TableReservation;
import static com.example.alec.positive_eating.Singleton_ShaneConnect_Factory.getShaneConnect;
import static com.example.alec.positive_eating.Singleton_TableCache_Factory.getTableCache;
import static com.example.alec.positive_eating.Singleton_CustomerObject_Factory.getCustomer;

public class ReservationAdapater {

    private ShaneConnect connect;
    private TableCache cache;
    private int tableSize;
    private final int STATUS_FULL = 3;
    private TableReservation buffer;
    private Context cont;

    public ReservationAdapater(int tableSize, Context cont) {
        connect = getShaneConnect();
        cache = getTableCache();
        cache.updateQue();
        this.tableSize = tableSize;
        this.cont = cont;
    }

    public void findTable() {
        findTable(0);
    }

    private void findTable(final int index) {
        connect.getTables(index, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if(!response.has("none")){
                    try{
                        if(response.getInt("number_seats") == tableSize) {
                            buffer = new TableReservation(getCustomer().getUserName(),
                                    0, response.getInt("id"), 0, getCustomer().getID());
                            if (response.getInt("status")!=STATUS_FULL) {
                                getTableCache().insertReservation(buffer);
                            } else {
                                findTable(index + 1);
                            }

                        } else {
                            findTable(index + 1);
                        }
                    } catch (Exception e) {
                        Toast.makeText(cont, "Something went wrong with the JSON Object...",
                                Toast.LENGTH_LONG).show();
                    }
                } else {
                    getTableCache().insertReservation(buffer);
                }
            }
        });
    }
}