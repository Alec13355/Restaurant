package com.example.alec.positive_eating;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.alec.positive_eating.Reservations.CustomerReservations;

/**
 * @author Christian Shinkle
 * The CustomerMainMenu class handles the main menu, by allowing the user to access the three main
 * functions of the Customer POSitiveEating app: Directions, Placing Orders, and Reservations. Each
 * can be accessed by pressing one of the three respective buttons.
 */
public class CustomerMainMenu extends AppCompatActivity implements View.OnClickListener {
    /**
     * A code needed to identify the CustomerReservations activity on its completion.
     */
    public static final int RESERVATION_CODE = 0;
    /**
     * Creates activity and assigns listeners to their respective buttons.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__meun);

        setupButtons();
    }
    /**
     * Decides which activity needs to be loaded based on location screen was clicked.
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.direction:
                loadMap();break;
            case R.id.order_food:
                loadMenu();break;
            case R.id.reservation:
                loadRes();break;
            default:
                break;
        }
    }

    /**
     * Checks if results from CustomerReservations.
     * If requestCode matches RESERVATION_CODE and resultCode match RESULT_OK
     *   then show table size via Toast
     * else
     *   then show error via Toast
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == RESERVATION_CODE && resultCode == RESULT_OK) {
            Toast.makeText(this, "The table size of the reservation is: " +
                    data.getExtras().getInt("TABLE_SIZE", -1), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Something went wrong along the way.\nrequestCode = "+requestCode
                    +"\nresultCode = "+ resultCode, Toast.LENGTH_LONG).show();
        }
    }

    private void setupButtons() {
        Button direction = (Button) findViewById(R.id.direction);
        direction.setOnClickListener(this);
        Button orderFood = (Button) findViewById(R.id.order_food);
        orderFood.setOnClickListener(this);
        Button reserve = (Button) findViewById(R.id.reservation);
        reserve.setOnClickListener(this);
    }

    private void loadRes() {
        startActivityForResult(new Intent(this, CustomerReservations.class), RESERVATION_CODE);
    }

    private void loadMap() {
        startActivity(new Intent(this, CustomerMap.class));
    }

    private void loadMenu() {
        startActivity(new Intent(this, CustomerOrderMenu.class));
    }

}
