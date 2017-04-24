package com.example.alec.positive_eating.editMenu;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by shane on 4/23/17.
 */

public class Food {

    private String name;

    private int id;

    private int quantity;

    private int price;

    private String desc;

    private ArrayList<String> options;

    public Food(JSONObject json){
        try {
            this.name = json.getString("name");
            this.id = json.getInt("food_id");
            this.quantity = json.getInt("quantity");
            this.price = json.getInt("price");
            this.desc = json.getString("desc");
            this.options = convertString(json.getString("options"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Food(){

    }


    public void setData(String name,int id,int quan,int price, String desc, String options){
        this.name=name;
        this.id = id;
        this.quantity=quan;
        this.price=price;
        this.desc=desc;
        this.options=convertString(options);
    }

    public ArrayList<String> convertString(String options){
        ArrayList<String> list = new ArrayList<String>();
        String buffer = "";
        for(int x=0;x<options.length();x++){
            if(options.charAt(x)==','){
                list.add(buffer);
                buffer="";
            }else{
                buffer+=options.charAt(x);
            }
        }
        return list;
    }

    public int getIntPrice(){
        return this.price;
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

    public ArrayList<String> getOptions(){
        return this.options;
    }

    public boolean isDrink(){
        return this.name.charAt(this.name.length()-1) == '_';
    }

    public boolean isEntree(){
        return Character.isUpperCase(this.name.charAt(0));
    }

    public boolean isSide(){
        return Character.isLowerCase(this.name.charAt(0));
    }


}
