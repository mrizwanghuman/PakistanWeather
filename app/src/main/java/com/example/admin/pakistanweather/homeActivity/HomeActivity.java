package com.example.admin.pakistanweather.homeActivity;


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



import com.example.admin.pakistanweather.R;

import com.example.admin.pakistanweather.SettingPage;


public class HomeActivity extends AppCompatActivity {

    ViewPager viewPager;
    private static final String TAG = "MainActivity";
    MainViewPagerAdapter mainViewPagerAdapter;
    TabLayout mainTabLayout;



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
