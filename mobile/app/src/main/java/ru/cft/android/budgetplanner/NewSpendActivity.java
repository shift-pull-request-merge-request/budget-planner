package ru.cft.android.budgetplanner;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import ru.cft.android.budgetplanner.models.Spending;
import ru.cft.android.budgetplanner.utils.DateUtils;

public class NewSpendActivity extends Activity {

    private EditText editTextSpendName;
    private EditText editTextSpendDescription;
    private EditText editTextSpendSum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_spend);

        findViews();
    }

    public void buttonAddSpendClick(View view) {
        String name = editTextSpendName.getText().toString();
        String description = editTextSpendDescription.getText().toString();
        int sum = Integer.parseInt(editTextSpendName.getText().toString());
        int day = DateUtils.getCurrentDay();
        Spending newSpend = new Spending(0, sum, name, description, day);
    }
    private void findViews() {
        editTextSpendName = findViewById(R.id.editTextSpendName);
        editTextSpendDescription = findViewById(R.id.editTextSpendDescription);
        editTextSpendSum = findViewById(R.id.editTextSpendSum);
    }
}
