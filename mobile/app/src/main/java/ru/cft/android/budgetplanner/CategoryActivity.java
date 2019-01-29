package ru.cft.android.budgetplanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CategoryActivity extends Activity {

    String currentCategory;

    TextView textViewCurrentCategory;
    Button buttonNewSpend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        currentCategory = getIntent().getStringExtra(Intent.EXTRA_TEXT);

        textViewCurrentCategory = findViewById(R.id.textViewCurrentCategory);
        buttonNewSpend = findViewById(R.id.buttonNewSpend);

        textViewCurrentCategory.setText(currentCategory);
    }

    public void buttonNewSpendClick(View view) {
        Intent intent = new Intent(this, NewSpendActivity.class);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, currentCategory);
        startActivity(intent);
    }
}
