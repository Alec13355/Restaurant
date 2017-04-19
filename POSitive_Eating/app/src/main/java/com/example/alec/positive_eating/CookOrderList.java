package com.example.alec.positive_eating;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.android.volley.Response;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import shaneconnect.ShaneConnect;

import static com.example.alec.positive_eating.Singleton_ShaneConnect_Factory.getShaneConnect;
public class CookOrderList extends AppCompatActivity {
    private CookListAdapter listAdapter;
    private ExpandableListView expListView;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;
    private ShaneConnect ModelM;
    private int recursiveInc;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_order_list);
        context = getApplicationContext();
        prepareListData();
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        getAllOrders();
    }

    private void getAllOrders() {
        ModelM = getShaneConnect();
        recursiveInc = 0;
        ArrayList<String> itemsInTheOrder = new ArrayList<String>();
        ModelM.getOrders(recursiveInc, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse (JSONObject response) {
                if (response.has("none")) {
                    expListView = (ExpandableListView) findViewById(R.id.lvExp);
                    listAdapter = new CookListAdapter(context, listDataHeader, listDataChild);
                    expListView.setAdapter(listAdapter);
                } else {
                    try {
                        String compString = response.getString("componentString");
                        final String orderNum = "Order #" + response.getInt("order_id");
                        listDataHeader.add(orderNum);
                        ModelM.getFoodByID(compString, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    ArrayList<String> order  = parseResponse(response);
                                    listDataChild.put(orderNum, order);
                                    ModelM.getOrders(++recursiveInc, this);
                                } catch(Exception e) {
                                    Toast.makeText(context,
                                            "An error occurred in getFoodByID(). " +
                                                    "Please press the back button and try again.",
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    } catch (Exception e) {
                        Toast.makeText(context,
                                "An error occurred in getOrders(). " +
                                "Please press the back button and try again.",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private ArrayList<String> parseResponse(JSONObject res) {
        ArrayList<String> order = new ArrayList<String>();
        for(int i =0;res.has("NAME"+i);i++) {
            // TODO: 4/19/2017
        }
        return order;
    }
}