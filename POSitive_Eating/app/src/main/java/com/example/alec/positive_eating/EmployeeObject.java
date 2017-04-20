package com.example.alec.positive_eating;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by ethantw on 4/19/2017.
 */

public class EmployeeObject extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toast.makeText(getApplicationContext(),Singleton_Current_Employee.getInstance().getEmployee().getID(), Toast.LENGTH_SHORT).show();
    }
}
