package bll;

import be.Author;
import be.Book;
import be.Category;
import javafx.collections.ObservableList;

import java.util.List;

public interface ILogicManager {

    ObservableList<Book> getAllBooks();
    ObservableList<Author> getAllAuthors();
    ObservableList<Category> getAllCategories();

    void addBook(Book book);
    void editBook(int bookID, Book book);
    void deleteBook(int bookID);

    Author getAuthorByID(int id);
    List<Book> getBooksByCategory(int categoryID);
    List<Book> getAllBooksByAuthor(int authorID);
    Book getBookById(int isbn);

    Category getCategoryByID(int id);
    String getNoteForBook(int bookID);
    void saveBookNote(int bookID, String note);
}
