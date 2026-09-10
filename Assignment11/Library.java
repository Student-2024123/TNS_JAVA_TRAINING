package Assignment11;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {
    private Map<String, Book> bookMap;

    public Library() {
        bookMap = new HashMap<>();
    }

    public boolean addBook(Book book) {
        if (bookMap.containsKey(book.getIsbn())) {
            return false;
        }
        bookMap.put(book.getIsbn(), book);
        return true;
    }

    public Book getBookByIsbn(String isbn) {
        return bookMap.get(isbn);
    }

    public boolean updateCopies(String isbn, int newCopies) {
        Book book = bookMap.get(isbn);
        if (book != null) {
            book.setCopiesAvailable(newCopies);
            return true;
        }
        return false;
    }

    public List<Book> getBooksSortedByTitle() {
        List<Book> books = new ArrayList<>(bookMap.values());
        books.sort(Comparator.comparing(Book::getTitle, String.CASE_INSENSITIVE_ORDER));
        return books;
    }

    public List<Book> getBooksSortedByAuthor() {
        List<Book> books = new ArrayList<>(bookMap.values());
        books.sort(Comparator.comparing(Book::getAuthor, String.CASE_INSENSITIVE_ORDER));
        return books;
    }
}
