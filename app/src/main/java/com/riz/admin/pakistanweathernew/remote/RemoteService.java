package com.riz.admin.pakistanweathernew.remote;



import com.riz.admin.pakistanweathernew.model.Currently;
import com.riz.admin.pakistanweathernew.model.Daily;
import com.riz.admin.pakistanweathernew.model.Datum;
import com.riz.admin.pakistanweathernew.model.Hourly;
import com.riz.admin.pakistanweathernew.model.WeatherData;


import java.util.List;

import io.reactivex.Observable;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by  Admin on 12/16/2017.
 */

public interface RemoteService {
//    @GET("json?")
//    Observable<UserLocList> getUserLoc(@Query("latlng") String latlng, @Query("key") String apiKey);

    @GET("{apiKey}/{latitude},{longitude}")
    Observable<List<Currently>> getCurrentWeather(@Path(value = "apiKey", encoded = true) String apiKey,
                                                    @Path(value = "latitude", encoded = true) double latitude,
                                                    @Path(value = "longitude", encoded = true) double longitude,
                                                    @Query(value = "units") String tempType);
//    @GET("{apiKey}/{latitude},{longitude}")
//    Call<Datum> getforCast(@Path(value = "apiKey", encoded = true) String apiKey,
//                           @Path(value = "latitude", encoded = true) double latitude,
//                           @Path(value = "longitude", encoded = true) double longitude,
//                           @Query(value = "units") String tempType);
//    @GET("{apiKey}/hourly/q/{state}/{city}.json")
//    Observable<HourlyForecastData> getHourlyForecast(@Path(value = "apiKey", encoded = true) String apiKey,
//                                                     @Path(value = "state", encoded = true) String state,
//                                                     @Path(value = "city", encoded = true) String city);
//
//    @GET("{apiKey}/forecast10day/q/{state}/{city}.json")
//    Observable<TenDaysForecastData> getTenDaysForeCast(@Path(value = "apiKey", encoded = true) String apiKey,
//                                                      @Path(value = "state", encoded = true) String state,
//                                                      @Path(value = "city", encoded = true) String city);
}
