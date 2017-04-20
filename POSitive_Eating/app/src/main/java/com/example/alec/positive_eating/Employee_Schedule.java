/**
 * @author Alec
 */
package com.example.alec.positive_eating;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import shaneconnect.ShaneConnect;

import static com.example.alec.positive_eating.Singleton_Employee_List.getListInstance;
import static com.example.alec.positive_eating.Singleton_ShaneConnect_Factory.getShaneConnect;


public class Employee_Schedule extends AppCompatActivity {
    public static ExpandableListView expandableListView;
    public static ExpandableListAdapter adapter;
    public List<employee> employeeList = new ArrayList<>();

    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        expandableListView = (ExpandableListView) findViewById(R.id.simple_expandable_listview);

        // Setting group indicator null for custom indicator
        expandableListView.setGroupIndicator(null);

        ShaneConnect alpha = getShaneConnect();
        employeeList = getListInstance().getEList();





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
        for(int i =0;i<employeeList.size();i++){
            header.add(employeeList.get(i).getFirst()+" "+employeeList.get(i).getLast()+" "+employeeList.get(i).getAvailability());
        }

        // Adding child data


        // Adding header and childs to hash map
        hashMap.put(header.get(0), child1);
        hashMap.put(header.get(1), child2);
        hashMap.put(header.get(2), child3);
        hashMap.put(header.get(3), child4);

        adapter = new ExpandableListAdapter(Employee_Schedule.this, header, hashMap);

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

                Toast.makeText(Employee_Schedule.this,
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
                        Employee_Schedule.this,
                        "You clicked : " + adapter.getChild(groupPos, childPos),
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}