package ftc.shift.api;


import ftc.shift.models.Month;
import ftc.shift.models.Spending;
import ftc.shift.repositories.InMemoryBudgetRepository;
import ftc.shift.services.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class Controller {

    private static final String MONTHS_PATH = "/api/months";

    private final BudgetService service;

    @Autowired
    public Controller(BudgetService service) {
        this.service = service;
    }

    @PostMapping(MONTHS_PATH)
    public ResponseEntity postMonth(@RequestBody Month body) {
        try {
            service.postMonth(body);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(body);
    }

    @GetMapping(MONTHS_PATH + "/{id}")
    public ResponseEntity getMonthById(@PathVariable int id) {
        Month result = service.getMonthById(id);
        return ResponseEntity.ok(result);
    }

    @PatchMapping(MONTHS_PATH + "/{monthId}/{balance}")
    public ResponseEntity addMonthBalance(@PathVariable int monthId, @PathVariable int balance) {
        Month month = service.addMonthBalance(monthId, balance);
        if (month == null) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(month);
    }

    @PatchMapping(MONTHS_PATH + "/{monthId}/category/{categoryName}/{categoryBalance}")
    public ResponseEntity addCategoryBalance(@PathVariable int monthId, @PathVariable String categoryName, @PathVariable int categoryBalance) {
        Month month = service.addCategoryBalance(monthId, categoryName, categoryBalance);
        if (month == null) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(month);
    }

    @PatchMapping(MONTHS_PATH + "/{monthId}/category/{categoryName}")
    public ResponseEntity addNewSpending(@PathVariable int monthId, @PathVariable String categoryName, @RequestBody Spending body) {
        Month month = service.updateCategoriesSpending(monthId, categoryName, body);
        if (month == null) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(month);
    }


}