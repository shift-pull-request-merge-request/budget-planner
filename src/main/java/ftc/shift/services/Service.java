package ftc.shift.services;

import ftc.shift.repositories.Repository;
import ftc.shift.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class Service {

    private final Repository repository;

    @Autowired
    public Service(Repository repository) {
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

}
