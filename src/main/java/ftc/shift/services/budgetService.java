package ftc.shift.services;

import ftc.shift.models.Month;
import ftc.shift.repositories.InMemoryBudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class budgetService {

    private final InMemoryBudgetRepository repository;

    @Autowired
    public budgetService(InMemoryBudgetRepository repository) {
        this.repository = repository;
    }

//    public Book provideBook(String id) {
//        return repository.fetchBook(id);
//    }
//
//    public Book updateBook(Book book) {
//        repository.updateBook(book);
//        return book;
//    }
//
//    public void deleteBook(String id) {
//        repository.deleteBook(id);
//    }
//
//
//    public Book createBook(Book book) {
//        repository.createBook(book);
//        return book;
//    }
//
//    public Collection<Book> provideBooks() {
//        return repository.getAllBooks();
//    }
//
//    public Month[] provideMonths() {
//        return new Month[0];
//    }

    public Month[] updateCategoriesValues(String id, int body) {
        return new Month[0];
    }

    public Month updateMonthValues(int id, int balance) {
        return repository.updateBalance(id, balance);
    }

    public Month getMonth(int id) {
        return repository.getMonthById(id);
    }

    public Month[] addNewSpending(String id, String name, int body) {
        return new Month[0];
    }

    public Month getMonthById(int id) {
       return repository.getMonthById(id);
    }
}
