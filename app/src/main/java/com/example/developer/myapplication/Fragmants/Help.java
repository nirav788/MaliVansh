package com.example.developer.myapplication.Fragmants;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.developer.myapplication.MainActivity;
import com.example.developer.myapplication.R;

/**
 * Created by Developer on 21-09-2016.
 */
public class Help extends Fragment {

    View view;
    TextView info, company;

    public Help() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.help, container, false);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Help");

        info = (TextView) view.findViewById(R.id.Malisamaj);
        company = (TextView) view.findViewById(R.id.Easybzee);

        Typeface companys = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Light.ttf");
        info.setTypeface(companys);

        Typeface samaj = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Light.ttf");
        company.setTypeface(samaj);

        // Inflate the layout for this fragment
        return view;
    }
}