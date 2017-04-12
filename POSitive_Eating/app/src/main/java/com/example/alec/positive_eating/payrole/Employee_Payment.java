package com.example.alec.positive_eating.payrole;

//import shaneconnect.ShaneConnectEmployee;

/**
 * Created by shane on 4/11/17.
 */

public class Employee_Payment { //extends ShaneConnectEmployee

    private int amountDue;

    private int milliseconds;

    private int totalTime;

    private int payRate;

    public Employee_Payment(String first,String last,int id, int payRate) {
       // super(first,last,id);
        this.payRate = payRate;
        this.amountDue =0;
    }

    public int getAmountDue(){
        return this.amountDue;
    }

    public void addTime(int milliSeconds){
        if(this.milliseconds>0){
            this.totalTime += this.milliseconds - milliSeconds;
            this.milliseconds = 0;
        }
    }

    public void calculateAmountDue(){
        this.amountDue = (this.totalTime / 3600000) * (this.payRate/100);
    }


}
