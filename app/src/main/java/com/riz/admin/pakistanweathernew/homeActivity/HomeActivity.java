package com.riz.admin.pakistanweathernew.homeActivity;


import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.zetterstrom.com.forecast.ForecastClient;
import android.zetterstrom.com.forecast.ForecastConfiguration;
import android.zetterstrom.com.forecast.models.DataBlock;
import android.zetterstrom.com.forecast.models.DataPoint;


import com.riz.admin.pakistanweathernew.R;

import com.riz.admin.pakistanweathernew.SettingPage;
import com.riz.admin.pakistanweathernew.fragments.currentforecast.CurrentForeCastFragment;
import com.riz.admin.pakistanweathernew.splashscreen.GetForecastContractor;
import com.riz.admin.pakistanweathernew.splashscreen.getForeCastPresenter;

import java.util.List;


public class HomeActivity extends AppCompatActivity {
getForeCastPresenter presenter;
    ViewPager viewPager;
    private static final String TAG = "MainActivity";
    MainViewPagerAdapter mainViewPagerAdapter;
    TabLayout mainTabLayout;

double defualtValueExtra=10;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
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
        FragmentManager fragmentManager = getSupportFragmentManager();
        switch (item.getItemId()) {
            case R.id.action_settings:
               // Log.d(TAG, "onOptionsItemSelected: ");
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                SettingPage settingPage = new SettingPage();
                fragmentTransaction.replace(R.id.fmSettingPageFrag, settingPage).isAddToBackStackAllowed();
                fragmentTransaction.commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }






}
