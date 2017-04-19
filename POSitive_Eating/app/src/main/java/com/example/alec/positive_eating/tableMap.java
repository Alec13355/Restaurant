package com.example.alec.positive_eating;
/*
@author Ethan Wieczorek
 */
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.alec.positive_eating.Singleton_ShaneConnect_Factory.getShaneConnect;

/**
 * @author Ethan
 */
public class tableMap extends Activity {
    private List<Table> allTheTables = new ArrayList<>();
    private ViewGroup mRootLayout;
    private int index;
    private int whichListener;
    private List<employee> employeeList;
    /**
     * onCreate first updates based on the
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        employeeList = new ArrayList<>();
        whichListener = 1;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_map);
        mRootLayout = (RelativeLayout) findViewById(R.id.activity_table_map);

        shaneconnect.ShaneConnect vista = getShaneConnect();
        index = 0;
        getEmployeeList(0, vista);


        final Button tableAdd = (Button) findViewById(R.id.addTable);
        tableAdd.setOnClickListener(new View.OnClickListener() {
            int Seats = 4;
            Table temp;
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(tableMap.this);
                builder.setTitle("Number of Seats");

                final EditText input = new EditText(tableMap.this);

                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setView(input);


                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Pattern p = Pattern.compile("(\\d)");
                        Matcher m = p.matcher(input.getText());
                        if (m.find()){
                            Seats = Integer.parseInt(input.getText().toString());
                            temp = new Table(String.valueOf(allTheTables.size() + 1), Seats, employeeList, tableMap.this, mRootLayout);
                            allTheTables.add(temp);
                            temp.drawManagerTable();
                        }else{
                            builder.show();
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

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

        final Button editMode = (Button) findViewById(R.id.changeEditMode);
        editMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(whichListener){
                    case 1 : {
                        whichListener = 2;
                        updateListener(whichListener);
                        Toast.makeText(tableMap.this, "Status Mode", Toast.LENGTH_SHORT).show();
                        tableAdd.setText("List View");
                        tableAdd.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent myIntent = new Intent(tableMap.this, tableListView.class);
                                tableMap.this.startActivity(myIntent);
                            }
                        });
                        break;
                    }
                    case 2 : {
                        whichListener = 3;
                        updateListener(whichListener);
                        Toast.makeText(tableMap.this, "Employee Mode", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case 3 : {
                        whichListener = 1;
                        updateListener(whichListener);
                        Toast.makeText(tableMap.this, "Drag Mode", Toast.LENGTH_SHORT).show();
                        tableAdd.setText("Add Table");
                        tableAdd.setOnClickListener(new View.OnClickListener() {
                            int Seats = 4;
                            Table temp;
                            @Override
                            public void onClick(View v) {
                                temp = new Table(String.valueOf(allTheTables.size() + 1), Seats, employeeList, tableMap.this, mRootLayout);
                                allTheTables.add(temp);
                                temp.drawManagerTable();
                                final AlertDialog.Builder builder = new AlertDialog.Builder(tableMap.this);
                                builder.setTitle("Number of Seats");

                                final EditText input = new EditText(tableMap.this);

                                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                                builder.setView(input);


                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Pattern p = Pattern.compile("(\\d)");
                                        Matcher m = p.matcher(input.getText());
                                        if (m.find()){
                                            Seats = Integer.parseInt(input.getText().toString());
                                            temp.setSeats(Seats);

                                        }else{
                                            builder.show();
                                        }
                                    }
                                });
                                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();

                                    }
                                });
                                builder.show();
                            }
                        });
                        break;
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
                    Table temp = new Table(response.getString("name"), response.getInt("x_coord"), response.getInt("y_coord"), response.getInt("status"), response.getInt("employee_id"), " ", response.getInt("number_seats"), employeeList, tableMap.this, mRootLayout);
                    allTheTables.add(temp);
                    temp.drawManagerTable();
                    temp.addListener(1);
                    retrieveTables(index+1,s);
                } catch (JSONException e) {
                    return;
                }
            }

        });
    }

    private void updateListener(int Listener){
        for(int i = 0; i < allTheTables.size(); i++){
            allTheTables.get(i).addListener(Listener);
        }
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
