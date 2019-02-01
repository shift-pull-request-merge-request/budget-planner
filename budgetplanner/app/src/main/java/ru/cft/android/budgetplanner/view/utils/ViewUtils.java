package ru.cft.android.budgetplanner.view.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

import java.util.Objects;

import ru.cft.android.budgetplanner.R;

public class ViewUtils {

    private ViewUtils() {

    }

    public static void showDefaultErrorDialog(Context context, Throwable t) {
        Activity activity = (Activity) context;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getString(R.string.error_title_string))
                .setMessage(context.getString(R.string.error_body_string) + " " + t.getMessage())
                .setCancelable(false)
                .setNegativeButton(context.getString(R.string.error_button_string), (dialog, id) -> activity.finish());
        AlertDialog alert = builder.create();
        alert.show();
    }

    public static void hideKeyboard(Context context) {
        Activity activity = (Activity) context;
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        Objects.requireNonNull(inputMethodManager).hideSoftInputFromWindow(
                Objects.requireNonNull(activity.getCurrentFocus()).getWindowToken(), 0);
    }
}
