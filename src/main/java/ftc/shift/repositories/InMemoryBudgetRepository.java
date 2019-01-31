package ftc.shift.repositories;

import ftc.shift.models.Category;
import ftc.shift.models.CategoryName;
import ftc.shift.models.Month;
import ftc.shift.models.Spending;
import org.springframework.stereotype.Repository;


@Repository
public class InMemoryBudgetRepository {

    public static int MONTH_NUM = 12;
    private static int CATEGORIES_NUM = 6;

    private Month[] months;

    public InMemoryBudgetRepository() {
        months = new Month[MONTH_NUM];
        for (int i = 0; i < MONTH_NUM; i++) {
            months[i] = new Month(i + 1, 0, new Category[CATEGORIES_NUM]);
            for (int j = 0; j < CATEGORIES_NUM; j++) {
                months[i].getCategories()[j] = new Category();
                months[i].getCategories()[j].setName(CategoryName.values()[j]);
            }
        }
    }

    public Month getMonthById(int id) {
        if (id > MONTH_NUM || id < 1)
            return null;
        else return months[id - 1];
    }

    public Month updateBalance(int id, int balance) {
        months[id - 1].setBalance(balance);
        return months[id - 1];
    }

    public Month updateCategoryBalance(int id, String categoryName, int newCategoryBalance) {
        Category category = months[id - 1].getCategories()[CategoryName.valueOf(categoryName.toUpperCase()).getId()];
        int newMonthBalance = months[id - 1].getBalance() + category.getBalance() - newCategoryBalance;
        if (newMonthBalance < 0) return null;
        months[id - 1].setBalance(newMonthBalance);
        category.setBalance(newCategoryBalance);
        return months[id - 1];
    }

    public Month
    updateCategorySpending(int monthId, String categoryName, Spending body) {
        Month month = months[monthId - 1];
        Category category = month.getCategories()[CategoryName.valueOf(categoryName.toUpperCase()).getId()];
        if (category.getBalance() < body.getCost()) return null;
        category.setBalance(category.getBalance() - body.getCost());
        body.setId(category.getSpendingHistory().size() + 1);               //ids start from 1
        category.getSpendingHistory().add(body);
        return month;
    }

    public void updateMonth(Month body) {
        if (body.getMonthId() > MONTH_NUM || body.getMonthId() < 1)
            throw new IllegalArgumentException();
        else months[body.getMonthId() - 1] = body;
    }
}
