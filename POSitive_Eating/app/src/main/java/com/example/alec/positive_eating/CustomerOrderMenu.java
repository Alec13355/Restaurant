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
import shaneconnect.ShaneConnect;
import static com.example.alec.positive_eating.Singleton_CustomerObject_Factory.getCustomer;
import static com.example.alec.positive_eating.Singleton_ShaneConnect_Factory.getShaneConnect;
import com.example.alec.positive_eating.customerRegisteration.Customer;
/**
 * @author Christian Shinkle
 * The CustomerOrderMenu class is used to create orders and sumbit them to the server. It includes
 * a ViewList for a scrollable list of the customer's order, as well as two buttons, one for adding
 * a new entree to the order and one for confirm the order to be sent to the server.
 */
public class CustomerOrderMenu extends AppCompatActivity implements View.OnClickListener {

    private static ArrayList<CustomerOrderItem> orderList;
    private static Integer[] imageId;

    /**
     * Creates the activity. Sets listeners for buttons and ListViews.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_menu);

        orderList = new ArrayList<CustomerOrderItem>();
        imageId = new Integer[]{
            R.drawable.hamburger,
            R.drawable.fries
        };
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

    /**
     * Every time activity is resumed, creates new adapter for ListView and updates list of items
     * ordered.
     */
    @Override
    public void onResume() {
        super.onResume();
        String[] tmp = new String[orderList.size()];
        for(int i =0;i<tmp.length;i++) {
            tmp[i] = orderList.get(i).toString();
        }
        CustomerOrderList adapter = new CustomerOrderList(this, tmp, imageId);
        ListView orderListView = (ListView) findViewById(R.id.order_list);
        orderListView.setAdapter(adapter);
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

    /**
     * Returns list of orders.
     * @return
     */
    public static ArrayList<CustomerOrderItem> getOrderList() {
        return orderList;
    }

    private void addItem() {
        Intent i = new Intent(this, CustomerEntreeSideList.class);
        i.putExtra("NEW_ORDER", true);
        startActivity(i);
    }

    private void confirmOrder(){
        ShaneConnect connect = getShaneConnect();
        ArrayList<String> compStringList = new ArrayList<String>();
        ArrayList<String> options = new ArrayList<String>();
        String desc = "Online Order for "+getCustomer().getUserName();
        for(CustomerOrderItem oi : orderList) {
            compStringList.add(oi.getEntreeName());
            options.add("entree place holder");
            if(oi.getSideName() != null) {
                compStringList.add(oi.getSideName());
                options.add("sides place holder");
            }
        }
        connect.placeOrder(desc, compStringList, options, 10, "To Go", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(getApplicationContext(), "The order was successfully placed!",
                        Toast.LENGTH_LONG).show();
                finish();
            }
        });

        /*
        ArrayList<String> a = new ArrayList<String>();
        String b = "mozzarella Sticks";
        String c = "Mozzarella Sticks";
        connect.addFood(b, 6, c, 1, a, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(getApplicationContext(),
                        response.toString(), Toast.LENGTH_LONG).show();
            }
        });
        */
    }

    private void addSide(int position) {
        Intent i = new Intent(this, CustomerEntreeSideList.class);
        i.putExtra("ADD_SIDE", position);
        startActivity(i);
    }
}
