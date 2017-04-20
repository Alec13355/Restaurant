package com.example.alec.positive_eating;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ethantw on 4/19/2017.
 */

public class Singleton_Table_List {
    private static Singleton_Table_List currentInstance = null;

    private static List<Table> tList = new ArrayList<>();

    public static Singleton_Table_List getTableListInstance(){
        if(currentInstance == null){
            currentInstance = new Singleton_Table_List();
        }
        return currentInstance;
    }

    public static List<Table> getTList() {
        return tList;
    }

    public void setTList(List<Table> tList){
        this.tList = tList;
    }
}
