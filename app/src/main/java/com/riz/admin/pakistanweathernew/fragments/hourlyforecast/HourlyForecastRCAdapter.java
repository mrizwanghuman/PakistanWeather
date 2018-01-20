package com.riz.admin.pakistanweathernew.fragments.hourlyforecast;

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
import android.zetterstrom.com.forecast.models.Icon;

import com.bumptech.glide.Glide;
import com.riz.admin.pakistanweathernew.R;
import com.riz.admin.pakistanweathernew.model.Hourly;
import com.riz.admin.pakistanweathernew.util.IconSwitch;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by  Admin on 12/26/2017.
 */

public class HourlyForecastRCAdapter extends RecyclerView.Adapter<HourlyForecastRCAdapter.ViewHolder> {
    List<DataPoint> hourlyForecasts= new ArrayList<>();
    HourlyForecastFrag hourlyForecastFrag;
    Context context;
    private String feelsLikeTemp;

    public HourlyForecastRCAdapter(List<DataPoint> hourlyForecasts) {
        this.hourlyForecasts = hourlyForecasts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hourly_forecast_rc, parent, false);

        this.context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DataPoint getHourlyForecastString = hourlyForecasts.get(position);

int temperature = (int)Math.round(getHourlyForecastString.getTemperature());
            feelsLikeTemp =temperature+"\u2103";
            Date date= getHourlyForecastString.getTime();
        Log.d("Time", "onBindViewHolder: "+date.getTime());

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date.getTime());

        int mYear = calendar.get(Calendar.YEAR);
        int mMonth = calendar.get(Calendar.MONTH);
        int mDay = calendar.get(Calendar.DAY_OF_WEEK);
        SimpleDateFormat getWeekDayString = new SimpleDateFormat("E"); // the day of the week abbreviated

        Log.d("DAYt", "onBindViewHolder: "+mDay);
        Log.d("math", "onBindViewHolder: "+date);
            String iconHourlySrc = String.valueOf(getHourlyForecastString.getIcon());
            Log.d("DATAPOINT", "onBindViewHolder: "+iconHourlySrc.toLowerCase());
            holder.ivHourlyIcon.setImageResource(IconSwitch.iconSwitchUnderScore(iconHourlySrc.toLowerCase()));
            holder.tvHourlyTemp.setText(feelsLikeTemp);
            Log.d("DataPoint", "onBindViewHolder: "+getHourlyForecastString.getApparentTemperature());
            holder.tvHourlyCond.setText(getHourlyForecastString.getSummary());

SimpleDateFormat getTime= new SimpleDateFormat("h:mm a");


       holder.tvHourlyHour.setText(getTime.format(date));
       int intPop= (int)Math.round(getHourlyForecastString.getPrecipProbability());
       String stringPop= intPop+"%";
       holder.tvHourlyPer.setText(stringPop);
        holder.tvhourlyDay.setText(getWeekDayString.format(date));

    }

    @Override
    public int getItemCount() {
        return hourlyForecasts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvHourlyTemp)
        TextView tvHourlyTemp;
        @BindView(R.id.tvHourlyCond)
        TextView tvHourlyCond;
        @BindView(R.id.ivHourlyIcon)
        ImageView ivHourlyIcon;
        @BindView(R.id.tvHourlyPer)
        TextView tvHourlyPer;
        @BindView(R.id.tvHourlyHour)
        TextView tvHourlyHour;
        @BindView(R.id.tvhourlyDay)
        TextView tvhourlyDay;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
