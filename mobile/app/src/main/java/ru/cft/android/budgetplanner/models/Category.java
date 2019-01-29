package ru.cft.android.budgetplanner.models;

import java.io.Serializable;

public class Category implements Serializable {
    public String categoryName;
    public Integer categoryValue;
    public Spending spending[];

    public Category(String categoryName, Integer categoryValue, Spending[] spending) {
        this.categoryName = categoryName;
        this.categoryValue = categoryValue;
        this.spending = spending;
    }
}