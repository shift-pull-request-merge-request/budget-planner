package ftc.shift.repositories;


import ftc.shift.models.Months;
import ftc.shift.models.Month;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@org.springframework.stereotype.Repository
public class InMemoryBudgetRepository{

    private Map<String, Months> bugdetCache = new HashMap<>();

    public InMemoryBudgetRepository(){
    //    bugdetCache.put();
    }
}
