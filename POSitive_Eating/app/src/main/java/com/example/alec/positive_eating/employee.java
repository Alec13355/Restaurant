package com.example.alec.positive_eating;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.view.Gravity.CENTER_HORIZONTAL;
import static com.example.alec.positive_eating.Singleton_Current_Employee.getEInstance;

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

    private Context context;
    private Spinner optionsSpinner;

    employee(String first, String last, int ID){
        this.first = first;
        this.last = last;
        this.ID = ID;
    }

    employee(String first, String last, int ID, String address, String phone, int rate, String pass, int permissions){
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
    public void setThisEmployee(LinearLayout thisEmployee){
        this.thisEmployee = thisEmployee;
    }

    public LinearLayout getThisEmployee(){
        return thisEmployee;
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
        this.context = context;

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
        if(getEInstance().getEmployee().getPermissions() == 0) {
            tempDetailString += "ID: " + getID() + "\nPermission Level: " + getPermissions() + "\n";
        }
        tempDetailString +=  "Availability: " + getAvailability() + "\nPhone Number: " + getPhone();
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
        if(getEInstance().getEmployee().getPermissions() == 0) {
            thisEmployee.setOnClickListener(new OptionsClickListener());
        }
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

    private void updateText(){
        String tempDetailString = "";
        if(getEInstance().getEmployee().getPermissions() == 0) {
            tempDetailString += "ID: " + getID() + "\nPermission Level: " + getPermissions() + "\n";
            String tempPasswordString =  "Password: " + pass;
            passwordView.setText(tempPasswordString);
        }
        tempDetailString +=  "Availability: " + getAvailability() + "\nPhone Number: " + getPhone();
        tempDetails.setText(tempDetailString);
    }

    private final class OptionsClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            dialogBuilder();
        }
    }

    private void dialogBuilder(){
        List<String> spinnerList = new ArrayList<>();

        spinnerList.add("Permission Level");
        spinnerList.add("Address");
        spinnerList.add("Phone Number");
        spinnerList.add("Password");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, spinnerList);
        optionsSpinner = new Spinner(context);
        optionsSpinner.setAdapter(adapter);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Select Item To Edit");

        builder.setView(optionsSpinner);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String oneUse = (String) optionsSpinner.getSelectedItem();
                changeSettings(oneUse);
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

    private void changeSettings(String option){
        switch (option) {
            case ("Permission Level") : {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(option);

                final EditText input = new EditText(context);

                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setView(input);

                builder.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Pattern p = Pattern.compile("(\\d)");
                        Matcher m = p.matcher(input.getText().toString());
                        if (m.find()){
                            if(input.getText().toString().equals("0") || input.getText().toString().equals("1") || input.getText().toString().equals("2") || input.getText().toString().equals("3") || input.getText().toString().equals("4")){
                                permissions = Integer.parseInt(input.getText().toString());
                                updateText();
                            }else{
                                Toast.makeText(context, "Invalid Input", Toast.LENGTH_SHORT);
                                changeSettings("Permission Level");
                            }
                        }else{
                            Toast.makeText(context, "Invalid Input", Toast.LENGTH_SHORT);
                            changeSettings("Permission Level");
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
                break;
            }
            case ("Address") : {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(option);

                final EditText input = new EditText(context);

                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                builder.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Pattern p = Pattern.compile("(\\w+)");
                        Matcher m = p.matcher(input.getText().toString());
                        if (m.find()){
                            address = input.getText().toString();
                            updateText();
                        }else{
                            Toast.makeText(context, "Invalid Input", Toast.LENGTH_SHORT);
                            changeSettings("Phone Number");
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
                break;
            }
            case ("Phone Number") : {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(option);

                final EditText input = new EditText(context);

                input.setInputType(InputType.TYPE_CLASS_PHONE);
                builder.setView(input);

                builder.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Pattern p = Pattern.compile("(\\d+)");
                        Matcher m = p.matcher(input.getText().toString());
                        if (m.find()){
                            if(input.getText().toString().length() == 10 || input.getText().toString().length() == 11) {
                                phoneNumber = input.getText().toString();
                                updateText();
                            }else{
                                Toast.makeText(context, "Invalid Input", Toast.LENGTH_SHORT);
                                changeSettings("Phone Number");
                            }
                        }else{
                            Toast.makeText(context, "Invalid Input", Toast.LENGTH_SHORT);
                            changeSettings("Phone Number");
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
                break;
            }
            case ("Password") : {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(option);

                final EditText input = new EditText(context);

                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                builder.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Pattern p = Pattern.compile("(\\w+)");
                        Matcher m = p.matcher(input.getText().toString());
                        if (m.find()){
                            pass = input.getText().toString();
                            updateText();
                        }else{
                            Toast.makeText(context, "Invalid Input", Toast.LENGTH_SHORT);
                            changeSettings("Phone Number");
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
                break;
            }
        }
        return;
    }
}
