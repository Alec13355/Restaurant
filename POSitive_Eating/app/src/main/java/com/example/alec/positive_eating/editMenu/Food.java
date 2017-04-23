package com.example.alec.positive_eating.editMenu;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by shane on 4/23/17.
 */

public class Food {

    private String name;

    private int id;

    private int quantity;

    private int price;

    private String desc;

    private String options;

    public Food(JSONObject json){
        try {
            this.name = json.getString("name");
            this.id = json.getInt("food_id");
            this.quantity = json.getInt("quantity");
            this.price = json.getInt("price");
            this.desc = json.getString("desc");
            this.options = json.getString("options");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public String getName(){
        return this.name;
    }

    public int getId(){
        return this.id;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public double getPrice(){
        return this.price/100;
    }

    public String getDesc(){
        return this.desc;
    }

    public String getOptions(){
        return this.options;
    }


}
