package com.example.alec.positive_eating;

import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.view.Gravity.CENTER_HORIZONTAL;

/**
 * Created by ethantw on 4/18/2017.
 */

public class employee {
    String first;
    String last;
    int ID;
    private String availability;
    private String phoneNumber;
    private int rate;
    private String pass;

    //List items
    private TextView tempDetails;

    employee(String first, String last, int ID){
        this.first = first;
        this.last = last;
        this.ID = ID;
    }

    employee(String first, String last, int ID, String availability, String phone, int rate, String pass){
        this.first = first;
        this.last = last;
        this.ID = ID;
        this.availability = availability;
        this.phoneNumber = phone;
        this.rate = rate;
        this.pass = pass;
    }
    /*
    First Name
     */
    public void setFirst(String first){
        this.first = first;
    }

    public String getFirst(){
        return first;
    }

    /*
    Last Name
     */
    public void setLast(String last){
        this.last = last;
    }

    public String getLast(){
        return last;
    }

    /*
    Employee ID
     */
    public void setID(int ID){
        this.ID = ID;
    }

    public int getID(){
        return ID;
    }

    /*
    Availability
     */
    public void setAvailability(String availability){
        this.availability = availability;
    }

    public String getAvailability(){
        return availability;
    }

    /*
    Phone Number
     */
    public void setPhone(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public String  getPhone(){
        return phoneNumber;
    }

    /*
    Pay Rate
     */
    public void setRate(int rate){
        this.rate = rate;
    }

    public int getRate(){
        return rate;
    }

    /*
    Password
     */
    public void setPassword(String pass){
        this.pass = pass;
    }

    public String getPassword(){
        return pass;
    }


    public void addListItem(ViewGroup mListLayout, Context context){
        this.tempDetails = new TextView(context);

        LinearLayout linearLayout = (LinearLayout) mListLayout;
        LinearLayout thisTable = new LinearLayout(context);
        thisTable.setGravity(CENTER_HORIZONTAL);
        thisTable.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        thisTable.setOrientation(LinearLayout.VERTICAL);

        //Add Employee Name
        String tempNameString = getFirst() + " " + getLast();
        TextView tempName = new TextView(context);
        tempName.setGravity(CENTER_HORIZONTAL);
        tempName.setText(tempNameString);
        tempName.setTextColor(Color.BLACK);
        tempName.setTextSize(40);

        String tempDetailString =  "ID: " + getID() + "\nAvailability: " + getAvailability() + "\nPhone Number: " + getPhone() + "\nPassword: " + getPassword();
        tempDetails = new TextView(context);
        tempDetails.setText(tempDetailString);
        tempDetails.setTextColor(Color.BLACK);
        tempDetails.setTextSize(20);

        linearLayout.addView(thisTable);

        //This is where you would set a click listener (example would be to delete employees)
        //thisTable.setOnClickListener(new Table.EmployeeClickListener());

        thisTable.addView(tempName);
        thisTable.addView(tempDetails);
    }
}
