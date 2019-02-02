package ru.cft.android.budgetplanner.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ru.cft.android.budgetplanner.R;
import ru.cft.android.budgetplanner.api.RetrofitApi;
import ru.cft.android.budgetplanner.api.callbacks.DefaultCallback;
import ru.cft.android.budgetplanner.models.CategoryName;
import ru.cft.android.budgetplanner.models.Spending;
import ru.cft.android.budgetplanner.utils.DateUtils;
import ru.cft.android.budgetplanner.view.utils.ViewUtils;

public class NewSpendActivity extends Activity {

    private int currentCategoryId;
    private int balance;

    private EditText editTextSpendName;
    private EditText editTextSpendDescription;
    private EditText editTextSpendSum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_spend);
        currentCategoryId = getIntent().getIntExtra(Intent.EXTRA_INDEX, 0);
        balance = getIntent().getIntExtra("balance", 0);
        findViews();
    }

    public void buttonAddSpendClick(View view) {
        ViewUtils.hideKeyboard(this);
        if (editTextSpendName.getText().toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.toast_name_is_empty), Toast.LENGTH_SHORT).show();
            return;
        }
        if (editTextSpendSum.getText().toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.toast_cost_is_empty), Toast.LENGTH_SHORT).show();
            return;
        }
        String name = editTextSpendName.getText().toString();
        String description = editTextSpendDescription.getText().toString();
        int sum = Integer.parseInt(editTextSpendSum.getText().toString());
        if (sum > balance) {
            displayNotCorrectSum();
            return;
        }
        int day = DateUtils.getCurrentDay();
        Spending newSpend = new Spending(-1, sum, name, description, day);
        CategoryName currentCategoryName = CategoryName.values()[currentCategoryId];
        RetrofitApi.getApi()
                .patchSpending(DateUtils.getCurrentMonth(), currentCategoryName, newSpend)
                .enqueue(new DefaultCallback(this, month -> onBackPressed()));
    }

    private void displayNotCorrectSum() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.alert_not_correct_spend_sum_title))
                .setMessage(getString(R.string.alert_not_correct_spend_sum_body))
                .setCancelable(false)
                .setNegativeButton(getString(R.string.alert_not_correct_spend_sum_button), null);
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void findViews() {
        editTextSpendName = findViewById(R.id.editTextSpendName);
        editTextSpendDescription = findViewById(R.id.editTextSpendDescription);
        editTextSpendSum = findViewById(R.id.editTextSpendSum);
    }
}
