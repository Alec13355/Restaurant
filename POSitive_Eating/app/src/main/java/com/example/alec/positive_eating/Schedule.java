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
<<<<<<< HEAD

    private void getAllOrders() {
        ModelM = getShaneConnect();
        recursiveInc = 0;
        ModelM.getEmployees(recursiveInc, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse (JSONObject response){
                if (response.has("none")) {
                    return;
                } else {
                    try {
                        String desc = response.getString("first");
                        String orderNum = "Address" + response.get("address");
                        ArrayList<String> itemsInTheOrder = new ArrayList<String>();
                        itemsInTheOrder.add(desc);
                        listDataHeader.add(orderNum);
                        listDataChild.put(orderNum, itemsInTheOrder);
                        recursiveInc++;
                        ModelM.getEmployees(recursiveInc, this);
                    } catch (Exception e) {
                        Toast.makeText(context,
                                "An error occurred. Please press the back button and try again.",
                                Toast.LENGTH_LONG).show();
                        return;
                    }
                }
            }
        });
=======
    private void confirmOrder() {
>>>>>>> f9199b2efe848cc321c6a01194f2ffce66b5c64f
    }
}
