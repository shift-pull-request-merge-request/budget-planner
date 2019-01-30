package ru.cft.android.budgetplanner.api;

import retrofit2.http.GET;

public interface Api {

    @GET("/api/get")
    Call<List<PostModel>> getData(@Query("name") String resourceName, @Query("num") int count);
}
