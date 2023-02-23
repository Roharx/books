package bll;

import be.Author;
import be.Book;
import be.Category;

import java.util.List;

public interface ILogicManager {

    List<Book> getAllBooks();
    List<Author> getAllAuthors();
    List<Category> getAllCategories();

    void addBook(Book book);
    void editBook(int bookID, Book book);
    void deleteBook(int bookID);

    Author getAuthorByID(int id);
    List<Book> getBooksByCategory(int categoryID);
    List<Book> getAllBooksByAuthor(int authorID);
    Book getBookByISBN(int isbn);

    Category getCategoryByID(int id);
}
