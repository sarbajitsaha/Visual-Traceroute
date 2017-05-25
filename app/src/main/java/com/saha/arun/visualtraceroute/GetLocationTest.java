package com.saha.arun.visualtraceroute;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by arunkr on 25/5/17.
 */

public class GetLocationTest
{
    public GetLocationTest()
    {
        IP2Location.getLocation("208.80.152.201").enqueue(new Callback<IPLocation>()
        {
            @Override
            public void onResponse(Call<IPLocation> call, Response<IPLocation> response)
            {
                Log.e("asd","asd");
                IPLocation location = response.body();
                if(location.getStatus() == "success")
                {
                    Log.e("IPLOCATION", "Lat: "+location.getLat());
                    Log.e("IPLOCATION", "Lon: "+location.getLon());
                }
            }

            @Override
            public void onFailure(Call<IPLocation> call, Throwable t)
            {
                Log.e("error","asd");

            }
        });
    }
}
