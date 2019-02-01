package ru.cft.android.budgetplanner.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ru.cft.android.budgetplanner.R;
import ru.cft.android.budgetplanner.api.RetrofitApi;
import ru.cft.android.budgetplanner.api.callbacks.DefaultCallback;
import ru.cft.android.budgetplanner.models.Category;
import ru.cft.android.budgetplanner.models.CategoryName;
import ru.cft.android.budgetplanner.models.Month;
import ru.cft.android.budgetplanner.models.Spending;
import ru.cft.android.budgetplanner.utils.DateUtils;
import ru.cft.android.budgetplanner.view.adapters.SpendingListAdapter;
import ru.cft.android.budgetplanner.view.utils.ViewUtils;

public class CategoryActivity extends Activity {

    private static final EnumMap<CategoryName, Integer> CATEGORIES_ID = new EnumMap<>(CategoryName.class);

    private String currentCategory;
    private int currentCategoryId;
    private int monthId;
    private int notDistributedBalance;
    private int balance;

    private TextView textViewCurrentCategory;
    private TextView textViewMonth;
    private TextView textViewBalanceInCurrentCategory2;
    private Button buttonSetBalanceInCurrentCategory;
    private EditText editTextEditBalanceInCurrentCategory;
    private ListView listViewHistory;
    private Button buttonNewSpend;

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
        currentCategoryId = getIntent().getIntExtra(Intent.EXTRA_INDEX, -1);
        findViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
        monthId = DateUtils.getCurrentMonth();
        setCurrentMonth(DateUtils.getCurrentMonth());
        setCategoryInformationFromServer(monthId);
        textViewCurrentCategory.setText(currentCategory);
    }

    public void buttonNewSpendClick(View view) {
        Intent intent = new Intent(this, NewSpendActivity.class);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_INDEX, currentCategoryId);
        intent.putExtra("balance", balance);
        startActivity(intent);
    }

    public void buttonSetBalanceInCurrentCategoryClick(View view) {
        ViewUtils.hideKeyboard(this);
        if (DateUtils.getCurrentMonth() != monthId) {
            return;
        }
        setVisibilityEditBalanceElements(View.GONE);
        if (editTextEditBalanceInCurrentCategory.getText().toString().isEmpty()) {
            return;
        }
        int readBalance = Integer.parseInt(editTextEditBalanceInCurrentCategory.getText().toString());
        editTextEditBalanceInCurrentCategory.setText("");
        if (readBalance > notDistributedBalance + balance) {
            displayNotCorrectNewBalanceAlert();
            return;
        }
        CategoryName currentCategoryName = CategoryName.values()[currentCategoryId];
        RetrofitApi.getApi()
                .patchCategoryBalance(monthId, currentCategoryName, readBalance)
                .enqueue(new DefaultCallback(this,
                        month -> textViewBalanceInCurrentCategory2.setText(String.valueOf(readBalance))));
    }

    private void findViews() {
        textViewCurrentCategory = findViewById(R.id.textViewCurrentCategory);
        textViewMonth = findViewById(R.id.textViewMonth);
        textViewBalanceInCurrentCategory2 = findViewById(R.id.textViewBalanceInCurrentCategory2);
        buttonSetBalanceInCurrentCategory = findViewById(R.id.buttonSetBalanceInCurrentCategory);
        editTextEditBalanceInCurrentCategory = findViewById(R.id.editTextEditBalanceInCurrentCategory);
        listViewHistory = findViewById(R.id.listViewHistory);
        buttonNewSpend = findViewById(R.id.buttonNewSpend);
    }

    private void displayNotCorrectNewBalanceAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.alert_not_correct_new_balance_title))
                .setMessage(getString(R.string.alert_not_correct_new_balance_body))
                .setCancelable(false)
                .setNegativeButton(getString(R.string.alert_not_correct_new_balance_button), null);
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void setCurrentMonth(int monthId) {
        String month = DateUtils.getTextMonth(monthId);
        textViewMonth.setText(month);
        if (monthId != DateUtils.getCurrentMonth()) {
            buttonNewSpend.setVisibility(View.GONE);
        } else {
            buttonNewSpend.setVisibility(View.VISIBLE);
        }
    }

    private void setHistorySpend(Category category) {
        List<Spending> spendingList = Stream.of(category.getSpendingHistory())
                .collect(Collectors.toList());
        Collections.reverse(spendingList);
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
                    setNotDistributedBalance(month);
                    setCurrentMonth(monthId);
                    setBalance(category);
                    setHistorySpend(category);
                }));
    }

    private void setNotDistributedBalance(Month month) {
        notDistributedBalance = month.getBalance();
    }

    private void setBalance(Category category) {
        balance = category.getBalance();
        String balanceText = " " + balance;
        textViewBalanceInCurrentCategory2.setText(balanceText);
    }

    private void setVisibilityEditBalanceElements(int visibility) {
        buttonSetBalanceInCurrentCategory.setVisibility(visibility);
        editTextEditBalanceInCurrentCategory.setVisibility(visibility);
    }

    public void buttonArrowLeft(View view) {
        monthId = (monthId == 1) ? 12 : --monthId;
        setCategoryInformationFromServer(monthId);
        setVisibilityEditBalanceElements(View.GONE);
    }

    public void buttonArrowRight(View view) {
        monthId = (monthId == 12) ? 1 : ++monthId;
        setCategoryInformationFromServer(monthId);
        setVisibilityEditBalanceElements(View.GONE);
    }

    public void textViewBalanceClick(View view) {
        if (monthId != DateUtils.getCurrentMonth()) {
            return;
        }
        setVisibilityEditBalanceElements(View.VISIBLE);
    }
}
