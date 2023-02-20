package dal;

import be.Book;
import be.Category;

import java.util.List;

public interface IDataAccess {

    void addBook(Book book);
    String getAllBooks();
    void editBook(int bookID, Book book);
    void deleteBook(int bookID);


    String getAllAuthors();
    Book getBookByAuthor(int authorID);
    List<Book> getBookByCategory(Category category);
    List<Book> getAllBooksByAuthor(int authorID);

}
