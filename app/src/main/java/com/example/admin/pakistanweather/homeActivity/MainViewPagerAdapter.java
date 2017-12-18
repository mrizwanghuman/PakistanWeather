package com.example.admin.pakistanweather.homeActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.admin.pakistanweather.fragments.TenDaysFrag;
import com.example.admin.pakistanweather.fragments.TomorrowHourlyFrag;
import com.example.admin.pakistanweather.fragments.TodayForeCastFragment;

/**
 * Created by  Admin on 12/15/2017.
 */

public class MainViewPagerAdapter extends FragmentPagerAdapter {
    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                TodayForeCastFragment todayHourlyFragment = new TodayForeCastFragment();
                return todayHourlyFragment;

            case 1:
                TomorrowHourlyFrag tomorrowHourlyFrag = new TomorrowHourlyFrag();
                return tomorrowHourlyFrag;
            case 2:
                TenDaysFrag tenDaysFrag = new TenDaysFrag();
                return tenDaysFrag;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Today's";
            case 1:
                return "Hourly";
            case 2:
                return "10 Days Forecast";
            default:
                return null;
        }
    }
}
