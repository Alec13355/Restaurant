package com.example.alec.positive_eating;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.android.volley.Response;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Scanner;
import shaneconnect.ShaneConnect;
import static com.example.alec.positive_eating.Singleton_Current_Employee.getEmployee;
import static com.example.alec.positive_eating.Singleton_CustomerObject_Factory.getCustomer;
import static com.example.alec.positive_eating.Singleton_OrderList.*;
import static com.example.alec.positive_eating.Singleton_ShaneConnect_Factory.getShaneConnect;
/**
 * @author Christian Shinkle
 * The EmployeeMenu class is used to create orders and sumbit them to the server. It includes
 * a ViewList for a scrollable list of the customer's order, as well as two buttons, one for adding
 * a new entree to the order and one for confirm the order to be sent to the server.
 */
public class Employee_Menu extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<CustomerOrderItem> orderList;
    private String tableName;
    /**
     * Creates the activity. Sets listeners for buttons and ListViews.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_menu);
        Bundle bundle = getIntent().getExtras();
        tableName = "TO GO";
        if(bundle != null) {
            tableName = bundle.getString("tableNum");
            String temp = ("Table Number " + tableName);
            Toast.makeText(Employee_Menu.this, temp, Toast.LENGTH_SHORT).show();
        }
        initOrderList();
        orderList = getOrderList();
        Button confirmOrderBut = (Button) findViewById(R.id.confirm_order);
        Button addItemBut = (Button) findViewById(R.id.add_item);
        confirmOrderBut.setOnClickListener(this);
        addItemBut.setOnClickListener(this);
        ListView list = (ListView) findViewById(R.id.order_list);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                addSide(position);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(Employee_Menu.this, tableMap.class);
        this.finishActivity(0);
        Employee_Menu.this.startActivity(myIntent);
    }

    /**
     * Every time activity is resumed, creates new adapter for ListView and updates list of items
     * ordered.
     */
    @Override
    public void onResume() {
        super.onResume();
        orderList = getOrderList();
        String[] tmp = new String[orderList.size()];
        for(int i =0;i<tmp.length;i++) {
            tmp[i] = orderList.get(i).toString();
        }
        CustomerOrderList adapter = new CustomerOrderList(this, tmp, null);
        ListView orderListView = (ListView) findViewById(R.id.order_list);
        orderListView.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        clearOrderList();
    }

    /**
     * Determines which method should be called for each respective button.
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirm_order:
                confirmOrder(); break;
            case R.id.add_item:
                addItem(); break;
            default: break;
        }
    }

    private void addItem() {
        Intent i = new Intent(this, CustomerEntreeSideList.class);
        i.putExtra("NEW_ORDER", true);
        startActivity(i);
    }

    private void confirmOrder() {
        ShaneConnect connect = getShaneConnect();
        ArrayList<String> compStringList = new ArrayList<String>();
        ArrayList<String> options = new ArrayList<String>();
        String desc = "Order for Table " + tableName;
        for (CustomerOrderItem oi : orderList) {
            compStringList.add(oi.getEntreeName());
            if (!oi.getOptionsEntree().equals("")) {
                {
                    Scanner s = new Scanner(oi.getOptionsEntree());
                    while (s.hasNextLine()) {
                        options.add(s.nextLine());
                    }
                    s.close();
                }
                if (oi.getSideName() != null) {
                    compStringList.add(oi.getSideName());
                    if (!oi.getOptionsSide().equals("")) {
                        {
                            Scanner s = new Scanner(oi.getOptionsSide());
                            while (s.hasNextLine()) {
                                options.add(s.nextLine());
                            }
                            s.close();
                        }
                    }
                }
                connect.placeOrder(desc, compStringList, options, 10, "Table "+tableName, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getApplicationContext(), "The order was successfully placed!",
                                Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
            }
        }
    }

    private void addSide(int position) {
        Intent i = new Intent(this, CustomerEntreeSideList.class);
        i.putExtra("ADD_SIDE", position);
        startActivity(i);
    }
}