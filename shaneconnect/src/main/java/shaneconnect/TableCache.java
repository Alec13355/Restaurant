package shaneconnect;

import com.android.volley.Response;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Created by shane on 3/7/17.
 */

/**
 * A table reservation mechanism to cache table reservations and store a cache for the corresponding tables
 */
public class TableCache {

    private ArrayList<PriorityQueue<TableReservation>> list;

    private ShaneConnect connect;

    /**
     * The constructor
     * @param connect A ShaneConnect obj
     */
    public TableCache(ShaneConnect connect){
        list = new ArrayList<PriorityQueue<TableReservation>>();
        this.connect=connect;
    }

    /**
     * Inserts the table reservation into local que and updates the server
     * @param res The table reservation you would like to enter
     */
    public void insertReservation(final TableReservation res){
        connect.getTableByID(res.get_table_ID(),new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(list.get(response.getInt("number_seats")) == null){
                        list.add(response.getInt("number_seats"),new PriorityQueue<TableReservation>());
                    }
                    list.get(response.getInt("number_seats")).add(res);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                connect.placeReservation(res.getDesc(),res.get_table_ID(),res.getStatus(),new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("Updated the server");
                    }
                });
            }
        });

    }

    /**
     * Updates the local cache with the server, any reservation with status of 3 is considered done
     */
    public void updateQue(){
        updateQue(0);
    }

    private void updateQue(final int index){
        connect.getReservation(index,new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(final JSONObject reservationInfo) {
                if(!(reservationInfo==null || reservationInfo.isNull("TABLE_ID"))){
                    try {
                        connect.getTableByID(reservationInfo.getInt("TABLE_ID"),new Response.Listener<JSONObject>(){
                            @Override
                            public void onResponse(JSONObject tableinfo) {
                                try {
                                    PriorityQueue<TableReservation> temp = list.get(tableinfo.getInt("number_seats"));
                                    TableReservation res = new TableReservation(reservationInfo.getString("DESCRIPTION"),reservationInfo.getInt("ID"), reservationInfo.getInt("TABLE_ID"), reservationInfo.getInt("STATUS"));
                                    if(temp.contains(res)){
                                        temp.remove(res);
                                        if(res.getStatus()!=3){
                                            temp.add(res);
                                        }
                                    }else{
                                        temp.add(res);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                updateQue(index+1);
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }

    /**
     * Method to get the top of the corresponding que for the table size but does not remove it.
     * @param tableSize The table size
     * @return TableReservation or null if is empty or no reservations exist for the table size
     */
    public TableReservation peekReservation(int tableSize){
        if(list.get(tableSize)!=null){
            return list.get(tableSize).peek();
        }else{
            return null;
        }
    }

    /**
     * Method to get the top of the corresponding que for the table size but does remove it.
     * @param tableSize The table size
     * @return TableReservation or null if is empty or no reservations exist for the table size
     */
    public TableReservation pollReservation(int tableSize){
        if(list.get(tableSize)!=null){
            return list.get(tableSize).poll();
        }else{
            return null;
        }
    }







}
