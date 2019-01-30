package ru.cft.android.budgetplanner.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApi {

    private RetrofitApi() {

    }

    private static Retrofit retrofit;

    private static Api api;

    static {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.9.53.92:8081/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(Api.class);
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }

    public static Api getApi() {
        return api;
    }
}
