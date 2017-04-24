package com.example.alec.positive_eating;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import static com.example.alec.positive_eating.Singleton_OrderList.getOrderList;

public class OrderOption extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_option);
        Button b = (Button) findViewById(R.id.option_submit);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText input = (EditText) findViewById(R.id.option_input);
                String options = input.getText().toString();
                int result = getIntent().getIntExtra("IS_ENTREE", -1);
                int position = getIntent().getIntExtra("POSITION", -1);
                if(position<0) {
                    Toast.makeText(getApplicationContext(),
                            "There was an error in the Intent. Returning to previous activity...",
                            Toast.LENGTH_LONG).show();
                } else {
                    switch (result) {
                        case 0:
                            getOrderList().get(position).setOptionsSide(options);
                            break;
                        case 1:
                            getOrderList().get(position).setOptionsEntree(options);
                            break;
                        default:
                            Toast.makeText(getApplicationContext(),
                                    "There was an error in the Intent. Returning to previous activity...",
                                    Toast.LENGTH_LONG).show();
                            break;
                    }
                }
                finish();
            }
        });
    }
}