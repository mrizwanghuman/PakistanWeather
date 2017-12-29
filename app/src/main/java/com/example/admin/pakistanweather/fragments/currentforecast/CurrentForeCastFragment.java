package com.example.admin.pakistanweather.fragments.currentforecast;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.admin.pakistanweather.R;
import com.example.admin.pakistanweather.fragments.hourlyforecast.HourlyForecastFrag;
import com.example.admin.pakistanweather.model.currentlocationdata.CityStateData;
import com.example.admin.pakistanweather.model.currentweatherdata.CurrentObservation;


import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CurrentForeCastFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CurrentForeCastFragment extends Fragment implements CurrentForecastContract.View {
    public static final String TAG ="TodayForeCast";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
CurrentForecastPresenter todayForecastPresenter;
    // TODO: Rename and change types of parameters
    private String mParam1;
static String  userCurrentLocLatLog;
    private TextView tvDisplayCity;

    private TextView tvCurTemp;
    private ImageView tvIconWeather;


    private TextView tvCurWeather;
    private List<CityStateData> setCityStateDataList = new ArrayList<>();

    public CurrentForeCastFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.

     * @return A new instance of fragment CurrentForeCastFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CurrentForeCastFragment newInstance(String param1) {
        CurrentForeCastFragment fragment = new CurrentForeCastFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        userCurrentLocLatLog = param1;
       // Log.d("Parm", "newInstance: "+param1);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
          //  Log.d("PAram", "onCreate: "+mParam1);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_today_forecast, container, false);

       // Log.d(TAG, "onCreateView: "+ userCurrentLocLatLog);
        tvDisplayCity = view.findViewById(R.id.tvDisplayCity);
        tvCurTemp = view.findViewById(R.id.tvCurTemp);
        tvIconWeather = view.findViewById(R.id.tvIconWeather);
        tvCurWeather = view.findViewById(R.id.tvCurWeather);

        todayForecastPresenter = new CurrentForecastPresenter();
        todayForecastPresenter.onAttachView(this);
          todayForecastPresenter.getCurrentLocation(userCurrentLocLatLog);

           //


        return view;
    }



    @Override
    public void showError(String e) {
        Toast.makeText(this.getContext(), e, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress(String progress) {
        Toast.makeText(this.getContext(), progress, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setDailyForecast(List<CurrentObservation> currentObservations) {
        for (int i = 0; i < currentObservations.size(); i++) {

            tvDisplayCity.setText(currentObservations.get(i).getDisplayLocation().getFull());
                        String currentTemp = String.valueOf(currentObservations.get(i).getTempF())+"\u2109";
                       tvCurTemp.setText(currentTemp);
                        String weatherIconSrc = currentObservations.get(i).getIconUrl();
//
//
                       Glide.with(getContext()).load(weatherIconSrc).into(tvIconWeather);
                        tvCurWeather.setText(currentObservations.get(i).getWeather());
        }
    }


    @Override
    public void setCurrentLocation(List<CityStateData> cityStateData) {
        setCityStateDataList = cityStateData;
Bundle bundle = new Bundle();
bundle.putString("cityName", cityStateData.get(0).getCityName());
bundle.putString("stateShortName", cityStateData.get(0).getShortStateName());
        HourlyForecastFrag hourlyForecastFrag = new HourlyForecastFrag();

        todayForecastPresenter.getDailyForecast(setCityStateDataList);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        todayForecastPresenter.onDetachView();
    }
}
