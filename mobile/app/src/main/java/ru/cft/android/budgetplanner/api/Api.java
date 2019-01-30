package ru.cft.android.budgetplanner.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.cft.android.budgetplanner.models.Month;

public interface Api {

    @GET("/api/months/{id}")
    Call<Month> getData(@Path("id") int id);
}
