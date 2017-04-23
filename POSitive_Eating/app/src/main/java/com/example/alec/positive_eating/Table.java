package com.example.alec.positive_eating;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.view.Gravity.CENTER;
import static android.view.Gravity.CENTER_HORIZONTAL;
import static com.example.alec.positive_eating.Singleton_Current_Employee.getEInstance;
import static com.example.alec.positive_eating.Singleton_Employee_List.getListInstance;
import static com.example.alec.positive_eating.Singleton_ShaneConnect_Factory.getShaneConnect;

/**
 * This is the table class. Any tables created in a tableMap view will be an object of this class.
 * @author Ethan
 */
public class Table {
    private String ID;
    private int xPos;
    private int yPos;
    private int Status;
    private int employeeID;
    private String customerID;
    private int tableNumber;
    private Context tableContext;
    private ViewGroup mRootLayout;
    private FrameLayout tempFrame;
    private TextView tempName;
    private int Seats;
    private List<employee> employeeList;
    private TextView tempDetails;
    private Map<Integer, String> employeeNameMap;
    private int whichListener;
    Map<Integer, String> statusToText;

    /**
     * Default constructor builds a table with all given information. Also saves this table to the server.
     *
     * @param ID
     * @param xPOS
     * @param yPOS
     * @param Status
     * @param employeeID
     * @param customerID
     */
    //TODO
    Table(String ID, int xPOS, int yPOS, int Status, int employeeID, String customerID, int Seats, List<employee> employeeList, Context context, ViewGroup mRootLayout) {
        this.ID = ID;
        if(xPOS > 0) { this.xPos = xPOS; }
        else{ this.xPos = 0; }
        if(yPOS > 0) { this.yPos = yPOS; }
        else{ this.yPos = 0; }
        this.employeeID = employeeID;
        this.customerID = customerID;
        this.tableContext = context;
        this.mRootLayout = mRootLayout;
        this.Seats = Seats;
        this.Status = Status;
        this.employeeList = new ArrayList<>();
        this.tempDetails = new TextView(tableContext);
        //this.employeeList = employeeList;
        this.employeeList = getListInstance().getEList();

        employeeNameMap = new HashMap<>();
        for(int i = 0; i < employeeList.size(); i++){
            String temp = employeeList.get(i).getFirst()+ " " + employeeList.get(i).getLast() + ", " + employeeList.get(i).getID();
            employeeNameMap.put(employeeList.get(i).getID(), temp);
        }
        whichListener = 0;

        statusToText = new HashMap<>();
        statusToText.put(0, "Nothing");
        statusToText.put(1, "Empty");
        statusToText.put(2, "Sat");
        statusToText.put(3, "Needs Cleaning");
    }

    /**
     * Constructor for originally adding a table without a status, employee, or customer
     *
     * @param ID
     * @param Seats
     * @param employeeList
     * @param context
     * @param mRootLayout
     */
    Table(String ID, int Seats, List<employee> employeeList, Context context, ViewGroup mRootLayout){
        this(ID, 100, 100, 0, -1, "", Seats, employeeList, context, mRootLayout);
    }

    /**
     * Saves a table to the database
     */
    protected void saveTable() {
        int tempX = (int)tempFrame.getX();
        int tempY = (int)tempFrame.getY();
        xPos = (int) tempFrame.getX();
        yPos = (int) tempFrame.getY();
        xPos=xPos-50;
        yPos=yPos-50;
        shaneconnect.ShaneConnect vista = getShaneConnect();
        vista.setTable(ID, xPos, yPos, Seats, Status, employeeID, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response.toString());
                Toast.makeText(tableContext.getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
    
    /**
     * Returns the ID of this table
     *
     * @param ID
     */
    protected void setID(String ID) {
        this.ID = ID;
        saveTable(); //save the new information to the server
    }

    /**
     * Sets the x position of this table and saves it to the database
     *
     * @param xPOS
     */
    protected void setX(int xPOS) {
        this.xPos = xPOS;
        this.saveTable();
    }

    /**
     * Sets the y position of this table and saves it to the database
     *
     * @param yPOS
     */
    protected void setY(int yPOS) {
        this.yPos = yPOS;
        this.saveTable();
    }

    /**
     * Sets the Status of this table and saves it to the database
     *
     * @param Status
     */
    protected void setStatus(int Status) {
        this.Status = Status;
        this.saveTable();
    }

    /**
     * Sets the employeeID of this table and saves it to the database
     *
     * @param employeeID
     */
    protected void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
        this.saveTable();
    }

    /**
     * Sets the customerID of this table and saves it to the database
     *
     * @param customerID
     */
    protected void setCustomerID(String customerID) {
        this.customerID = customerID;
        this.saveTable();
    }

    protected void setSeats(int Seats) {
        this.Seats = Seats;
        saveTable(); //save the new information to the server
    }

    protected void setRootLayout(ViewGroup mRootLayout) {
        this.mRootLayout = mRootLayout;
    }

    protected void setContext(Context context) {
        this.tableContext = context;
    }

    /**
     * Gets the ID of this table after making sure the table is updated with the server.
     *
     * @return ID
     */
    protected String getID() {
        return this.ID;
    }

    /**
     * Gets the x position of this table after making sure the table is updated with the server.
     *
     * @return x position
     */
    protected int getX() {
        return this.xPos;
    }

    /**
     * Gets the y position of this table after making sure the table is updated with the server.
     *
     * @return y position
     */
    protected int getY() {
        return this.yPos;
    }

    /**
     * Gets the Status of this table after making sure the table is updated with the server.
     *
     * @return status
     */
    protected int getStatus() {
        return this.Status;
    }

    /**
     * Gets the employeeID of this table after making sure the table is updated with the server.
     *
     * @return employeeID
     */
    protected int getEmployeeID() {
        return this.employeeID;
    }

    /**
     * Gets the customerID of this table after making sure the table is updated with the server.
     *
     * @return customerID
     */
    //TODO
    protected String getCustomerID() {
        return this.customerID;
    }

    protected int getSeats() {
        return this.Seats;
    }

    /**
     * Updates this table with the data stored on the database
     */

    protected void drawManagerTable(){ //View parentView
        tempFrame = new FrameLayout(tableContext);
        tempFrame.setX(xPos);
        tempFrame.setY(yPos);
//        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT); //WRAP_CONTENT param can be FILL_PARENT
//        params.leftMargin = xPos; //XCOORD
//        params.topMargin = yPos; //YCOORD
//        tempFrame.setLayoutParams(params);
        ImageView temp = new ImageView(tableContext);
        temp.setImageResource(R.drawable.squaretable);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(150, 150);
        temp.setLayoutParams(layoutParams);
        tempFrame.addView(temp);

        //This is adding a label above the imageView so you know which table it is
        tempName = new TextView(tableContext);
        tempName.setHeight(temp.getHeight());
        tempName.setWidth(temp.getWidth());
        tempName.setText(String.valueOf(ID));
        tempName.setTextColor(Color.BLACK);
        tempName.setTextSize(30);

        RelativeLayout.LayoutParams textLayoutParams = new RelativeLayout.LayoutParams(150,150);
        textLayoutParams.addRule(RelativeLayout.ALIGN_LEFT, temp.getId());
        textLayoutParams.addRule(RelativeLayout.ALIGN_RIGHT, temp.getId());
        textLayoutParams.addRule(RelativeLayout.ALIGN_TOP, temp.getId());
        textLayoutParams.addRule(RelativeLayout.ALIGN_BOTTOM, temp.getId());
        textLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        tempName.setLayoutParams(textLayoutParams);
        tempName.setGravity(CENTER);
        tempFrame.addView(tempName);

        mRootLayout.addView(tempFrame);
        colorTable();
        if(getEInstance().getEmployee().getPermissions() == 1){
            if(getEInstance().getEmployee().getID() != getEmployeeID()) {
                temp.setVisibility(View.INVISIBLE);
            }
        }
        addListener(whichListener);

    }

    private final class MyTouchListener implements View.OnTouchListener {
//        private int xDelta;
//        private int yDelta;
//        @Override
//        public boolean onTouch(View view, MotionEvent event) {
//            final int X = (int) event.getRawX();
//            final int Y = (int) event.getRawY();
//            switch (event.getAction() & MotionEvent.ACTION_MASK) {
//                case MotionEvent.ACTION_DOWN:
//                    RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
//                    xDelta = X - lParams.leftMargin;
//                    yDelta = Y - lParams.topMargin;
//                    break;
//                case MotionEvent.ACTION_UP:
//                    break;
//                case MotionEvent.ACTION_POINTER_DOWN:
//                    break;
//                case MotionEvent.ACTION_POINTER_UP:
//                    break;
//                case MotionEvent.ACTION_MOVE:
//                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
//                    layoutParams.leftMargin = X - xDelta;
//                    layoutParams.topMargin = Y - yDelta;
//                    layoutParams.rightMargin = 150;
//                    layoutParams.bottomMargin = 150;
//                    view.setLayoutParams(layoutParams);
//                    break;
//            }
//            //mRootLayout.invalidate();
//            return true;
//        }


        /*
        http://stackoverflow.com/questions/9398057/android-move-a-view-on-touch-move-action-move
         */
        int mX, mY;
        @Override
        public boolean onTouch(View view, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mX = (int)view.getX() - (int)event.getRawX();
                    mY = (int)view.getY() - (int)event.getRawY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    view.animate().x(event.getRawX() + mX).y(event.getRawY() + mY).setDuration(0).start();
                    break;
                default:
                    return false;
            }
            return true;
        }
    }

    private void colorTable(){
        switch(Status){
            case 0: {
                tempName.setTextColor(Color.BLACK);
                break;
            }
            case 1: {
                tempName.setTextColor(Color.GREEN);
                break;
            }
            case 2: {
                tempName.setTextColor(Color.RED);
                break;
            }
            case 3: {
                tempName.setTextColor(Color.YELLOW);
                break;
            }
        }
    }

    public void addListItem(ViewGroup mListLayout){
        LinearLayout linearLayout = (LinearLayout) mListLayout;
        LinearLayout thisTable = new LinearLayout(tableContext);
        thisTable.setGravity(CENTER_HORIZONTAL);
        thisTable.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        thisTable.setOrientation(LinearLayout.VERTICAL);

        String tempNameString =  "Table: " + ID;
        TextView tempName = new TextView(tableContext);
        tempName.setGravity(CENTER_HORIZONTAL);
        tempName.setText(tempNameString); //+ "\nX: " + getX() + " Y: " + getY() + "\nNumber of seats: " + getSeats() + "\nStatus: " + getStatus()
        tempName.setTextColor(Color.BLACK);
        tempName.setTextSize(40);

        String tempDetailString =  "Number of seats: " + getSeats() + "\nStatus: " + getStatus() + "\nEmployee: " + employeeNameMap.get(employeeID) + "\n";
        tempDetails = new TextView(tableContext);
        tempDetails.setText(tempDetailString);
        tempDetails.setTextColor(Color.BLACK);
        tempDetails.setTextSize(20);

        linearLayout.addView(thisTable);
        thisTable.setOnClickListener(new EmployeeClickListener());
        thisTable.addView(tempName);
        thisTable.addView(tempDetails);
    }

    //The following 3 classes are used for the table list view. and selecting a server for a table.


    protected void addListener(int selection){
        switch(selection){
            case 0 : {
                tempFrame.setOnTouchListener(null);
                tempFrame.setClickable(false);
                break;
            }
            case 1 : {
                tempFrame.setClickable(false);
                tempFrame.setOnTouchListener(new MyTouchListener());
                break;
            }
            case 2 : {
                tempFrame.setOnTouchListener(null);
                tempFrame.setClickable(true);
                tempFrame.setOnClickListener(new MyClickListener());
                break;
            }
            case 3 : {
                tempFrame.setOnTouchListener(null);
                tempFrame.setClickable(true);
                tempFrame.setOnClickListener(new EmployeeClickListener());
                break;
            }
            case 4 : {
                tempFrame.setOnTouchListener(null);
                tempFrame.setClickable(true);
                tempFrame.setOnClickListener(new OrderClickListener());
                break;
            }
        }
    }

    private final class MyClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(tableContext);
            builder.setTitle(ID);

            TextView input = new TextView(tableContext);
            String stringStatus = statusToText.get(Status);
            input.setText("\n Employee: " + employeeNameMap.get(employeeID) + "\nNumber of Seats: " + Seats + "\nStatus: " + stringStatus);
            input.setGravity(CENTER);

            builder.setView(input);

            builder.setPositiveButton("Switch Status", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(getEInstance().getEmployee().getPermissions() == 1){
                        if(getEInstance().getEmployee().getID() == getEmployeeID()) {
                            switch (Status) {
                                case 0: {
                                    tempName.setTextColor(Color.GREEN);
                                    setStatus(1);
                                    break;
                                }
                                case 1: {
                                    tempName.setTextColor(Color.RED);
                                    setStatus(2);
                                    break;
                                }
                                case 2: {
                                    tempName.setTextColor(Color.YELLOW);
                                    setStatus(3);
                                    break;
                                }
                                case 3: {
                                    tempName.setTextColor(Color.BLACK);
                                    setStatus(0);
                                    break;
                                }
                            }
                        }else{
                            dialog.cancel();
                            Toast.makeText(tableContext, "No Permission", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        switch (Status) {
                            case 0: {
                                tempName.setTextColor(Color.GREEN);
                                setStatus(1);
                                break;
                            }
                            case 1: {
                                tempName.setTextColor(Color.RED);
                                setStatus(2);
                                break;
                            }
                            case 2: {
                                tempName.setTextColor(Color.YELLOW);
                                setStatus(3);
                                break;
                            }
                            case 3: {
                                tempName.setTextColor(Color.BLACK);
                                setStatus(0);
                                break;
                            }
                        }
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
            saveTable();
        }
    }

    private final class EmployeeClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            dialogBuilder();
            saveTable();
        }
    }

    private void dialogBuilder(){
        List<String> spinnerList = new ArrayList<>();
        final Map<String, Integer> employeeMap = new HashMap<>(); //this is how I will remember which ID goes with which name when an option is selected
        for(int i = 0; i < employeeList.size(); i++){
            if(employeeList.get(i).getPermissions() == 1) {
                String temp = employeeList.get(i).getLast() + ", " + employeeList.get(i).getFirst() + ": " + employeeList.get(i).getID();
                employeeMap.put(temp, employeeList.get(i).getID());
                spinnerList.add(temp);
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(tableContext, android.R.layout.simple_spinner_dropdown_item, spinnerList);
        final Spinner employeeSpinner = new Spinner(tableContext);
        employeeSpinner.setAdapter(adapter);
        AlertDialog.Builder builder = new AlertDialog.Builder(tableContext);
        builder.setTitle("Employee: " + employeeNameMap.get(employeeID));

        builder.setView(employeeSpinner);

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(getEInstance().getEmployee().getPermissions() == 1){
                    if(getEInstance().getEmployee().getID() != getEmployeeID()) {
                        dialog.cancel();
                        Toast.makeText(tableContext, "No Permission", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }else {
                    String oneUse = (String) employeeSpinner.getSelectedItem();
                    setEmployeeID(employeeMap.get(oneUse));
                    Toast.makeText(tableContext, String.valueOf(employeeMap.get(oneUse)), Toast.LENGTH_SHORT).show();
                    saveTable();
                    String tempDetailString = "Number of seats: " + getSeats() + "\nStatus: " + getStatus() + "\nEmployee: " + employeeNameMap.get(employeeID) + "\n";
                    tempDetails.setText(tempDetailString);
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

    private final class OrderClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if(getEInstance().getEmployee().getPermissions() == 1){
                if(getEInstance().getEmployee().getID() != getEmployeeID()) {
                    Toast.makeText(tableContext, "No Permission", Toast.LENGTH_SHORT).show();
                }
            }else {
                Intent myIntent = new Intent(tableContext, Employee_Menu.class); /** Class name here */
                myIntent.putExtra("tableNum", ID);
                tableContext.startActivity(myIntent);
            }
        }
    }
}