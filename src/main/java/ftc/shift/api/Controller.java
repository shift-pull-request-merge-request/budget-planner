package ftc.shift.api;


import ftc.shift.models.Category;
import ftc.shift.models.Month;
import ftc.shift.models.Spending;
import ftc.shift.services.budgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    private static final String MONTHS_PATH = "/api/months";

    @Autowired
    private budgetService service;

    @GetMapping(MONTHS_PATH + "/{id}")
    public ResponseEntity<Month> getMonthById(@PathVariable int id) {
        Month result = service.getMonthById(id);
        return ResponseEntity.ok(result);
    }

    @PatchMapping(MONTHS_PATH)                    //update month categories values
    public ResponseEntity<Month> updateMonthValue(@RequestBody Month body) {
        //Month[] result = service.updateMonthValues(body);
        System.out.println(body);
        return ResponseEntity.ok(body);
    }

    @PatchMapping(MONTHS_PATH + "/{id}")                    //TODO: update month categories values
    public ResponseEntity<Month> updateCategoriesValues(@PathVariable int id, @RequestBody Month body) {
        //Months result = service.updateCategoriesValues(id, body);
        //return ResponseEntity.ok(result);
        return null;
    }

    @PatchMapping(MONTHS_PATH + "/{id}/{name}")             //TODO: add new spending
    public ResponseEntity<Month> addNewSpending(@PathVariable int id, @PathVariable String name, @RequestBody Month body) {
        //onths result = service.addNewSpending(id, name, body);
        //return ResponseEntity.ok(result);
        return null;
    }
}