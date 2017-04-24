package com.example.alec.positive_eating;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.example.alec.positive_eating.Singleton_Current_Employee.getEInstance;
import static com.example.alec.positive_eating.Singleton_ShaneConnect_Factory.getShaneConnect;

public class ViewEmployeeList extends AppCompatActivity {
    List<employee> employeeList;
    ViewGroup mRootLayout;
    LinearLayout listView;
    Button viewPasswords;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_employee_list);
        mRootLayout = (RelativeLayout) findViewById(R.id.activity_view_employee_list);
        employeeList = new ArrayList<>();

        ScrollView scroll = new ScrollView(this);

        listView = new LinearLayout(this);
        listView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        listView.setOrientation(LinearLayout.VERTICAL);
        mRootLayout.addView(scroll);
        scroll.addView(listView);

        viewPasswords = (Button) findViewById(R.id.viewPass);
        viewPasswords.setText("VIEW\nPASSWORDS");
        viewPasswords.setVisibility(View.INVISIBLE);
        viewPasswords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!employeeList.isEmpty()){
                    Iterator<employee> tableIterator = employeeList.iterator();
                    while(tableIterator.hasNext()){
                        tableIterator.next().changePasswordVisibility();
                    }
                }
            }
        });

        shaneconnect.ShaneConnect vista = getShaneConnect();
        getEmployeeList(0, vista);
    }

    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(ViewEmployeeList.this, Employee_MainScreen.class);
        this.finishActivity(0);
        ViewEmployeeList.this.startActivity(myIntent);
    }

    private void getEmployeeList(final int index, final shaneconnect.ShaneConnect s) {
        s.getEmployees(index, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    //employee(String first, String last, int ID, String availability, String phone, int rate, String pass){
                    employee temp = new employee(response.getString("first"), response.getString("last"), response.getInt("emp_id"), response.getString("address"), response.getString("phone"), response.getInt("rate"), response.getString("pass"), response.getInt("status"), response.getString("social"), response.getString("bank_num"), response.getString("routing"));
                    employeeList.add(temp);
                    temp.addListItem(listView, ViewEmployeeList.this);

                    getEmployeeList(index + 1, s);
                } catch (JSONException e) {
                    if(getEInstance().getEmployee().getPermissions() == 0) viewPasswords.setVisibility(View.VISIBLE);
                    return;
                }
            }

        });
    }
}
