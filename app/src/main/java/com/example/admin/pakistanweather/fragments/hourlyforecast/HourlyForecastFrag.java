package com.example.admin.pakistanweather.fragments.hourlyforecast;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.pakistanweather.R;
import com.example.admin.pakistanweather.model.currentlocationdata.CityStateData;
import com.example.admin.pakistanweather.model.hourforecast.HourlyForecast;
import com.example.admin.pakistanweather.model.hourforecast.HourlyForecastData;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HourlyForecastFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HourlyForecastFrag extends Fragment implements HourlyForecastContract.View{
    HourlyForeCastPresenter hourlyForeCastPresenter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    public static final String TAG="Hourly";
    private static String currentLogLat;
    private RecyclerView recyclerView;
    private TextView tvCurrentCity;


    public HourlyForecastFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     *
     * @return A new instance of fragment HourlyForecastFrag.
     */

    public static HourlyForecastFrag newInstance(String param1) {
        HourlyForecastFrag fragment = new HourlyForecastFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);

        fragment.setArguments(args);
        Log.d(TAG, "newInstance: "+param1);
        currentLogLat = param1;
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_hourly, container, false);
        recyclerView = view.findViewById(R.id.rcHourlyForeCast);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        tvCurrentCity = view.findViewById(R.id.tvCurrentCity);
        hourlyForeCastPresenter = new HourlyForeCastPresenter();

       // cityStateData.add( new CityStateData("Atlanta", "Ga"));
        hourlyForeCastPresenter.onAttachView(this);

hourlyForeCastPresenter.getCurrentLocation(currentLogLat);

        Log.d(TAG, "onCreateView: "+currentLogLat);
        return view;
    }

    @Override
    public void showError(String e) {

    }

    @Override
    public void showProgress(String progress) {

    }

    @Override
    public void setHourlyForeCast(List<HourlyForecast> hourlyForecastDataList) {
       // Log.d(TAG, "setHourlyForeCast: "+hourlyForecastDataList.get(0).getFeelslike());
        HourlyForecastRCAdapter hourlyForecastRCAdapter = new HourlyForecastRCAdapter(hourlyForecastDataList);

        recyclerView.setAdapter(hourlyForecastRCAdapter);

    }



    @Override
    public void setCurentLocation(List<CityStateData> cityStateData) {
        Log.d(TAG, "setCurentLocation: "+cityStateData.get(0).getCityName());
        tvCurrentCity.setText(cityStateData.get(0).getCityName()+ cityStateData.get(0).getShortStateName());
        hourlyForeCastPresenter.getHourForeCast(cityStateData);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hourlyForeCastPresenter.onDetachView();
    }
}
