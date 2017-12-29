package com.example.admin.pakistanweather.splashscreen;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.admin.pakistanweather.R;
import com.example.admin.pakistanweather.homeActivity.HomeActivity;
import com.example.admin.pakistanweather.util.LocationPermission;
import com.google.android.gms.location.FusedLocationProviderClient;

public class SplashScreenActivity extends AppCompatActivity {

    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationPermission locationPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        fusedLocationProviderClient = new FusedLocationProviderClient(this);
        //checkPermission();
        locationPermission = new LocationPermission(this);
        locationPermission.checkPermission(fusedLocationProviderClient);
       // Log.d("splash", "onCreate: "+locationPermission);
startHomeActivity();
    }


    private void startHomeActivity(){

        Handler splashScreenIntent = new Handler();
        splashScreenIntent.postDelayed(new Runnable(){


            @Override
            public void run() {
                Intent homeActivityIntent = new Intent(SplashScreenActivity.this, HomeActivity.class);
                startActivity(homeActivityIntent);
                finish();
            }
        },1000);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case LocationPermission.MY_PERMISSIONS_REQUEST_GET_FINE_LOC: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    locationPermission.getLocation(fusedLocationProviderClient);
                  //  Log.d("Spasl", "onRequestPermissionsResult: ");
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
