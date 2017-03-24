package com.example.alec.positive_eating;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static android.view.Gravity.FILL;

public class tableListView extends AppCompatActivity {
    private List<Table> allTheTables = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_list_view);
        allTheTables = (ArrayList<Table>) getIntent().getSerializableExtra("allthetables");
        ViewGroup mRootLayout = (LinearLayout) findViewById(R.id.linearView);

        if(allTheTables != null){
            if(!allTheTables.isEmpty()){
                Iterator<Table> tableIterator = allTheTables.iterator();
                while(tableIterator.hasNext()){
                    Table temp = tableIterator.next();
                    TextView tempName = new TextView(tableListView.this);
                    tempName.setHeight(FILL);
                    tempName.setWidth(FILL);
                    tempName.setText(
                            "Table: " + temp.getID() + "\nX: " + temp.getX() + " Y: " + temp.getY() + "\nNumber of seats: " + temp.getSeats() + "\nStatus: " + temp.getStatus()
                    );
                    tempName.setTextColor(Color.BLACK);
                    mRootLayout.addView(tempName);
                }
            }
        }
    }
}
