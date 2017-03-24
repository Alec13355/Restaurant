package com.example.alec.positive_eating;
/*
@author Ethan Wieczorek
 */
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.example.alec.positive_eating.Table;
/**
 * @author Ethan
 */
public class tableMap extends Activity {
    private List<Table> allTheTables = new ArrayList<>();
    private ViewGroup mRootLayout;
    private int _xDelta;
    private int _yDelta;

    /**
     * onCreate first updates based on the
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_map);
        mRootLayout = (RelativeLayout) findViewById(R.id.activity_table_map);

        
        //TODO get tables add to allthetables


        Button tableAdd = (Button) findViewById(R.id.addTable);
        tableAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allTheTables.add(new Table(String.valueOf(allTheTables.size() + 1), tableMap.this, mRootLayout));
            }
        });

        Button saveData = (Button) findViewById(R.id.sendToServer);
        saveData.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            if(!allTheTables.isEmpty()){
                Iterator<Table> tableIterator = allTheTables.iterator();
                while(tableIterator.hasNext()){
                    tableIterator.next().saveTable();
                }
            }
            }
        });
    }


    /**
     * MyTouchListener is the drag and drop listener that allows the user to move a view around another view.
     * @PARAMS View view: any XML view that isn't the top level.
     */
    private final class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent event) {
            final int X = (int) event.getRawX();
            final int Y = (int) event.getRawY();
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    _xDelta = X - lParams.leftMargin;
                    _yDelta = Y - lParams.topMargin;
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    break;
                case MotionEvent.ACTION_MOVE:
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    layoutParams.leftMargin = X - _xDelta;
                    layoutParams.topMargin = Y - _yDelta;
                    layoutParams.rightMargin = 350;
                    layoutParams.bottomMargin = 350;
                    view.setLayoutParams(layoutParams);
                    System.out.println("X: " + String.valueOf(view.getX()) + ", Y: " + String.valueOf(view.getY())); //debug
                    break;
            }
            mRootLayout.invalidate();
            return true;
        }
    }

//    public void addNewTable(){
//        ImageView temp = new ImageView(this);
//        temp.setImageResource(R.drawable.squaretable);
//        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(150, 150);
//        temp.setLayoutParams(layoutParams);
//        temp.setOnTouchListener(new MyTouchListener());
//        allTables.add(temp);
//        mRootLayout.addView(temp);
//    }
//
//    public void saveTables(View view){
//        if(!allTables.isEmpty()) {
//            Iterator iterator = allTables.iterator();
//            while (iterator.hasNext()) {
//                //add to server
//            }
//        }
//    }
}
