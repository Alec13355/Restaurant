package com.example.alec.positive_eating;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Ethan
 */

public class  viewTableMap extends AppCompatActivity {
    private ViewGroup mRootLayout;
    private List<View> allTables = new ArrayList<>();
    private int _xDelta;
    private int _yDelta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_table_map);
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
}
