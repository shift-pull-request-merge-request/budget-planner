package ftc.shift.repositories;

import ftc.shift.models.Book;

import java.util.Collection;

/**
 * Интерфейс для получения данных по книгам
 */
public interface Repository {

    Book fetchBook(String id);

    Book updateBook(Book book);

    void deleteBook(String id);

    Book createBook(Book book);

    Collection<Book> getAllBooks();
}
