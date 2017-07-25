package com.example.developer.myapplication.Fragmants;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.developer.myapplication.ConnectionDetector.ConnectionDetector;
import com.example.developer.myapplication.Globals.Global;
import com.example.developer.myapplication.JSON.JsonParse;
import com.example.developer.myapplication.LoginActivity;
import com.example.developer.myapplication.MainActivity;
import com.example.developer.myapplication.R;
import com.squareup.picasso.Picasso;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.developer.myapplication.extras.CommonConstants.Login_Shared_PREFERENCES;
import static com.example.developer.myapplication.extras.CommonConstants.User_KEY;

/**
 * Created by Developer on 21-09-2016.
 */
public class MyProfile extends Fragment {

    ConnectionDetector cd;
    Boolean isInternetPresent = false;
    public ProgressDialog pDialog;
    SharedPreferences sharedPreferences;
    private ArrayList<NameValuePair> param;
    public String user;
    private ImageView imagepersonal;
    private TextView textperson;

    private ImageView button1;
    private ImageView button2;

    private ImageView button3;
    private ImageView button4;
    private ImageView header;
    private TextView fullname;

    private TextView dobhint;
    private TextView dob;
    private TextView Bloodhint;
    private TextView Bloodgrup;

    private TextView religionhint;
    private TextView Religion;
    private TextView Casthint;
    private TextView Cast;

    private TextView Subcasthint;
    private TextView Subcast;
    private TextView Marridhint;
    private TextView Marrid;
    private TextView HintAddress;
    private TextView Address;
    private TextView HintNumber;
    private TextView Phnumber;
    private TextView HintEmail;
    private TextView EmailAdd;

    private TextView PermanentAdd;
    private TextView LivePlaces;
    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2016-09-27 14:51:14 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    String UserName,FirstName,MidName,LastName,Relegion,CastName,SubCast,CurAddress,Mob1,Email,DOB,NativePlaceId,ProPic,LivePlace;



    View view;

    public MyProfile() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.profile, container, false);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle("My Profile");

        pDialog = new ProgressDialog(getActivity());
        cd = new ConnectionDetector(getActivity().getBaseContext());
        sharedPreferences = getActivity().getSharedPreferences(Login_Shared_PREFERENCES,
                Context.MODE_PRIVATE);

        if (sharedPreferences.contains(User_KEY)) {
            user = sharedPreferences.getString(User_KEY, "");

            Log.d("user name",user);


        }

        imagepersonal = (ImageView) view.findViewById(R.id.imagepersonal);
        textperson = (TextView) view.findViewById(R.id.textperson);

        button1 = (ImageView) view.findViewById(R.id.button1);
        button2 = (ImageView) view.findViewById(R.id.button2);


        button3 = (ImageView) view.findViewById(R.id.button3);
        button4 = (ImageView) view.findViewById(R.id.button4);
        header = (ImageView) view.findViewById(R.id.header);
        fullname = (TextView) view.findViewById(R.id.fullname);

        dobhint = (TextView) view.findViewById(R.id.dobhint);
        dob = (TextView) view.findViewById(R.id.dob);
        Bloodhint = (TextView) view.findViewById(R.id.Bloodhint);
        Bloodgrup = (TextView) view.findViewById(R.id.Bloodgrup);

        religionhint = (TextView) view.findViewById(R.id.religionhint);
        Religion = (TextView) view.findViewById(R.id.Religion);
        Casthint = (TextView) view.findViewById(R.id.Casthint);
        Cast = (TextView) view.findViewById(R.id.Cast);

        PermanentAdd = (TextView) view.findViewById(R.id.PerAdd);
        Subcasthint = (TextView) view.findViewById(R.id.Subcasthint);
        Subcast = (TextView) view.findViewById(R.id.Subcast);
        Marridhint = (TextView) view.findViewById(R.id.Marridhint);
        Marrid = (TextView) view.findViewById(R.id.Marrid);
        HintAddress = (TextView) view.findViewById(R.id.HintAddress);
        Address = (TextView) view.findViewById(R.id.Address);
        HintNumber = (TextView) view.findViewById(R.id.HintNumber);
        Phnumber = (TextView) view.findViewById(R.id.Phnumber);
        HintEmail = (TextView) view.findViewById(R.id.HintEmail);
        EmailAdd = (TextView) view.findViewById(R.id.EmailAdd);
        LivePlaces = (TextView) view.findViewById(R.id.LivePlace);


        isInternetPresent = cd.isConnectingToInternet();
        if (isInternetPresent) {
            new LoginAsyntask().execute();
        } else {
            Toast.makeText(getActivity(), "Check internet connection", Toast.LENGTH_SHORT).show();

        }

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // Inflate the layout for this fragment
        return view;
    }


    public class LoginAsyntask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Please Wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }


        @Override
        protected String doInBackground(String... args) {
            JsonParse jsonParser = new JsonParse();
            param = new ArrayList<NameValuePair>();

            param.add(new BasicNameValuePair("UserId", user));

            String json = jsonParser.makeHttpRequest(Global.server_link+"GetPersonalProfile", "POST", param);
            Log.d("Response: ", "> " + json);

            return json;
        }

        protected void onPostExecute(String jsonStr) {
            // TODO Auto-generated method stub
            super.onPostExecute(jsonStr);
            if (pDialog.isShowing()) {
                pDialog.dismiss();
            }

            if (jsonStr != null) {
                try {


                    JSONObject BaseObject = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray abc = BaseObject.getJSONArray("PersonalProfile");

                    // looping through All Contacts
                    for (int i = 0; i < abc.length(); i++) {
                        JSONObject c = abc.getJSONObject(i);


                        if(c.getString("UserName").toString() != null) {
                            UserName = c.getString("UserName");
                        }
                        else
                        {
                            UserName = null;
                        } if(c.getString("FirstName").toString() != null) {
                            FirstName = c.getString("FirstName");
                        }
                        else
                        {
                            FirstName = null;
                        }if(c.getString("MidName").toString() != null) {
                            MidName = c.getString("MidName");
                        }
                        else
                        {
                            MidName = null;
                        }if(c.getString("LastName").toString() != null) {
                            LastName = c.getString("LastName");
                        }
                        else
                        {
                            LastName = null;
                        }if(c.getString("ReligionName").toString() != null) {
                            Relegion = c.getString("ReligionName");
                        }
                        else
                        {
                            Relegion = null;
                        }if(c.getString("CastNam").toString() != null) {
                            CastName = c.getString("CastNam");
                        }
                        else
                        {
                            CastName = null;
                        }if(c.getString("SubCastName").toString() != null) {
                            SubCast = c.getString("SubCastName");
                        }
                        else
                        {
                            SubCast = null;
                        }if(c.getString("CurAddress").toString() != null) {
                            CurAddress = c.getString("CurAddress");
                        }
                        else
                        {
                            CurAddress = null;
                        }if(c.getString("Mob1").toString() != null) {
                            Mob1 = c.getString("Mob1");
                        }
                        else
                        {
                            Mob1 = null;
                        }if(c.getString("Email").toString() != null) {
                            Email = c.getString("Email");
                        }
                        else
                        {
                            Email = null;
                        }if(c.getString("DOB").toString() != null) {
                            DOB = c.getString("DOB");
                        }
                        else
                        {
                            DOB = null;
                        }if(c.getString("DOB").toString() != null) {
                            NativePlaceId = c.getString("NativeName");
                        }
                        else
                        {
                            NativePlaceId = null;
                        }if(c.getString("ProPic").toString() != null) {
                            ProPic = c.getString("ProPic");
                        }
                        else
                        {
                            ProPic = null;
                        }if(c.getString("LiveName").toString() != null) {
                            LivePlace = c.getString("LiveName");
                        }
                        else
                        {
                            LivePlace.equals(null);
                        }




                        textperson.setText(UserName);
                        fullname.setText(FirstName + MidName + LastName);
                        Religion.setText(Relegion);
                        Cast.setText(CastName);
                        Subcast.setText(SubCast);
                        Address.setText(CurAddress);
                        Phnumber.setText(Mob1);
                        EmailAdd.setText(Email);
                        dob.setText(DOB);
                        PermanentAdd.setText(NativePlaceId);
                        LivePlaces.setText(LivePlace);

                        Picasso.with(getActivity()).load(Global.ImagesforProfile+ProPic).into(imagepersonal);


                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(getActivity(), "Sorry No Data Found", Toast.LENGTH_SHORT).show();
            }
        }

    }
}