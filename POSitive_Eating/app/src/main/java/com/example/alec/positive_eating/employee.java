package com.example.alec.positive_eating;

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
}
