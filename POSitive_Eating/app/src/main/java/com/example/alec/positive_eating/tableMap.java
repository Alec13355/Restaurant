package com.example.alec.positive_eating;
/*
@author Ethan Wieczorek
 */
import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.alec.positive_eating.Singleton_ShaneConnect_Factory.getShaneConnect;

/**
 * @author Ethan
 */
public class tableMap extends Activity {
    private List<Table> allTheTables = new ArrayList<>();
    private ViewGroup mRootLayout;
    private int index;
    /**
     * onCreate first updates based on the
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_map);
        mRootLayout = (RelativeLayout) findViewById(R.id.activity_table_map);

        shaneconnect.ShaneConnect vista = getShaneConnect();
        index = 0;
        retrieveTables(index, vista);


        Button tableAdd = (Button) findViewById(R.id.addTable);
        tableAdd.setOnClickListener(new View.OnClickListener() {
            int Seats = 4;
            boolean exitvalue = false;
            Table temp;
            @Override
            public void onClick(View v) {
                temp = new Table(String.valueOf(allTheTables.size() + 1), Seats, tableMap.this, mRootLayout);
                allTheTables.add(temp);
                temp.drawManagerTable();
                AlertDialog.Builder builder = new AlertDialog.Builder(tableMap.this);
                builder.setTitle("Number of Seats");

                final EditText input = new EditText(tableMap.this);

                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setView(input);


                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Seats = Integer.parseInt(input.getText().toString());
                        temp.setSeats(Seats);
                        exitvalue = true;
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        exitvalue = true;
                    }
                });
                builder.show();
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
                    //Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                    //TODO
                    Table temp = new Table(response.getString("name"), response.getInt("x_coord"), response.getInt("y_coord"), response.getInt("status"), response.getInt("employeeID"), "", response.getInt("number_seats"), tableMap.this, mRootLayout);
                    allTheTables.add(temp);
                    temp.drawManagerTable();
                    retrieveTables(index+1,s);
                } catch (JSONException e) {
                    return;
                }
            }

        });
    }
}
