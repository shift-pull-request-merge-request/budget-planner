package ftc.shift.services;

import ftc.shift.models.Month;
import ftc.shift.models.Spending;
import ftc.shift.repositories.InMemoryBudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BudgetService {

    private final InMemoryBudgetRepository repository;

    @Autowired
    public BudgetService(InMemoryBudgetRepository repository) {
        this.repository = repository;
    }

    public Month addCategoryBalance(int monthId, String categoryName, int categoryBalance) {
        return repository.updateCategoryBalance(monthId, categoryName, categoryBalance);
    }

    public Month addMonthBalance(int id, int balance) {
        return repository.updateBalance(id, balance);
    }

    public Month updateCategoriesSpending(int monthId, String category, Spending body) {
        return repository.updateCategorySpending(monthId, category, body);
    }

    public Month getMonthById(int id) {
        return repository.getMonthById(id);
    }
}
