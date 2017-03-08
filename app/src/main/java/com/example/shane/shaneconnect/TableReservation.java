package com.example.shane.shaneconnect;

/**
 * Created by shane on 3/7/17.
 */

public class TableReservation implements Comparable<TableReservation> {
    private String desc;
    private int id;
    private int table_id;
    private int status;


    public TableReservation(String desc,int id,int table_id,int status){
        this.desc=desc;
        this.id=id;
        this.table_id=table_id;
        this.status=status;
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

    public void updateData(String newDesc,int table_id,int stat){
        this.desc=newDesc;
        this.table_id=table_id;
        this.status=stat;
    }



    @Override
    public int compareTo(TableReservation o) {
        return this.id - o.getID();
    }
}
