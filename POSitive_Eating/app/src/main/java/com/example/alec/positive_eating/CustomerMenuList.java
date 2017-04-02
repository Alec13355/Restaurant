package com.example.alec.positive_eating;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author Christian Shinkle
 * The CustomerMenuList class is used as a custom implementation for both the CustomerEntreeList
 * and the CustomerSidesList. By extending ArrayAdapter, this class can be used to create
 * ListView views.
 */
public class CustomerMenuList extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] menuOfItems;
    private final Integer[] imageId;

    /**
     * Calls super class for default constructor and initializes instance variables.
     * @param theContext
     * @param theMenu
     * @param theImageId
     */
    public CustomerMenuList(Activity theContext, String[] theMenu, Integer[] theImageId) {
        super(theContext, R.layout.list_menu, theMenu);
        context = theContext;
        menuOfItems = theMenu;
        imageId = theImageId;
    }

    /**
     * Uses inflater to inflate view. Applies text from menu and sets image of food item.
     * @param position
     * @param view
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_menu, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txtTitle.setText(menuOfItems[position]);

        imageView.setImageResource(imageId[0]);
        return rowView;
    }
}
