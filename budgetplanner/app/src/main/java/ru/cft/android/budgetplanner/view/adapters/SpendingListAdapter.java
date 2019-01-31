package ru.cft.android.budgetplanner.view.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ru.cft.android.budgetplanner.R;
import ru.cft.android.budgetplanner.models.Spending;

public class SpendingListAdapter extends ArrayAdapter<Spending> {

    private Context context;

    public SpendingListAdapter(Context context, List<Spending> spendingList) {
        super(context, android.R.layout.simple_list_item_2, spendingList);
        this.context = context;
    }


    @SuppressWarnings("ConstantConditions")
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        String costText = context.getResources().getString(R.string.spend_list_adapter_history_cost);
        String dateText = context.getResources().getString(R.string.spend_list_adapter_history_date);
        Spending spending = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(android.R.layout.simple_list_item_2, null);
        }
        String topString = spending.getName() + " " + costText + spending.getCost() + " " + dateText + spending.getDay();
        String bottomString = spending.getDescription();
        ((TextView) convertView.findViewById(android.R.id.text1)).setText(topString);
        ((TextView) convertView.findViewById(android.R.id.text2)).setText(bottomString);
        return convertView;
    }
}
