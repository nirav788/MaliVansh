package com.example.developer.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.toolbox.StringRequest;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Developer on 24-09-2016.
 */

public class FaltuOnce extends AppCompatActivity implements Spinner.OnItemSelectedListener{

    Spinner spinner;
    private ArrayList<String> students;
    SearchableSpinner spinns;
    //JSON Array
    private JSONArray result;

    //TextViews to display details
    private TextView textViewName;
  //  AutoCompleteTextView text;
    String KEY_USERNAME ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faltuone);

        //Initializing the ArrayList
        students = new ArrayList<String>();

        //Initializing Spinner
        spinner = (Spinner) findViewById(R.id.spinner);
        spinns = (SearchableSpinner)findViewById(R.id.spina);
        //text = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
        //Adding an Item Selected Listener to our Spinner
        //As we have implemented the class Spinner.OnItemSelectedListener to this class iteself we are passing this to setOnItemSelectedListener
        spinns.setOnItemSelectedListener(this);

        //Initializing TextViews
        textViewName = (TextView) findViewById(R.id.textViewName);

       // KEY_USERNAME = text.getText().toString();


        //This method will fetch the data from the URL
        getData();
    }

    private void getData(){
        //Creating a string request
        StringRequest stringRequest = new StringRequest("http://49.50.67.178:3211/GetRegister.asmx/GetNativePlace",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            //Parsing the fetched Json String to JSON Object
                            j = new JSONObject(response);

                            //Storing the Array of JSON String to our JSON Array
                            result = j.getJSONArray("Native");

                            //Calling method getStudents to get the students from the JSON Array
                            getStudents(result);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(FaltuOnce.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }) {
           /* @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(KEY_PASSWORD, "surat");
                return params;
            }*/
        };

        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    private void getStudents(JSONArray j){
        //Traversing through all the items in the json array

        students.add("--Select--");
        students.add("0");
        for(int i=0;i<j.length();i++){
            try {

                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
                students.add(json.getString("AllInOne"));
               } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //Setting adapter to show the items in the spinner
       // text.setAdapter(new ArrayAdapter<String>(FaltuOnce.this, android.R.layout.simple_spinner_dropdown_item, students));

        spinns.setAdapter(new ArrayAdapter<String>(FaltuOnce.this, android.R.layout.simple_spinner_dropdown_item, students));
    }

    //Method to get student name of a particular position
    private String getName(int position){
        String name="";
        try {
            //Getting object of given index
            JSONObject json = result.getJSONObject(position);

            //Fetching name from that object
            name = json.getString("tId");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Returning the name
        return name;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Setting the values to textviews for a selected item
        textViewName.setText(getName(position));
    }

    //When no item is selected this method would execute
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        textViewName.setText("");

    }
}