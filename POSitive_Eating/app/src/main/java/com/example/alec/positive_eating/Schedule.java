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

public class Schedule extends AppCompatActivity {

    private ExpandableListAdapter listAdapter;
    private ExpandableListView expListView;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;
    private ShaneConnect ModelM;
    private int recursiveInc;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);

        context = getApplicationContext();
        prepareListData();
        Button b = (Button) findViewById(R.id.load);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expListView = (ExpandableListView) findViewById(R.id.lvExp);

                listAdapter = new ExpandableListAdapter(context, listDataHeader, listDataChild);

                // setting list adapter
                expListView.setAdapter(listAdapter);
            }
        });
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        getAllOrders();
    }

    private void getAllOrders() {
        ModelM = getShaneConnect();
        recursiveInc = 0;
        ModelM.getEmployees(recursiveInc, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse (JSONObject response){
                if (response.has("none")) {
                    return;
                } else {
                    try {
                        String desc = response.getString("first");
                        String orderNum = "Address" + response.get("address");
                        ArrayList<String> itemsInTheOrder = new ArrayList<String>();
                        itemsInTheOrder.add(desc);
                        listDataHeader.add(orderNum);
                        listDataChild.put(orderNum, itemsInTheOrder);
                        recursiveInc++;
                        ModelM.getEmployees(recursiveInc, this);
                    } catch (Exception e) {
                        Toast.makeText(context,
                                "An error occurred. Please press the back button and try again.",
                                Toast.LENGTH_LONG).show();
                        return;
                    }
                }
            }
        });
    }
}
