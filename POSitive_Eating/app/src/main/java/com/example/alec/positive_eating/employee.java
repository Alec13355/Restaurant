package com.example.alec.positive_eating;

/**
 * Created by ethantw on 4/18/2017.
 */

public class employee {
    String first;
    String last;
    int ID;

    employee(String first, String last, int ID){
        this.first = first;
        this.last = last;
        this.ID = ID;
    }

    public void setFirst(String first){
        this.first = first;
    }

    public void setLast(String last){
        this.last = last;
    }

    public void setID(int ID){
        this.ID = ID;
    }

    public String getFirst(){
        return first;
    }

    public String getLast(){
        return last;
    }

    public int getID(){
        return ID;
    }
}
