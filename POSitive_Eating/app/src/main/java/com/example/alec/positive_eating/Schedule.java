package com.example.alec.positive_eating;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.android.volley.Response;
import com.example.shaneconnect.ShaneConnect;
import org.json.JSONObject;
import static com.example.alec.positive_eating.Singleton_ShaneConnect_Factory.getShaneConnect;

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
    private void confirmOrder(){
        ShaneConnect vista = getShaneConnect();
        //ShaneConnect vista = new ShaneConnect("http://proj-309-yt-4.cs.iastate.edu:1234", this);
        vista.addFood("Apples", 10, "Apples", 1, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
            }
        });
        }
}
