package com.example.alec.positive_eating;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import java.text.DateFormat;
import java.util.Date;

/**
 * @author Alec
 * This class will handle all clocking in functionality
 */
public class Clocking_in extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clocking_in);
        TextView time = (TextView) findViewById(R.id.time);
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

// textView is the TextView view that should display it
        time.setText(currentDateTimeString);
    }
}
