package ftc.shift.api;


import ftc.shift.models.Month;
import ftc.shift.models.Spending;
import ftc.shift.services.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    private static final String MONTHS_PATH = "/api/months";

    private final BudgetService service;

    @Autowired
    public Controller(BudgetService service) {
        this.service = service;
    }

    @GetMapping(MONTHS_PATH + "/{id}")
    public ResponseEntity<Month> getMonthById(@PathVariable int id) {
        Month result = service.getMonthById(id);
        return ResponseEntity.ok(result);
    }

    @PatchMapping(MONTHS_PATH + "/{monthId}/{balance}")
    public ResponseEntity<Month> addMonthBalance(@PathVariable int monthId, @PathVariable int balance, @RequestBody Month body) {
        Month month = service.addMonthBalance(monthId, balance);
        return ResponseEntity.ok(month);
    }

    @PatchMapping(MONTHS_PATH + "/{monthId}/category/{categoryName}/{categoryBalance}")
    public ResponseEntity<Month> addCategoryBalance(@PathVariable int monthId, @PathVariable String categoryName, @PathVariable int categoryBalance, @RequestBody Month body) {
        Month month = service.addCategoryBalance(monthId, categoryName, categoryBalance);
        return ResponseEntity.ok(month);
    }

    @PatchMapping(MONTHS_PATH + "/{monthId}/category/{categoryName}")
    public ResponseEntity<Month> addNewSpending(@PathVariable int monthId, @PathVariable String categoryName, @RequestBody Spending body) {
        Month month = service.updateCategoriesSpending(monthId, categoryName, body);
        return ResponseEntity.ok(month);
    }


}