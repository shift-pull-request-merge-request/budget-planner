package ru.cft.android.budgetplanner;

import android.app.Application;

import retrofit2.Retrofit;
import ru.cft.android.budgetplanner.api.Api;
import ru.cft.android.budgetplanner.api.RetrofitApi;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Retrofit retrofit = RetrofitApi.getRetrofit();
        Api api = RetrofitApi.getApi();
    }
}
