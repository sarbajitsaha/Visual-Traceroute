package com.saha.arun.visualtraceroute.Helpers;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by arunkr on 25/5/17.
 */

public interface IpAPI
{
    @GET("/json/{ip}")
    Call<IPLocation> getLocation(@Path("ip") String ip);
}
