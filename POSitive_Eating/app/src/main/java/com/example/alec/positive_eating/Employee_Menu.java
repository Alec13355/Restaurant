package com.example.alec.positive_eating;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
*@author  http://www.androhub.com/android-expandablelistview/
 * Implementing my own data into it.
 */



public class Employee_Menu extends ActionBarActivity {
    private static ExpandableListView expandableListView;
    private static ExpandableListAdapter adapter;
    Button a;
    int counter, indexholder, groupprev, groupPos, food;
    String extrastuff;
    int table;
    ArrayList<String> thefood = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        a = (Button) findViewById(R.id.Order1);
        indexholder = 0;
        counter = 0;
        extrastuff = "";

        /**
         * Makes an expandable list view variable sets it to null then calls
         * setItem() and setListener()
         */


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

        for (int i = 1; i < 16; i++) {
            child1.add("" + i);
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

        adapter = new ExpandableListAdapter(Employee_Menu.this, header, hashMap);

        // Setting adpater over expandablelistview
        expandableListView.setAdapter(adapter);
    }

    // Setting different listeners to expandablelistview

    /**
     * Sets listeners so when you touch the expandable list parts
     */
    void setListener() {

        // This listener will show toast on group click
        expandableListView.setOnGroupClickListener(new OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView listview, View view,
                                        int group_pos, long id) {

                Toast.makeText(Employee_Menu.this,
                        "You clicked : " + adapter.getGroup(group_pos),
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // This listener will expand one group at one time
        // You can remove this listener for expanding all groups
        expandableListView
                .setOnGroupExpandListener(new OnGroupExpandListener() {

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
        expandableListView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView listview, View view,
                                        int groupPos, int childPos, long id) {
                Toast.makeText(
                        Employee_Menu.this,
                        "You clicked : " + adapter.getChild(groupPos, childPos),
                        Toast.LENGTH_SHORT).show();


                if (groupPos == 0) {
                    table = Integer.parseInt(adapter.getChild(groupPos, childPos).toString());

                } else if (groupPos == 1) {
                    if (groupprev != 1) {
                        extrastuff = "";
                        fixfood(groupprev, food);
                        thefood.add(counter, "Hamburger");
                        counter++;
                        groupprev = 1;


                        food = 0;
                    }
                    if (childPos >= 0) {
                        if (childPos == 0 && food < 26) {
                            food += childPos + 1;
                        } else if (childPos == 1 && food < 26) {
                            food += childPos + 4;
                        } else if (childPos == 2 && food < 26) {
                            food += childPos + 5;
                        } else if (childPos == 3 && food < 26) {
                            food += childPos + 10;
                        }


                    }

                } else if (groupPos == 2) {
                    if (groupprev != 2) {
                        extrastuff = "";
                        fixfood(groupprev, food);
                        thefood.add(counter, "Chicken Sandwhich");
                        counter++;
                        groupprev = 2;
                        food = 0;
                    }
                    if (childPos >= 0) {

                        if (childPos == 0 && food < 26) {
                            food += childPos + 1;
                        } else if (childPos == 1 && food < 26) {
                            food += childPos + 4;
                        } else if (childPos == 2 && food < 26) {
                            food += childPos + 5;
                        } else if (childPos == 3 && food < 26) {
                            food += childPos + 10;
                        }

                    }

                } else if (groupPos == 3) {
                    if (groupprev != 3) {
                        extrastuff = "";
                        fixfood(groupprev, food);
                        thefood.add(counter, "Fettuccine Alfredo");
                        counter++;
                        groupprev = 3;

                        food = 0;
                    }
                    if (childPos >= 0) {
                        food += childPos + 1;
                    }
                } else if (groupPos == 4) {
                    if (groupprev != 4) {
                        extrastuff = "";
                        fixfood(groupprev, food);
                        thefood.add(counter, "Salad");
                        counter++;
                        groupprev = 4;
                        food = 0;
                    }
                    if (childPos >= 0) {
                        if (childPos == 0 && food < 50) {
                            food += childPos + 1;
                        } else if (childPos == 1 && food < 50) {
                            food += childPos + 4;
                        } else if (childPos == 2 && food < 50) {
                            food += childPos + 5;
                        } else if (childPos == 3 && food < 50) {
                            food += childPos + 10;
                        } else if (childPos == 4 && food < 50) {
                            food += childPos + 20;
                        }

                    }
                }


                return false;
            }
        });
        a.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        placeorder();
                    }
                }
        );
    }

    private void placeorder() {
        //Was used to place orders. Git removed it....
        //Need to re add shane connect stuff and actually place the order.
        //TODO
    }



    private void fixfood(int groupprev, int food) {
        //TODO
        //Used by something git removed it....
        //Tranverses food and add the stuff to a text string to add it to a sting to be added
        //To a server

    }
}