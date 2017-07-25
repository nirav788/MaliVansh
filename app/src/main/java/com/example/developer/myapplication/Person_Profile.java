package com.example.developer.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.opengl.Visibility;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.developer.myapplication.Beans.Bean_User_List;
import com.example.developer.myapplication.ConnectionDetector.ConnectionDetector;
import com.example.developer.myapplication.Fragmants.ContactList;
import com.example.developer.myapplication.Globals.Global;
import com.example.developer.myapplication.JSON.JsonParse;
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
 * Created by Developer on 08-10-2016.
 */

public class Person_Profile extends AppCompatActivity{

    String user_ids =new String();
    ArrayList<Bean_User_List> array_bean = new ArrayList<Bean_User_List>();
    ListView Listview;
    Bean_User_List bean;
    ConnectionDetector cd;
    Boolean isInternetPresent = false;
    public ProgressDialog pDialog;
    private ArrayList<NameValuePair> param;
    String user;
    String UserName, FirstName, MidName, LastName, Relegion, CastName, SubCast, CurAddress, Mob1, Email, DOB, NativePlaceId, ProPic, LivePlace;
    TextView Person_Name,Email_Add,Phone_Number;
    ImageView Imgs;
    ImageView calling,location,shareing,zooming,cals;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.person_profile);

        user_ids = getIntent().getStringExtra("classes").toString();


      // pDialog = new ProgressDialog(getApplicationContext());
        cd = new ConnectionDetector(getBaseContext());


        Person_Name = (TextView)findViewById(R.id.person_name);
        Phone_Number = (TextView)findViewById(R.id.phone_no) ;
        Email_Add = (TextView)findViewById(R.id.email_add);
        Imgs = (ImageView)findViewById(R.id.image);
        calling = (ImageView)findViewById(R.id.btn1);
        location = (ImageView)findViewById(R.id.btn2);
        shareing = (ImageView)findViewById(R.id.btn3);
        zooming = (ImageView)findViewById(R.id.btn4);
        cals = (ImageView)findViewById(R.id.callperson);





        isInternetPresent = cd.isConnectingToInternet();
        if (isInternetPresent) {
            new get_Users_Profile().execute();
        } else {
            Toast.makeText(getApplicationContext(), "Check internet connection", Toast.LENGTH_SHORT).show();

        }


        calling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                
                cals.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        if (Phone_Number.getText().toString() == null){

                            Phone_Number.setText("number not available");
                        }
                        else {

                            Intent callIntent = new Intent(Intent.ACTION_CALL);
                            callIntent.setData(Uri.parse("tel:" + Phone_Number.getText().toString()));
                            startActivity(callIntent);
                        }
                    }
                });

            }
        });


        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        shareing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Mali Samaj");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Now Learn Android with AndroidSolved clicke here to visit https://androidsolved.wordpress.com/ ");
                startActivity(Intent.createChooser(sharingIntent, "Share via"));

            }
        });

        zooming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });
    }



    public class get_Users_Profile extends AsyncTask<String, String, String> {

       /* @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            pDialog = new ProgressDialog(getApplicationContext());
            pDialog.setMessage("Please Wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }*/


        @Override
        protected String doInBackground(String... args) {
            JsonParse jsonParser = new JsonParse();
            param = new ArrayList<NameValuePair>();

            param.add(new BasicNameValuePair("UserId", user_ids));

            String json = jsonParser.makeHttpRequest(Global.server_link+"GetPersonalProfile", "POST", param);
            Log.d("Response: ", "> " + json);

            return json;
        }

        protected void onPostExecute(String jsonStr) {
            // TODO Auto-generated method stub
            super.onPostExecute(jsonStr);
          /*  if (pDialog.isShowing()) {
                pDialog.dismiss();
            }*/

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



                        Person_Name.setText(FirstName + MidName + LastName);
                        Email_Add.setText("Email:- "+Email);
                        Phone_Number.setText("Ph +91-"+Mob1);
                        Picasso.with(getApplicationContext()).load(Global.ImagesforProfile+ProPic).into(Imgs);

                     /*   textperson.setText(UserName);
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
*/
                       /* Picasso.with(getActivity()).load("http://49.50.67.178:3211/ProfileImg/"+ProPic).into(imagepersonal);*/


                       // Log.d("unamke",UserName);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Sorry No Data Found", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
