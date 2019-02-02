package ru.cft.android.budgetplanner.models;

public class Spending {

    private int spendingId;
    private int cost;
    private String name;
    private String description;
    private int day;

    public Spending(int spendingId, int cost, String name, String description, int day) {
        this.spendingId = spendingId;
        this.cost = cost;
        this.name = name;
        this.description = description;
        this.day = day;
    }

    @SuppressWarnings("unused")
    public int getSpendingId() {
        return spendingId;
    }

    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getDay() {
        return day;
    }
}
