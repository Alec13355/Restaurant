package com.example.alec.positive_eating;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.example.alec.positive_eating.Singleton_Current_Employee.getInstance;
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

        Toast.makeText(ViewEmployeeList.this, getInstance().getEmployee().getFirst(), Toast.LENGTH_SHORT).show();
        Toast.makeText(ViewEmployeeList.this, "Done", Toast.LENGTH_SHORT).show();

        ScrollView scroll = new ScrollView(this);

        listView = new LinearLayout(this);
        listView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        listView.setOrientation(LinearLayout.VERTICAL);
        mRootLayout.addView(scroll);
        scroll.addView(listView);

        viewPasswords = (Button) findViewById(R.id.viewPass);
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

    private void getEmployeeList(final int index, final shaneconnect.ShaneConnect s) {
        s.getEmployees(index, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    //employee(String first, String last, int ID, String availability, String phone, int rate, String pass){
                    employee temp = new employee(response.getString("first"), response.getString("last"), response.getInt("emp_id"), response.getString("address"), response.getString("phone"), response.getInt("rate"), response.getString("pass"), response.getInt("status"));
                    employeeList.add(temp);
                    temp.addListItem(listView, ViewEmployeeList.this);
                    getEmployeeList(index + 1, s);
                } catch (JSONException e) {
                    if(getInstance().getEmployee().getPermissions() < 2) viewPasswords.setVisibility(View.VISIBLE);
                    return;
                }
            }

        });
    }
}
