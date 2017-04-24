package com.example.alec.positive_eating.editMenu;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.example.alec.positive_eating.R;

import org.json.JSONObject;

import shaneconnect.ConcreteCommand;
import shaneconnect.DeleteFood;
import shaneconnect.ShaneConnect;


import static com.example.alec.positive_eating.Singleton_ShaneConnect_Factory.getShaneConnect;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FoodGUI.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FoodGUI#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FoodGUI extends android.app.Fragment {

    private OnFragmentInteractionListener mListener;

    private String name;

    private Button b;

    private TextView text;

    private Context context;



    public FoodGUI() {
        // Required empty public constructor
    }

    public void setProperties(String n, Context cont){
        this.name = n;
        this.context = cont;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment FoodGUI.
     */
    // TODO: Rename and change types and number of parameters
    public static FoodGUI newInstance(Food f, Context cont) {
        FoodGUI fragment = new FoodGUI();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        fragment.setProperties(f.getName(),cont);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    public void setDeleteButton(Button del){
        this.b=del;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_food_gui, container, false);


        final Fragment fra= this;

        this.b = (Button) v.findViewById(R.id.fragButton);
        this.text = (TextView) v.findViewById(R.id.fragText);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConcreteCommand command = new ConcreteCommand();
                DeleteFood del = new DeleteFood(getShaneConnect(),name,command);


                del.exectute(new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response.has("success")){
                            getActivity().getFragmentManager().beginTransaction().remove(fra).commit();

                        }
                    }
                });
            }
        });


        text.setText(this.name);

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
