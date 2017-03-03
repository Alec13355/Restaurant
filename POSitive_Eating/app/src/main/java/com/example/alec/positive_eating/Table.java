package com.example.alec.positive_eating;

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

    public void Table(String ID, int xPOS, int yPOS){
        this.ID = ID;
        this.xPos = xPOS;
        this.yPos = yPOS;
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
