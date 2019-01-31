package ru.cft.android.budgetplanner.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ru.cft.android.budgetplanner.R;
import ru.cft.android.budgetplanner.api.RetrofitApi;
import ru.cft.android.budgetplanner.api.callbacks.DefaultCallback;
import ru.cft.android.budgetplanner.models.Category;
import ru.cft.android.budgetplanner.models.CategoryName;
import ru.cft.android.budgetplanner.models.Spending;
import ru.cft.android.budgetplanner.utils.DateUtils;
import ru.cft.android.budgetplanner.view.adapters.SpendingListAdapter;

public class CategoryActivity extends Activity {

    private static final EnumMap<CategoryName, Integer> CATEGORIES_ID = new EnumMap<>(CategoryName.class);

    private String currentCategory;
    private int currentCategoryId;
    private int monthId;

    private TextView textViewCurrentCategory;
    private TextView textViewMonth;
    private TextView textViewBalanceInCurrentCategory2;
    private Button buttonNewSpend;
    private Button buttonEditBalanceInCurrentCategory;
    private EditText editTextEditBalanceInCurrentCategory;
    private ListView listViewHistory;

    static {
        CATEGORIES_ID.put(CategoryName.FOOD, 0);
        CATEGORIES_ID.put(CategoryName.RESTAURANT, 1);
        CATEGORIES_ID.put(CategoryName.TRANSPORT, 2);
        CATEGORIES_ID.put(CategoryName.COMMUNICATION, 3);
        CATEGORIES_ID.put(CategoryName.FUN, 4);
        CATEGORIES_ID.put(CategoryName.PURCHASE, 5);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        currentCategory = getIntent().getStringExtra(Intent.EXTRA_TEXT);
        currentCategoryId = getIntent().getIntExtra("id", -1);
        monthId = DateUtils.getCurrentMonth();

        findViews();

        setCurrentMonth(DateUtils.getCurrentMonth());
        setCategoryInformationFromServer(monthId);
        textViewCurrentCategory.setText(currentCategory);
    }

    public void buttonNewSpendClick(View view) {
        Intent intent = new Intent(this, NewSpendActivity.class);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_INDEX, currentCategoryId);
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
        int readBalance = Integer.parseInt(editTextEditBalanceInCurrentCategory.getText().toString());
        CategoryName currentCategoryName = CategoryName.values()[currentCategoryId];
        RetrofitApi.getApi()
                .patchCategoryBalance(monthId, currentCategoryName ,readBalance)
                .enqueue(new DefaultCallback(this, month -> {
                    int balance = month.getBalance();
                    String balanceText = getResources().getString(R.string.balance_string) + " " + balance;
                    editTextEditBalanceInCurrentCategory.setText(balanceText);
                }));
        editTextEditBalanceInCurrentCategory.setText("");
    }

    private void findViews() {
        textViewCurrentCategory = findViewById(R.id.textViewCurrentCategory);
        textViewMonth = findViewById(R.id.textViewMonth);
        textViewBalanceInCurrentCategory2 = findViewById(R.id.textViewBalanceInCurrentCategory2);
        buttonNewSpend = findViewById(R.id.buttonNewSpend);
        buttonEditBalanceInCurrentCategory = findViewById(R.id.buttonEditBalanceInCurrentCategory);
        editTextEditBalanceInCurrentCategory = findViewById(R.id.editTextEditBalanceInCurrentCategory);
        listViewHistory = findViewById(R.id.listViewHistory);
    }

    private void setCurrentMonth(int monthId) {
        String month = DateUtils.getTextMonth(monthId);
        textViewMonth.setText(month);
    }

    private void setHistorySpend(Category category) {
        List<Spending> spendingList = Stream.of(category.getSpendingHistory())
                .collect(Collectors.toList());
        ArrayAdapter<Spending> spendingListAdapter =
                new SpendingListAdapter(getApplicationContext(), spendingList);
        listViewHistory.setAdapter(spendingListAdapter);
    }

    @SuppressWarnings({"squid:S3655", "OptionalGetWithoutIsPresent", "ConstantConditions"})
    private void setCategoryInformationFromServer(int monthId) {
        RetrofitApi.getApi()
                .getData(monthId)
                .enqueue(new DefaultCallback(this, month -> {
                    Category category = Stream.of(month.getCategories())
                            .filter(s -> CATEGORIES_ID.get(s.getName()) == currentCategoryId)
                            .findAny()
                            .get();
                    setCurrentMonth(monthId);
                    setCategoryName(category);
                    setBalance(category);
                    setHistorySpend(category);
                }));
    }

    private void setCategoryName(Category category) {
        int balance = category.getBalance();
        String balanceText = " " + balance;
        textViewBalanceInCurrentCategory2.setText(balanceText);
    }

    private void setBalance(Category category) {
        int balance = category.getBalance();
        String balanceText = " " + balance;
        textViewBalanceInCurrentCategory2.setText(balanceText);
    }

    private void setVisibilityEditBalanceElements(int visibility) {
        buttonEditBalanceInCurrentCategory.setVisibility(visibility);
        editTextEditBalanceInCurrentCategory.setVisibility(visibility);
    }

    public void buttonArrowLeft(View view) {
        monthId = (monthId == 1) ? 12 : --monthId;
        setCategoryInformationFromServer(monthId);
    }

    public void buttonArrowRight(View view) {
        monthId = (monthId == 12) ? 1 : ++monthId;
        setCategoryInformationFromServer(monthId);
    }
}
