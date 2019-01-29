package ftc.shift.api;


import ftc.shift.models.Category;
import ftc.shift.models.Month;
import ftc.shift.models.Months;
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

    @GetMapping(MONTHS_PATH)                                //update month money value
    public ResponseEntity<Months> listMonths() {
        //Month[] result = service.provideMonths();
        Category[] cats = new Category[1];
        Spending[] spendings = new Spending[2];
        spendings[0] = new Spending("1", 50, "Coke", "bottle of Coke", "5");
        spendings[1] = new Spending("2", 500, "Vodka", "litter of vodka", "10");
        cats[0] = new Category("food", 1000, spendings);
        Months result = new Months();
        result.months = new Month[]{new Month("01", 10000, cats)};
        return ResponseEntity.ok(result);
    }

    @PatchMapping(MONTHS_PATH)                    //update month categories values
    public ResponseEntity<Months> updateMonthValue(@RequestBody Months body) {
        //Month[] result = service.updateMonthValues(body);
        System.out.println(body);
        return ResponseEntity.ok(body);
    }

    @PatchMapping(MONTHS_PATH + "/{id}")                    //update month categories values
    public ResponseEntity<Months> updateCategoriesValues(@PathVariable String id, @RequestBody Months body) {
        //Months result = service.updateCategoriesValues(id, body);
        //return ResponseEntity.ok(result);
        return null;
    }

    @PatchMapping(MONTHS_PATH + "/{id}/{name}")             //add new spending
    public ResponseEntity<Months> addNewSpending(@PathVariable String id, @PathVariable String name, @RequestBody Months body) {
        //onths result = service.addNewSpending(id, name, body);
        //return ResponseEntity.ok(result);
        return null;
    }
}