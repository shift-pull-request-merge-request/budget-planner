package ru.cft.android.budgetplanner.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApi {

    private static final String SERVER_IP = "http://192.168.0.102:8081";

    private static Retrofit retrofit;
    private static Api api;

    static {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_IP)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(Api.class);
    }

    private RetrofitApi() {

    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }

    public static Api getApi() {
        return api;
    }
}
