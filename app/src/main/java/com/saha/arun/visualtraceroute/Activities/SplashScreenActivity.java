package com.saha.arun.visualtraceroute.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.saha.arun.visualtraceroute.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
        startActivity(intent);


        /*IP2Location.getInstance().getLocation("208.80.152.201").enqueue(new Callback<IPLocation>()
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
        });*/

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
