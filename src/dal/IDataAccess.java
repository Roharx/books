package dal;

import be.Author;
import be.Book;
import be.Category;

import java.util.List;

public interface IDataAccess {

    void addBook(Book book);
    List<Book> getAllBooks();
    void editBook(int bookID, Book book);
    void deleteBook(int bookID);


    List<Author> getAllAuthors();
    Author getAuthorByID(int id);
    List<Book> getBooksByCategory(Category category);
    List<Book> getAllBooksByAuthor(int authorID);
    Book getBookByISBN(int isbn);

    List<Category> getAllCategories();
    Category getCategoryByID(int id);

}
