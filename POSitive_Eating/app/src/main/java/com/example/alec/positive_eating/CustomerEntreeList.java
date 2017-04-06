package com.example.alec.positive_eating;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.Toast;

import com.android.volley.Response;

import org.json.JSONObject;

import java.util.ArrayList;
import shaneconnect.ShaneConnect;
import static com.example.alec.positive_eating.CustomerOrderMenu.getOrderList;
import static com.example.alec.positive_eating.Singleton_ShaneConnect_Factory.getShaneConnect;

/**
 * @author Christian Shinkle
 * The CustomerEntreeList class is an activity that displays the main entrees offered at the
 * restraunt. It uses the CustomerMenuList as an adapter to generate a scrolling list of food items.
 * The user simply taps on the item they want and it adds to their order list and returns to it.
 */
public class CustomerEntreeList extends AppCompatActivity {
    private ArrayList<String> entreeList;
    private int recursiveInc;
    private ShaneConnect connect;
    /**
     * Creates an instance of the activity, calls adapter constructor, and sets a listener
     * to handle clicking on an item.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entree);
        /*
        final String[] arr = new String[] {
          "Hamburger", "Reuben", "Bacon Hamburger", "CheeseBurger", "Bacon CheeseBurger",
          "Philly Cheese Steak", "Taco Salad", "Chef Salad", "Chicken Bacon Melt",
          "Chicken Strips",
        };
        */
        entreeList = new ArrayList<String>();
        recursiveInc = 0;
        connect = getShaneConnect();
        connect.getFoodByIndex(recursiveInc, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if(response.has("none")) {
                    setupActivity(entreeList.toArray(new String[entreeList.size()]));
                } else {
                    try {
                        String nameOfFood = response.getString("name");
                        if(Character.isUpperCase(nameOfFood.charAt(0))) {
                            entreeList.add(nameOfFood);
                        }
                        connect.getFoodByIndex(++recursiveInc, this);
                    } catch(Exception e) {
                        Toast.makeText(getApplicationContext(),
                                "An error occurred. Please try again.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private void setupActivity(final String[] arr) {
        Integer[] imageId = {
                R.drawable.hamburger
        };
        CustomerMenuList adapter = new CustomerMenuList(this, arr, imageId);
        ListView listViewMenu = (ListView) findViewById(R.id.entree_list);
        listViewMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getOrderList().add(new CustomerOrderItem(arr[position], null));
                finish();
            }
        });
        listViewMenu.setAdapter(adapter);
    }
}