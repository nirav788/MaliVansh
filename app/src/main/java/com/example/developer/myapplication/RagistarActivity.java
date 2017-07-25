package com.example.developer.myapplication;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.developer.myapplication.ConnectionDetector.ConnectionDetector;
import com.example.developer.myapplication.Globals.Global;
import com.example.developer.myapplication.JSON.JsonParse;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.loopj.android.http.RequestParams;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.widget.Toast;


/**
 * Created by Developer on 26-09-2016.
 */

public class RagistarActivity extends AppCompatActivity {
    TextView gender, info_text, Regilion, Adarcd;
    //public ProgressDialog pDialog;
    ProgressDialog loadingView;
    int oops;
    Boolean isInternetPresent = false;
    RadioButton button1, button2;
    EditText Fname, Mname, Lname, Dob, EMailadd, Currentadd, Contactnum, Uname;
    final Calendar myCalendar = Calendar.getInstance();
    Button submit;
    private KProgressHUD hud;
    private ArrayList<String> students;
    private ArrayList<String> Ideas;
    private ArrayList<String> teachers;
    private ArrayList<String> CastName;
    private ArrayList<String> SubCastName;
    Spinner spinner1, spinner2, spinner3, spinner4;
    private ArrayList<NameValuePair> param;
    SearchableSpinner spinns;
    private JSONArray result, results, Castresult, SubCastresult, Ideresult;
    ConnectionDetector cd;
    private TextView textViewName, spinnerreligiontext;
    ImageView Uploadimg, AAdharcard;
    String reg, stst, aaa, bbb, ccc, spin22, spin44;
    ProgressDialog prgDialog;
    String encodedString;
    RequestParams params = new RequestParams();
    private ArrayList<NameValuePair> parammm;
    String imgPath, fileName;
    Bitmap bitmap;
    private static int RESULT_LOAD_IMG = 1;
    String filename;
    Context context;
    RadioGroup rg;
    int xxx;
    String uname, fname, mname, lname, address, dob, sex, mail, curradd, connum;
    String tmpStr10, nirav;

    int REQUEST_CAMERA = 100, SELECT_FILE = 10;
    String base64, base641, base642, radiovalue;
    String doesdone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registration_main);


        prgDialog = new ProgressDialog(this);
        cd = new ConnectionDetector(getBaseContext());
        prgDialog.setCancelable(false);
        gender = (TextView) findViewById(R.id.gender);
        Regilion = (TextView) findViewById(R.id.religion);
        info_text = (TextView) findViewById(R.id.info_text);
        // Adarcd = (TextView) findViewById(R.id.aadharcardinfo);


        rg = (RadioGroup) findViewById(R.id.radioGroup1);
        button1 = (RadioButton) findViewById(R.id.radio0);
        button2 = (RadioButton) findViewById(R.id.radio1);

        Uploadimg = (ImageView) findViewById(R.id.upload_img);
        // AAdharcard = (ImageView) findViewById(R.id.aadharcard);

        Fname = (EditText) findViewById(R.id.input_firstname);
        Mname = (EditText) findViewById(R.id.input_middlename);
        Lname = (EditText) findViewById(R.id.input_lastname);
        Dob = (EditText) findViewById(R.id.input_dob);
        EMailadd = (EditText) findViewById(R.id.input_email);
        Currentadd = (EditText) findViewById(R.id.input_currentadd);
        Uname = (EditText) findViewById(R.id.input_username);
        Contactnum = (EditText) findViewById(R.id.input_connumber);

        textViewName = (TextView) findViewById(R.id.textViewName);
        spinnerreligiontext = (TextView) findViewById(R.id.spinnerreligiontext);


        spinns = (SearchableSpinner) findViewById(R.id.spina);
        spinner4 = (SearchableSpinner) findViewById(R.id.spinnermarital);

        spinner1 = (Spinner) findViewById(R.id.spinnerreligion);
        spinner2 = (Spinner) findViewById(R.id.spinnercast);
        spinner3 = (Spinner) findViewById(R.id.spinnersubcast);

        submit = (Button) findViewById(R.id.submit);


        students = new ArrayList<String>();
        Ideas = new ArrayList<String>();
        teachers = new ArrayList<String>();
        CastName = new ArrayList<String>();
        SubCastName = new ArrayList<String>();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MaterialSpinner spinnerblood = (MaterialSpinner) findViewById(R.id.spinnerblood);


        isInternetPresent = cd.isConnectingToInternet();
        if (isInternetPresent) {

            getData();
            ReligionName();
            getDatas();


        } else {
            // Ask user to connect to Internet
            showAlertDialog(RagistarActivity.this, "No Internet Connection",
                    "You don't have internet connection.", false);
        }


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "yyyy-MM-dd"; // In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                Dob.setText(sdf.format(myCalendar.getTime()));
            }

        };

        Dob.setInputType(InputType.TYPE_NULL);
        Dob.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(RagistarActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        Fname.setHint("First Name");
        Typeface copperplateGothicLightsss = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        Fname.setTypeface(copperplateGothicLightsss);

        Mname.setHint("Middle Name");
        Typeface copperplateGothicLightssss = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        Mname.setTypeface(copperplateGothicLightssss);


        Lname.setHint("Last Name");
        Typeface copperplateGothicLightsssss = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        Lname.setTypeface(copperplateGothicLightsssss);

        Dob.setHint("Date Of Birth");
        Typeface copperplateGothicLightssssss = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        Dob.setTypeface(copperplateGothicLightssssss);

        EMailadd.setHint("Email Address");
        Typeface copperplateGothicLightsssssss = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        EMailadd.setTypeface(copperplateGothicLightsssssss);

        Currentadd.setHint("Current Address");
        Typeface currentadd = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        Currentadd.setTypeface(currentadd);


        Uname.setHint("User Name");
        Typeface username = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        Uname.setTypeface(username);


        Contactnum.setHint("Contact Number");
        Typeface contactnum = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        Contactnum.setTypeface(contactnum);

        button1.setText("Male");
        Typeface copperplateGothicLightss = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        button1.setTypeface(copperplateGothicLightss);


        button2.setText("Female");
        Typeface copperplateGothicLights = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        button2.setTypeface(copperplateGothicLights);

        Typeface abc = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        gender.setTypeface(abc);
        gender.setTextColor(getResources().getColor(R.color.black));

        Typeface xxx = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");
        Regilion.setTypeface(xxx);
        Regilion.setTextColor(getResources().getColor(R.color.black));
        Regilion.setText("Select Religion");
        Regilion.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

        Typeface xyz = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        info_text.setTypeface(xyz);
        info_text.setText("Upload Profile Photo");


      /*  Typeface sss = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        Adarcd.setTypeface(sss);
        Adarcd.setText("Upload Aadhar Card");
*/
        Typeface copperplateGothicLight = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");
        submit.setText("SUBMIT");
        submit.setTextColor(getResources().getColor(R.color.White));
        submit.setTypeface(copperplateGothicLight);


        spinnerblood.setItems("Blood Group", "O +", "O -", "A+", "A-", "B+", "B-", "AB+", "AB-");
        spinnerblood.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                //Snackbar.make(view, "Selected " + item, Snackbar.LENGTH_LONG).show();
                ccc = item;

                Log.d("blood", ccc);


            }
        });


        spinns.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                textViewName.setText(getName(position));
                reg = textViewName.getText().toString();

                Log.d("regs", reg);

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                spinnerreligiontext.setText(getNames(position));
                stst = spinnerreligiontext.getText().toString();
                System.out.println(getNames(position));

                SubCastName.clear();
                CastName.clear();


                CastName();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                spin22 = getNamess(position);
                System.out.println("spin2" + getNamess(position));
                SubCastName.clear();
                CastName.clear();

                SubCastName();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                bbb = getSubNamess(position);

                System.out.println("spin3" + getSubNamess(position));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                spin44 = getNameplace(position);

                System.out.println("spin4" + getNameplace(position));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Uploadimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectImage();
            }
        });


       /* AAdharcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(galleryIntent, RESULT_LOAD_IMG);

            }
        });*/

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //  xml_ajax();

                new LoginAsyntask().execute();

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // xml_ajax();

                // new LoginAsyntask().execute();
                int selectedId = rg.getCheckedRadioButtonId();
                button1 = (RadioButton) findViewById(selectedId);

                uname = Uname.getText().toString();
                fname = Fname.getText().toString();
                mname = Mname.getText().toString();
                lname = Lname.getText().toString();
                address = Currentadd.getText().toString();
                dob = Dob.getText().toString();
                mail = EMailadd.getText().toString();
                connum = Contactnum.getText().toString();
                sex = button1.getText().toString();

                Toast.makeText(getApplicationContext(), uname, Toast.LENGTH_SHORT).show();


                if (Uname.equals("") || Fname.equals("") || Mname.equals("") || Lname.equals("") || Currentadd.equals("") || Dob.equals("") || EMailadd.equals("") || Contactnum.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please Enter all fields", Toast.LENGTH_SHORT).show();
                    //showAlertDialog(getActivity(), "Alert", "Enter both fields", false);
                } else {

                    isInternetPresent = cd.isConnectingToInternet();
                    if (isInternetPresent) {

                        uname = Uname.getText().toString();
                        fname = Fname.getText().toString();
                        mname = Mname.getText().toString();
                        lname = Lname.getText().toString();
                        address = Currentadd.getText().toString();
                        dob = Dob.getText().toString();
                        mail = EMailadd.getText().toString();
                        connum = Contactnum.getText().toString();

                        oops = 1;

                        tmpStr10 = String.valueOf(oops);

                        nirav = "hiehello";
                        new LoginAsyntask().execute();
                    } else {
                        Toast.makeText(getApplicationContext(), "Check internet connection", Toast.LENGTH_SHORT).show();

                    }
                }
                /*Intent i = new Intent(getActivity(), MainActivity.class);
                startActivity(i);*/
            }

        });

    }


    private void getData() {
        //Creating a string request
        StringRequest stringRequest = new StringRequest(Global.server_link+"GetNativePlace",
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
                        Toast.makeText(RagistarActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {

        };

        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    private void getStudents(JSONArray j) {

        for (int i = 0; i < j.length(); i++) {
            try {

                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
                students.add(json.getString("AllInOne"));


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        spinns.setAdapter(new ArrayAdapter<String>(RagistarActivity.this, android.R.layout.simple_spinner_dropdown_item, students));

    }

    //Method to get student name of a particular position
    public String getName(int position) {
        String name = "";
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


    private void getDatas() {
        //Creating a string request
        StringRequest stringRequest = new StringRequest(Global.server_link+"GetNativePlace",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            //Parsing the fetched Json String to JSON Object
                            j = new JSONObject(response);

                            //Storing the Array of JSON String to our JSON Array
                            Ideresult = j.getJSONArray("Native");

                            //Calling method getStudents to get the students from the JSON Array
                            getIdea(Ideresult);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RagistarActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {

        };

        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    private void getIdea(JSONArray j) {

        for (int i = 0; i < j.length(); i++) {
            try {

                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
                Ideas.add(json.getString("AllInOne"));


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        spinner4.setAdapter(new ArrayAdapter<String>(RagistarActivity.this, android.R.layout.simple_spinner_dropdown_item, Ideas));
    }

    //Method to get student name of a particular position
    public String getNameplace(int position) {
        String name = "";
        try {
            //Getting object of given index
            JSONObject json = Ideresult.getJSONObject(position);

            //Fetching name from that object
            name = json.getString("tId");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Returning the name
        return name;
    }


    private void ReligionName() {
        //Creating a string request
        StringRequest stringRequest = new StringRequest(Global.server_link+"GetReligion",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;

                        try {
                            //Parsing the fetched Json String to JSON Object
                            j = new JSONObject(response);

                            //Storing the Array of JSON String to our JSON Array
                            results = j.getJSONArray("ReligionName");

                            //Calling method getStudents to get the students from the JSON Array
                            getTeachers(results);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RagistarActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {

        };

        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    private void getTeachers(JSONArray j) {

        //teachers.add("select");
        //teachers.add(0,"select");

        for (int i = 0; i < j.length(); i++) {
            try {

                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
                teachers.add(json.getString("ReligionName"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        spinner1.setAdapter(new ArrayAdapter<String>(RagistarActivity.this, android.R.layout.simple_spinner_dropdown_item, teachers));
    }

    //Method to get student name of a particular position
    private String getNames(int position) {
        String name = "";
        try {
            //Getting object of given index
            JSONObject json = results.getJSONObject(position);

            //Fetching name from that object
            name = json.getString("Id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Returning the name
        return name;
    }


    private void CastName() {
        //Creating a string request


        StringRequest stringRequest = new StringRequest(Global.server_link+"GetCast?RelId=" + stst, new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {
                JSONObject j = null;


                try {
                    //Parsing the fetched Json String to JSON Object
                    j = new JSONObject(response);

                    //Storing the Array of JSON String to our JSON Array
                    Castresult = j.getJSONArray("ReligionName");

                    //Calling method getStudents to get the students from the JSON Array
                    getCastresult(Castresult);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RagistarActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {

        };

        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    private void getCastresult(JSONArray j) {

        for (int i = 0; i < j.length(); i++) {
            try {

                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
                CastName.add(json.getString("CastName"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        spinner2.setAdapter(new ArrayAdapter<String>(RagistarActivity.this, android.R.layout.simple_spinner_dropdown_item, CastName));
    }

    //Method to get student name of a particular position
    private String getNamess(int position) {
        String name = "";
        try {
            //Getting object of given index
            JSONObject json = Castresult.getJSONObject(position);

            //Fetching name from that object
            name = json.getString("cId");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Returning the name
        return name;
    }


    private void SubCastName() {
        //Creating a string request


        StringRequest stringRequest = new StringRequest(Global.server_link+"GetSubCast?CastId=" + aaa, new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {
                JSONObject j = null;


                try {
                    //Parsing the fetched Json String to JSON Object
                    j = new JSONObject(response);

                    //Storing the Array of JSON String to our JSON Array
                    SubCastresult = j.getJSONArray("ReligionName");

                    //Calling method getStudents to get the students from the JSON Array
                    getSubCastresult(SubCastresult);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RagistarActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {

        };

        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    private void getSubCastresult(JSONArray j) {

        for (int i = 0; i < j.length(); i++) {
            try {

                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
                SubCastName.add(json.getString("SubCastName"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        spinner3.setAdapter(new ArrayAdapter<String>(RagistarActivity.this, android.R.layout.simple_spinner_dropdown_item, SubCastName));
    }

    //Method to get student name of a particular position
    private String getSubNamess(int position) {
        String name = "";
        try {
            //Getting object of given index
            JSONObject json = SubCastresult.getJSONObject(position);

            //Fetching name from that object
            name = json.getString("subId");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Returning the name
        return name;
    }

    public void showAlertDialog(Context context, String title, String message,
                                Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        // Setting Dialog Title
        alertDialog.setTitle(title);

        // Setting Dialog Message
        alertDialog.setMessage(message);

        // Setting alert dialog icon
        alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);

        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }



    /*public void xml_ajax() {
        AQuery aq = new AQuery(this);
        aq.ajax("http://49.50.67.178:3211/GetRegister.asmx/TestInsert?id="+tmpStr10+"&Name="+nirav, JSONObject.class, this, "savedone");
    }
    public void savedone(String url, JSONObject Json, AjaxStatus status) {
        if (Json != null) {
            Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "unsuccess to access", Toast.LENGTH_LONG).show();
        }
    }*/


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = this.managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }


    public void onActivityResult(int requestCode, int resultCode,
                                 Intent data) {
        super.onActivityResult(requestCode, resultCode,
                data);
        if (resultCode == this.RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {
                File f = new File
                        (Environment.getExternalStorageDirectory()
                                .toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("temp.jpg")) {
                        f = temp;
                        break;
                    }
                }
                try {
                    Bitmap bm;
                    BitmapFactory.Options btmapOptions = new
                            BitmapFactory.Options();

                    bm = BitmapFactory.decodeFile(f.getAbsolutePath(),
                            btmapOptions);
                    bm = Bitmap.createScaledBitmap(bm, 300, 400, true);
                    Uploadimg.setImageBitmap(bm);
                    Uploadimg.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

                    base64 = encodeTobase64(bm);

                    String path = android.os.Environment
                            .getExternalStorageDirectory()
                            + File.separator
                            + "Phoenix" + File.separator + "default";
                    f.delete();
                    OutputStream fOut = null;
                    File file = new File(path, String.valueOf(System
                            .currentTimeMillis()) + ".jpg");
                    try {
                        fOut = new FileOutputStream(file);
                        bm.compress(Bitmap.CompressFormat.JPEG, 200,
                                fOut);
                        fOut.flush();
                        fOut.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == SELECT_FILE) {
                Uri selectedImageUri = data.getData();
                String tempPath = getPath(selectedImageUri);
                Bitmap bm;
                BitmapFactory.Options btmapOptions = new
                        BitmapFactory.Options();
                bm = BitmapFactory.decodeFile(tempPath, btmapOptions);
                bm = Bitmap.createScaledBitmap(bm, 300, 400, true);

                Uploadimg.setImageBitmap(BitmapFactory.decodeFile(getPath(selectedImageUri)));
                base64 = encodeTobase64(bm);

                System.out.print(base64);
            }
        }
    }

    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder
                (this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    Intent intent = new Intent
                            (MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(android.os.Environment
                            .getExternalStorageDirectory(),
                            "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,
                            Uri.fromFile(f));
                    startActivityForResult(intent, REQUEST_CAMERA);
                } else if (items[item].equals("Choose from Library")) {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,

                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(
                            Intent.createChooser(intent, "Select File"),
                            SELECT_FILE);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    public String encodeTobase64(Bitmap image) {
        Bitmap immagex = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immagex.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageoced = Base64.encodeToString(b, Base64.DEFAULT);
        return imageoced;
    }

    public static Bitmap decodeBase64(String input) {
        byte[] bt = null;
        try {
            bt = com.example.developer.myapplication.extras.Base64.decode(input);
        } catch (Exception ex) {
        }
        return BitmapFactory.decodeByteArray(bt, 0, bt.length);
    }


    public class LoginAsyntask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

        }


        @Override
        protected String doInBackground(String... args) {
            JsonParse jsonParser = new JsonParse();
            param = new ArrayList<NameValuePair>();
            param.add(new BasicNameValuePair("uName", uname));
            param.add(new BasicNameValuePair("fName", fname));
            param.add(new BasicNameValuePair("mName", mname));
            param.add(new BasicNameValuePair("lName", lname));
            param.add(new BasicNameValuePair("Rel", stst));
            param.add(new BasicNameValuePair("CastName", spin22));
            param.add(new BasicNameValuePair("SubCast", bbb));
            param.add(new BasicNameValuePair("Add", address));
            param.add(new BasicNameValuePair("Mob", connum));
            param.add(new BasicNameValuePair("Email", mail));
            param.add(new BasicNameValuePair("DOB", dob));
            param.add(new BasicNameValuePair("Gen", sex));
            param.add(new BasicNameValuePair("Native", reg));
            param.add(new BasicNameValuePair("Live", spin44));
            param.add(new BasicNameValuePair("ProPicBase", base64));



            Log.d("value", "\n uName" + uname + "\n fName" + fname + "\n mName" + mname + "\n lName" + lname + "\n Rel" + stst + "\n CastName" + spin22 + "\n SubCast" + bbb + "\n Add" + address + "\n Mob" + connum + "\n Email" + mail + "\n DOB" + dob + "\n Gen" + sex + "\n Native" + reg + "\n Live" + spin44 + "\n Image" + base64);


            String json = jsonParser.makeHttpRequest(Global.server_link+"UserRegistration", "POST", param);
            Log.d("Response: ", "> " + json);
            Log.d("year", dob);
            return json;
        }

        protected void onPostExecute(String jsonStr) {
            // TODO Auto-generated method stub
            super.onPostExecute(jsonStr);

            if (jsonStr != null) {

                Toast.makeText(getApplicationContext(), "sucessful", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "sorry somthing went to wrong....", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
