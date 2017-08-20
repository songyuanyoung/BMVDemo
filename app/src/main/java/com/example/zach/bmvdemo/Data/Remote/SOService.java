package com.example.zach.bmvdemo.Data.Remote;

import com.example.zach.bmvdemo.Data.Model.Location;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by zhangwenpurdue on 8/20/2017.
 */

public interface SOService {
    @GET("/api/Locations")
    Call<List<Location>> getLocations();
}
