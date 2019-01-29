package ftc.shift.services;

import ftc.shift.models.Month;
import ftc.shift.repositories.Repository;
import ftc.shift.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class budgetService {

    private final Repository repository;

    @Autowired
    public budgetService(Repository repository) {
        this.repository = repository;
    }

    public Book provideBook(String id) {
        return repository.fetchBook(id);
    }

    public Book updateBook(Book book) {
        repository.updateBook(book);
        return book;
    }

    public void deleteBook(String id) {
        repository.deleteBook(id);
    }


    public Book createBook(Book book) {
        repository.createBook(book);
        return book;
    }

    public Collection<Book> provideBooks() {
        return repository.getAllBooks();
    }

    public Month[] provideMonths() {
        return new Month[0];
    }

    public Month[] updateCategoriesValues(String id, int body) {
        return new Month[0];
    }

    public Month[] updateMonthValues(Month[] body) {
        return new Month[0];
    }

    public Month[] addNewSpending(String id, String name, int body) {
        return new Month[0];
    }
}
