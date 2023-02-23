package dal;

import be.Author;
import be.Book;
import be.Category;

import java.util.List;

public interface IDataAccess {

    void addBook(Book book);
    void editBook(int isbn, Book book);
    void deleteBook(int isbn);

    List<Book> getAllBooks();
    List<Author> getAllAuthors();
    List<Category> getAllCategories();
    List<String> getBookAuthConn();
    List<String> getBookCatConn();

}
