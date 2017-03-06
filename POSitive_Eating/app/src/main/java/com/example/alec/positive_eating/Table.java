package com.example.alec.positive_eating;

import android.widget.TextView;

import com.android.volley.Response;
import com.example.shaneconnect.ShaneConnect;

import org.json.JSONObject;

import static android.R.attr.id;

/**
 * Created by ethantw on 3/2/2017.
 */

public class Table {
    private String ID;
    private int xPos;
    private int yPos;
    private int Status;
    private String employeeID;
    private String customerID;

    //Main default table constructor to build a table object
    public void Table(String ID, int xPOS, int yPOS, int Status, String employeeID, String customerID){
        this.ID = ID;
        this.xPos = xPOS;
        this.yPos = yPOS;
        this.Status = Status;
        this.employeeID = employeeID;
        this.customerID = customerID;

        saveTable();
    }

    //This is the default constructor for setting a table when the add table button gets pressed
    public void Table(String ID, int xPOS, int yPOS){
        Table(ID, xPOS, yPOS, 0, null, null);
    }

    //This is the default constructor for getting tables from the server if you already have an ID
    public void Table(String ID){
        ShaneConnect sc = new ShaneConnect("http://proj-309-yt-4.cs.iastate.edu:", this);
        sc.getTable(ID, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //call Table(ID, x, y, status, employeeid, customerid) with the retrieved data.
                TextView tv = (TextView) findViewById(R.id.text_edit);
                tv.setText(response.toString());
            }
        });
    }

    protected void saveTable(){
        ShaneConnect sc = new ShaneConnect("http://proj-309-yt-4.cs.iastate.edu:", this);
        sc.setTable(ID, xPos, yPos, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                TextView tv = (TextView) findViewById(R.id.text_edit);
                tv.setText(response.toString());
            }
        });
    }


    protected String setID(String ID){
        this.ID = ID;
        saveTable(); //save the new information to the server
    }

    protected void setX(int xPOS){
        this.xPos = xPOS;
        saveTable(); //save the new information to the server
    }

    protected void setY(int yPOS){
        this.yPos = yPOS;
        saveTable(); //save the new information to the server
    }

    protected void setStatus(int Status){
        this.Status = Status;
        saveTable(); //save the new information to the server
    }

    protected void setEmployeeID(String employeeID){
        this.employeeID = employeeID;
        saveTable(); //save the new information to the server
    }

    protected void setCustomerID(String customerID){
        this.customerID = customerID;
        saveTable(); //save the new information to the server
    }


    protected String getID(){
        return this.ID;
    }

    protected int getX(){
        return this.xPos;
    }

    protected  int getY(){
        return this.yPos;
    }

    protected  int getStatus(){
        return this.Status;
    }

    protected String getEmployeeID(){
        return this.employeeID;
    }

    protected  String getCustomerID(){
        return this.customerID;
    }

}
