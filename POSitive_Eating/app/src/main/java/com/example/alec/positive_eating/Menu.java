package com.example.alec.positive_eating;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/*
*Found on http://www.androhub.com/android-expandablelistview/
 * Implementing my own data into it.
 */
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

public class Menu extends ActionBarActivity {
    private static ExpandableListView expandableListView;
    private static ExpandableListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        expandableListView = (ExpandableListView) findViewById(R.id.simple_expandable_listview);

        // Setting group indicator null for custom indicator
        expandableListView.setGroupIndicator(null);

        setItems();
        setListener();

    }

    // Setting headers and childs to expandable listview
    void setItems() {

        // Array list for header
        ArrayList<String> header = new ArrayList<String>();

        // Array list for child items
        List<String> child1 = new ArrayList<String>();
        List<String> child2 = new ArrayList<String>();
        List<String> child3 = new ArrayList<String>();
        List<String> child4 = new ArrayList<String>();

        // Hash map for both header and child
        HashMap<String, List<String>> hashMap = new HashMap<String, List<String>>();

        // Adding headers to list
        for (int i = 1; i < 5; i++) {
            header.add("Group " + i);

        }
        // Adding child data
        for (int i = 1; i < 5; i++) {
            child1.add("Group 1  - " + " : Child" + i);

        }
        // Adding child data
        for (int i = 1; i < 5; i++) {
            child2.add("Group 2  - " + " : Child" + i);

        }
        // Adding child data
        for (int i = 1; i < 6; i++) {
            child3.add("Group 3  - " + " : Child" + i);

        }
        // Adding child data
        for (int i = 1; i < 7; i++) {
            child4.add("Group 4  - " + " : Child" + i);

        }

        // Adding header and childs to hash map
        hashMap.put(header.get(0), child1);
        hashMap.put(header.get(1), child2);
        hashMap.put(header.get(2), child3);
        hashMap.put(header.get(3), child4);

        adapter = new ExpandableListAdapter(Menu.this, header, hashMap);

        // Setting adpater over expandablelistview
        expandableListView.setAdapter(adapter);
    }

    // Setting different listeners to expandablelistview
    void setListener() {

        // This listener will show toast on group click
        expandableListView.setOnGroupClickListener(new OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView listview, View view,
                                        int group_pos, long id) {

                Toast.makeText(Menu.this,
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
        expandableListView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView listview, View view,
                                        int groupPos, int childPos, long id) {
                Toast.makeText(
                        Menu.this,
                        "You clicked : " + adapter.getChild(groupPos, childPos),
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}