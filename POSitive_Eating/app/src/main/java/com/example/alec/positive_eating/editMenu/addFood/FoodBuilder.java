package com.example.alec.positive_eating.editMenu.addFood;

import android.widget.EditText;

import com.example.alec.positive_eating.editMenu.Food;

import java.util.ArrayList;

/**
 * Created by shane on 4/23/17.
 */

public class FoodBuilder {

    private String name;

    private int price;

    private String desc;

    private int quan;

    private int id;

    private String options;


    public FoodBuilder(EditText name,EditText price, EditText quantity, EditText desc, EditText options, TypeOfFood type){
        this.price = Integer.parseInt(price.getText().toString());
        this.desc = desc.getText().toString();
        this.quan = Integer.parseInt(quantity.getText().toString());
        this.options = options.getText().toString();
        this.id = id;
        String nameinput = name.getText().toString();
        if(type.isDesert()){
            this.name = "#" + nameinput;
        }else {
            if(type.isDrink()){
                this.name = "_" + nameinput;
            }else{
                if(type.isEntre()){
                    char firstletter = nameinput.charAt(0);
                    nameinput = nameinput.substring(1,nameinput.length());
                    firstletter = Character.toUpperCase((firstletter));
                    this.name = firstletter + nameinput;
                }else{
                    if(type.isSide()){
                        char firstletter = nameinput.charAt(0);
                        nameinput = nameinput.substring(1,nameinput.length());
                        firstletter = Character.toLowerCase((firstletter));
                        this.name = firstletter + nameinput;
                    }
                }
            }
        }
    }

    public Food buildFood(){
       Food f = new Food();
       f.setData(name,-1,quan,price,desc,options);
        return f;
    }
}
