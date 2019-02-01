package ru.cft.android.budgetplanner.api.callbacks;

import android.app.Activity;
import android.support.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.cft.android.budgetplanner.api.QueryException;
import ru.cft.android.budgetplanner.models.Month;
import ru.cft.android.budgetplanner.view.utils.ViewUtils;

public class DefaultCallback implements Callback<Month> {

    private Activity activity;
    private CallbackResponseListener callbackResponseListener;

    public DefaultCallback(Activity activity, CallbackResponseListener callbackResponseListener) {
        this.activity = activity;
        this.callbackResponseListener = callbackResponseListener;
    }

    @Override
    public void onResponse(@NonNull Call<Month> call, @NonNull Response<Month> response) {
        Month month = response.body();
        if (month == null) {
            ViewUtils.showDefaultErrorDialog(activity, new QueryException("Response is null"));
        } else {
            callbackResponseListener.run(month);
        }
    }

    @Override
    public void onFailure(@NonNull Call<Month> call, @NonNull Throwable t) {
        ViewUtils.showDefaultErrorDialog(activity, t);
    }
}
