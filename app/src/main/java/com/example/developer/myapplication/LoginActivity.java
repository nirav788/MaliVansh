package com.example.developer.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences.Editor;

import com.example.developer.myapplication.ConnectionDetector.ConnectionDetector;
import com.example.developer.myapplication.Globals.Global;
import com.example.developer.myapplication.JSON.JsonParse;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.developer.myapplication.extras.CommonConstants.Login_Shared_PREFERENCES;
import static com.example.developer.myapplication.extras.CommonConstants.User_KEY;

/**
 * Created by Developer on 30-08-2016.
 */
public class LoginActivity extends AppCompatActivity {

    TextView text, welcome, malivansh, join, connected, forgotpwd;
    Button Login, Registration;
    ConnectionDetector cd;
    Boolean isInternetPresent = false;
    EditText UserName,PassWord;
    private ArrayList<NameValuePair> param;
    SharedPreferences sharedPreferences;
    String unames, pwds;
    String id;
    public ProgressDialog pDialog;
    String htmlString = "<u>Underlined Text</u>";
    String user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login_main);

        sharedPreferences = this.getSharedPreferences(Login_Shared_PREFERENCES, Context.MODE_PRIVATE);
        Login = (Button) findViewById(R.id.login);
        Registration = (Button) findViewById(R.id.registartion);

        UserName = (EditText) findViewById(R.id.input_username);
        PassWord = (EditText) findViewById(R.id.input_password);

        text = (TextView) findViewById(R.id.htmltext);
        welcome = (TextView) findViewById(R.id.wlcome);
        malivansh = (TextView) findViewById(R.id.malivansh);
        join = (TextView) findViewById(R.id.join);
        connected = (TextView) findViewById(R.id.connected);
        forgotpwd = (TextView) findViewById(R.id.forgotpwd);

        cd = new ConnectionDetector(getBaseContext());


        isInternetPresent = cd.isConnectingToInternet();

        if (sharedPreferences.contains(User_KEY)) {
            user = sharedPreferences.getString(User_KEY, "");
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Welcome " + user, Toast.LENGTH_SHORT).show();
        }


        Registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), RagistarActivity.class);
                startActivity(i);
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                unames = UserName.getText().toString().trim();
                pwds = PassWord.getText().toString().trim();

                if (unames.equals("") && pwds.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please Enter both fields", Toast.LENGTH_SHORT).show();
                    //showAlertDialog(getActivity(), "Alert", "Enter both fields", false);
                } else {


                    if (isInternetPresent) {

                        isInternetPresent = cd.isConnectingToInternet();
                        new LoginAsyntask().execute();

                    } else {
                        Toast.makeText(getApplicationContext(), "Check internet connection", Toast.LENGTH_SHORT).show();

                    }
                }
                /*Intent i = new Intent(getActivity(), MainActivity.class);
				startActivity(i);*/
            }


        });

        UserName.setHint("Username");
        Typeface copperplateGothicLightss = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        UserName.setTypeface(copperplateGothicLightss);

        PassWord.setHint("Password");
        Typeface copperplateGothicLightsss = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        PassWord.setTypeface(copperplateGothicLightsss);

        Typeface copperplateGothicLight = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        Login.setTypeface(copperplateGothicLight);

        Typeface copperplateGothicLights = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        Registration.setTypeface(copperplateGothicLights);


        Typeface facesssss = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        forgotpwd.setTypeface(facesssss);

        Typeface facessss = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        join.setTypeface(facessss);

        Typeface facesss = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        connected.setTypeface(facesss);

        Typeface facess = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        malivansh.setTypeface(facess);


        Typeface faces = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        welcome.setTypeface(faces);

        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        text.setTypeface(face);
        String str = "<p> By Signing up, I agree to Mali Vansh's <u> Terms of Service,&nbsp Payments Terms of Service,</u>&nbsp<u>Privacy Policy,</u>&nbsp<u> Guest Refund Policy </u> and Host Guarantee Terms </p>";
        //need to import android.text.Html class
        //set text style bold, italic and underline from html tag
        text.setText(Html.fromHtml(str));

        //  text.setPaintFlags(text.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }


    public class LoginAsyntask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            pDialog = new ProgressDialog(LoginActivity.this);
            pDialog.setMessage("Please Wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }


        @Override
        protected String doInBackground(String... args) {
            JsonParse jsonParser = new JsonParse();
            param = new ArrayList<NameValuePair>();
            param.add(new BasicNameValuePair("UserName", unames));
            param.add(new BasicNameValuePair("Pass", pwds));


            Log.d("faltu", unames.toString());
            Log.d("value", "\n uName" + unames + "\n fName" + pwds);


            String json = jsonParser.makeHttpRequest(Global.server_link+"GetLogIn", "POST", param);
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
                    JSONArray abc = BaseObject.getJSONArray("UserId");

                    // looping through All Contacts
                    for (int i = 0; i < abc.length(); i++) {
                        JSONObject c = abc.getJSONObject(i);

                        id = c.getString("UserId");

                        Log.d("value", id);

                        if (id.equals("")||id.equals(null)){
                            Toast.makeText(getApplicationContext(), "please check username and pwd", Toast.LENGTH_SHORT).show();

                        }

                        else {
                            Editor editor=sharedPreferences.edit();
                            editor.putString(User_KEY, id.toString());
                            editor.commit();

                            Intent ii = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(ii);
                            Toast.makeText(getApplicationContext(), "Login Successful..Welcome", Toast.LENGTH_SHORT).show();
                            finish();
                        }


                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(getApplicationContext(), "please check username and pwd", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
