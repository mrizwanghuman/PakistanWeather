package com.example.admin.pakistanweather.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.pakistanweather.R;
import com.example.admin.pakistanweather.model.currentweatherdata.CurrentWeather;
import com.example.admin.pakistanweather.remote.RetrofitHelper;
import com.example.admin.pakistanweather.model.currentlocationdata.UserLocList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TodayForeCastFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TodayForeCastFragment extends Fragment {
    public static final String TAG ="TodayForeCast";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
static String  userCurrentLocLatLog;
    private TextView tvDisplayCity;
    private String stateShortName;
    private String cityName;


    public TodayForeCastFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.

     * @return A new instance of fragment TodayForeCastFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TodayForeCastFragment newInstance(String param1) {
        TodayForeCastFragment fragment = new TodayForeCastFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        userCurrentLocLatLog = param1;
        Log.d("Parm", "newInstance: "+param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            Log.d("PAram", "onCreate: "+mParam1);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_today_forecast, container, false);

        tvDisplayCity = view.findViewById(R.id.tvDisplayCity);


        getCurrentCity(userCurrentLocLatLog);
        getCurrentForecast();
        return view;
    }
    public void getCurrentCity(String lotLag){


        RetrofitHelper.getCurrentCity(lotLag, "AIzaSyD9qEwgs7H4WyV9UWYCv177zUAjYQRghg4").observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<UserLocList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserLocList userLocList) {

                        cityName = userLocList.getResults().get(0).getAddressComponents().get(2).getLongName()+",";
                            tvDisplayCity.setText(cityName);
                        stateShortName = userLocList.getResults().get(0).getAddressComponents().get(4).getShortName();

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void getCurrentForecast(){
        RetrofitHelper.getCurrentForecast("658671bf6a896877", stateShortName, cityName).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<CurrentWeather>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CurrentWeather currentWeather) {
                        Log.d(TAG, "onNext: "+currentWeather.getCurrentObservation().getFeelslikeC());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
