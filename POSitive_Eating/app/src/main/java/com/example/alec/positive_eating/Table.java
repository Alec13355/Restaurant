package com.example.alec.positive_eating;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import shaneconnect.ShaneConnect;

import org.json.JSONException;
import org.json.JSONObject;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.view.Gravity.*;
import static android.view.ViewGroup.LayoutParams.FILL_PARENT;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
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
    //TODO
    private String customerID;
    private Context tableContext;
    private ViewGroup mRootLayout;
    private FrameLayout tempFrame;
    private TextView tempName;
    private int Seats;
    private List<employee> employeeList;

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
    Table(String ID, int xPOS, int yPOS, int Status, int employeeID, String customerID, int Seats, Context context, ViewGroup mRootLayout) {
        this.ID = ID;
        if(xPOS > 0) { this.xPos = xPOS; }
        else{ this.xPos = 10; }
        if(yPOS > 0) { this.yPos = yPOS; }
        else{ this.yPos = 10; }
        this.employeeID = employeeID;
        this.customerID = customerID;
        this.tableContext = context;
        this.mRootLayout = mRootLayout;
        this.Seats = Seats;
        this.Status = Status;
        employeeList = new ArrayList<>();
       // saveTable();
    }

    /**
     * Constructor for originally adding a table without a status, employee, or customer
     *
     * @param ID
     * @param xPOS
     * @param yPOS
     */
    Table(String ID, int xPOS, int yPOS, Context context, ViewGroup mRootLayout) {
        this(ID, xPOS, yPOS, 0, -1, "", 0, context, mRootLayout);
    }

    Table(String ID, Context context, ViewGroup mRootLayout){
        this(ID, 100, 100, 0, -1, "", 0, context, mRootLayout);
    }

    Table(String ID, int Seats, Context context, ViewGroup mRootLayout){
        this(ID, 100, 100, 0, -1, "", Seats, context, mRootLayout);
    }

    /**
     * Saves a table to the database
     */
    protected void saveTable() {
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
    //TODO
    protected void setCustomerID(String customerID) {
        this.customerID = customerID;
        this.saveTable();
    }

    protected void setSeats(int Seats) {
        this.Seats = Seats;
        saveTable(); //save the new information to the server
    }

    /**
     * Gets the ID of this table after making sure the table is updated with the server.
     *
     * @return ID
     */
    protected String getID() {
        updateTable();
        return this.ID;
    }

    /**
     * Gets the x position of this table after making sure the table is updated with the server.
     *
     * @return x position
     */
    protected int getX() {
        updateTable();
        return this.xPos;
    }

    /**
     * Gets the y position of this table after making sure the table is updated with the server.
     *
     * @return y position
     */
    protected int getY() {
        updateTable();
        return this.yPos;
    }

    /**
     * Gets the Status of this table after making sure the table is updated with the server.
     *
     * @return status
     */
    protected int getStatus() {
        updateTable();
        return this.Status;
    }

    /**
     * Gets the employeeID of this table after making sure the table is updated with the server.
     *
     * @return employeeID
     */
    protected int getEmployeeID() {
        updateTable();
        return this.employeeID;
    }

    /**
     * Gets the customerID of this table after making sure the table is updated with the server.
     *
     * @return customerID
     */
    //TODO
    protected String getCustomerID() {
        updateTable();
        return this.customerID;
    }

    protected int getSeats() {
        updateTable();
        return this.Seats;
    }

    /**
     * Updates this table with the data stored on the database
     */
    protected void updateTable() {
//        shaneconnect.ShaneConnect vista = getShaneConnect();
//        vista.setTable(ID, xPos, yPos, 4, Status, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                System.out.println(response.toString());
//                Toast.makeText(tableContext, response.toString(), Toast.LENGTH_LONG).show();
//            }
//        });
    }

    protected void drawTable(){ //View parentView
        tempFrame = new FrameLayout(tableContext);
        tempFrame.setX((float) xPos);
        tempFrame.setY((float) yPos);
        tempFrame.setForegroundGravity(CENTER);

        ImageView temp = new ImageView(tableContext);
        temp.setImageResource(R.drawable.squaretable);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(150, 150);
        temp.setLayoutParams(layoutParams);
        //temp.setId(allTables.size()); //the id for the button we be it's index in the array + 1, this way there is no table 0 when it comes to labeling.
        tempFrame.addView(temp);

        //This is adding a label above the imageView so you know which table it is
        tempName = new TextView(tableContext);
        tempName.setHeight(FILL);
        tempName.setWidth(FILL);
        tempName.setText(String.valueOf(ID));
        tempName.setTextColor(Color.BLACK);
        tempName.setTextSize(30);

        RelativeLayout.LayoutParams textLayoutParams = new RelativeLayout.LayoutParams(150,150);
//        textLayoutParams.addRule(RelativeLayout.ALIGN_LEFT, temp.getId());
//        textLayoutParams.addRule(RelativeLayout.ALIGN_RIGHT, temp.getId());
//        textLayoutParams.addRule(RelativeLayout.ALIGN_TOP, temp.getId());
//        textLayoutParams.addRule(RelativeLayout.ALIGN_BOTTOM, temp.getId());
        textLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        tempName.setLayoutParams(textLayoutParams);
        tempFrame.addView(tempName);

        //System.out.println(String.valueOf(allTables.size())); //debug statement

        Log.d("touch", "about to initialize listener");
        mRootLayout.addView(tempFrame);
        colorTable();
        tempFrame.setOnClickListener(new MyClickListener());
        saveTable();
    }

    protected void drawManagerTable(){ //View parentView
        tempFrame = new FrameLayout(tableContext);
        tempFrame.setX((float) xPos);
        tempFrame.setY((float) yPos);

        ImageView temp = new ImageView(tableContext);
        temp.setImageResource(R.drawable.squaretable);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(150, 150);
        temp.setLayoutParams(layoutParams);
        //temp.setId(allTables.size()); //the id for the button we be it's index in the array + 1, this way there is no table 0 when it comes to labeling.
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
        tempFrame.addView(tempName);

        //System.out.println(String.valueOf(allTables.size())); //debug statement

        Log.d("touch", "about to initialize listener");
        tempFrame.setOnTouchListener(new MyTouchListener());
        mRootLayout.addView(tempFrame);
        colorTable();
        saveTable();
    }

    protected void drawHostTable(){ //View parentView
        RelativeLayout tempFrame = new RelativeLayout(tableContext);
        tempFrame.setX((float) xPos);
        tempFrame.setY((float) yPos);
        tempFrame.setGravity(CENTER);

        ImageView temp = new ImageView(tableContext);
        temp.setImageResource(R.drawable.squaretable);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(150, 150);
        temp.setLayoutParams(layoutParams);
        //temp.setId(allTables.size()); //the id for the button we be it's index in the array + 1, this way there is no table 0 when it comes to labeling.
        tempFrame.addView(temp);

        //This is adding a label above the imageView so you know which table it is
        tempName = new TextView(tableContext);
        tempName.setText(String.valueOf(ID));
        tempName.setTextColor(Color.BLACK);

        RelativeLayout.LayoutParams textLayoutParams = new RelativeLayout.LayoutParams(150,150);
        textLayoutParams.addRule(RelativeLayout.ALIGN_LEFT, temp.getId());
        textLayoutParams.addRule(RelativeLayout.ALIGN_TOP, temp.getId());
        //textLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        tempName.setLayoutParams(textLayoutParams);
        tempName.setTextSize(200);
        tempFrame.addView(tempName);

        //System.out.println(String.valueOf(allTables.size())); //debug statement

        Log.d("touch", "about to initialize listener");
        tempFrame.setOnClickListener(new MyClickListener());
        mRootLayout.addView(tempFrame);
        colorTable();
        saveTable();
    }

    private final class MyTouchListener implements View.OnTouchListener {
        private int _xDelta;
        private int _yDelta;
        @Override
        public boolean onTouch(View view, MotionEvent event) {
            final int X = (int) event.getRawX();
            final int Y = (int) event.getRawY();
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    _xDelta = X - lParams.leftMargin;
                    _yDelta = Y - lParams.topMargin;
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    break;
                case MotionEvent.ACTION_MOVE:
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    layoutParams.leftMargin = X - _xDelta;
                    layoutParams.topMargin = Y - _yDelta;
                    layoutParams.rightMargin = 150;
                    layoutParams.bottomMargin = 150;
                    view.setLayoutParams(layoutParams);
                    if(view.getX() > 0) xPos = (int) view.getX();
                    if(view.getY() > 0) yPos = (int) view.getY();
 //                   System.out.println("X: " + String.valueOf(view.getX()) + ", Y: " + String.valueOf(view.getY())); //debug
                    break;
            }
            mRootLayout.invalidate();
            return true;
        }
    }

    private final class MyClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(tableContext);
            builder.setTitle(ID);

            TextView input = new TextView(tableContext);
            String stringStatus = "";
            stringStatus = statusToText();
            input.setText("\nNumber of Seats: " + Seats + "\nStatus: " + stringStatus);
            input.setGravity(CENTER);

            builder.setView(input);

            builder.setPositiveButton("Switch Status", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
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
        thisTable.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        thisTable.setOrientation(LinearLayout.VERTICAL);

        String tempNameString =  "Table: " + ID;
        TextView tempName = new TextView(tableContext);
        tempName.setText(tempNameString); //+ "\nX: " + getX() + " Y: " + getY() + "\nNumber of seats: " + getSeats() + "\nStatus: " + getStatus()
        tempName.setTextColor(Color.BLACK);
        tempName.setTextSize(40);

        String tempDetailString =  "Number of seats: " + getSeats() + "\nStatus: " + getStatus() + "\nEmployee: " + getEmployeeID() + "\n";
        TextView tempDetails = new TextView(tableContext);
        tempDetails.setText(tempDetailString);
        tempDetails.setTextColor(Color.BLACK);
        tempDetails.setTextSize(20);

        linearLayout.addView(thisTable);
        thisTable.setOnClickListener(new EmployeeClickListener());
        thisTable.addView(tempName);
        thisTable.addView(tempDetails);
//        Space space = new Space(tableContext);
//        space.setX(MATCH_PARENT);
//        space.setY(50);
//        thisTable.addView(space);
    }

    private String statusToText(){
        String stringStatus = "";
        switch (Status){
            case 0: {
                stringStatus = "Nothing";
                break;
            }
            case 1: {
                stringStatus = "Empty";
                break;
            }
            case 2: {
                stringStatus = "Sat";
                break;
            }
            case 3: {
                stringStatus = "Needs Cleaning";
                break;
            }
        }
        return stringStatus;
    }

    private final class EmployeeClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            dialogBuilder();
            saveTable();
        }
    }

    private void dialogBuilder(){
        final EditText input = new TextInputEditText(tableContext);
        AlertDialog.Builder builder = new AlertDialog.Builder(tableContext);
        builder.setTitle("Employee: " + employeeID);

        builder.setView(input);

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //employee list drop down box
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

    private void getEmployeeList(final int index, final shaneconnect.ShaneConnect s) {
        s.getTables(index, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    employee temp = new employee(response.getString("first"), response.getString("last"), response.getInt("ID"));
                    employeeList.add(temp);
                    getEmployeeList(index + 1, s);
                } catch (JSONException e) {
                    return;
                }
            }

        });

    }

    
}