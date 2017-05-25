package com.saha.arun.visualtraceroute;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        GetLocationTest gt = new GetLocationTest();

       /* Intent intent = new Intent(SplashScreenActivity.this,MapsActivity.class);
        double []lat = {-5.0,5.0,10,15,20};
        double []longa = {78,132,89,0.5,-45};
        String []loc = {"a","b","c","d","e"};
        intent.putExtra("latitudes",lat);
        intent.putExtra("longitudes",longa);
        intent.putExtra("locations",loc);
        startActivity(intent);*/
    }
}
