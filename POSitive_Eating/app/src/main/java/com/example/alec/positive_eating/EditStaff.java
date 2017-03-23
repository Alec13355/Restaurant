/**
 * @author Alec
 */
package com.example.alec.positive_eating;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.shane.shaneconnect.ShaneConnect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.alec.positive_eating.Singleton_ShaneConnect_Factory.getShaneConnect;

/**
 * This class will be used to manage and get current workers.
 */
public class EditStaff extends ActionBarActivity {
    private static ExpandableListView expandableListView;
    private static ExpandableListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_staff);
        Button add = (Button) findViewById(R.id.Add_Employee);
        //Initilizes the buttons.

        add.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view){
                        Intent myIntent = new Intent(EditStaff.this, add_employee.class); /** Class name here */
                        EditStaff.this.startActivity(myIntent);
                    }
                }
        );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        expandableListView = (ExpandableListView) findViewById(R.id.simple_expandable_listview);

        // Setting group indicator null for custom indicator
        expandableListView.setGroupIndicator(null);

        setItems();
        setListener();
    }
    // Setting headers and childs to expandable listview

    /**
     * Set tiems will make string array lists.
     * Then it is currently hard coded until we can iterate through the server to set items
     * and the tables.
     * The hashMap is also set with each header and children.
     */
    void setItems() {
        ShaneConnect vista = getShaneConnect();


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

        adapter = new ExpandableListAdapter(EditStaff.this, header, hashMap);

        // Setting adpater over expandablelistview
        expandableListView.setAdapter(adapter);
    }

    // Setting different listeners to expandablelistview

    /**
     * Sets listeners so when you touch the expandable list parts
     */
    void setListener() {

        // This listener will show toast on group click
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView listview, View view,
                                        int group_pos, long id) {

                Toast.makeText(EditStaff.this,
                        "You clicked : " + adapter.getGroup(group_pos),
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // This listener will expand one group at one time
        // You can remove this listener for expanding all groups
        expandableListView
                .setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

                    // Default position
                    int previousGroup = -1;

                    @Override
                    public void onGroupExpand(int groupPosition) {
                        if (groupPosition != previousGroup)

                            // Collapse the expanded group
                            expandableListView.collapseGroup(previousGroup);
                        previousGroup = groupPosition;
                    }

                });

        // This listener will show toast on child click
        /**
         * This is the toast to show people what they touched.
         */
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView listview, View view,
                                        int groupPos, int childPos, long id) {
                Toast.makeText(
                        EditStaff.this,
                        "You clicked : " + adapter.getChild(groupPos, childPos),
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

}
