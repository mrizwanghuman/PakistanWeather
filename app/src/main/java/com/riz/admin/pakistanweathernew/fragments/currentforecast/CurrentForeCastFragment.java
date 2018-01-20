package com.riz.admin.pakistanweathernew.fragments.currentforecast;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.zetterstrom.com.forecast.models.DataPoint;

import com.riz.admin.pakistanweathernew.R;
import com.riz.admin.pakistanweathernew.homeActivity.HomeActivity;

import com.riz.admin.pakistanweathernew.util.IconSwitch;
import com.riz.admin.pakistanweathernew.util.LocationUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CurrentForeCastFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CurrentForeCastFragment extends Fragment {
    public static final String TAG = "TodayForeCast";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_LAT = "latitude";
    private static final String ARG_LONG = "longitude";

    // TODO: Rename and change types of parameters

    static List<DataPoint> currentlyList = new ArrayList<>();
    private static String Arg_City;
    private static String Arg_State;
    String mCity;
    String mState;
    private TextView tvCurTemp;
    private ImageView tvIconWeather;


    private TextView tvCurWeather;

    private static String locationState;
    private TextView tvDisplayCity;
    private String mLatitude;
    private String mLongtitude;
    static double locLat;
    static double locLang;
    private TextView tvForecastSummary;
    private String currTemp;

    public CurrentForeCastFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param currentlies Parameter 1.
     *                    //    * @param cityName Parameter 2.
     *                    //        * @param stateName Parameter 2.
     * @return A new instance of fragment CurrentForeCastFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CurrentForeCastFragment newInstance(List<DataPoint> currentlies) {
        CurrentForeCastFragment fragment = new CurrentForeCastFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_LAT, (Serializable) currentlies);

        currentlyList = currentlies;

        // Log.d("Parm", "newInstance: "+param1);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mLatitude = getArguments().getString(ARG_LAT);

            //  mLongtitude= getArguments().getString(ARG_LONG);
            // hourlyList= getArguments().getDouble(ARG_LAT);


            //  Log.d("PAram", "onCreate: "+mParam1);

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_current_forecast, container, false);


        tvDisplayCity = view.findViewById(R.id.tvDisplayCity);
        tvCurTemp = view.findViewById(R.id.tvCurTemp);
        tvIconWeather = view.findViewById(R.id.tvIconWeather);
        tvCurWeather = view.findViewById(R.id.tvCurWeather);
        TextView tvWind = view.findViewById(R.id.tvWind);
        TextView tvHumidity = view.findViewById(R.id.tvHumidity);
        TextView tvPrecipType= view.findViewById(R.id.tvPrecType);

        TextView tvPrec = view.findViewById(R.id.tvPrec);

        SharedPreferences prefs = getContext().getSharedPreferences(LocationUtil.LP_Shared, MODE_PRIVATE);

        //  String name = prefs.getString("name", "No name defined");//"No name defined" is the default value.
        String cityName = prefs.getString("city", "");
//        String stateName = prefs.getString("state", ""); //0 is the default value.
////        Log.d("sshared", "onCreate: " + cityName);
//        String cityState = cityName + ", " + stateName;
//        Log.d(TAG, "onCreateView: "+stateName);
        tvDisplayCity.setText(cityName);
        for (int i = 0; i < currentlyList.size(); i++) {
            int getInt = currentlyList.get(i).getTemperature().intValue();
            currTemp = String.valueOf(getInt) + "\u2103";
            tvCurTemp.setText(currTemp);
            String icon = currentlyList.get(i).getIcon().getText();
            int iconid = IconSwitch.iconSwitchert(icon);
            // Log.d(TAG, "onCreateView: " + iconid);
            tvIconWeather.setImageResource(iconid);
            tvCurWeather.setText(currentlyList.get(i).getSummary());
            String precip ="Precip: "+ String.valueOf(currentlyList.get(i).getPrecipProbability());
            tvPrec.setText(precip);
            String windSpeed ="Wind Speed: "+ String.valueOf(currentlyList.get(i).getWindSpeed());
            tvWind.setText(windSpeed);
            String humidity = "Humidity: "+String.valueOf(currentlyList.get(i).getHumidity());

            tvHumidity.setText(humidity);
            String precipType= String.valueOf(currentlyList.get(i).getPrecipitationType());
            tvPrecipType.setText(precipType);

        }


        //

        // createNotification();
        createNotification();
        return view;
    }


//    @Override
//    public void setCurrentForecast(List<Currently> currentlyList) {
//        currentForecastPresenter.getCurrentForecast(currentlyList);
//                for (int i = 0; i < currentlyList.size(); i++) {
//
//            tvDisplayCity.setText(currentlyList.get(i).getTemperature());
//            String currentTemp = currentlyList.get(i).getTempC() + "\u2103";
//            tvCurTemp.setText(currentTemp);
//            String weatherIconSrc = currentlyList.get(i).getIconUrl();
////
////
//            Glide.with(getContext()).load(weatherIconSrc).into(tvIconWeather);
//            tvCurWeather.setText(currentlyList.get(i).getWeather());
//        }
//    }


//    @Override
//    public void setDailyForecast(List<CurrentObservation> currentObservations) {
//        for (int i = 0; i < currentObservations.size(); i++) {
//
//            tvDisplayCity.setText(currentObservations.get(i).getDisplayLocation().getFull());
//            String currentTemp = currentObservations.get(i).getTempC() + "\u2103";
//            tvCurTemp.setText(currentTemp);
//            String weatherIconSrc = currentObservations.get(i).getIconUrl();
////
////
//            Glide.with(getContext()).load(weatherIconSrc).into(tvIconWeather);
//            tvCurWeather.setText(currentObservations.get(i).getWeather());
//        }
    //}


//    @Override
//    public void setCurrentLocation(List<CityStateData> cityStateData) {
//        setCityStateDataList = cityStateData;
//        Bundle bundle = new Bundle();
//        bundle.putString("cityName", cityStateData.get(0).getCityName());
//        bundle.putString("stateShortName", cityStateData.get(0).getShortStateName());
//        HourlyForecastFrag hourlyForecastFrag = new HourlyForecastFrag();
//
//        currentForecastPresenter.getDailyForecast(setCityStateDataList);
//    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    void createNotification() {

        Intent intent = new Intent(this.getActivity(), HomeActivity.class);
// use System.currentTimeMillis() to have a unique ID for the pending intent
        PendingIntent pIntent = PendingIntent.getActivity(this.getContext(), (int) System.currentTimeMillis(), intent, 0);

// build notification
// the addAction re-use the same intent to keep the example short
        Notification n = new Notification.Builder(this.getContext())
                .setContentTitle("آج کا موسم  ")
                .setContentText(currTemp)
                .setSmallIcon(R.drawable.ic_weather_noti)
                .setContentIntent(pIntent)
                .setAutoCancel(true)
                .addAction(R.drawable.ic_weather_noti, "Call", pIntent)
                .build();


        NotificationManager notificationManager =
                (NotificationManager) this.getActivity().getSystemService(NOTIFICATION_SERVICE);

        notificationManager.notify(0, n);

    }

}
