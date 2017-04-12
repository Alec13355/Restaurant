package com.example.alec.positive_eating;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static android.view.Gravity.FILL;
import static com.example.alec.positive_eating.Singleton_ShaneConnect_Factory.getShaneConnect;

public class tableListView extends AppCompatActivity {
    private int index;
    ViewGroup mRootLayout;
    LinearLayout listView;
    private List<Table> allTheTables = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_list_view);
        //allTheTables = (ArrayList<Table>) getIntent().getSerializableExtra("allthetables");
        mRootLayout = (RelativeLayout) findViewById(R.id.activity_table_list_view);
        ScrollView scroll = new ScrollView(this);

        listView = new LinearLayout(this);
        listView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        listView.setOrientation(LinearLayout.VERTICAL);
        mRootLayout.addView(scroll);
        scroll.addView(listView);



        shaneconnect.ShaneConnect vista = getShaneConnect();
        index = 0;
        retrieveTables(index, vista);
    }

    public void retrieveTables(final int index, final shaneconnect.ShaneConnect s) {
        s.getTables(index, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    //Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                    //// TODO
                    Table temp = new Table(response.getString("name"), response.getInt("x_coord"), response.getInt("y_coord"), response.getInt("status"), response.getInt("employee_id"), "", response.getInt("number_seats"), tableListView.this, listView);
                    allTheTables.add(temp);
                    temp.addListItem(listView);
                    retrieveTables(index+1,s);
                } catch (JSONException e) {
                    return;
                }
            }

        });
    }
}
