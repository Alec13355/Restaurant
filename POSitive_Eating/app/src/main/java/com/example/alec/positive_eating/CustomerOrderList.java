package com.example.alec.positive_eating;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.alec.positive_eating.CustomerOrderMenu.getOrderList;


/**
 * @author Christian Shinkle
 * The CustomerOrderList class is used as a custom implementation for the CustomerOrderMenu.By
 * extending ArrayAdapter, this class can be used to create ListView views.
 */
public class CustomerOrderList extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] order;
    private final Integer[] imageId;
    /**
     * Calls the super constructor and initializes instance variables.
     * @param theContext
     * @param theOrder
     * @param theImageId
     */
    public CustomerOrderList(Activity theContext, String[] theOrder, Integer[] theImageId) {
        super(theContext, R.layout.list_order, theOrder);
        context = theContext;
        order = theOrder;
        imageId = theImageId;
    }

    /**
     * Uses inflater to inflate view. Applies text from order array and applies image to entree and
     * side if side isn't null (i.e. if the side has been added).
     * @param position
     * @param view
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_order, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);

        ImageView imageViewEntree = (ImageView) rowView.findViewById(R.id.imgEntree);
        ImageView imageViewSide = (ImageView) rowView.findViewById(R.id.imgSide);
        txtTitle.setText(order[position]);

        imageViewEntree.setImageResource(imageId[0]);
        if(getOrderList().get(position).getSide()!=null) {
            imageViewSide.setImageResource(imageId[1]);
        }

        return rowView;
    }
}