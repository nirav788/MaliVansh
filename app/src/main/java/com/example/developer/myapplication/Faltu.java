package com.example.developer.myapplication;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.example.developer.myapplication.Globals.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Developer on 02-09-2016.
 */
public class Faltu  extends AppCompatActivity {



    static final String KEY_OID = "Id";
    static final String KEY_OTYPE = "Name";
    static final String KEY_ONAME = "Country";
    private ListView lv;
    ProgressDialog prgDialog;

    ArrayList<HashMap<String, String>> Resqresult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faltu);
        Resqresult = new ArrayList<>();

        lv = (ListView) findViewById(R.id.list);

        xml_ajax();

    }
    public void xml_ajax() {
        AQuery aq = new AQuery(this);
        aq.progress(R.id.progressBar1).ajax("http://49.50.67.178:3211/Testing.asmx/getvac" , JSONObject.class, this, "getJobs");
    }
    public void getJobs(String url,JSONObject json, AjaxStatus status) {
        if (json != null) {
            JSONArray info = null;
            try {
                info = json.getJSONArray("testtest");
                if (info.length() == 0) {
                    Toast.makeText(this, "No Faltu Data Founds", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(this, "success", Toast.LENGTH_LONG).show();
                    Resqresult = new ArrayList<HashMap<String, String>>();
                    for (int i = 0; i < info.length(); i++) {
                        JSONObject info1 = info.getJSONObject(i);
                        HashMap<String, String> map = new HashMap<>();
                        map.put(KEY_OID, info1.getString("Id"));
                        map.put(KEY_OTYPE, info1.getString("Name"));
                        map.put(KEY_ONAME, info1.getString("Country"));

                        Resqresult.add(map);
                    }
                    if (Resqresult.size() != 0) {
                        ListAdapter adapter = new SimpleAdapter(
                                Faltu.this, Resqresult,
                                R.layout.faltu_adapter, new String[]{"Id", "Name",
                                "Country"}, new int[]{R.id.name,
                                R.id.email, R.id.mobile});

                        lv.setAdapter(adapter);

                    }else{
                        Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_LONG).show();
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this,"unsuccess to access",Toast.LENGTH_LONG).show();
        }
    }
}
