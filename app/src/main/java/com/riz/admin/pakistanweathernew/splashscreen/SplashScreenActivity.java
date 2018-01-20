package com.riz.admin.pakistanweathernew.splashscreen;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import android.zetterstrom.com.forecast.ForecastClient;
import android.zetterstrom.com.forecast.ForecastConfiguration;
import android.zetterstrom.com.forecast.models.DataBlock;
import android.zetterstrom.com.forecast.models.DataPoint;
import android.zetterstrom.com.forecast.models.Unit;

import com.google.android.gms.ads.MobileAds;
import com.riz.admin.pakistanweathernew.R;
import com.riz.admin.pakistanweathernew.fragments.currentforecast.CurrentForeCastFragment;
import com.riz.admin.pakistanweathernew.fragments.hourlyforecast.HourlyForecastFrag;
import com.riz.admin.pakistanweathernew.fragments.tendaysforecast.TenDaysFrag;
import com.riz.admin.pakistanweathernew.model.LocationLatLog;
import com.riz.admin.pakistanweathernew.util.LocationPermission;
import com.riz.admin.pakistanweathernew.util.LocationUtil;
import com.google.android.gms.location.FusedLocationProviderClient;

import java.util.List;

public class SplashScreenActivity extends AppCompatActivity implements GetForecastContractor.View{
getForeCastPresenter presenter;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationPermission locationPermission;
    private LocationUtil locationUtil;
    private List<LocationLatLog> locationLatLogList;
    private List<LocationLatLog> locationLatLogList1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        fusedLocationProviderClient = new FusedLocationProviderClient(this);
        locationUtil = new LocationUtil(this);
        locationUtil.checkPermission(fusedLocationProviderClient);
        //checkPermission();
        //  locationPermission = new LocationPermission(this);
//       // Log.d("splash", "onCreate: "+locationPermission);
        MobileAds.initialize(this, "ca-app-pub-1359300460426716~8737460567");
        ForecastConfiguration configuration =
                new ForecastConfiguration.Builder(com.riz.admin.pakistanweathernew.util.Constants.apiKey)
                        .setCacheDirectory(getCacheDir())
                        .setDefaultUnit(Unit.unitFromString("uk"))
                        .build();
        ForecastClient.create(configuration);
        SharedPreferences prefs = getSharedPreferences(LocationUtil.LP_Shared, MODE_PRIVATE);

          //  String name = prefs.getString("name", "No name defined");//"No name defined" is the default value.
        String stringLat = prefs.getString("lati",null);
            String stringLongi = prefs.getString("longi", null); //0 is the default value.
        Log.d("sshared", "onCreate: "+stringLat);
        presenter= new getForeCastPresenter();
        presenter.onAttachView(this);
        if (stringLat!=null && stringLongi!=null) {
            double latitude = Double.parseDouble(stringLat);
            double longitude = Double.parseDouble(stringLongi);
            presenter.getForecast(latitude, longitude);

            locationLatLogList1 = locationUtil.getLocationLatLog(fusedLocationProviderClient);
        }
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
                    //  locationPermission.getLocation(fusedLocationProviderClient);
                    locationLatLogList = locationUtil.getLocationLatLog(fusedLocationProviderClient);
                  //  Log.d("Testing", "onRequestPermissionsResult: "+locationLatLogList.get(0).getCityName());
                    locationUtil.startHomeActivity();

                    //  Log.d("Spasl", "onRequestPermissionsResult: ");
                } else {
                    Toast.makeText(this, "Location Permission is required ", Toast.LENGTH_LONG).show();

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    closeApp();
                }

            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//
//        LocationUtil locationUtil = new LocationUtil(this);
//        locationUtil.checkPermission(fusedLocationProviderClient);
//        Log.d("Great", "onPause: ");
//    }

    public void closeApp(){
       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               finish();
               System.exit(0);
           }
       }, 1000);

    }


    @Override
    public void showError(String e) {

    }

    @Override
    public void showProgress(String progress) {

    }

    @Override
    public void setForeCast(List<DataPoint> currentlyList, List<DataBlock> hourlyList, List<DataBlock> dailyList) {
        for (int i = 0; i < currentlyList.size(); i++) {
            Log.d("Kiran", "setForeCast: "+currentlyList.get(i).getSummary());
        }


        CurrentForeCastFragment currentForeCastFragment = CurrentForeCastFragment.newInstance(currentlyList);
        HourlyForecastFrag hourlyForecastFrag= HourlyForecastFrag.newInstance(hourlyList);
        TenDaysFrag tenDaysFrag= TenDaysFrag.newInstance(dailyList);

    }


}
