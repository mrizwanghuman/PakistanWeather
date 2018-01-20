package com.riz.admin.pakistanweathernew.fragments.tendaysforecast;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.zetterstrom.com.forecast.models.DataBlock;
import android.zetterstrom.com.forecast.models.DataPoint;

import com.bumptech.glide.Glide;
import com.riz.admin.pakistanweathernew.R;
import com.riz.admin.pakistanweathernew.model.Daily;
import com.riz.admin.pakistanweathernew.util.IconSwitch;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by  Admin on 12/28/2017.
 */

public class TenDaysForecastRCAdapter extends RecyclerView.Adapter<TenDaysForecastRCAdapter.ViewHolder> {
    List<DataPoint> forecastdayList = new ArrayList<>();
    //List<Forecastday_> forecastday_list = new ArrayList<>();
    Context context;

    public TenDaysForecastRCAdapter(List<DataPoint> forecastdayList) {
        this.forecastdayList = forecastdayList;
     //   this.forecastday_list = forecastday_list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ten_days_forecast_rc, null, false);
        context = parent.getContext();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        DataPoint tenDayForecast= forecastdayList.get(position);
//
//
       String highTemp = "H " +(int) Math.round(tenDayForecast.getTemperatureMax()) + "\u2103";
       holder.tvHighTemp.setText(highTemp);
        String lowTemp = "L " + (int)Math.round(tenDayForecast.getApparentTemperatureMin()) + "\u2103";
        holder.tvLowTemp.setText(lowTemp);
//        String epochTime = forecastday_list.get(position).getDate().getEpoch();
//        long epochTimeLong = Long.parseLong(epochTime);
 Date date = tenDayForecast.getTime();
       SimpleDateFormat dateformate = new SimpleDateFormat("dd/MM/yyyy");
      String weatherDate= dateformate.format(date);
        SimpleDateFormat weekDayString = new SimpleDateFormat("E");
        String forecastDay=weekDayString.format(date);
        holder.tvWeekDay.setText(forecastDay);
//        Log.d("Date", "onBindViewHolder: "+weatherDate);
        holder.tvDate.setText(weatherDate);
       String iconSrc = String.valueOf(tenDayForecast.getIcon());
//        Glide.with(context).load(iconSrc).into(holder.ivConditionIcon);
        holder.ivConditionIcon.setImageResource(IconSwitch.iconSwitchUnderScore(iconSrc.toLowerCase()));
        Log.d("ICONTEN", "onBindViewHolder: "+iconSrc);


    }

    @Override
    public int getItemCount() {
        return forecastdayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvWeekDay)
        TextView tvWeekDay;
        @BindView(R.id.ivConditionIcon)
        ImageView ivConditionIcon;
        @BindView(R.id.tvHighTemp)
        TextView tvHighTemp;
        @BindView(R.id.tvLowTemp)
        TextView tvLowTemp;

        @BindView(R.id.tvDate)
        TextView tvDate;

        public ViewHolder(View itemView) {

            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
