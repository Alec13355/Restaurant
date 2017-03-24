//package com.example.alec.positive_eating;
///*
//@author Ethan Wieczorek
// */
//import android.app.Activity;
//import android.os.Bundle;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.RelativeLayout;
//
//import com.android.volley.Response;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//import static com.example.alec.positive_eating.Singleton_ShaneConnect_Factory.getShaneConnect;
//
///**
// * @author Ethan
// */
//public class tableMap extends Activity {
//    private List<Table> allTheTables = new ArrayList<>();
//    private ViewGroup mRootLayout;
//    private int index;
//    private boolean returnBool = false;
//    /**
//     * onCreate first updates based on the
//     * @param savedInstanceState
//     */
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_table_map);
//        mRootLayout = (RelativeLayout) findViewById(R.id.activity_table_map);
//
//        shaneconnect.ShaneConnect vista = getShaneConnect();
//        index = 0;
//        retrieveTables(index, vista);
//
//        //TODO get tables add to allthetables
//
//
//
//
//        Button tableAdd = (Button) findViewById(R.id.addTable);
//        tableAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                allTheTables.add(new Table(String.valueOf(allTheTables.size() + 1), tableMap.this, mRootLayout));
//            }
//        });
//
//        Button saveData = (Button) findViewById(R.id.sendToServer);
//        saveData.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//            if(!allTheTables.isEmpty()){
//                Iterator<Table> tableIterator = allTheTables.iterator();
//                while(tableIterator.hasNext()){
//                    tableIterator.next().saveTable();
//                }
//            }
//            }
//        });
//    }
//
//    public void retrieveTables(final int index, final shaneconnect.ShaneConnect s) {
//        s.getTables(index, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try{
//                    if(response.length()>0){
//                        Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
//                        allTheTables.add(new Table(response.getString("name"), response.getInt("x_coord"), response.getInt("y_coord"), tableMap.this, mRootLayout));
//                        retrieveTables(index+1,s);
//                    }
//                } catch (JSONException e) {
//                    return;
//                }
//            }
//
//        });
//    }
//}
