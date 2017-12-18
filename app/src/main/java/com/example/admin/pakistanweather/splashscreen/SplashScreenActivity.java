package com.example.admin.pakistanweather.splashscreen;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.admin.pakistanweather.R;
import com.example.admin.pakistanweather.homeActivity.HomeActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        startHomeActivity();
    }


    private void startHomeActivity(){

        Handler splashScreenIntent = new Handler();
        splashScreenIntent.postDelayed(new Runnable(){


            @Override
            public void run() {
                Intent homeActivityIntent = new Intent(SplashScreenActivity.this, HomeActivity.class);
                startActivity(homeActivityIntent);
                finish();
            }
        },2000);
    }

}
