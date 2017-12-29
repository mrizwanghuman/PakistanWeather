package com.example.admin.pakistanweather.util;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.example.admin.pakistanweather.fragments.currentforecast.CurrentForeCastFragment;
import com.example.admin.pakistanweather.fragments.hourlyforecast.HourlyForecastFrag;
import com.example.admin.pakistanweather.fragments.tendaysforecast.TenDaysFrag;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

/**
 * Created by  Admin on 12/23/2017.
 */

public class LocationPermission {
    private static FusedLocationProviderClient fusedLocationProviderClient;
    static Location currentLocation;
    static Context context;
    private  String stringLatLog;
    private CurrentForeCastFragment currentForeCastFragment;
    private  final String TAG = "MainActivity";
    private HourlyForecastFrag hourlyForecastFrag;
    private TenDaysFrag tenDaysFrag;

    public LocationPermission(Context context) {
        this.context = context;
    }

    public static final int MY_PERMISSIONS_REQUEST_GET_FINE_LOC = 10;

    public  void checkPermission(FusedLocationProviderClient fusedLocationProviderClient) {
        Log.d(TAG, "checkPermission: ");
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                AlertDialog.Builder alertDialog= new AlertDialog.Builder(context);
                alertDialog.setTitle("Pakistan Weather");
                alertDialog.setMessage("Pakistan Weather App required location permission and may not work without it");
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},MY_PERMISSIONS_REQUEST_GET_FINE_LOC);

                    }
                });
                alertDialog.create();
                alertDialog.show();
            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions((Activity) context,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_GET_FINE_LOC);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }

        }else {
            getLocation(fusedLocationProviderClient);
        }
    }


    @SuppressLint("MissingPermission")
    public  String getLocation(FusedLocationProviderClient fusedLocationProviderClient) {
       // Log.d(TAG, "getLocation: ");
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                currentLocation = location;
                //Log.d(TAG, "Locaation "+location.getLongitude());
                stringLatLog = currentLocation.getLatitude() + "," + currentLocation.getLongitude();
//               // Log.d(TAG, "onSuccess: "+stringLatLog);
//                Bundle bundle = new Bundle();
//                bundle.putString("latlog", stringLatLog);
                //Log.d(TAG, "onSuccess: "+stringLatLog);
                currentForeCastFragment = CurrentForeCastFragment.newInstance(stringLatLog);
                hourlyForecastFrag = HourlyForecastFrag.newInstance(stringLatLog);
                tenDaysFrag = TenDaysFrag.newInstance(stringLatLog);

//
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
             //   Log.d(TAG, "onFailure: " + e.getMessage());
                stringLatLog = e.getMessage();

            }
        });
        return stringLatLog;
    }
}
