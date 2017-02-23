package com.example.alec.positive_eating;
/*
@author Ethan Wieczorek
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class tableMap extends Activity {
    private List<View> allTables = new ArrayList<>();
    private ViewGroup mRootLayout;
    private int _xDelta;
    private int _yDelta;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_map);
        mRootLayout = (RelativeLayout) findViewById(R.id.activity_table_map);

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


                FrameLayout tempFrame = new FrameLayout(tableMap.this);

                ImageView temp = new ImageView(tableMap.this);
                temp.setImageResource(R.drawable.squaretable);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(150, 150);
                temp.setLayoutParams(layoutParams);
                temp.setX((float) tempX);
                temp.setY((float) tempY);
                temp.setId(allTables.size()); //the id for the button we be it's index in the array + 1, this way there is no table 0 when it comes to labeling.
                tempFrame.addView(temp);

                //This is adding a label above the imageView so you know which table it is
                TextView tempName = new TextView(tableMap.this);
                tempName.setHeight(temp.getHeight());
                tempName.setWidth(temp.getWidth());
                tempName.setText(String.valueOf(allTables.size()));
                tempName.setTextColor(Color.BLACK);

                RelativeLayout.LayoutParams textLayoutParams = new RelativeLayout.LayoutParams(150,150);
                textLayoutParams.addRule(RelativeLayout.ALIGN_LEFT, temp.getId());
                textLayoutParams.addRule(RelativeLayout.ALIGN_RIGHT, temp.getId());
                textLayoutParams.addRule(RelativeLayout.ALIGN_TOP, temp.getId());
                textLayoutParams.addRule(RelativeLayout.ALIGN_BOTTOM, temp.getId());
                textLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
                tempName.setLayoutParams(textLayoutParams);
                tempFrame.addView(tempName);

                System.out.println(String.valueOf(allTables.size())); //debug statement

                mRootLayout.addView(tempFrame);
                allTables.add(tempFrame);
            }
        }

        Button tableAdd = (Button) findViewById(R.id.addTable);
        tableAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ImageView image = new ImageView(MainActivity.this);
//                image.setImageResource(your_image_here);
//                image.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
//                test.addView(image);

                //this adds the new imageview when the button is pressed

                FrameLayout tempFrame = new FrameLayout(tableMap.this);

                ImageView temp = new ImageView(tableMap.this);
                temp.setImageResource(R.drawable.squaretable);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(150, 150);
                temp.setLayoutParams(layoutParams);
                temp.setId(allTables.size()); //the id for the button we be it's index in the array + 1, this way there is no table 0 when it comes to labeling.
                tempFrame.addView(temp);

                //This is adding a label above the imageView so you know which table it is
                TextView tempName = new TextView(tableMap.this);
                tempName.setHeight(temp.getHeight());
                tempName.setWidth(temp.getWidth());
                tempName.setText(String.valueOf(allTables.size()));
                tempName.setTextColor(Color.BLACK);

                RelativeLayout.LayoutParams textLayoutParams = new RelativeLayout.LayoutParams(150,150);
                textLayoutParams.addRule(RelativeLayout.ALIGN_LEFT, temp.getId());
                textLayoutParams.addRule(RelativeLayout.ALIGN_RIGHT, temp.getId());
                textLayoutParams.addRule(RelativeLayout.ALIGN_TOP, temp.getId());
                textLayoutParams.addRule(RelativeLayout.ALIGN_BOTTOM, temp.getId());
                textLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
                tempName.setLayoutParams(textLayoutParams);
                tempFrame.addView(tempName);

                System.out.println(String.valueOf(allTables.size())); //debug statement

                tempFrame.setOnTouchListener(new MyTouchListener());
                mRootLayout.addView(tempFrame);
                allTables.add(tempFrame);
            }
        });

        Button saveData = (Button) findViewById(R.id.sendToServer);
        saveData.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String id;
                int tempX, tempY;
                if (!allTables.isEmpty()) {
                    Iterator iterator = allTables.iterator();
                    while (iterator.hasNext()) {
                        //add to server
                        View temp = (View) iterator.next();
                        id = String.valueOf(temp.getId());
                        tempX = (int) temp.getX();
                        tempY = (int) temp.getY();

                        //line necessary to add to server.
                    }
                }
            }
        });
    }



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
