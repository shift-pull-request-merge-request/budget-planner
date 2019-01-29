package ru.cft.android.budgetplanner.models;

import java.io.Serializable;
import java.util.List;

public class Spending implements Serializable {
    public String spendingId;
    public Integer cost;
    public String spendingName;
    public String desc;
    public String day;

    public Spending(String spendingId, Integer cost, String spendingName, String desc, String day) {
        this.spendingId = spendingId;
        this.cost = cost;
        this.spendingName = spendingName;
        this.desc = desc;
        this.day = day;
    }
}