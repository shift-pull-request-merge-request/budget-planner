package ru.cft.android.budgetplanner.models;

public class Month {

    private int monthId;
    private int balance;
    private Category[] categories;

    public Month(int monthId, int balance, Category[] categories) {
        this.monthId = monthId;
        this.balance = balance;
        this.categories = categories;
    }

    public int getMonthId() {
        return monthId;
    }

    public int getBalance() {
        return balance;
    }

    public Category[] getCategories() {
        return categories;
    }
}