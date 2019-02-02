package ru.cft.android.budgetplanner.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;
import ru.cft.android.budgetplanner.models.CategoryName;
import ru.cft.android.budgetplanner.models.Month;
import ru.cft.android.budgetplanner.models.Spending;

public interface Api {

    @GET("/api/months/{id}")
    Call<Month> getData(@Path("id") int id);

    @PATCH("/api/months/{monthId}/{balance}")
    Call<Month> patchBalance(@Path("monthId") int monthId, @Path("balance") int balance);

    @PATCH("/api/months/{monthId}/category/{categoryName}/{categoryBalance}")
    Call<Month> patchCategoryBalance(@Path("monthId") int monthId,
                                     @Path("categoryName") CategoryName categoryName,
                                     @Path("categoryBalance") int categoryBalance);

    @PATCH("/api/months/{monthId}/category/{categoryName}")
    Call<Month> patchSpending(@Path("monthId") int monthId,
                              @Path("categoryName") CategoryName categoryName,
                              @Body Spending spending);
}
