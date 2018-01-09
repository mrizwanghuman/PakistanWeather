package com.example.admin.pakistanweather.remote;



import com.example.admin.pakistanweather.model.currentlocationdata.UserLocList;
import com.example.admin.pakistanweather.model.currentweatherdata.CurrentWeather;
import com.example.admin.pakistanweather.model.hourforecast.HourlyForecastData;
import com.example.admin.pakistanweather.model.tendaysforecast.TenDaysForecastData;


import io.reactivex.Observable;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by  Admin on 12/16/2017.
 */

public interface RemoteService {
    @GET("json?")
    Observable<UserLocList> getUserLoc(@Query("latlng") String latlng, @Query("key") String apiKey);

    @GET("{apiKey}/conditions/q/{state}/{city}.json")
    Observable<CurrentWeather> getCurrentWeather(@Path(value = "apiKey", encoded = true) String apiKey,
                                                 @Path(value = "state", encoded = true) String state,
                                                 @Path(value = "city", encoded = true) String city);

    @GET("{apiKey}/hourly/q/{state}/{city}.json")
    Observable<HourlyForecastData> getHourlyForecast(@Path(value = "apiKey", encoded = true) String apiKey,
                                                     @Path(value = "state", encoded = true) String state,
                                                     @Path(value = "city", encoded = true) String city);

    @GET("{apiKey}/forecast10day/q/{state}/{city}.json")
    Observable<TenDaysForecastData> getTenDaysForeCast(@Path(value = "apiKey", encoded = true) String apiKey,
                                                      @Path(value = "state", encoded = true) String state,
                                                      @Path(value = "city", encoded = true) String city);
}
