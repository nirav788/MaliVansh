package com.example.developer.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.developer.myapplication.Fragmants.Community;
import com.example.developer.myapplication.Fragmants.ContactList;
import com.example.developer.myapplication.Fragmants.Donation;
import com.example.developer.myapplication.Fragmants.FamilyTree;
import com.example.developer.myapplication.Fragmants.Help;
import com.example.developer.myapplication.Fragmants.JobPortal;
import com.example.developer.myapplication.Fragmants.Matrimonial;
import com.example.developer.myapplication.Fragmants.MyProfile;
import com.example.developer.myapplication.Fragmants.Settings;
import com.example.developer.myapplication.Fragmants.SocialActivity;
import com.example.developer.myapplication.Fragmants.Webview_Tree;

import static com.example.developer.myapplication.extras.CommonConstants.Login_Shared_PREFERENCES;
import static com.example.developer.myapplication.extras.CommonConstants.User_KEY;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SharedPreferences sharedPreferences;
    public String user;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedPreferences = this.getSharedPreferences(Login_Shared_PREFERENCES,
                Context.MODE_PRIVATE);

        if (sharedPreferences.contains(User_KEY)) {
            user = sharedPreferences.getString(User_KEY, "");

        }
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);
        ImageView imgvw = (ImageView)header.findViewById(R.id.imageView);
        TextView tv = (TextView)header.findViewById(R.id.textname);
        TextView mail = (TextView)header.findViewById(R.id.mail);

        imgvw.setImageDrawable(getResources().getDrawable(R.drawable.icn_dashboard_admin));
        tv.setText(user);
        mail.setText("Easybzee@gmail.com");
      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


      /*  FragmentManager fragmentManager1;
        FragmentTransaction fragmentTransaction1;
        fragmentManager1 = getFragmentManager();
        fragmentTransaction1 = fragmentManager1.beginTransaction();
        MyProfile homeFragmnet = new MyProfile();
        fragmentTransaction1.replace(R.id.main_framelayout, homeFragmnet);
        fragmentTransaction1.commit();*/
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

        finish();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentManager fragmentManager1;
        FragmentTransaction fragmentTransaction1;
        fragmentManager1 = getFragmentManager();
        fragmentTransaction1 = fragmentManager1.beginTransaction();

        if (id == R.id.profile) {
            MyProfile homeFragmnet1 = new MyProfile();
            fragmentTransaction1.replace(R.id.main_framelayout, homeFragmnet1);
            fragmentTransaction1.commit();

            // Handle the camera action
        } else if (id == R.id.familytree) {

            FamilyTree homeFragmnet1 = new FamilyTree();
            fragmentTransaction1.replace(R.id.main_framelayout, homeFragmnet1);
            fragmentTransaction1.commit();


        }else if (id == R.id.familywebtree) {

            Webview_Tree homeFragmnet1 = new Webview_Tree();
            fragmentTransaction1.replace(R.id.main_framelayout, homeFragmnet1);
            fragmentTransaction1.commit();


        } else if (id == R.id.contact) {

            ContactList homeFragmnet3 = new ContactList();
            fragmentTransaction1.replace(R.id.main_framelayout, homeFragmnet3);
            fragmentTransaction1.commit();

        } else if (id == R.id.matrimonial) {

            Matrimonial homeFragmnet4 = new Matrimonial();
            fragmentTransaction1.replace(R.id.main_framelayout, homeFragmnet4);
            fragmentTransaction1.commit();

        } else if (id == R.id.jobportal) {

            JobPortal homeFragmnet5 = new JobPortal();
            fragmentTransaction1.replace(R.id.main_framelayout, homeFragmnet5);
            fragmentTransaction1.commit();

        } else if (id == R.id.socialactivity) {

            SocialActivity homeFragmnet6 = new SocialActivity();
            fragmentTransaction1.replace(R.id.main_framelayout, homeFragmnet6);
            fragmentTransaction1.commit();

        } else if (id == R.id.donation) {

            Donation homeFragmnet7 = new Donation();
            fragmentTransaction1.replace(R.id.main_framelayout, homeFragmnet7);
            fragmentTransaction1.commit();

        } else if (id == R.id.community) {

            Community homeFragmnet8 = new Community();
            fragmentTransaction1.replace(R.id.main_framelayout, homeFragmnet8);
            fragmentTransaction1.commit();


        } else if (id == R.id.donation) {

            Donation homeFragmnet9 = new Donation();
            fragmentTransaction1.replace(R.id.main_framelayout, homeFragmnet9);
            fragmentTransaction1.commit();

        } else if (id == R.id.help) {

            Help homeFragmnet10 = new Help();
            fragmentTransaction1.replace(R.id.main_framelayout, homeFragmnet10);
            fragmentTransaction1.commit();

        } else if (id == R.id.setting) {

            Settings homeFragmnet11 = new Settings();
            fragmentTransaction1.replace(R.id.main_framelayout, homeFragmnet11);
            fragmentTransaction1.commit();

        } else if (id == R.id.logout) {

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

            Toast.makeText(getApplicationContext(), "Thank You...!!!!", Toast.LENGTH_SHORT).show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
