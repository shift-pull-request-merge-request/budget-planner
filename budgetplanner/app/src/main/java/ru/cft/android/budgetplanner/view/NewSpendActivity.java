package ru.cft.android.budgetplanner.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import ru.cft.android.budgetplanner.R;
import ru.cft.android.budgetplanner.api.RetrofitApi;
import ru.cft.android.budgetplanner.api.callbacks.DefaultCallback;
import ru.cft.android.budgetplanner.models.CategoryName;
import ru.cft.android.budgetplanner.models.Spending;
import ru.cft.android.budgetplanner.utils.DateUtils;

public class NewSpendActivity extends Activity {

    private EditText editTextSpendName;
    private EditText editTextSpendDescription;
    private EditText editTextSpendSum;

    private int currentCategoryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_spend);
        currentCategoryId = getIntent().getIntExtra(Intent.EXTRA_INDEX, -1);

        findViews();
    }

    public void buttonAddSpendClick(View view) {
        String name = editTextSpendName.getText().toString();
        String description = editTextSpendDescription.getText().toString();
        int sum = Integer.parseInt(editTextSpendName.getText().toString());
        int day = DateUtils.getCurrentDay();
        Spending newSpend = new Spending(-1, sum, name, description, day);

        CategoryName currentCategoryName = CategoryName.values()[currentCategoryId];
        RetrofitApi.getApi()
                .patchSpending(DateUtils.getCurrentMonth(), currentCategoryName ,newSpend)
                .enqueue(new DefaultCallback(this, month -> {
                    onBackPressed();
                }));

        //editTextSpendName.setText("");
        //editTextSpendDescription.setText("");
       /* editTextSpendName.setText("");
        editTextSpendDescription.setText("");
        editTextSpendSum.setText("");
        */
    }
    private void findViews() {
        editTextSpendName = findViewById(R.id.editTextSpendName);
        editTextSpendDescription = findViewById(R.id.editTextSpendDescription);
        editTextSpendSum = findViewById(R.id.editTextSpendSum);
    }
}
