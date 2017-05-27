package com.saha.arun.visualtraceroute;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreenActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        IP2Location.getInstance().getLocation("208.80.152.201").enqueue(new Callback<IPLocation>()
        {
            @Override
            public void onResponse(Call<IPLocation> call, Response<IPLocation> response)
            {
                if(response.isSuccessful())
                {
                    IPLocation location= response.body();
                    if(location.getStatus().equals("success"))
                    {
                        double latitude = location.getLat();
                        double longitude = location.getLon();
                        Toast.makeText(SplashScreenActivity.this, location.getAs(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<IPLocation> call, Throwable t)
            {
                Toast.makeText(SplashScreenActivity.this,"fail",Toast.LENGTH_SHORT).show();
                Log.e("SPLASH","FAIL");
            }
        });

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
