package com.example.alec.positive_eating;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.List;

public class ViewEmployeeList extends AppCompatActivity {
    List<employee> employeeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_employee_list);

        employeeList = new ArrayList<>();
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
                    //build the list
                    return;
                }
            }

        });
    }
}
