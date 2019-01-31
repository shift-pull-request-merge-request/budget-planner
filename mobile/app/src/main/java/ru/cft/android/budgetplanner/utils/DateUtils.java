package ru.cft.android.budgetplanner.utils;

import java.text.DateFormatSymbols;
import java.util.Calendar;

public class DateUtils {

    private DateUtils() {

    }

    public static String getTextMonth(int id) {
        Calendar calendar = Calendar.getInstance();
        return new DateFormatSymbols().getMonths()[id - 1];
    }

    public static String getCurrentTextMonth() {
        Calendar calendar = Calendar.getInstance();
        return new DateFormatSymbols().getMonths()[calendar.get(Calendar.MONTH)];
    }

    public static int getCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static int getCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }
}
