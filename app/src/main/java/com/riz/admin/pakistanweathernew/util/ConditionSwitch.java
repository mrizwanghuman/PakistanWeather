package com.riz.admin.pakistanweathernew.util;

import com.riz.admin.pakistanweathernew.R;

/**
 * Created by  Admin on 1/16/2018.
 */

public class ConditionSwitch {

    public static String conditionSwitch(String condition){
        switch (condition) {

            case "clear-day":
                condition = "Clear Day";
                return condition;
            case "clear-night":
                condition = "Clear Night";
                return condition;
            case "rain":
                condition = "Rain";
                return condition;
            case "snow":
                condition = "Snow";
                return condition;
            case "wind":
                condition = "Windy";
                return condition;
            case "fog":
                condition = "Fog";
                return condition;
            default:
                condition = "Weather";
                return condition;

        }

        }
}
