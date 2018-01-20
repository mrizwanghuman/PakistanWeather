package com.riz.admin.pakistanweathernew.util;

import com.riz.admin.pakistanweathernew.R;

/**
 * Created by  Admin on 1/13/2018.
 */

public  class IconSwitch {

    public static int iconSwitchert(String icon){
        int id;
        switch (icon){

            case "clear-day":
             id=   R.drawable.sun;
             return id;
            case "clear-night":
                id=R.drawable.moon_waning_crescent;
                return  id;
            case "rain":
                id= R.drawable.cloud_rain;
                return id;
            case "snow":
                id=R.drawable.cloud_snow;
                return id;
            case "wind":
                id= R.drawable.wind;
                return id;
            case "fog":
                id= R.drawable.cloud_fog;
                return id;
            case "partly-cloudy-day":
                id= R.drawable.cloud_sun;
                return id;
            case "partly-cloudy-night":
                id=R.drawable.cloud_moon;
                return id;
                default:
                    id=R.drawable.moon_new;
                    return id;



        }

    }
    public static int iconSwitchUnderScore(String icon){
        int id;
        switch (icon){

            case "clear_day":
                id=   R.drawable.sun;
                return id;
            case "clear_night":
                id=R.drawable.moon_waning_crescent;
                return  id;
            case "rain":
                id= R.drawable.cloud_rain;
                return id;
            case "snow":
                id=R.drawable.cloud_snow;
                return id;
            case "wind":
                id= R.drawable.wind;
                return id;
            case "fog":
                id= R.drawable.cloud_fog;
                return id;
            case "partly_cloudy_day":
                id= R.drawable.cloud_sun;
                return id;
            case "partly_cloudy_night":
                id=R.drawable.cloud_moon;
                return id;
            case "cloudy":
                id=R.drawable.cloud;
                return id;
            default:
                id=R.drawable.moon_new;
                return id;



        }

    }
}
