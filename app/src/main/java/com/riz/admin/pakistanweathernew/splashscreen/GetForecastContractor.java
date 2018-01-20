package com.riz.admin.pakistanweathernew.splashscreen;

import android.zetterstrom.com.forecast.models.DataBlock;
import android.zetterstrom.com.forecast.models.DataPoint;

import com.riz.admin.pakistanweathernew.model.Currently;
import com.riz.admin.pakistanweathernew.model.Daily;
import com.riz.admin.pakistanweathernew.model.Hourly;
import com.riz.admin.pakistanweathernew.util.BasePresenter;
import com.riz.admin.pakistanweathernew.util.BaseView;

import java.util.List;

/**
 * Created by  Admin on 1/12/2018.
 */

public interface GetForecastContractor {
interface View extends BaseView{
    void showProgress(String progress);
    void setForeCast(List<DataPoint> currentlyList, List<DataBlock> hourlyList, List<DataBlock> dailyList);
}
interface Presenter extends BasePresenter<View>{
    void getForecast(double latitude, double longitude);

}
}
