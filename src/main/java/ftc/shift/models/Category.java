package ftc.shift.models;

import java.io.Serializable;
import java.util.List;

public class Category implements Serializable{
    public String categoryName;
    public Integer categoryValue;
    public Spending spendings[];

    public Category() {
    }

    public Category(String categoryName, Integer categoryValue, Spending[] spending){
        this.categoryName = categoryName;
        this.categoryValue = categoryValue;
        this.spendings = spending;
    }

    public void setCategoryValue (Integer categoryValue) {this.categoryValue = categoryValue;}
}