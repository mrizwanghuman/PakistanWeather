package com.riz.admin.pakistanweathernew.remote;

import android.os.Environment;
import android.zetterstrom.com.forecast.Constants;
import android.zetterstrom.com.forecast.ForecastClient;
import android.zetterstrom.com.forecast.ForecastConfiguration;

import com.riz.admin.pakistanweathernew.model.Currently;
import com.riz.admin.pakistanweathernew.model.Daily;
import com.riz.admin.pakistanweathernew.model.Hourly;
import com.riz.admin.pakistanweathernew.model.WeatherData;


import java.io.File;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by  Admin on 12/15/2017.
 */

public class RetrofitHelper {
    public static final String BASE_URL_Google="https://maps.googleapis.com/maps/api/geocode/";
    public static final String BASE_URL_CUR_WEATHER ="https://api.darksky.net/forecast/";




    private static OkHttpClient httpClientConfig(HttpLoggingInterceptor interceptor){
       // File httpCache = new File(Context.getExternalCacheDir().getAbsolutePath() + "/tile_cache");
        File httpCacheDirectory = new File(Environment.getExternalStorageDirectory(), "HttpCache");// Here to facilitate the file directly on the SD Kagan catalog HttpCache in ， Generally put in context.getCacheDir() in

        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(httpCacheDirectory, cacheSize);
        return new OkHttpClient.Builder().cache(cache).addInterceptor(interceptor).build();

    }

    private static HttpLoggingInterceptor loggingInterceptor(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return  httpLoggingInterceptor;
    }

    public static Retrofit createConfig(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_Google)
                .client(httpClientConfig(loggingInterceptor()))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }

//    public static Observable<UserLocList> getCurrentCity(String userLatLog, String apiKey){
//
//        Retrofit retrofit = create();
//        RemoteService remoteService = retrofit.create(RemoteService.class);
//
//        return remoteService.getUserLoc(userLatLog, apiKey);
//    }

    //Current Forecast

    public static Retrofit weatherRetrofit(){
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(BASE_URL_CUR_WEATHER)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClientConfig(loggingInterceptor()))
                .build();
        return retrofit;


    }
    public  static Observable<List<Currently>> getForecast(String apiKey, double latitude, double longitude, String units){
        Retrofit retrofit = weatherRetrofit();
        RemoteService remoteService = retrofit.create(RemoteService.class);
        return remoteService.getCurrentWeather(apiKey, latitude, longitude, units);
    }

//    public static Call<WeatherData> getForec(String apiKey, double latitude, double longitude, String units){
//        Retrofit retrofit = weatherRetrofit();
//        RemoteService remoteService = retrofit.create(RemoteService.class);
//       return remoteService.getforCast(apiKey,latitude, longitude, units);
//    }

}
