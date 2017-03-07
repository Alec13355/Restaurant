package com.example.shane.shaneconnect;

/**
 * Created by shane on 3/7/17.
 */

public class TableReservation implements Comparable<TableReservation> {
    private String desc;
    private int id;
    private int table_id;
    private int status;


    public TableReservation(){

    }
    public String getDesc(){
        return this.desc;
    }
    public int getID(){
        return this.id;
    }
    public int get_table_ID(){
        return this.table_id;
    }
    public int getStatus(){
        return this.status;
    }



    @Override
    public int compareTo(TableReservation o) {
        return this.id - o.getID();
    }
}
