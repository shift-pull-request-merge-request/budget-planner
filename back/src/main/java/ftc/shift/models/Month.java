package ftc.shift.models;

import java.io.Serializable;

public class Month implements Serializable {
    private int monthId;
    private Integer balance;
    private Category[] categories;

    public int getMonthId() {
        return monthId;
    }

    public void setMonthId(int monthId) {
        this.monthId = monthId;
    }

    public Category[] getCategories() {
        return categories;
    }

    public void setCategories(Category[] categories) {
        this.categories = categories;
    }

    public Integer getBalance() {

        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Month() {

    }

    public Month(int monthId, int balance, Category[] categories){
        this.monthId = monthId;
        this.balance = balance;
        this.categories = categories;
    }

}