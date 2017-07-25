package com.example.developer.myapplication.Fragmants;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.developer.myapplication.Beans.Bean_User_List;
import com.example.developer.myapplication.ConnectionDetector.ConnectionDetector;
import com.example.developer.myapplication.Globals.Global;
import com.example.developer.myapplication.JSON.JsonParse;
import com.example.developer.myapplication.MainActivity;
import com.example.developer.myapplication.Person_Profile;
import com.example.developer.myapplication.R;
import com.squareup.picasso.Picasso;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import android.widget.AdapterView.OnItemClickListener;

import static com.example.developer.myapplication.extras.CommonConstants.Login_Shared_PREFERENCES;
import static com.example.developer.myapplication.extras.CommonConstants.User_KEY;

/**
 * Created by Developer on 21-09-2016.
 */
public class ContactList extends Fragment {

    ArrayList<Bean_User_List> array_bean = new ArrayList<Bean_User_List>();
    ListView Listview;
    Bean_User_List bean;
    ConnectionDetector cd;
    Boolean isInternetPresent = false;
    public ProgressDialog pDialog;
    SharedPreferences sharedPreferences;
    private ArrayList<NameValuePair> param;
    String user;
    SwipeRefreshLayout refrest;

    String UserId, UserName, FirstName, MidName, LastName, Relegion, CastName, SubCast, CurAddress, Mob1, Email, DOB, NativePlaceId, ProPic, LivePlace;
    View view;

    public ContactList() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.contactlist, container, false);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Contact List");

        Listview = (ListView) view.findViewById(R.id.list_Salary);
        refrest = (SwipeRefreshLayout) view.findViewById(R.id.refrest);

        pDialog = new ProgressDialog(getActivity());
        cd = new ConnectionDetector(getActivity().getBaseContext());
        sharedPreferences = getActivity().getSharedPreferences(Login_Shared_PREFERENCES, Context.MODE_PRIVATE);

        isInternetPresent = cd.isConnectingToInternet();
        if (isInternetPresent) {

            refrest.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    new get_Users_List().execute();
                }
            });
        } else {
            Toast.makeText(getActivity(), "Check internet connection", Toast.LENGTH_SHORT).show();

        }


        if (sharedPreferences.contains(User_KEY)) {
            user = sharedPreferences.getString(User_KEY, "");

            Log.d("user name", user);


        }


        isInternetPresent = cd.isConnectingToInternet();
        if (isInternetPresent) {
            new get_Users_List().execute();
        } else {
            Toast.makeText(getActivity(), "Check internet connection", Toast.LENGTH_SHORT).show();

        }


        Listview.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getActivity(), Person_Profile.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //overridePendingTransition(R.anim.animation_leave,R.anim.animation_enter);
                i.putExtra("classes", array_bean.get(position).getUSer_ID());


                Log.d("userid", array_bean.get(position).getUSer_ID());

                startActivity(i);

            }
        });


        // Inflate the layout for this fragment
        return view;
    }


    public class CustomAdapter_classwork extends BaseAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return array_bean.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(final int position, View convertView,
                            ViewGroup parent) {
            // TODO Auto-generated method stub

            if (convertView == null) {

                convertView = getActivity().getLayoutInflater().inflate(
                        R.layout.userlist_adapter, null);
            }

            TextView tv = (TextView) convertView.findViewById(R.id.uname);
            TextView tvs = (TextView) convertView.findViewById(R.id.mobnumber);
            TextView tvss = (TextView) convertView.findViewById(R.id.emailadds);
            ImageView userimage = (ImageView) convertView.findViewById(R.id.userImg);


            tv.setText(array_bean.get(position).getFirstName() + " " + array_bean.get(position).getMidName() + " " + array_bean.get(position).getLastName());
            tvs.setText(array_bean.get(position).getMob1());
            tvss.setText(array_bean.get(position).getEmail());

            String myImage = array_bean.get(position).getImage();
            Log.e("img : ", myImage);
            Glide.with(getActivity()).load(Global.ImagesforProfile+myImage).into(userimage);


            return convertView;
        }
    }


    public class get_Users_List extends AsyncTask<String, String, String> {

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

            String json = jsonParser.makeHttpRequest(Global.server_link+"GetContactLsit", "POST", param);
            Log.d("Response: ", "> " + json);

            return json;
        }

        protected void onPostExecute(String jsonStr) {
            // TODO Auto-generated method stub
            super.onPostExecute(jsonStr);
            if (pDialog.isShowing()) {
                pDialog.dismiss();
                refrest.setRefreshing(false);
            }

            if (jsonStr != null) {
                try {


                    JSONObject BaseObject = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray abc = BaseObject.getJSONArray("ContactList");

                    // looping through All Contacts
                    for (int i = 0; i < abc.length(); i++) {
                        JSONObject c = abc.getJSONObject(i);

                        Bean_User_List bean = new Bean_User_List();

                        bean.setUSer_ID(UserId = c.getString("UserId"));
                        bean.setUserName(UserName = c.getString("UserName"));
                        bean.setFirstName(FirstName = c.getString("FirstName"));
                        bean.setMidName(MidName = c.getString("MidName"));
                        bean.setLastName(LastName = c.getString("LastName"));
                        bean.setMob1(Mob1 = c.getString("Mob1"));
                        bean.setEmail(Email = c.getString("Email"));
                        bean.setImage(Email = c.getString("ProPic"));
                        array_bean.add(bean);


                    }
                    Listview.setVisibility(View.VISIBLE);
                    Listview.setAdapter(new CustomAdapter_classwork());


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(getActivity(), "Sorry No Data Found", Toast.LENGTH_SHORT).show();
            }
        }

    }
}