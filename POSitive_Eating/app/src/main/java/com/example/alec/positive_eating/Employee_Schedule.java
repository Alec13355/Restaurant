/**
 * @author Alec
 */
package com.example.alec.positive_eating;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

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
            header.add(employeeList.get(i).getFirst()+" "+employeeList.get(i).getLast()+" "+employeeList.get(i).getSchedule());
        }

        // Adding child data


        // Adding header and childs to hash map
        for(int i =0; i<header.size();i++){
            hashMap.put(header.get(i),child1);
        }


        adapter = new ExpandableListAdapter(Employee_Schedule.this, header, hashMap);

        // Setting adpater over expandablelistview
        expandableListView.setAdapter(adapter);
    }

    // Setting different listeners to expandablelistview


}