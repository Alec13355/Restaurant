package com.example.alec.positive_eating;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import static com.example.alec.positive_eating.CustomerOrderMenu.getOrderList;

/**
 * The CustomerSidesList class is an activity that displays the sides offered at the
 * restaurant. It uses the CustomerMenuList as an adapter to generate a scrolling list of food items.
 * The user simply taps on the item they want and it adds to their order list and returns to it.
 */
public class CustomerSidesList extends AppCompatActivity {
    /**
     * Creates an instance of the activity. Calls adapter constructor and sets a listener
     * to handle clicking on an item.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side_list);

        final String[] arr = new String[] {
            "french fries", "onion rings", "mozzarella sticks",
            "chili", "side salad", "cheese curds", "chicken fries",
            "jalapeno poppers", "macaroni and cheese", "curly fries",
            "tater tots", "waffle fries"
        };
        Integer[] imageId = {
            R.drawable.fries
        };
        CustomerMenuList adapter = new CustomerMenuList(this, arr, imageId);
        ListView listViewMenu = (ListView) findViewById(R.id.side_list);
        listViewMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int sidePosition, long id) {
                int orderPosition = getIntent().getIntExtra("ADD_SIDE", -1);
                getOrderList().get(orderPosition).setSide(arr[sidePosition]);
                finish();
            }
        });
        listViewMenu.setAdapter(adapter);
    }
}
