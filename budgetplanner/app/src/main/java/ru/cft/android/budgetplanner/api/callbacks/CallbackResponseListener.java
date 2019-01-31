package ru.cft.android.budgetplanner.api.callbacks;

import ru.cft.android.budgetplanner.models.Month;

@FunctionalInterface
public interface CallbackResponseListener {

    void run(Month month);
}
