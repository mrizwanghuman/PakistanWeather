package com.riz.admin.pakistanweathernew.fragments.tendaysforecast;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.zetterstrom.com.forecast.models.DataBlock;
import android.zetterstrom.com.forecast.models.DataPoint;

import com.riz.admin.pakistanweathernew.R;
import com.riz.admin.pakistanweathernew.model.Daily;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TenDaysFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TenDaysFrag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_STATE = "stateName";
    private static final String ARG_CITY = "cityName";


    // TODO: Rename and change types of parameters
    private String mState;
    private String mCity;
    private static String latLog;
    private RecyclerView recyclerView;
    private TenDaysForecastRCAdapter tenDaysForecastRCAdapter;
    private static String locStateName;
    private static String locCityName;

static List<DataBlock> dataBlocksList;
    public TenDaysFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param dataBlocks  Parameter 1.
    // * @param stateName Paramet 2.
     * @return A new instance of fragment TenDaysFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static TenDaysFrag newInstance(List<DataBlock> dataBlocks) {
        TenDaysFrag fragment = new TenDaysFrag();
        Bundle args = new Bundle();
        args.putSerializable(ARG_STATE, (Serializable) dataBlocks);
        dataBlocksList= dataBlocks;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mState = getArguments().getString(ARG_STATE);


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ten_days, container, false);
        Log.d("ten", "onCreateView: " + latLog);

        recyclerView = view.findViewById(R.id.rcTenDaysForecast);
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
        for (int i = 0; i < dataBlocksList.size(); i++) {

            ArrayList<DataPoint> dataPointArrayList= dataBlocksList.get(i).getDataPoints();
//            recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
            tenDaysForecastRCAdapter = new TenDaysForecastRCAdapter(dataPointArrayList);
            recyclerView.setAdapter(tenDaysForecastRCAdapter);
        }

        return view;
    }



//    @Override
//    public void setTenDaysForeCast(List<Forecastday_> simpleForecasts, List<Forecastday> forecastDays) {
//        String TAG = "Great";
//        for (int i = 0; i < simpleForecasts.size(); i++) {
//            Log.d(TAG, "setTenDaysForeCast: " + forecastDays.get(i).getTitle());
//        }
//        Log.d(TAG, "setTenDaysForeCast: " + simpleForecasts.size() + " " + forecastDays.size());
//        tenDaysForecastRCAdapter = new TenDaysForecastRCAdapter(forecastDays, simpleForecasts);
//        recyclerView.setAdapter(tenDaysForecastRCAdapter);
//    }




    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
