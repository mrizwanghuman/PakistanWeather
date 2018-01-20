package com.riz.admin.pakistanweathernew.util;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.riz.admin.pakistanweathernew.homeActivity.HomeActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.riz.admin.pakistanweathernew.model.LocationLatLog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by  Admin on 12/31/2017.
 */

public class LocationUtil {

    public static final String LP_Shared = "Location";
    Context context;
    private List<Address> addressList;
    private String cityName;
    private String stateName;
    private Activity activity;
    private Boolean gpsEnable;
    private List<LocationLatLog> locationLatLogList;
    private double latitude;
    private double longitude;
    private String latidudeint;
    private String longitudeint;


    public LocationUtil(Context context) {
        this.context = context;
    }


    public void checkPermission(FusedLocationProviderClient fusedLocationProviderClient) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        gpsEnable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        Log.d(Constants.TAG, "getLocationLatLog: " + gpsEnable);

        if (gpsEnable) {



            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                getLocationLatLog(fusedLocationProviderClient);


            }else{
                requestLocationPermission();
            }
        } else {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
            alertDialog.setMessage("Enable GPS to use the application and open the application again");
            alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    context.startActivity(intent);
//                    activity = (Activity) context;
//                    activity.finish();
                }
            });
            alertDialog.show();


        }



    }

    private void requestLocationPermission() {

        // Permission has not been granted and must be requested.
        if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Provide an additional rationale to the user if the permission was not granted
            // and the user would benefit from additional context for the use of the permission.
            // Display a SnackBar with a button to request the missing permission.



            // Show an explanation to the user *asynchronously* -- don't block
            // this thread waiting for the user's response! After the user
            // sees the explanation, try again to request the permission.
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
            alertDialog.setTitle("Pakistan Weather");
            alertDialog.setMessage("Pakistan Weather App required location permission and may not work without it");
            alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ActivityCompat.requestPermissions((Activity) context,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Constants.MY_PERMISSIONS_REQUEST_GET_FINE_LOC);

                }
            });
            alertDialog.create();
            alertDialog.show();
        } else {

            // Request the permission. The result will be received in onRequestPermissionResult().
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    Constants.MY_PERMISSIONS_REQUEST_GET_FINE_LOC);
        }
    }

    @SuppressLint("MissingPermission")
    public List<LocationLatLog> getLocationLatLog(FusedLocationProviderClient fusedLocationProviderClientParm) {


        fusedLocationProviderClientParm.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    locationLatLogList = new ArrayList<>();
                    Log.d(Constants.TAG, "onSuccess: " + location.getLatitude());
                    Log.d(Constants.TAG, "onSuccess: " + location.getLongitude());
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();

                    Geocoder geocoder = new Geocoder(context);
                    try {
                        addressList = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.d("JJ", "onSuccess: "+addressList);
                    cityName = addressList.get(0).getLocality();
                    stateName = addressList.get(0).getAdminArea();
                    locationLatLogList.add(new LocationLatLog(location.getLatitude(), location.getLongitude(),cityName, stateName));
                    //  Log.d(Constants.TAG, "onSuccessIf: "+cityName);
                    latidudeint =  Double.toString(latitude);
                    longitudeint = Double.toString(longitude);
                   // CurrentForeCastFragment currentForeCastFragment = CurrentForeCastFragment.newInstance(latitude, longitude);
//                    HourlyForecastFrag hourlyForecastFrag = HourlyForecastFrag.newInstance(stateName, cityName);
//                    TenDaysFrag tenDaysFrag = TenDaysFrag.newInstance(stateName, cityName);
                } else {
                    cityName = "Lahore";
                    stateName = "Pakistan";
                    locationLatLogList.add(new LocationLatLog(31.582045, 74.329376,cityName, stateName));
                }
                Log.d(Constants.TAG, "onSuccesseelse: " + cityName);
                startHomeActivity();
                SharedPreferences.Editor shareEditor = context.getSharedPreferences(LP_Shared, MODE_PRIVATE).edit();
                shareEditor.putString("lati",  latidudeint);
                Log.d("LATII", "getLocationLatLog: "+latidudeint);
                shareEditor.putString("longi",  longitudeint);
                shareEditor.putString("city", cityName);
                shareEditor.putString("state", stateName);
                shareEditor.apply();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("Great", "onFailure: "+e.getMessage());
            }
        });


return locationLatLogList;
    }

//    public static  void checkGPS(Context context){
//
//        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
//
//        Boolean gpsEnable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
//
//        if (gpsEnable){
//
//        }
//
//    }

    public void startHomeActivity() {

            Handler splashScreenIntent = new Handler();
        splashScreenIntent.postDelayed(new Runnable() {


            @Override
            public void run() {
                Intent homeActivityIntent = new Intent(context, HomeActivity.class);

                context.startActivity(homeActivityIntent);
                // activity.finish();
            }
        }, 1000);
    }



}
