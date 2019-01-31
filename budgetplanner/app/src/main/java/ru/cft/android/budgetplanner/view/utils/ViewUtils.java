package ru.cft.android.budgetplanner.view.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;

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
                .setNegativeButton(context.getString(R.string.error_button_string),
                        (dialog, id) -> activity.finish());
        AlertDialog alert = builder.create();
        alert.show();
    }
}
