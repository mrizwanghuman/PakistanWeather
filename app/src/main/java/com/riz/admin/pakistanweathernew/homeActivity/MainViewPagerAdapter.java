package com.riz.admin.pakistanweathernew.homeActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.riz.admin.pakistanweathernew.fragments.hourlyforecast.HourlyForecastFrag;
import com.riz.admin.pakistanweathernew.fragments.tendaysforecast.TenDaysFrag;
import com.riz.admin.pakistanweathernew.fragments.currentforecast.CurrentForeCastFragment;

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
                CurrentForeCastFragment todayHourlyFragment = new CurrentForeCastFragment();
                return todayHourlyFragment;

            case 1:
                HourlyForecastFrag tomorrowHourlyFrag = new HourlyForecastFrag();
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
