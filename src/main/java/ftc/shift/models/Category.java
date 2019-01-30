package ftc.shift.models;

import java.io.Serializable;
import java.util.List;

public class Category implements Serializable{
    private CategoryName name;
    private int balance;
    private Spending spendingHistory[];

    public CategoryName getName() {
        return name;
    }

    public void setName(CategoryName name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Spending[] getSpendingHistory() {
        return spendingHistory;
    }

    public void setSpendingHistory(Spending[] spendingHistory) {
        this.spendingHistory = spendingHistory;
    }

    public Category() {
    }

    public Category(CategoryName name, int balance, Spending[] spendingHistory){
        this.name = name;
        this.balance = balance;
        this.spendingHistory = spendingHistory;
    }
}