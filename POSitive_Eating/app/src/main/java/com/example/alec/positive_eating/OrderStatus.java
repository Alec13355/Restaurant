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

public class OrderStatus extends AppCompatActivity {

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

<<<<<<< HEAD
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        getAllOrders();
=======
    // Setting headers and childs to expandable listview
    void setItems() {

        // Array list for header
        ArrayList<String> header = new ArrayList<String>();

        // Array list for child items
        List<String> child1 = new ArrayList<String>();
        List<String> child2 = new ArrayList<String>();
        List<String> child3 = new ArrayList<String>();
        List<String> child4 = new ArrayList<String>();
        List<String> child5 = new ArrayList<String>();

        // Hash map for both header and child
        HashMap<String, List<String>> hashMap = new HashMap<String, List<String>>();

        // Adding headers to list
        header.add("Table #");
        header.add("Hamburger");
        header.add("Chicken Sandwhich");
        header.add("Fettuccine Alfredo");
        header.add("Salad");

        for(int i =1;i<16;i++) {
            child1.add(""+i);
        }
        // Adding child data

        child2.add("Cheese");
        child2.add("Tomato");
        child2.add("Onion");
        child2.add("Pickles");


        // Adding child data
        child3.add("Cheese");
        child3.add("Tomato");
        child3.add("Onion");
        child3.add("Pickles");
        // Adding child data
        child4.add("Extra Cheese");
        // Adding child data
        child5.add("Ranch");
        child5.add("Honey Mustard");
        child5.add("Cesar");
        child5.add("French");
        child5.add("Plain");

        // Adding header and childs to hash map
        hashMap.put(header.get(0), child1);
        hashMap.put(header.get(1), child2);
        hashMap.put(header.get(2), child3);
        hashMap.put(header.get(3), child4);
        hashMap.put(header.get(4), child5);

        adapter = new ExpandableListAdapter(OrderStatus.this, header, hashMap);

        // Setting adpater over expandablelistview
        expandableListView.setAdapter(adapter);
>>>>>>> f1c2d10f983c043c4842a1f8f1beac438e0f41a7
    }

    private void getAllOrders() {
        ModelM = getShaneConnect();
        recursiveInc = 0;
        ModelM.getOrders(recursiveInc, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse (JSONObject response){
                if (response.has("none")) {
                    return;
                } else {
                    try {
                        String desc = response.getString("desc");
                        String orderNum = "Order #" + response.getInt("order_id");
                        ArrayList<String> itemsInTheOrder = new ArrayList<String>();
                        itemsInTheOrder.add(desc);
                        listDataHeader.add(orderNum);
                        listDataChild.put(orderNum, itemsInTheOrder);
                        recursiveInc++;
                        ModelM.getOrders(recursiveInc, this);
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
