package com.example.developer.myapplication;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.developer.myapplication.R.id.textViewName;
import static java.lang.Character.getName;

/**
 * Created by Developer on 24-09-2016.
 */

public class FaltuOnces extends AppCompatActivity implements Spinner.OnItemSelectedListener{

    String fuck;
    Spinner spinner;
    ArrayList<String> Arrcateg = new ArrayList<String>();
    ArrayList<String> Arrcatvalue = new ArrayList<String>();
    ArrayAdapter<String> spinneradapterProduct;
    private TextView textViewName;
    JSONArray info;
    static final String KEY_OID = "Id";
    static final String KEY_OTYPE = "Name";
    ProgressDialog prgDialog;
    AutoCompleteTextView text;
    ArrayList<HashMap<String, String>> Resqresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faltuone);
        textViewName = (TextView) findViewById(R.id.textViewName);
        Resqresult = new ArrayList<>();
        text = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
        spinner = (Spinner) findViewById(R.id.spinner);
        fuck = "rajasthan";


        xml_ajax(fuck);
        getproduct();



        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                textViewName.setText(getName(position));
            }
        });
    }

    public void xml_ajax(String fuck) {
        AQuery aq = new AQuery(this);
        aq.progress(R.id.progressBar1).ajax("http://49.50.67.178:3211/GetRegister.asmx/GetNativePlace?SearchText=" + fuck, JSONObject.class, this, "getJobs");
    }

    public void getJobs(String url, JSONObject json, AjaxStatus status) {
        Arrcateg = new ArrayList<String>();
        Arrcatvalue = new ArrayList<String>();
        if (json != null) {
            Arrcatvalue.add("--Select--");
            Arrcatvalue.add("0");
            JSONArray info = null;
            try {
                info = json.getJSONArray("Native");
                if (info.length() == 0) {
                    Toast.makeText(this, "No Faltu Data Founds", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "success", Toast.LENGTH_LONG).show();
                    for (int i = 0; i < info.length(); i++) {
                        JSONObject info1 = info.getJSONObject(i);
                        Arrcateg.add(info1.getString("tId"));
                        Arrcatvalue.add(info1.getString("AllInOne"));
                    }

                    Log.d("wtf",Arrcatvalue+"");
                    spinneradapterProduct = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Arrcatvalue);
                    spinneradapterProduct.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    text.setAdapter(spinneradapterProduct);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this,"unsuccess to access",Toast.LENGTH_LONG).show();
        }
    }




    public void getproduct() {
        AQuery aq = new AQuery(this);
        aq.ajax("http://49.50.67.178:3211/GetRegister.asmx/GetReligion" , JSONObject.class, this, "getCategoryname");
    }
    public void getCategoryname(String url, JSONObject Json, AjaxStatus status) {
        Arrcateg = new ArrayList<String>();
        Arrcatvalue = new ArrayList<String>();
        if (Json != null) {
            Arrcateg.add("--Select--");
            Arrcatvalue.add("0");
            try {

                info = Json.getJSONArray("ReligionName");
                if (info.length() == 0) {
                } else {
                    for (int i = 0; i < info.length(); i++) {
                        JSONObject info1 = info.getJSONObject(i);
                        Arrcateg.add(info1.getString("ReligionName"));
                        Arrcatvalue.add(info1.getString("Id"));
                    }
                    spinneradapterProduct = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Arrcateg);
                    spinneradapterProduct.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(spinneradapterProduct);
                }


            } catch (Exception ex) {
            }
        } else {
        }
    }
    private String getName(int position){
        String name="";
        try {
            //Getting object of given index
            JSONObject json = info.getJSONObject(position);

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

    }

    //When no item is selected this method would execute
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        textViewName.setText("");

    }
}
