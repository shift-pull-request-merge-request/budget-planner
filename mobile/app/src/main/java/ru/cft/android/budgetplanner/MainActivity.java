package ru.cft.android.budgetplanner;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Arrays;

import retrofit2.Response;
import ru.cft.android.budgetplanner.api.RetrofitApi;
import ru.cft.android.budgetplanner.models.Month;

public class MainActivity extends ListActivity {

    private Gson gson;

    private int balance;

    private TextView textViewBalance;

    public MainActivity() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

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
    }

    private void setBalance() {
        try {
            Response response = RetrofitApi.getApi().getData(1).execute();
            Log.i("qwerty", "" + response.code());
            Log.i("qwerty", "" + response.body().toString());

        } catch (IOException e) {
            Log.e("exception", e.getMessage());
        }
        balance = gson.fromJson(JSON, Month.class).getBalance();
        String balanceText = getResources().getString(R.string.balance_string) + " " + balance;
        textViewBalance.setText(balanceText);
    }

    public static final String JSON = "{\n" +
            "      \"balance\": 50,\n" +
            "      \"category\": [\n" +
            "        {\n" +
            "          \"balance\": 2000,\n" +
            "          \"name\": \"FOOD\",\n" +
            "          \"spendingHistory\": [\n" +
            "            {\n" +
            "              \"cost\": 1000,\n" +
            "              \"day\": 5,\n" +
            "              \"description\": \"restoran\",\n" +
            "              \"name\": \"food123\",\n" +
            "              \"spendingId\": 1\n" +
            "            },\n" +
            "            {\n" +
            "              \"cost\": 5000,\n" +
            "              \"day\": 10,\n" +
            "              \"description\": \"shop\",\n" +
            "              \"name\": \"tv\",\n" +
            "              \"spendingId\": 2\n" +
            "            }\n" +
            "          ]\n" +
            "        }\n" +
            "      ],\n" +
            "      \"monthId\": 1\n" +
            "    }";

}
