package com.example.alec.positive_eating;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.location.Address;
//import android.support.v4.content.ModernAsyncTask;
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

import com.android.volley.Response;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import shaneconnect.ConcreteCommand;
import shaneconnect.DeleteEmployee;
import shaneconnect.ShaneConnect;

import static android.view.Gravity.CENTER;
import static android.view.Gravity.CENTER_HORIZONTAL;
import static com.example.alec.positive_eating.Singleton_Current_Employee.getEInstance;
import static com.example.alec.positive_eating.Singleton_ShaneConnect_Factory.getShaneConnect;

/**
 * Created by ethantw on 4/18/2017.
 */

public class employee {
    String first;
    String last;
    int ID;
    private String schedule;
    private String phoneNumber;
    private int rate;
    private String pass;
    private boolean visible;
    private int permissions;
    private String social;
    private String bankAccount;
    private String routing;


    //List items
    private TextView tempDetails;
    private TextView passwordView;
    private LinearLayout thisEmployee;

    private Context context;
    private Spinner optionsSpinner;

    private HashMap<Integer, String> permissionLevel;

    employee(String first, String last, int ID){
        this.first = first;
        this.last = last;
        this.ID = ID;
    }

    employee(String first, String last, int ID, String schedule, String phone, int rate, String pass, int permissions, String social, String bankAccount, String routing){
        this.first = first;
        this.last = last;
        this.ID = ID;
        this.schedule = schedule;
        this.phoneNumber = phone;
        this.rate = rate;
        this.pass = pass;
        this.visible = true;
        this.permissions = permissions;
        this.social = social;
        this.bankAccount = bankAccount;
        this.routing = routing;
        permissionLevel = new HashMap<>();
            permissionLevel.put(0, "Manager");
            permissionLevel.put(1, "Waitstaff");
            permissionLevel.put(2, "Host");
            permissionLevel.put(3, "Busser");
            permissionLevel.put(4, "Kitchen");
            permissionLevel.put(5, "Bartender");
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
    public void setSchedule(String schedule){
        this.schedule = schedule;
    }

    public String getSchedule(){
        return schedule;
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
        String tempNameString = "Employee: " + getFirst() + " " + getLast();
        TextView tempName = new TextView(context);
        tempName.setGravity(CENTER_HORIZONTAL);
        tempName.setText(tempNameString);
        tempName.setTextColor(Color.BLACK);
        tempName.setTextSize(40);

        String tempDetailString = "";
        if(getEInstance().getEmployee().getPermissions() == 0) {
            tempDetailString += "ID: " + getID() + "\nPermission Level: " + getPermissions() + ", " + permissionLevel.get(getPermissions()) + "\nSSN: " + social + "\nRouting Number: " + routing + "\nAccount Number: " + bankAccount + "\n";
        }
        tempDetailString +=  "Schedule: " + getSchedule() + "\nPhone Number: " + getPhone();
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
            tempDetailString += "ID: " + getID() + "\nPermission Level: " + getPermissions() + ", " + permissionLevel.get(getPermissions()) + "\nSSN: " + social + "\nRouting Number: " + routing + "\nAccount Number: " + bankAccount + "\n";
            String tempPasswordString =  "Password: " + pass;
            passwordView.setText(tempPasswordString);
        }
        tempDetailString +=  "Schedule: " + getSchedule() + "\nPhone Number: " + getPhone();
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
        spinnerList.add("Schedule");
        spinnerList.add("Phone Number");
        spinnerList.add("Password");
        spinnerList.add("Account Number");
        spinnerList.add("Routing Number");
        if(getEInstance().getEmployee().getID() != ID) {
            spinnerList.add("DELETE EMPLOYEE");
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, spinnerList);
        optionsSpinner = new Spinner(context);
        optionsSpinner.setAdapter(adapter);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        String tempNameString = "Edit Employee: " + getFirst() + " " + getLast();
        builder.setTitle(tempNameString);

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
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Edit " + option);
        switch (option) {
            case ("Permission Level") : {
                final EditText input = new EditText(context);

                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setView(input);

                builder.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Pattern p = Pattern.compile("(\\d)");
                        Matcher m = p.matcher(input.getText().toString());
                        if (m.find()){
                            if(input.getText().toString().equals("0") || input.getText().toString().equals("1") || input.getText().toString().equals("2") || input.getText().toString().equals("3") || input.getText().toString().equals("4") || input.getText().toString().equals("5") ){
                                permissions = Integer.parseInt(input.getText().toString());
                                updateText();
                                saveEmployee();
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
                break;
            }
            case ("Schedule") : {
                final EditText input = new EditText(context);

                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                builder.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Pattern p = Pattern.compile("(\\w+)");
                        Matcher m = p.matcher(input.getText().toString());
                        if (m.find()){
                            schedule = input.getText().toString();
                            updateText();
                            saveEmployee();
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
                break;
            }
            case ("Phone Number") : {
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
                                saveEmployee();
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
                break;
            }
            case ("Password") : {
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
                            saveEmployee();
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
                break;
            }
            case ("Account Number") : {
                final EditText input = new EditText(context);
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setView(input);
                builder.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Pattern p = Pattern.compile("(\\d)");
                        Matcher m = p.matcher(input.getText().toString());
                        if (m.find()){
                            bankAccount = input.getText().toString();
                            updateText();
                            saveEmployee();
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
                break;
            }
            case ("Routing Number") : {
                final EditText input = new EditText(context);
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setView(input);
                builder.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Pattern p = Pattern.compile("(\\d)");
                        Matcher m = p.matcher(input.getText().toString());
                        if (m.find()){
                            bankAccount = input.getText().toString();
                            updateText();
                            saveEmployee();
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
                break;
            }
            case ("DELETE EMPLOYEE") : {
                builder.setTitle("WARNING!");
                TextView output = new TextView(context);
                String stringStatus = "Are you sure you want to delete user:\n" + first + " " + last;
                output.setText(stringStatus);
                output.setGravity(CENTER);

                builder.setView(output);

                builder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteUser();
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
        builder.show();
        return;
    }

    private void deleteUser() {

        ShaneConnect a = getShaneConnect();

        ConcreteCommand b = new ConcreteCommand();
        new DeleteEmployee(a,first,last,String.valueOf(ID),b).exectute(new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        });

        thisEmployee.setVisibility(View.INVISIBLE);
    }

    //TODO save employee updates to database
    private void saveEmployee() {
        ShaneConnect vista = getShaneConnect();
        vista.createAccount(last,first,"DontKnow", routing, social,bankAccount, permissions, pass, schedule,"Not relevant",phoneNumber,rate,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
