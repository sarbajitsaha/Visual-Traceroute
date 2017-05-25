package com.saha.arun.visualtraceroute;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by arunkr on 25/5/17.
 */

public interface IpAPI
{
    @GET("{ip}")
    Call<IPLocation> getLocation(@Path("ip") String ip);
}
