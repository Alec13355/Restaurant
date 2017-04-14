package com.example.alec.positive_eating.Reservations;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.alec.positive_eating.R;

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
        setResult(RESULT_OK, new Intent().putExtra("TABLE_SIZE", reservationSize));
        finish();
    }

    private void placeReservation() {
        new ReservationAdapater(reservationSize, this).findTable();
    }
}