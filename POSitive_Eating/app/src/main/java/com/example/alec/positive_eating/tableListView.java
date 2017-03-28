package com.example.alec.positive_eating;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
    private List<Table> allTheTables = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_list_view);
        //allTheTables = (ArrayList<Table>) getIntent().getSerializableExtra("allthetables");
        mRootLayout = (LinearLayout) findViewById(R.id.linearView);

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
                    Table temp = new Table(response.getString("name"), response.getInt("x_coord"), response.getInt("y_coord"), response.getInt("status"), "", "", response.getInt("number_seats"), tableListView.this, mRootLayout);
                    allTheTables.add(temp);
                    temp.addListItem(mRootLayout);
                    retrieveTables(index+1,s);
                } catch (JSONException e) {
                    return;
                }
            }

        });
    }
}
