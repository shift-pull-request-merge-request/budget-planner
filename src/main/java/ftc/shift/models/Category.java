package ftc.shift.models;

import java.io.Serializable;
import java.util.List;

public class Category implements Serializable{
    private CategoryName name;
    private int balance;
    private Spending spendingHistory[];

    public Category() {
    }

    public Category(CategoryName name, int balance, Spending[] spendingHistory){
        this.name = name;
        this.balance = balance;
        this.spendingHistory = spendingHistory;
    }
}