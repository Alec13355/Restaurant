package com.example.alec.positive_eating;
/*
@author Ethan Wieczorek
 */
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
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
    private int whichListener;
    private List<employee> employeeList;
    private Button tableAdd;
    private Button saveData;
    private Button editMode;
    private TextView currentMode;
    /**
     * onCreate first updates based on the
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        employeeList = new ArrayList<>();
        currentMode = new TextView(tableMap.this);
        currentMode.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        currentMode.setX(10);
        currentMode.setY(10);
        currentMode.setTextSize(20);
        currentMode.setTextColor(Color.BLACK);

        whichListener = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_map);
        mRootLayout = (RelativeLayout) findViewById(R.id.activity_table_map);
        mRootLayout.addView(currentMode);

        this.employeeList = getListInstance().getEList();
        this.allTheTables = getTableListInstance().getTList();

        Iterator<Table> tableIterator = allTheTables.iterator();
        while(tableIterator.hasNext()){
            Table temp = tableIterator.next();
            temp.setContext(tableMap.this);
            temp.setRootLayout(mRootLayout);
            temp.drawManagerTable();
        }

        tableAdd = (Button) findViewById(R.id.addTable);
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
        editMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            changeEditMode();
            }
        });

        changeEditMode();
    }

    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(tableMap.this, Employee_MainScreen.class);
        this.finishActivity(0);
        tableMap.this.startActivity(myIntent);
    }

    private void changeEditMode(){
        switch(whichListener){
            case 0 : {
                //if(user.permissions == manager){
                if(getEInstance().getEmployee().getPermissions() == 0) {
                    whichListener = 1;
                    updateListener(whichListener);
                    currentMode.setText(String.valueOf("Drag Mode"));
                    //Toast.makeText(tableMap.this, "Drag Mode", Toast.LENGTH_SHORT).show();
                    tableAdd.setText(String.valueOf("Add Table"));
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
                    currentMode.setText(String.valueOf("Status Mode"));
                    //Toast.makeText(tableMap.this, "Status Mode", Toast.LENGTH_SHORT).show();
                    tableAdd.setText(String.valueOf("List View"));
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
                    currentMode.setText(String.valueOf("Employee Mode"));
                    //Toast.makeText(tableMap.this, "Employee Mode", Toast.LENGTH_SHORT).show();
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
                    currentMode.setText(String.valueOf("Order Mode"));
                    //Toast.makeText(tableMap.this, "Order Mode", Toast.LENGTH_SHORT).show();
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
                currentMode.setText(String.valueOf("View Mode"));
                //Toast.makeText(tableMap.this, "View Mode", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }

    private void updateListener(int Listener){
        for(int i = 0; i < allTheTables.size(); i++){
            if(getEInstance().getEmployee().getPermissions() == 1){
                if(getEInstance().getEmployee().getID() == allTheTables.get(i).getEmployeeID()){
                    allTheTables.get(i).addListener(Listener);
                }
            }else{
                allTheTables.get(i).addListener(Listener);
            }
        }
    }
}
