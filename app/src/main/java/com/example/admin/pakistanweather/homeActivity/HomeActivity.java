package com.example.admin.pakistanweather.homeActivity;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;



import com.example.admin.pakistanweather.R;

import com.example.admin.pakistanweather.SettingPage;
import com.example.admin.pakistanweather.fragments.TodayForeCastFragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;




public class HomeActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 10;
    ViewPager viewPager;
    private static final String TAG = "MainActivity";
    private FragmentManager fragmentManager;
    MainViewPagerAdapter mainViewPagerAdapter;
    TabLayout mainTabLayout;
    private FusedLocationProviderClient fusedLocationProviderClient;
    Location currentLocation;


    private String stringLatLog;
    private TodayForeCastFragment todayForeCastFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar homeToolbar = findViewById(R.id.home_toolbar);
        viewPager = findViewById(R.id.app_main_pager);
        mainTabLayout = findViewById(R.id.app_main_tabs);
        mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mainViewPagerAdapter);
        mainTabLayout.setupWithViewPager(viewPager);
        setSupportActionBar(homeToolbar);
        fusedLocationProviderClient = new FusedLocationProviderClient(this);

getlocation();

    }

    // inflating toolbar menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        fragmentManager = getSupportFragmentManager();
        switch (item.getItemId()) {
            case R.id.action_settings:
                Log.d(TAG, "onOptionsItemSelected: ");
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                SettingPage settingPage = new SettingPage();
                fragmentTransaction.replace(R.id.fmSettingPageFrag, settingPage).isAddToBackStackAllowed();
                fragmentTransaction.commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }



    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    getlocation();
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

    @SuppressLint("MissingPermission")
    public void getlocation() {

        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                currentLocation = location;

                stringLatLog = currentLocation.getLatitude() + "," + currentLocation.getLongitude();

                Bundle bundle = new Bundle();
                bundle.putString("latlog", stringLatLog);
                todayForeCastFragment = TodayForeCastFragment.newInstance(stringLatLog);
                Log.d(TAG, "onSuccess: learing git");
//
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "onFailure: " + e.getMessage());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
getlocation();
    }


}
