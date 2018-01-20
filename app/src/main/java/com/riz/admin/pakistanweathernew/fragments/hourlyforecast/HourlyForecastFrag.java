package com.riz.admin.pakistanweathernew.fragments.hourlyforecast;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.zetterstrom.com.forecast.models.DataBlock;
import android.zetterstrom.com.forecast.models.DataPoint;

import com.riz.admin.pakistanweathernew.R;
import com.riz.admin.pakistanweathernew.model.Hourly;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HourlyForecastFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HourlyForecastFrag extends Fragment{

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_Hour = "cityName";
    private static final String ARG_CITYNAME = "stateName";

    // TODO: Rename and change types of parameters
    private String mHourlyList;
    public static final String TAG="Hourly";

    private RecyclerView recyclerView;
    private TextView tvCurrentCity;

static List<DataBlock>hourlies;

    public HourlyForecastFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param hourlyList Parameter 1.
    // *@param cityName Parameter 2.
     * @return A new instance of fragment HourlyForecastFrag.
     */

    public static HourlyForecastFrag newInstance(List<DataBlock>hourlyList) {
        HourlyForecastFrag fragment = new HourlyForecastFrag();
        Bundle args = new Bundle();
        args.putSerializable(ARG_Hour, (Serializable) hourlyList);
      //  args.putString(ARG_CITYNAME, cityName);
        hourlies= hourlyList;
        fragment.setArguments(args);

       // currentLogLat = stateName;
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mHourlyList = getArguments().getString(ARG_Hour);

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
        if (hourlies!=null) {
            for (int i = 0; i < hourlies.size(); i++) {
                ArrayList<DataPoint> dataPoint = hourlies.get(i).getDataPoints();
                Log.d("Greatt", "onCreateView: " + dataPoint.get(i).getIcon());
                HourlyForecastRCAdapter hourlyForecastRCAdapter = new HourlyForecastRCAdapter(dataPoint);
                recyclerView.setAdapter(hourlyForecastRCAdapter);
            }
        }

//        for (int i = 0; i < hourlyList.size(); i++) {
//            Log.d(TAG, "onCreateView: "+hourlyList.get(i).getSummary());
//        }
        return view;
    }



//    @Override
//    public void setHourlyForeCast(List<HourlyForecast> hourlyForecastDataList) {
//        Log.d(TAG, "setHourlyForeCast: "+hourlyForecastDataList.get(0).getFeelslike());
//
//
//
//
//    }





    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
