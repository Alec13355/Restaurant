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

import static com.example.alec.positive_eating.Singleton_Current_Employee.getEInstance;
import static com.example.alec.positive_eating.Singleton_Employee_List.getListInstance;
import static com.example.alec.positive_eating.Singleton_ShaneConnect_Factory.getShaneConnect;
import static com.example.alec.positive_eating.Singleton_Table_List.getTableListInstance;

/**
 * @author Ethan
 */
public class tableMap extends Activity {
    private List<Table> allTheTables = new ArrayList<>();
    private ViewGroup mRootLayout;
    private int index;
    private int whichListener;
    private List<employee> employeeList;
    private Button tableAdd;
    private Button saveData;
    private Button editMode;
    /**
     * onCreate first updates based on the
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        employeeList = new ArrayList<>();
        whichListener = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_map);
        mRootLayout = (RelativeLayout) findViewById(R.id.activity_table_map);

        index = 0;
        //getEmployeeList(0, vista);
        this.employeeList = getListInstance().getEList();
        this.allTheTables = getTableListInstance().getTList();

        Iterator<Table> tableIterator = allTheTables.iterator();
        while(tableIterator.hasNext()){
            Table temp = tableIterator.next();
            temp.setContext(tableMap.this);
            temp.setRootLayout(mRootLayout);
            temp.drawManagerTable();
        }

        changeEditMode();

        tableAdd = (Button) findViewById(R.id.addTable);
        //tableAdd.setVisibility(View.INVISIBLE);
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

        saveData = (Button) findViewById(R.id.sendToServer);
        //saveData.setVisibility(View.INVISIBLE);
        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(!allTheTables.isEmpty()){
                Iterator<Table> tableIterator = allTheTables.iterator();
                while(tableIterator.hasNext()){
                    tableIterator.next().saveTable();
                }
                Toast.makeText(tableMap.this, "Saved", Toast.LENGTH_SHORT).show();
            }
            }
        });

        editMode = (Button) findViewById(R.id.changeEditMode);
        //editMode.setVisibility(View.INVISIBLE);
        editMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            changeEditMode();
            }
        });
    }

//    public void retrieveTables(final int index, final shaneconnect.ShaneConnect s) {
//        s.getTables(index, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try{
//                    //Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
//                    //if(userPermission == server) then check to see if employeeID matches singleton employeeID, otherwise discard table
//                    Table temp = new Table(response.getString("name"), response.getInt("x_coord"), response.getInt("y_coord"), response.getInt("status"), response.getInt("employee_id"), " ", response.getInt("number_seats"), employeeList, tableMap.this, mRootLayout);
//                    if(getEInstance().getEmployee().getPermissions() == 1){
//                        if(getEInstance().getEmployee().getID() == temp.getEmployeeID()){
//                            allTheTables.add(temp);
//                            temp.drawManagerTable();
//                            temp.addListener(1);
//                        }
//                    }else{
//                        allTheTables.add(temp);
//                        temp.drawManagerTable();
//                        temp.addListener(1);
//                    }
//                    retrieveTables(index+1,s);
//                } catch (JSONException e) {
//                    tableAdd.setVisibility(View.VISIBLE);
//                    saveData.setVisibility(View.VISIBLE);
//                    editMode.setVisibility(View.VISIBLE);
//                    changeEditMode();
//                    return;
//                }
//            }
//
//        });
//    }

//
//    private void getEmployeeList(final int index, final shaneconnect.ShaneConnect s) {
//        s.getEmployees(index, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try {
//                    employee temp = new employee(response.getString("first"), response.getString("last"), response.getInt("emp_id"), response.getString("address"), response.getString("phone"), response.getInt("rate"), response.getString("pass"), response.getInt("status"));
//                    employeeList.add(temp);
//                    String temp2 = temp.getLast() + ", " + temp.getFirst();
//                    getEmployeeList(index + 1, s);
//                } catch (JSONException e) {
//                    return;
//                }
//            }
//
//        });
//    }

    private void changeEditMode(){
        switch(whichListener){
            case 0 : {
                //if(user.permissions == manager){
                if(getEInstance().getEmployee().getPermissions() == 0) {
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
                                    if (m.find()) {
                                        Seats = Integer.parseInt(input.getText().toString());
                                        temp.setSeats(Seats);

                                    } else {
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
                }else{
                    whichListener = 1;
                }
            }
            case 1 : {
//                if(userPermission == manager | hostess | server) {
                if(getEInstance().getEmployee().getPermissions() <= 3) {
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
                }else{
                    whichListener = 2;
                }
            }
            case 2 : {
                //if(userPermission == manager){
                if(getEInstance().getEmployee().getPermissions() == 0) {
                    whichListener = 3;
                    updateListener(whichListener);
                    Toast.makeText(tableMap.this, "Employee Mode", Toast.LENGTH_SHORT).show();
                    break;
                }else{
                    whichListener = 3;
                }
            }
            case 3 : {
                //if(userPermission == manager or server)
                if(getEInstance().getEmployee().getPermissions() <= 1) {
                    whichListener = 4;
                    updateListener(whichListener);
                    Toast.makeText(tableMap.this, "Order Mode", Toast.LENGTH_SHORT).show();
                    break;
                }else{
                    whichListener = 4;
                }
                //}else{
                //whichListener = 4;
                //}
            }
            case 4 : {
                //everyone can see this
                whichListener = 0;
                updateListener(whichListener);
                Toast.makeText(tableMap.this, "View Mode", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }

    private void updateListener(int Listener){
        for(int i = 0; i < allTheTables.size(); i++){
            allTheTables.get(i).addListener(Listener);
        }
    }
}
