package com.example.alec.positive_eating;

import android.content.Context;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import shaneconnect.ShaneConnect;
import org.json.JSONObject;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;;

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
    private String employeeID;
    private String customerID;
    private Context tableContext;
    private ViewGroup mRootLayout;

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
    Table(String ID, int xPOS, int yPOS, int Status, String employeeID, String customerID, Context context, ViewGroup mRootLayout) {
        this.ID = ID;
        this.xPos = xPOS;
        this.yPos = yPOS;
        this.Status = Status;
        this.employeeID = employeeID;
        this.customerID = customerID;
        this.tableContext = context;
        this.mRootLayout = mRootLayout;
        drawTable();
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
        this(ID, xPOS, yPOS, 0, "", "", context, mRootLayout);
    }

    Table(String ID, Context context, ViewGroup mRootLayout){
        this(ID, 10, 10, 0, "", "", context, mRootLayout);
    }

    /**
     * Saves a table to the database
     */
    protected void saveTable() {
        shaneconnect.ShaneConnect vista = getShaneConnect();
        vista.setTable(ID, xPos, yPos, 4, Status, new Response.Listener<JSONObject>() {
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
    protected void setEmployeeID(String employeeID) {
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
    protected String getEmployeeID() {
        updateTable();
        return this.employeeID;
    }

    /**
     * Gets the customerID of this table after making sure the table is updated with the server.
     *
     * @return customerID
     */
    protected String getCustomerID() {
        updateTable();
        return this.customerID;
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
        FrameLayout tempFrame = new FrameLayout(tableContext);

        ImageView temp = new ImageView(tableContext);
        temp.setImageResource(R.drawable.squaretable);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(150, 150);
        temp.setLayoutParams(layoutParams);
        //temp.setId(allTables.size()); //the id for the button we be it's index in the array + 1, this way there is no table 0 when it comes to labeling.
        tempFrame.addView(temp);

        //This is adding a label above the imageView so you know which table it is
        TextView tempName = new TextView(tableContext);
        tempName.setHeight(temp.getHeight());
        tempName.setWidth(temp.getWidth());
        tempName.setText(String.valueOf(ID));
        tempName.setTextColor(Color.BLACK);

        RelativeLayout.LayoutParams textLayoutParams = new RelativeLayout.LayoutParams(150,150);
        textLayoutParams.addRule(RelativeLayout.ALIGN_LEFT, temp.getId());
        textLayoutParams.addRule(RelativeLayout.ALIGN_RIGHT, temp.getId());
        textLayoutParams.addRule(RelativeLayout.ALIGN_TOP, temp.getId());
        textLayoutParams.addRule(RelativeLayout.ALIGN_BOTTOM, temp.getId());
        textLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        tempName.setLayoutParams(textLayoutParams);
        tempFrame.addView(tempName);

        //System.out.println(String.valueOf(allTables.size())); //debug statement

        tempFrame.setOnTouchListener(new MyTouchListener());
        mRootLayout.addView(tempFrame);
        saveTable();
    }

    private final class MyTouchListener implements View.OnTouchListener {
        private int _xDelta;
        private int _yDelta;
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
                    layoutParams.rightMargin = 350;
                    layoutParams.bottomMargin = 350;
                    view.setLayoutParams(layoutParams);
                    System.out.println("X: " + String.valueOf(X) + ", Y " + String.valueOf(Y)); //debug
                    break;
            }
//            setX(X);
//            setY(Y);
            //mRootLayout.invalidate();
            return true;
        }
    }
}