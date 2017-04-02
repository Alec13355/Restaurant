package com.example.alec.positive_eating;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.AdapterView;
import static com.example.alec.positive_eating.CustomerOrderMenu.getOrderList;

/**
 * @author Christian Shinkle
 * The CustomerEntreeList class is an activity that displays the main entrees offered at the
 * restraunt. It uses the CustomerMenuList as an adapter to generate a scrolling list of food items.
 * The user simply taps on the item they want and it adds to their order list and returns to it.
 */
public class CustomerEntreeList extends AppCompatActivity{
    /**
     * Creates an instance of the activity, calls adapter constructor, and sets a listener
     * to handle clicking on an item.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entree);
        final String[] arr = new String[] {
          "Hamburger", "Reuben", "Bacon Hamburger", "CheeseBurger", "Bacon CheeseBurger",
          "Philly Cheese Steak", "Taco Salad", "Chef Salad", "Chicken Bacon Melt",
          "Chicken Strips",
        };
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