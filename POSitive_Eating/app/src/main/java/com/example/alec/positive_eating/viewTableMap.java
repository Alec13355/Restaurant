package com.example.alec.positive_eating;

/*
@author Ethan Wieczorek
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.example.alec.positive_eating.Singleton_ShaneConnect_Factory.getShaneConnect;

/**
 * This class makes a basic view of the table map after pulling all of the tables from the database.
 * @author Ethan
 */
public class  viewTableMap extends Activity {
    private List<Table> allTheTables = new ArrayList<>();
    private ViewGroup mRootLayout;
    private int index;
    private List<employee> employeeList;
    /**
     * onCreate first updates based on the
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        employeeList = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_table_map);
        mRootLayout = (RelativeLayout) findViewById(R.id.activity_view_table_map);


        shaneconnect.ShaneConnect vista = getShaneConnect();
        index = 0;
        getEmployeeList(0, vista);

        Button listView = (Button) findViewById(R.id.goToListView);
        listView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(viewTableMap.this, tableListView.class); /** Class name here */
                viewTableMap.this.startActivity(myIntent);
            }
        });

        Button saveData = (Button) findViewById(R.id.sendToServer);
        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!allTheTables.isEmpty()){
                    Iterator<Table> tableIterator = allTheTables.iterator();
                    while(tableIterator.hasNext()){
                        tableIterator.next().saveTable();
                    }
                }
            }
        });
    }


    public void retrieveTables(final int index, final shaneconnect.ShaneConnect s) {
        s.getTables(index, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    Table temp = new Table(response.getString("name"), response.getInt("x_coord"), response.getInt("y_coord"), response.getInt("status"), response.getInt("employee_id"), " ", response.getInt("number_seats"), employeeList, viewTableMap.this, mRootLayout);
                    allTheTables.add(temp);
                    temp.drawTable();
                    //Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
                    retrieveTables(index+1,s);
                } catch (JSONException e) {
                    return;
                }
            }

        });
    }

    private void getEmployeeList(final int index, final shaneconnect.ShaneConnect s) {
        s.getEmployees(index, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    employee temp = new employee(response.getString("first"), response.getString("last"), response.getInt("emp_id"));
                    employeeList.add(temp);
                    String temp2 = temp.getLast() + ", " + temp.getFirst();
                    getEmployeeList(index + 1, s);
                } catch (JSONException e) {
                    retrieveTables(0, s);
                    return;
                }
            }

        });
    }
}
