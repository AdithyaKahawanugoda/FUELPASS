package com.example.fuelpass;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIQuotaManager {
    @PUT("Quota/{id}")
    Call<ModelQuota> updateQuota(@Path("id") String id, @Body ModelQuota modelQuota);
}
