package com.example.admin.pakistanweather.fragments.tendaysforecast;


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

import com.example.admin.pakistanweather.R;
import com.example.admin.pakistanweather.model.currentlocationdata.CityStateData;
import com.example.admin.pakistanweather.model.tendaysforecast.Forecast;
import com.example.admin.pakistanweather.model.tendaysforecast.Forecastday;
import com.example.admin.pakistanweather.model.tendaysforecast.Forecastday_;
import com.example.admin.pakistanweather.model.tendaysforecast.Simpleforecast;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TenDaysFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TenDaysFrag extends Fragment implements TenDaysForecastContractor.View {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private static String latLog;
    private TenDaysForecastPresenter tenDaysForecastPresenter;
    private RecyclerView recyclerView;
    private TenDaysForecastRCAdapter tenDaysForecastRCAdapter;


    public TenDaysFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment TenDaysFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static TenDaysFrag newInstance(String param1) {
        TenDaysFrag fragment = new TenDaysFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        latLog = param1;
        fragment.setArguments(args);
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
        View view = inflater.inflate(R.layout.fragment_ten_days, container, false);
        Log.d("ten", "onCreateView: "+latLog);
        tenDaysForecastPresenter = new TenDaysForecastPresenter();
        tenDaysForecastPresenter.onAttachView(this);
        tenDaysForecastPresenter.getCurrentLocation(latLog);
        recyclerView = view.findViewById(R.id.rcTenDaysForecast);
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
       // recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        return  view;
    }

    @Override
    public void showError(String e) {
        Toast.makeText(getContext(), e, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgress(String progress) {

    }

    @Override
    public void setTenDaysForeCast(List<Forecastday_> simpleForecasts, List<Forecastday> forecastDays) {
        String TAG ="Great";
        for (int i = 0; i < simpleForecasts.size(); i++) {
            Log.d(TAG, "setTenDaysForeCast: "+forecastDays.get(i).getTitle());
        }
        Log.d(TAG, "setTenDaysForeCast: "+simpleForecasts.size()+" "+forecastDays.size());
        tenDaysForecastRCAdapter = new TenDaysForecastRCAdapter(forecastDays, simpleForecasts);
       recyclerView.setAdapter(tenDaysForecastRCAdapter);
    }


    @Override
    public void setCurrentLocation(List<CityStateData> cityStateData) {
        tenDaysForecastPresenter.getTenDaysForeCast(cityStateData);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        tenDaysForecastPresenter.onDetachView();
    }
}
