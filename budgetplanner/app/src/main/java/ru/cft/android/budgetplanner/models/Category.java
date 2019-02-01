package ru.cft.android.budgetplanner.models;

public class Category {

    private CategoryName name;
    private int balance;
    private Spending[] spendingHistory;

    public Category(CategoryName name, int balance, Spending[] spendingHistory) {
        this.name = name;
        this.balance = balance;
        this.spendingHistory = spendingHistory;
    }

    public CategoryName getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public Spending[] getSpendingHistory() {
        return spendingHistory;
    }
}
