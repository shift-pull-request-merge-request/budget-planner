package ftc.shift.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import ftc.shift.models.*;
import org.springframework.stereotype.Repository;

import java.io.*;


@Repository
public class InMemoryBudgetRepository {
    public static int MONTH_NUM = 12;
    private static int CATEGORIES_NUM = 6;

    private Months months;

    public InMemoryBudgetRepository() {
        Months loadedMonths = new Months();
        this.months = new Months();
        try (InputStream fileInputStream = new FileInputStream("work/db.json")) {
            loadedMonths = new ObjectMapper().readerFor(Months.class).readValue(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < loadedMonths.getMonths().length; i++)
            this.months.getMonths()[i] = loadedMonths.getMonths()[i];
    }

    public Month getMonthById(int id) {
        if (id > MONTH_NUM || id < 1)
            return null;
        else return months.getMonths()[id - 1];
    }

    public Month updateBalance(int id, int balance) {
        try (OutputStream fos = new FileOutputStream("work/db.json")) {
            months.getMonths()[id - 1].setBalance(balance);
            new ObjectMapper().writerFor(Months.class).writeValue(fos, months);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return months.getMonths()[id - 1];
    }

    public Month updateCategoryBalance(int id, String categoryName, int newCategoryBalance) {
        Category category = months.getMonths()[id - 1].getCategories()[CategoryName.valueOf(categoryName.toUpperCase()).getId()];
        int newMonthBalance = months.getMonths()[id - 1].getBalance() + category.getBalance() - newCategoryBalance;
        if (newMonthBalance < 0) return null;
        months.getMonths()[id - 1].setBalance(newMonthBalance);
        category.setBalance(newCategoryBalance);
        try (OutputStream fos = new FileOutputStream("work/db.json")) {
            new ObjectMapper().writerFor(Months.class).writeValue(fos, months);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return months.getMonths()[id - 1];
    }

    public Month
    updateCategorySpending(int monthId, String categoryName, Spending body) {
        Month month = months.getMonths()[monthId - 1];
        Category category = month.getCategories()[CategoryName.valueOf(categoryName.toUpperCase()).getId()];
        if (category.getBalance() < body.getCost()) return null;
        try (OutputStream fos = new FileOutputStream("work/db.json")) {
            category.setBalance(category.getBalance() - body.getCost());
            body.setId(category.getSpendingHistory().size() + 1);               //ids start from 1
            category.getSpendingHistory().add(body);
            new ObjectMapper().writerFor(Months.class).writeValue(fos, months);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return month;
    }

    public void updateMonth(Month body) {
        if (body.getMonthId() > MONTH_NUM || body.getMonthId() < 1)
            throw new IllegalArgumentException();
        try (OutputStream fos = new FileOutputStream("work/db.json")) {
            months.getMonths()[body.getMonthId() - 1] = body;
            new ObjectMapper().writerFor(Months.class).writeValue(fos, months);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}