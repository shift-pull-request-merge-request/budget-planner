package ru.cft.android.budgetplanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.EnumMap;
import java.util.stream.Stream;

import ru.cft.android.budgetplanner.models.Category;
import ru.cft.android.budgetplanner.models.CategoryName;
import ru.cft.android.budgetplanner.models.Month;
import ru.cft.android.budgetplanner.utils.DateUtils;

public class CategoryActivity extends Activity {

    private static final EnumMap<CategoryName, Integer> CATEGORIES_ID = new EnumMap<>(CategoryName.class);

    private String currentCategory;
    private int currentCategoryId;

    private TextView textViewCurrentCategory;
    private TextView textViewMonth;
    private TextView textViewBalanceInCurrentCategory;
    private Button buttonNewSpend;
    private Button buttonEditBalanceInCurrentCategory;
    private EditText editTextEditBalanceInCurrentCategory;

    private Gson gson;

    static {
        CATEGORIES_ID.put(CategoryName.FOOD, 0);
        CATEGORIES_ID.put(CategoryName.RESTAURANT, 1);
        CATEGORIES_ID.put(CategoryName.TRANSPORT, 2);
        CATEGORIES_ID.put(CategoryName.COMMUNICATION, 3);
        CATEGORIES_ID.put(CategoryName.FUN, 4);
        CATEGORIES_ID.put(CategoryName.PURCHASE, 5);
    }

    CategoryActivity() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        currentCategory = getIntent().getStringExtra(Intent.EXTRA_TEXT);
        currentCategoryId = getIntent().getIntExtra("id", -1);

        findViews();

        setCurrentMonth();
        setCurrentBalance();
        textViewCurrentCategory.setText(currentCategory);

        fillInformationFromJson();
    }

    public void buttonNewSpendClick(View view) {
        Intent intent = new Intent(this, NewSpendActivity.class);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, currentCategory);
        startActivity(intent);
    }

    public void buttonBalanceMinusClick(View view) {
        setVisibilityEditBalanceElements(View.VISIBLE);
    }

    public void buttonBalancePlusClick(View view) {
        setVisibilityEditBalanceElements(View.VISIBLE);
    }

    public void buttonEditBalanceInCurrentCategoryClick(View view) {
        setVisibilityEditBalanceElements(View.GONE);
    }

    private void findViews() {
        textViewCurrentCategory = findViewById(R.id.textViewCurrentCategory);
        textViewMonth = findViewById(R.id.textViewMonth);
        textViewBalanceInCurrentCategory = findViewById(R.id.textViewBalanceInCurrentCategory);
        buttonNewSpend = findViewById(R.id.buttonNewSpend);
        buttonEditBalanceInCurrentCategory = findViewById(R.id.buttonEditBalanceInCurrentCategory);
        editTextEditBalanceInCurrentCategory = findViewById(R.id.editTextEditBalanceInCurrentCategory);
    }

    private void setCurrentMonth() {
        String month = DateUtils.getCurrentTextMonth();
        textViewMonth.setText(month);
    }

    @SuppressWarnings({"squid:S3655", "OptionalGetWithoutIsPresent", "ConstantConditions"})
    private void setCurrentBalance() {
        Month month = gson.fromJson(MainActivity.JSON, Month.class);
        Category category = Stream.of(month.getCategory())
                .filter(s -> CATEGORIES_ID.get(s.getName()) == currentCategoryId)
                .findAny()
                .get();
        int balance = category.getBalance();
        String balanceText = getResources().getString(R.string.balance_string) + " " + balance;
        this.textViewBalanceInCurrentCategory.setText(balanceText);
    }

    private void setVisibilityEditBalanceElements(int visibility) {
        buttonEditBalanceInCurrentCategory.setVisibility(visibility);
        editTextEditBalanceInCurrentCategory.setVisibility(visibility);
    }

    private void fillInformationFromJson() {


    }


}
