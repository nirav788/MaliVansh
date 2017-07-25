package com.example.developer.myapplication.Fragmants;

import android.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.developer.myapplication.MainActivity;
import com.example.developer.myapplication.R;

/**
 * Created by Developer on 21-09-2016.
 */
public class FamilyTree extends Fragment {


    ImageView dad, mom, own, son, daughtor, wife, sister;
    TextView Daddy, Mummy, Owns, SON, DAUGHTOR, WIFE, SIS;

    View view;

    public FamilyTree() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.familytree, container, false);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Family Tree");

        own = (ImageView) view.findViewById(R.id.imageview1);
        dad = (ImageView) view.findViewById(R.id.imageview2);
        mom = (ImageView) view.findViewById(R.id.imageview3);
        son = (ImageView) view.findViewById(R.id.imageview4);
        daughtor = (ImageView) view.findViewById(R.id.imageview5);
        wife = (ImageView) view.findViewById(R.id.imageview6);
        sister = (ImageView) view.findViewById(R.id.Sister);

        Owns = (TextView) view.findViewById(R.id.Own);
        Daddy = (TextView) view.findViewById(R.id.father);
        Mummy = (TextView) view.findViewById(R.id.mother);
        SON = (TextView) view.findViewById(R.id.Son);
        DAUGHTOR = (TextView) view.findViewById(R.id.Daughtor);
        WIFE = (TextView) view.findViewById(R.id.wife);
        SIS = (TextView) view.findViewById(R.id.sis);


        sister.setVisibility(View.GONE);
        SIS.setVisibility(View.GONE);

        handleBigCameraPhoto();

        handleBigCamera();


        // Inflate the layout for this fragment


        return view;
    }


    private void handleBigCameraPhoto() {

        own.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dad.setVisibility(View.GONE);
                mom.setVisibility(View.GONE);
                son.setVisibility(View.GONE);
                daughtor.setVisibility(View.GONE);
                wife.setVisibility(View.GONE);
                Daddy.setVisibility(View.GONE);
                Mummy.setVisibility(View.GONE);
                SON.setVisibility(View.GONE);
                DAUGHTOR.setVisibility(View.GONE);
                WIFE.setVisibility(View.GONE);


                sister.setVisibility(View.VISIBLE);
                SIS.setVisibility(View.VISIBLE);
            }
        });


    }


    private void handleBigCamera() {

        dad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dad.setVisibility(View.GONE);
                Daddy.setVisibility(View.GONE);
                Owns.setVisibility(View.GONE);
                mom.setVisibility(View.GONE);
                son.setVisibility(View.GONE);
                daughtor.setVisibility(View.GONE);
                wife.setVisibility(View.GONE);
                own.setVisibility(View.GONE);
                Mummy.setVisibility(View.GONE);
                SON.setVisibility(View.GONE);
                DAUGHTOR.setVisibility(View.GONE);
                WIFE.setVisibility(View.GONE);


                own.setImageDrawable(getResources().getDrawable(R.drawable.icn_dashboard_superadmin));
                own.setVisibility(View.VISIBLE);
                Owns.setText("Navin Bhai ");
                Owns.setVisibility(View.VISIBLE);

                son.setImageDrawable(getResources().getDrawable(R.drawable.icn_dashboard_admin));
                son.setVisibility(View.VISIBLE);
                SON.setText("Sachin \n SON");
                SON.setGravity(Gravity.CENTER);
                SON.setVisibility(View.VISIBLE);

                daughtor.setImageDrawable(getResources().getDrawable(R.drawable.rani));
                daughtor.setVisibility(View.VISIBLE);
                DAUGHTOR.setText("Ragini \nDAUGHTOR IN LOW");
                DAUGHTOR.setVisibility(View.VISIBLE);


                son.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        own.setImageDrawable(getResources().getDrawable(R.drawable.icn_dashboard_admin));
                        own.setVisibility(View.VISIBLE);
                        Owns.setText("Mr.Sachin \n Own");
                        Owns.setVisibility(View.VISIBLE);


                        son.setVisibility(View.GONE);
                        SON.setVisibility(View.GONE);
                        daughtor.setVisibility(View.GONE);
                        DAUGHTOR.setVisibility(View.GONE);


                        dad.setImageDrawable(getResources().getDrawable(R.drawable.icn_dashboard_superadmin));
                        dad.setVisibility(View.VISIBLE);
                        Daddy.setText("Navin Bhai \n Father ");
                        Daddy.setVisibility(View.VISIBLE);


                        mom.setImageDrawable(getResources().getDrawable(R.drawable.icn_dashboard_supervisor));
                        mom.setVisibility(View.VISIBLE);
                        Mummy.setText("Nailam Ben \n Mother ");
                        Mummy.setVisibility(View.VISIBLE);


                        son.setImageDrawable(getResources().getDrawable(R.drawable.icn_dashboard_teacher));
                        son.setVisibility(View.VISIBLE);
                        SON.setText("Anupam  \n Son");
                        SON.setVisibility(View.VISIBLE);

                        daughtor.setImageDrawable(getResources().getDrawable(R.drawable.icn_dashboard_student));
                        daughtor.setVisibility(View.VISIBLE);
                        DAUGHTOR.setText("Malaika  \n Daughter");
                        DAUGHTOR.setVisibility(View.VISIBLE);

                        wife.setImageDrawable(getResources().getDrawable(R.drawable.rani));
                        wife.setVisibility(View.VISIBLE);
                        WIFE.setText("Ragini \n Wife");
                        WIFE.setVisibility(View.VISIBLE);



                    }
                });

            }
        });


    }
}