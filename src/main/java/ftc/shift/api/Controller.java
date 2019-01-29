package ftc.shift.api;


import ftc.shift.models.Book;
import ftc.shift.models.Month;
import ftc.shift.services.budgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class Controller {

    private static final String MONTHS_PATH = "/api/months";

    @Autowired
    private budgetService service;

    @GetMapping(MONTHS_PATH)                                //update month money value
    public ResponseEntity<Month[]> listMonths() {
        Month[] result = service.provideMonths();
        return ResponseEntity.ok(result);
    }

    @PatchMapping(MONTHS_PATH)                    //update month categories values
    public ResponseEntity<Month[]> updateMonthValue(@PathVariable String id, @RequestBody int body) {
        Month[] result = service.updateMonthValues(id, body);
        return ResponseEntity.ok(result);
    }

    @PatchMapping(MONTHS_PATH + "/{id}")                    //update month categories values
    public ResponseEntity<Month[]> updateCategoriesValues(@PathVariable String id, @RequestBody int body) {
        Month[] result = service.updateCategoriesValues(id, body);
        return ResponseEntity.ok(result);
    }

    @PatchMapping(MONTHS_PATH + "/{id}/{name}")             //add new spending
    public ResponseEntity<Month[]> addNewSpending(@PathVariable String id, @PathVariable String name, @RequestBody int body) {
        Month[] result = service.addNewSpending(id, name, body);
        return ResponseEntity.ok(result);
    }
}