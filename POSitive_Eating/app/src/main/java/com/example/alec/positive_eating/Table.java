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

    public void Table(String ID, int xPOS, int yPOS, int Status, String employeeID, String customerID){
        this.ID = ID;
        this.xPos = xPOS;
        this.yPos = yPOS;
        this.Status = Status;
        this.employeeID = employeeID;
        this.customerID = customerID;
    }

    public void Table(String ID, int xPOS, int yPOS){
        Table(ID, xPOS, yPOS, 0, null, null);
    }

    public void saveTable(){
        ShaneConnect sc = new ShaneConnect("http://proj-309-yt-4.cs.iastate.edu:", this);
        sc.setTable(ID, xPos, yPos, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                TextView tv = (TextView) findViewById(R.id.text_edit);
                tv.setText(response.toString());
            }
        });
    }

    protected void setX(int xPOS){
        this.xPos = xPOS;
    }

    protected void setY(int yPOS){
        this.yPos = yPOS;
    }

    protected void setStatus(int Status){
        this.Status = Status;
    }

    protected void setEmployeeID(String employeeID){
        this.employeeID = employeeID;
    }

    protected void setCustomerID(String customerID){
        this.customerID = customerID;
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
