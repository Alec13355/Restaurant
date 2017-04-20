package com.example.alec.positive_eating;

import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ethantw on 4/19/2017.
 */

public class Singleton_Employee_List {
    private static Singleton_Employee_List currentInstance = null;

    private static List<employee> eList = new ArrayList<>();

    public static Singleton_Employee_List getListInstance(){
        if(currentInstance == null){
            currentInstance = new Singleton_Employee_List();
        }
        return currentInstance;
    }

    public static List<employee> getEList() {
        return eList;
    }

    public void setEList(List<employee> eList){
        this.eList = eList;
    }
}
