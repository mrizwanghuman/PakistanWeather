package com.riz.admin.pakistanweathernew.splashscreen;

import android.util.Log;
import android.zetterstrom.com.forecast.ForecastClient;
import android.zetterstrom.com.forecast.models.DataBlock;
import android.zetterstrom.com.forecast.models.DataPoint;
import android.zetterstrom.com.forecast.models.Forecast;

import com.riz.admin.pakistanweathernew.model.WeatherData;
import com.riz.admin.pakistanweathernew.splashscreen.GetForecastContractor;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by  Admin on 1/12/2018.
 */

public class getForeCastPresenter implements GetForecastContractor.Presenter {
    GetForecastContractor.View view;
    List<DataPoint> currentlyList= new ArrayList<>();
    List<DataBlock> hourlyList= new ArrayList<>();
    List<DataBlock> dailyList= new ArrayList<>();
    private List<WeatherData> weatherData= new ArrayList<>();

    @Override
    public void onAttachView(GetForecastContractor.View view) {
        this.view= view;
    }

    @Override
    public void onDetachView() {
this.view= null;
    }

    @Override
    public void getForecast(double latitude, double longitude) {
       // Log.d("GGGG", "getForecast: ");
view.showProgress("Weather will load shortly");
        ForecastClient.getInstance()
                .getForecast(latitude, longitude, new Callback<Forecast>() {
                    @Override
                    public void onResponse(Call<Forecast> call, retrofit2.Response<Forecast> response) {
                        Log.d("Cool", "onResponse: "+response.body());
                        Forecast responseForecast = response.body();
                        currentlyList.add(responseForecast.getCurrently());
                        hourlyList.add(responseForecast.getHourly());
                        dailyList.add(responseForecast.getDaily());
                        view.setForeCast(currentlyList, hourlyList, dailyList);
                    }

                    @Override
                    public void onFailure(Call<Forecast> call, Throwable t) {
view.showError(t.getMessage());
                    }
                });

    }
}
