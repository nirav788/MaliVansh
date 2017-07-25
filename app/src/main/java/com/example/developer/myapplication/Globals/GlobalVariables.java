package com.example.developer.myapplication.Globals;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
/**
 * Created by Developer on 02-09-2016.
 */
public class GlobalVariables {

    public static String UserName;
    public static String Password;
    public static String PortalName;
    public static String deviceSN;
    public static String deviceGCM;
    /** Checking the internet is available or not */
    public static boolean isConnected = true;

    /** Setting DEBUG to false */
    public static final boolean DEBUG = false;

    public static final String UserPreference = "Pref";
    public static final String UserType = "User";
    public static String DEVICEID_PUSHWOOSH;
    public static String window="window_end" ;
    /**
     * This is a broadcast receiver that will be registered for receiving the network status broadcast from the OS.
     * and will set the <strong>isConnected</strong> flag to according value.
     */
    public static BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("BroadCast", "Is Connected =" + isConnected);
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo ni = cm.getActiveNetworkInfo();
            Log.i("BroadCastReceiver", "Connected =" + isConnected);
            if (ni != null && ni.isConnected()) {
                isConnected = true;
            } else {
                isConnected = false;
            }
        }
    };



    public static void AlertForNetwork(Context con) {
        AlertDialog.Builder builder = new AlertDialog.Builder(con);
        builder.setTitle("Network error!");
        builder.setMessage("Network seems Unavailable! Please try after sometime.").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();

            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public boolean isConnectingToInternet(Context con){
        ConnectivityManager connectivity = (ConnectivityManager) con.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivity != null)
        {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }
        }
        return false;
    }
}
