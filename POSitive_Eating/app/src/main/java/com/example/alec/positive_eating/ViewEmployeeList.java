package com.example.alec.positive_eating;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.List;

import static com.example.alec.positive_eating.Singleton_ShaneConnect_Factory.getShaneConnect;

public class ViewEmployeeList extends AppCompatActivity {
    List<employee> employeeList;
    ViewGroup mRootLayout;
    LinearLayout listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_employee_list);
        mRootLayout = (RelativeLayout) findViewById(R.id.activity_view_employee_list);
        employeeList = new ArrayList<>();

        ScrollView scroll = new ScrollView(this);

        listView = new LinearLayout(this);
        listView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        listView.setOrientation(LinearLayout.VERTICAL);
        mRootLayout.addView(scroll);
        scroll.addView(listView);

        shaneconnect.ShaneConnect vista = getShaneConnect();
        getEmployeeList(0, vista);
    }

    private void getEmployeeList(final int index, final shaneconnect.ShaneConnect s) {
        s.getEmployees(index, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    //employee(String first, String last, int ID, String availability, String phone, int rate, String pass){
                    employee temp = new employee(response.getString("first"), response.getString("last"), response.getInt("emp_id"), response.getString("address"), response.getString("phone"), response.getInt("rate"), response.getString("pass"));
                    employeeList.add(temp);
                    temp.addListItem(listView, ViewEmployeeList.this);
                    getEmployeeList(index + 1, s);
                } catch (JSONException e) {
                    //build the list
                    return;
                }
            }

        });
    }
}
