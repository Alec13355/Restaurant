package com.example.alec.positive_eating.editMenu;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.alec.positive_eating.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FoodGUI.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FoodGUI#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FoodGUI extends Fragment {

    private OnFragmentInteractionListener mListener;

    private String name;

    private Button b;

    private TextView text;

    public FoodGUI() {
        // Required empty public constructor
    }

    public void setName(String n){
        this.name = n;

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment FoodGUI.
     */
    // TODO: Rename and change types and number of parameters
    public static FoodGUI newInstance(Food f) {
        FoodGUI fragment = new FoodGUI();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        fragment.setName(f.getName());
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        text.setText(name);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food_gui, container, false);
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
