/**
 * @author Alec
 */
package com.example.alec.positive_eating;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * This will display the current weeks schdeule
 */
public class Schedule extends AppCompatActivity {
   Button Button_a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        Button_a=(Button) findViewById(R.id.Button22);

        Button_a.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        confirmOrder();
                    }

                });

    }
    private void confirmOrder() {
    }
}
