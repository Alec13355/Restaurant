package com.example.shane.shaneconnect;



/**
 * Represents a TableReservation
 */
public class TableReservation implements Comparable<TableReservation> {
    /**
     * The description
     */
    private String desc;
    /**
     * id of the reservation
     */
    private int id;
    /**
     * the table ID
     */
    private int table_id;
    /**
     * the status of the reservation, 3 is considered done
     */
    private int status;

    /**
     * The constructor
     * @param desc the description
     * @param id the id of the reservation
     * @param table_id the id of the table
     * @param status the current status of the reservations, status 3 means the reservation is done
     */
    public TableReservation(String desc,int id,int table_id,int status){
        this.desc=desc;
        this.id=id;
        this.table_id=table_id;
        this.status=status;
    }

    /**
     *
     * @return the description
     */
    public String getDesc(){
        return this.desc;
    }

    /**
     *
     * @return gets the id
     */
    public int getID(){
        return this.id;
    }

    /**
     *
     * @return gets the table ID
     */
    public int get_table_ID(){
        return this.table_id;
    }

    /**
     *
     * @return gets the status
     */
    public int getStatus(){
        return this.status;
    }

    /**
     * Updates table reservation
     * @param newDesc new description
     * @param table_id new table ID
     * @param stat new stats
     */
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
