package com.example.developer.myapplication;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Developer on 30-08-2016.
 */
public class SplashScreen extends Activity{
    private static boolean splashLoaded = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        if (!splashLoaded) {
            setContentView(R.layout.activity_splash);


            int secondsDelayed = 1;
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    Intent i = new Intent(SplashScreen.this,LoginActivity.class);
                    startActivity(i);
                    finish();
                }
            }, secondsDelayed * 3000);

            splashLoaded = true;
        }
        else {
            Intent goToMainActivity = new Intent(SplashScreen.this, LoginActivity.class);
            goToMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(goToMainActivity);
            finish();
        }
    }

}