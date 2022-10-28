package com.example.fuelpass;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIStationManager {

    // get station
    @GET("Station/{id}")
    Call<ModelStation> getStationData(@Path("id") String id);

    // update station
    @PUT("Station/{id}")
    Call<ModelStation> updateStation(@Path("id") String id, @Body ModelStation modelStation);
}
