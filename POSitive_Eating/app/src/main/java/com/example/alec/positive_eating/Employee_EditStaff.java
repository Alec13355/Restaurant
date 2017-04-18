/**
 * @author Alec
 */
package com.example.alec.positive_eating;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import com.example.alec.positive_eating.payrole.AssistantDirector;
import com.example.alec.positive_eating.payrole.ConcreteListViewBuilderUsers;

import static com.example.alec.positive_eating.Singleton_ShaneConnect_Factory.getShaneConnect;

/**
 * This class will be used to manage and get current workers.
 */
public class Employee_EditStaff extends ActionBarActivity {
    private static ExpandableListView expandableListView;
    private static ExpandableListAdapter adapter;
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_staff);
        LinearLayout list1 = (LinearLayout) findViewById(R.id.listOStuff);
        ConcreteListViewBuilderUsers builder  = new ConcreteListViewBuilderUsers(list1,this);
        final AssistantDirector dir = new AssistantDirector(getShaneConnect(),builder);
        dir.directDisplay();


        Button add = (Button) findViewById(R.id.Add_Employee);
        //Initilizes the buttons.

        add.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        Intent myIntent = new Intent(Employee_EditStaff.this, Employee_add_employee.class); /** Class name here */
                        Employee_EditStaff.this.startActivity(myIntent);
                    }
                }
                /*Displays who is owed money */




        );
    }
}

