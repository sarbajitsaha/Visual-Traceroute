package com.saha.arun.visualtraceroute;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by arunkr on 25/5/17.
 */

public class IP2Location
{
    private static IpAPI service;

    public static Call<IPLocation> getLocation(String ip)
    {
        if(service==null)
        {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                    .readTimeout(2, TimeUnit.MINUTES)
                    .writeTimeout(2, TimeUnit.MINUTES)
                    .connectTimeout(3, TimeUnit.MINUTES);

            Retrofit retrofit = new Retrofit.Builder()
                    .client(httpClient.build())
                    .baseUrl("http://ip-api.com/json/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            service = retrofit.create(IpAPI.class);
        }
        return service.getLocation(ip);
    }
}
