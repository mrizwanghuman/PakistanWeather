package com.example.admin.pakistanweather.remote;

import com.example.admin.pakistanweather.model.currentlocationdata.UserLocList;
import com.example.admin.pakistanweather.model.currentweatherdata.CurrentWeather;
import com.example.admin.pakistanweather.model.currentweatherdata.Response;
import com.example.admin.pakistanweather.remote.RemoteService;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by  Admin on 12/15/2017.
 */

public class RetrofitHelper {
    public static final String BASE_URL_Google="https://maps.googleapis.com/maps/api/geocode/";
    public static final String BASE_URL_CUR_WEATHER ="http://api.wunderground.com/api/";

    private static OkHttpClient httpClientConfig(HttpLoggingInterceptor interceptor){
        return new OkHttpClient.Builder().addInterceptor(interceptor).build();



    }
    private static HttpLoggingInterceptor loggingInterceptor(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return  httpLoggingInterceptor;
    }

    public static Retrofit create(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_Google)
                .client(httpClientConfig(loggingInterceptor()))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }

    public static Observable<UserLocList> getCurrentCity(String userLatLog, String apiKey){

        Retrofit retrofit = create();
        RemoteService remoteService = retrofit.create(RemoteService.class);

        return remoteService.getUserLoc(userLatLog, apiKey);
    }

    //Current Forecast

    public static Retrofit createCurrentForecast(){
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(BASE_URL_CUR_WEATHER)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClientConfig(loggingInterceptor()))
                .build();
        return retrofit;


    }
    public  static Observable<CurrentWeather> getCurrentForecast(String apiKey, String state, String city){
        Retrofit retrofit = createCurrentForecast();
        RemoteService remoteService = retrofit.create(RemoteService.class);
        return remoteService.getCurrentWeather(apiKey, state, city);
    }

}
