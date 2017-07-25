package com.example.developer.myapplication.Fragmants;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.developer.myapplication.MainActivity;
import com.example.developer.myapplication.R;

/**
 * Created by Developer on 21-09-2016.
 */
public class Community extends Fragment {

    View view;

    public Community() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.community, container, false);



        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Community");

        // Inflate the layout for this fragment
        return view;
    }
}