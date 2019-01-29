package ftc.shift.sample.repositories;

import ftc.shift.sample.models.Book;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Реализиция, хранящая все данные в памяти приложения
 */
@Repository
public class InMemoryBookRepository implements BookRepository {

    private Map<String, Book> bookCache = new HashMap<>();

    public InMemoryBookRepository() {
        bookCache.put("1", new Book("1", "Название 1", "Автор Авторович", 12,
                false, Collections.singletonList("Фантастика")));
        bookCache.put("2", new Book("2", "Название 2", "Автор Писателевич", 1000,
                true, Collections.singletonList("Детектив")));
    }

    @Override
    public Book fetchBook(String id) {
        return bookCache.get(id);
    }

    @Override
    public Book updateBook(Book book) {
        bookCache.put(book.getId(), book);
        return book;
    }

    @Override
    public void deleteBook(String id) {
        bookCache.remove(id);
    }

    @Override
    public Book createBook(Book book) {
        book.setId(String.valueOf(System.currentTimeMillis()));  //очень плохой способ генерировать Id, тут только для примера
        bookCache.put(book.getId(), book);
        return book;
    }

    @Override
    public Collection<Book> getAllBooks() {
        return bookCache.values();
    }
}
