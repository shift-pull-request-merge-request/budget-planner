package ftc.shift.models;

import java.io.Serializable;

public class Spending implements Serializable {
    private int id;
    private int cost;
    private String name;
    private String description;
    private int day;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Spending() {
    }

    public Spending(int spendingId, int cost, String spendingName, String desc, int day) {
        this.id = spendingId;
        this.cost = cost;
        this.name = spendingName;
        this.description = desc;
        this.day = day;
    }

    public void setSpending(int spendingId, int cost, String spendingName, String desc, int day) {
        this.id = spendingId;
        this.cost = cost;
        this.name = spendingName;
        this.description = desc;
        this.day = day;
    }

}