package com.example.alec.positive_eating.editMenu.addFood;

import android.widget.CheckBox;

/**
 * Created by shane on 4/23/17.
 */

public class TypeOfFood {

    private boolean desert;

    private boolean entre;

    private boolean side;

    private boolean drink;


    public TypeOfFood(CheckBox desert, CheckBox entre, CheckBox side, CheckBox drink){
        this.desert = false;
        this.entre = false;
        this.side = false;
        this.drink = false;

        if(desert.isChecked()){
            this.desert=true;
        }
        if(entre.isChecked()){
            this.entre=true;
        }
        if(side.isChecked()){
            this.side = true;
        }
        if(drink.isChecked()){
            this.drink = true;
        }
    }


    public boolean isDesert(){
        return this.desert;
    }

    public boolean isEntre(){
        return this.entre;
    }

    public boolean isSide(){
        return this.side;
    }

    public boolean isDrink(){
        return this.drink;
    }
}
