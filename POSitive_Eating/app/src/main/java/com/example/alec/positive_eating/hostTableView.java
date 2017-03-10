package com.example.alec.positive_eating;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Response;
import com.example.shaneconnect.ShaneConnect;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.os.Build.ID;

public class hostTableView extends AppCompatActivity {

    List<Table> tables = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_table_view);

        ShaneConnect sc = new ShaneConnect("http://proj-309-yt-4.cs.iastate.edu:", this);
        sc.getTables(ID, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                //call Table(ID, x, y, status, employeeid, customerid) with the retrieved data.
                //add this table to tables.

                TextView tv = (TextView) findViewById(R.id.text_edit);
                tv.setText(response.toString());
            }
        });
    }
}
