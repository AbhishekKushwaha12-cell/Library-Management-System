package service;

import dao.BookDAO;
import model.Book;

import java.util.ArrayList;
import java.util.List;

public class LibraryService {
    private BookDAO dao;
    private List<Book> books;

    public LibraryService() {
        dao = new BookDAO();
        books = dao.getAllBooks();
    }

    public boolean addBook(String isbn, String title, String author) {
        for (Book b : books) {
            if (b.getIsbn().equals(isbn)) return false;
        }
        books.add(new Book(isbn, title, author));
        dao.saveBooks(books);
        return true;
    }

    public boolean removeBook(String isbn) {
        boolean removed = books.removeIf(book -> book.getIsbn().equals(isbn));
        if (removed) dao.saveBooks(books);
        return removed;
    }

    public List<Book> searchByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> getAllBooks() {
        return books;
    }
}
