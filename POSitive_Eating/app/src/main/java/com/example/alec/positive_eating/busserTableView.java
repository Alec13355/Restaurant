package com.example.alec.positive_eating;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.example.shaneconnect.ShaneConnect;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static android.os.Build.ID;

/**
 * This is the busser table view. All tables can be clicked on to change the status of the table for seating purposes. Statuses: Seated, Empty, Needs Cleaning.
 * @author Ethan
 */
public class busserTableView extends AppCompatActivity {

    List<Table> tables = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_table_view);

        //get all tables from the server
        ShaneConnect sc = new ShaneConnect("http://proj-309-yt-4.cs.iastate.edu:", this);
        sc.getTables(ID, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                //call Table(ID, x, y, status, employeeid, customerid) with the retrieved data.
                //add this table to tables.

                TextView tv = (TextView) findViewById(R.id.text_edit);
                tv.setText(response.toString());
            }
        });

        if (!allTables.isEmpty()) { //TODO if server table is not empty
            String id;
            int tempX, tempY;
            Iterator iterator = allTables.iterator();
            while (iterator.hasNext()) {
                //add to server
                View tempView = (View) iterator.next();
                id = String.valueOf(tempView.getId()); //server
                tempX = (int) tempView.getX(); //server
                tempY = (int) tempView.getY(); //server


                FrameLayout tempFrame = new FrameLayout(viewTableMap.this);

                ImageView temp = new ImageView(viewTableMap.this);
                temp.setImageResource(R.drawable.squaretable);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(150, 150);
                temp.setLayoutParams(layoutParams);
                temp.setX((float) tempX);
                temp.setY((float) tempY);
                temp.setId(allTables.size()); //the id for the button we be it's index in the array + 1, this way there is no table 0 when it comes to labeling.
                tempFrame.addView(temp);

                //This is adding a label above the imageView so you know which table it is
                TextView tempName = new TextView(viewTableMap.this);
                tempName.setHeight(temp.getHeight());
                tempName.setWidth(temp.getWidth());
                tempName.setText(id);
                tempName.setTextColor(Color.BLACK);

                RelativeLayout.LayoutParams textLayoutParams = new RelativeLayout.LayoutParams(150,150);
                textLayoutParams.addRule(RelativeLayout.ALIGN_LEFT, temp.getId());
                textLayoutParams.addRule(RelativeLayout.ALIGN_RIGHT, temp.getId());
                textLayoutParams.addRule(RelativeLayout.ALIGN_TOP, temp.getId());
                textLayoutParams.addRule(RelativeLayout.ALIGN_BOTTOM, temp.getId());
                textLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
                tempName.setLayoutParams(textLayoutParams);
                tempFrame.addView(tempName);

                //System.out.println(String.valueOf(allTables.size())); //debug statement

                mRootLayout.addView(tempFrame);
                allTables.add(tempFrame);
            }
        }


    }
    private final class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent event) {
            final int X = (int) event.getRawX();
            final int Y = (int) event.getRawY();
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    break;
                case MotionEvent.ACTION_MOVE:
                    break;
            }
            mRootLayout.invalidate();
            return true;
        }
    }
}
