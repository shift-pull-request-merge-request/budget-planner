package ru.cft.android.budgetplanner.view;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;

import ru.cft.android.budgetplanner.R;
import ru.cft.android.budgetplanner.api.RetrofitApi;
import ru.cft.android.budgetplanner.api.callbacks.DefaultCallback;
import ru.cft.android.budgetplanner.utils.DateUtils;

public class MainActivity extends ListActivity {

    private TextView textViewBalance;
    private EditText editTextEditBalance;
    private Button buttonSetBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        String[] listStandardCategory = getResources().getStringArray(R.array.standard_category_array);

        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, Arrays.asList(listStandardCategory));
        setListAdapter(listAdapter);

        setBalance();
    }

    @Override
    protected void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);
        Intent intent = new Intent(this, CategoryActivity.class);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, String.valueOf(listView.getItemAtPosition(position)));
        intent.putExtra("id", position);
        startActivity(intent);
    }

    private void findViews() {
        LayoutInflater currentInflater = getLayoutInflater();
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View currentView = currentInflater.inflate(android.R.layout.simple_list_item_1, viewGroup);
        textViewBalance = currentView.findViewById(android.R.id.text1);
        editTextEditBalance = findViewById(R.id.editTextEditBalance);
        buttonSetBalance = findViewById(R.id.buttonSetBalance);
    }

    private void setBalance() {
        RetrofitApi.getApi()
                .getData(DateUtils.getCurrentMonth())
                .enqueue(new DefaultCallback(this, month -> {
                    int balance = month.getBalance();
                    String balanceText = getResources().getString(R.string.balance_string) + " " + balance;
                    textViewBalance.setText(balanceText);
                }));
    }

    private void setVisibilityEditBalanceElements(int visibility) {
        editTextEditBalance.setVisibility(visibility);
        buttonSetBalance.setVisibility(visibility);
    }

    public void buttonEditBalanceClick(View view) {
        setVisibilityEditBalanceElements(View.VISIBLE);
    }

    public void buttonSetBalanceClick(View view) {
        setVisibilityEditBalanceElements(View.GONE);
        int readBalance = Integer.parseInt(editTextEditBalance.getText().toString());
        RetrofitApi.getApi()
                .patchBalance(DateUtils.getCurrentMonth(), readBalance)
                .enqueue(new DefaultCallback(this, month -> {
                    int balance = month.getBalance(); // todo
                    String balanceText = getResources().getString(R.string.balance_string) + " " + balance;
                    textViewBalance.setText(balanceText);
                }));
        editTextEditBalance.setText("");
    }
}
