package ftc.shift.repositories;

import ftc.shift.models.Category;
import ftc.shift.models.Month;

@org.springframework.stereotype.Repository
public class InMemoryBudgetRepository {

    //private Map<String, Months> bugdetCache = new HashMap<>();

    private Month[] months;

    public InMemoryBudgetRepository() {
        months = new Month[12];
        for (int i = 0; i < 12; i++) {
            months[i] = new Month(i+1, 0, new Category[6]);
            for (int j =0; j<6; j++){
                //months[i].
            }
        }
    }

    public Month getMonthById(int id) {
        if (id > 12 || id < 1)
            return null;
        else return months[id - 1];
    }

    public Month updateBalance(int id, int balance){
        months[id-1].balance = balance;
        return months[id-1];
    }

}
