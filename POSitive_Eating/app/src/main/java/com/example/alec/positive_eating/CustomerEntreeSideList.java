package com.example.alec.positive_eating;

import android.content.Intent;
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
 * The CustomerEntreeSideList class is an activity that displays the main entrees offered at the
 * restraunt. It uses the CustomerMenuList as an adapter to generate a scrolling list of food items.
 * The user simply taps on the item they want and it adds to their order list and returns to it.
 */
public class CustomerEntreeSideList extends AppCompatActivity {
    private ArrayList<String> menuList;
    private int recursiveInc;
    private ShaneConnect connect;
    private int foodTypeID;
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
        checkIntentExtras();
        menuList = new ArrayList<String>();
        recursiveInc = 0;
        connect = getShaneConnect();
        connect.getFoodByIndex(recursiveInc, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.has("none")) {
                        setupActivity(menuList.toArray(new String[menuList.size()]));
                    } else {
                        if(checkFoodType(response.getString("name")))
                            menuList.add(response.getString("desc"));
                        connect.getFoodByIndex(++recursiveInc, this);
                    }
                } catch(Exception e) {
                    Toast.makeText(getApplicationContext(),
                            "An error occurred. Please try again.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean checkFoodType(String food) {
        switch(foodTypeID) {
            case 0:
                return Character.isUpperCase(food.charAt(0));
            case 1:
                return Character.isLowerCase(food.charAt(0));
        }
        return false;
    }

    private void checkIntentExtras() {
        Intent i = getIntent();
        if(i.hasExtra("NEW_ORDER")) {
            foodTypeID = 0;
        } else if(i.hasExtra("ADD_SIDE")) {
            foodTypeID = 1;
        } else {
            Toast.makeText(getApplicationContext(),
                    "There wasn't a correct intent. Returning to previous activity...",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void setupActivity(final String[] arr) {
        Integer[] imageId = new Integer[1];
        if(foodTypeID==0) {
            imageId[0] = R.drawable.hamburger;
        } else if(foodTypeID==1) {
            imageId[0] = R.drawable.fries;
        }
        CustomerMenuList adapter = new CustomerMenuList(this, arr, imageId);
        ListView listViewMenu = (ListView) findViewById(R.id.entree_list);
        /*
        if(foodTypeID==0)
            listViewMenu = (ListView) findViewById(R.id.entree_list);
        else if(foodTypeID==1)
            listViewMenu = (ListView) findViewById(R.id.side_list);
        */
        listViewMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(foodTypeID==0) {
                    getOrderList().add(new CustomerOrderItem(arr[position], null));
                } else if(foodTypeID==1) {
                    getOrderList().get(getIntent().
                            getIntExtra("ADD_SIDE", -1)).setSide(arr[position]);
                }
                finish();
            }
        });
        listViewMenu.setAdapter(adapter);
    }
}