package com.example.alec.positive_eating;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import shaneconnect.ShaneConnect;

import static android.view.Gravity.CENTER;
import static com.example.alec.positive_eating.Singleton_ShaneConnect_Factory.getShaneConnect;

public class CookOrderList extends AppCompatActivity {
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;
    private ArrayList<JSONObject> orders;
    private ShaneConnect ModelM;
    private int recursiveInc;
    private int bufferOrderToDelete;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_order_list);
        context = getApplicationContext();
        bufferOrderToDelete = -1;
    }

    @Override
    public void onResume() {
        super.onResume();
        prepareListData();
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        getAllOrders();
    }

    private void setup() {
        ExpandableListView expListView = (ExpandableListView) findViewById(R.id.lvExp);
        CookListAdapter listAdapter = new CookListAdapter(context, listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);

        TextView text = new TextView(this);
        text.setText(String.valueOf("Are you sure this order is complete?"));
        text.setGravity(CENTER);

        final AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle("Finished With Order?");
        builder.setView(text);

        builder.setPositiveButton("Yes (Delete order)", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            beginRemoveOrder();
            }
        });
        builder.setNegativeButton("No (Go back)", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
            }
        });
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView listview, View view, int groupPos,
                                        int childPos, long id) {
                bufferOrderToDelete = groupPos;
                builder.show();
                return false;
            }
        });
    }

    private void beginRemoveOrder() {
        String descriptions = null;
        try {
            descriptions = orders.get(bufferOrderToDelete).getString("desc");
        } catch (Exception e){
            Toast.makeText(context, "A very weird error occurred in beginRemoveOrder...",
                    Toast.LENGTH_LONG).show();
        }
        if(bufferOrderToDelete<0 || descriptions==null)
            return;
        ModelM.removeOrder(descriptions, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                prepareListData();
            }
        });
    }

    private void processOrders(final int i) {
        try {
            String compString = orders.get(i).getString("componentString");
            final String orderNum = "Order #" + orders.get(i).getInt("order_id");
            ModelM.getFoodByID(compString, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        ArrayList<String> orderInfo = parseResponse(response);
                        listDataHeader.add(orderNum);
                        listDataChild.put(orderNum, orderInfo);
                        if (i == orders.size() - 1) {
                            setup();
                        } else {
                            processOrders(i + 1);
                        }
                    } catch (Exception e) {
                        Toast.makeText(context, "An error occurred in getFoodByID" +
                                        "Please press the back button and try again.",
                                Toast.LENGTH_LONG).show();
                    }
                }
            });
        } catch (Exception e) {
            Toast.makeText(context, "An error occurred in getOrders." +
                            "Please press the back button and try again.",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void getAllOrders() {
        ModelM = getShaneConnect();
        recursiveInc = 0;
        orders = new ArrayList<JSONObject>();
        ModelM.getOrders(recursiveInc, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse (JSONObject response) {
                if (response.has("none")) {
                    processOrders(0);
                } else {
                    orders.add(response);
                    ModelM.getOrders(++recursiveInc, this);
                }
            }
        });
    }

    private ArrayList<String> parseResponse(JSONObject res) throws JSONException {
        ArrayList<String> order = new ArrayList<String>();
        for(int i =0;res.has("NAME"+i);i++) {
            try {
                String name = "";
                name+=res.getString("DESCR" + i)+"\n";
                name+="Quantity:" + res.getInt("QUANTITY" + i)+"\n";
                name+="Price:$" + res.getInt("PRICE" + i)+"\n";
                if(res.has("OPTIONS"+i)) {
                    JSONObject jsonOptions = res.getJSONObject("OPTIONS" + i);
                    name += "Options:";
                    int j;
                    for (j = 0; jsonOptions.has("OPTION" + j); j++) {
                        name += "\n\t"+ jsonOptions.getString("OPTION" + j);
                    }
                    if(j==0)
                        name+="\n (None)";
                }
                order.add(name);
            } catch (Exception e) {
                throw e;
            }
        }
        return order;
    }
}