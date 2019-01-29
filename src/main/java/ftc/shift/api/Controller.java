package ftc.shift.api;


import ftc.shift.models.Book;
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
    public ResponseEntity<Collection<Book>> listBooks() {
        Collection<Book> books = service.provideBooks();
        return ResponseEntity.ok(books);
    }

    @PatchMapping(MONTHS_PATH + "/{id}")                    //update month categories values
    public ResponseEntity<Book> updateMonthValue(@PathVariable String id, @RequestBody int val) {
        Month[] result = service.updateBook(book);
        return ResponseEntity.ok(result);
    }

    @PatchMapping(MONTHS_PATH + "/{id}/{name}")             //add new spending
    public ResponseEntity<Book> updateMonthValue(@PathVariable String id, @PathVariable String name) {
//        Book result = service.updateBook(book);
        return ResponseEntity.ok(result);
    }
}