package com.example.alec.positive_eating;
/*
@author Ethan Wieczorek
 */
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.android.volley.Response;
import com.example.alec.positive_eating.Table;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.alec.positive_eating.Singleton_ShaneConnect_Factory.getShaneConnect;

/**
 * @author Ethan
 */
public class tableMap extends Activity {
    private List<Table> allTheTables = new ArrayList<>();
    private ViewGroup mRootLayout;
    private int _xDelta;
    private int _yDelta;

    /**
     * onCreate first updates based on the
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_map);
        mRootLayout = (RelativeLayout) findViewById(R.id.activity_table_map);


        //TODO get tables add to allthetables
        shaneconnect.ShaneConnect vista = getShaneConnect();
        int index = 0;
        vista.getTables(index, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    allTheTables.add(new Table(response.getString("ID"), response.getInt("x"), response.getInt("y"), tableMap.this, mRootLayout));
                    index++;

                } catch (JSONException e) {
                    return;
            }

            }
        });
        index++;




        Button tableAdd = (Button) findViewById(R.id.addTable);
        tableAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allTheTables.add(new Table(String.valueOf(allTheTables.size() + 1), tableMap.this, mRootLayout));
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
}
