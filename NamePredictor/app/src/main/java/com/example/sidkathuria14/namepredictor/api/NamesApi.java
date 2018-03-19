package com.example.sidkathuria14.namepredictor.api;

import android.location.GpsStatus;


import com.example.sidkathuria14.namepredictor.model.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by sidkathuria14 on 8/3/18.
 */

public interface NamesApi {
    @GET("names/{name}")
    Call<Result> getPrediction(@Path("name")String name);
}
