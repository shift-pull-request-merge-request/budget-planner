package ftc.shift.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Category implements Serializable{
    private CategoryName name;
    private int balance;
    private ArrayList<Spending> spendingHistory;

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

    public ArrayList<Spending> getSpendingHistory() {
        return spendingHistory;
    }

    public Category() {
        spendingHistory = new ArrayList<>();
    }

    public Category(CategoryName name, int balance, ArrayList<Spending> spendingHistory){
        this.name = name;
        this.balance = balance;
        this.spendingHistory = spendingHistory;
    }
}