package com.example.alec.positive_eating;

/**
 * Created by ethantw on 4/19/2017.
 */
public class Singleton_Current_Employee {
    private static Singleton_Current_Employee currentInstance = null;

    private static employee e = new employee("temp", "temp", -1);

    public static Singleton_Current_Employee getInstance(){
        if(currentInstance == null){
            currentInstance = new Singleton_Current_Employee();
        }
        return currentInstance;
    }

    public static employee getEmployee() {
        return e;
    }

    public void setEmployee(employee e){
        this.e = e;
    }
}
