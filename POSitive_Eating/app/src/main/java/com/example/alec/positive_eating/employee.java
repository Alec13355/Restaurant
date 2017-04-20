package com.example.alec.positive_eating;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.view.Gravity.CENTER_HORIZONTAL;
import static com.example.alec.positive_eating.Singleton_Current_Employee.getInstance;

/**
 * Created by ethantw on 4/18/2017.
 */

public class employee {
    String first;
    String last;
    int ID;
    private String address;
    private String phoneNumber;
    private int rate;
    private String pass;
    private boolean visible;
    private int permissions;

    //List items
    private TextView tempDetails;
    private TextView passwordView;
    private LinearLayout thisEmployee;

    employee(String first, String last, int ID){
        this.first = first;
        this.last = last;
        this.ID = ID;
    }

    employee(String first, String last, int ID, String addreass, String phone, int rate, String pass, int permissions){
        this.first = first;
        this.last = last;
        this.ID = ID;
        this.address = address;
        this.phoneNumber = phone;
        this.rate = rate;
        this.pass = pass;
        this.visible = true;
        this.permissions = permissions;
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
        this.address = availability;
    }

    public String getAvailability(){
        return address;
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

    /*
    Password
    */
    public void setPermissions(int permissions){
        this.permissions = permissions;
    }

    public int getPermissions(){
        return permissions;
    }

    public void addListItem(ViewGroup mListLayout, Context context){
        this.tempDetails = new TextView(context);

        LinearLayout linearLayout = (LinearLayout) mListLayout;
        thisEmployee = new LinearLayout(context);
        thisEmployee.setGravity(CENTER_HORIZONTAL);
        thisEmployee.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        thisEmployee.setOrientation(LinearLayout.VERTICAL);

        //Add Employee Name
        String tempNameString = getFirst() + " " + getLast();
        TextView tempName = new TextView(context);
        tempName.setGravity(CENTER_HORIZONTAL);
        tempName.setText(tempNameString);
        tempName.setTextColor(Color.BLACK);
        tempName.setTextSize(40);

        String tempDetailString = "";
        if(getInstance().getEmployee().getPermissions() == 0) {
            tempDetailString += "ID: " + getID() + "\n";
        }
        tempDetailString +=  "Availability: " + getAvailability() + "\nPhone Number: " + getPhone() + "\nPermission Level: " + getPermissions();
        tempDetails = new TextView(context);
        tempDetails.setText(tempDetailString);
        tempDetails.setTextColor(Color.BLACK);
        tempDetails.setTextSize(20);

        String tempPasswordString =  "Password: " + pass;
        passwordView = new TextView(context);
        passwordView.setText(tempPasswordString);
        passwordView.setTextColor(Color.BLACK);
        passwordView.setTextSize(20);

        linearLayout.addView(thisEmployee);

        //This is where you would set a click listener (example would be to delete employees)
        //thisTable.setOnClickListener(new Table.EmployeeClickListener());

        thisEmployee.addView(tempName);
        thisEmployee.addView(tempDetails);
    }

    public void changePasswordVisibility(){
        if(!visible){
            thisEmployee.removeView(passwordView);
            visible = true;
        }else{
            thisEmployee.addView(passwordView);
            visible = false;
        }
    }
}
