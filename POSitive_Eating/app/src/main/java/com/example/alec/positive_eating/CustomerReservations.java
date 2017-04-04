package com.example.alec.positive_eating;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import shaneconnect.ShaneConnect;
import shaneconnect.TableCache;

import static com.example.alec.positive_eating.Singleton_ShaneConnect_Factory.getShaneConnect;

/**
 * The CustomerReservations is used to reserve table for a party of any size the customer chooses.
 * Note, this class is still in development and hasn't been brought online yet.
 */
public class CustomerReservations extends AppCompatActivity {

    private Spinner sizeList;

    private Button submit;

    private int reservationSize;

    /**
     * Creates activity and adds a spinner for the customer to select table size and adds a button
     * for confirming size.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations);

        reservationSize = -1;
        sizeList = (Spinner) findViewById(R.id.size_list);
        sizeList.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                reservationSize = pos + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "Select a table size.",
                        Toast.LENGTH_LONG).show();
            }
        });

        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(reservationSize >= 0) {
                    placeReservation();
                } else {
                    Toast.makeText(getApplicationContext(), "Select a table size.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void finishUp() {
        Intent i = new Intent();
        i.putExtra("TABLE_SIZE", reservationSize);
        setResult(RESULT_OK, i);
        finish();
    }

    private void placeReservation() {
        ShaneConnect connection = getShaneConnect();
        TableCache tc = new TableCache(connection);
        tc.updateQue();
        //TableReservation tr = new TableReservation("jim@bob.com", 8, ,-1)
    }
}