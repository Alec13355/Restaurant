package com.example.alec.positive_eating;

import android.widget.TextView;

import com.android.volley.Response;
import com.example.shane.shaneconnect.ShaneConnect;

import org.json.JSONObject;

import static android.R.attr.id;

/**
 * Created by ethantw on 3/2/2017.
 * @author Ethan
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
        ShaneConnect sc = new ShaneConnect("http://proj-309-yt-4.cs.iastate.edu:", this);
        sc.changeTableX(ID, xPOS, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                TextView tv = (TextView) findViewById(R.id.text_edit);
                tv.setText(response.toString());
            }
        });
    }

    protected void setY(int yPOS){
        this.yPos = yPOS;
        ShaneConnect sc = new ShaneConnect("http://proj-309-yt-4.cs.iastate.edu:", this);
        sc.changeTableY(ID, yPOS, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                TextView tv = (TextView) findViewById(R.id.text_edit);
                tv.setText(response.toString());
            }
        });
    }

    protected void setStatus(int Status){
        this.Status = Status;
        ShaneConnect sc = new ShaneConnect("http://proj-309-yt-4.cs.iastate.edu:", this);
        sc.changeTableStatus(ID, Status, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                TextView tv = (TextView) findViewById(R.id.text_edit);
                tv.setText(response.toString());
            }
        });
    }

    protected void setEmployeeID(String employeeID){
        this.employeeID = employeeID;
        ShaneConnect sc = new ShaneConnect("http://proj-309-yt-4.cs.iastate.edu:", this);
        sc.changeTableEmployeeID(ID, employeeID, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                TextView tv = (TextView) findViewById(R.id.text_edit);
                tv.setText(response.toString());
            }
        });
    }

    protected void setCustomerID(String customerID){
        this.customerID = customerID;
        ShaneConnect sc = new ShaneConnect("http://proj-309-yt-4.cs.iastate.edu:", this);
        sc.changeTableCustomerID(ID, customerID, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                TextView tv = (TextView) findViewById(R.id.text_edit);
                tv.setText(response.toString());
            }
        });
    }


    protected String getID(){
        updateTable();
        return this.ID;
    }

    protected int getX(){
        updateTable();
        return this.xPos;
    }

    protected  int getY(){
        updateTable();
        return this.yPos;
    }

    protected  int getStatus(){
        updateTable();
        return this.Status;
    }

    protected String getEmployeeID(){
        updateTable();
        return this.employeeID;
    }

    protected  String getCustomerID(){
        updateTable();
        return this.customerID;
    }

    protected void updateTable(){
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

}
