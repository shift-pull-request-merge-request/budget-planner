package ftc.shift.repositories;

import ftc.shift.models.Month;

@org.springframework.stereotype.Repository
public class InMemoryBudgetRepository {

    //private Map<String, Months> bugdetCache = new HashMap<>();

    private Month[] months;

    public InMemoryBudgetRepository() {
        months = new Month[12];
        for (int i = 0; i < 12; i++) {
      //      months[i].monthId = String.valueOf(i + 1);
        //    months[i].value = 0;
        }
    }

    public Month getMonthById(int id) {
        if (id > 12 || id < 1)
            return null;
        else return months[id - 1];
    }
}
