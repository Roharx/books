package dal;

import be.Author;
import be.Book;
import be.Category;

import java.util.List;
/*
@author Bálint Farkas
 */

public interface IDataAccess {

    void addBook(Book book);
    void editBook(int id, Book book);
    void deleteBook(int id);
    void addCategory(Category category);
    void deleteCategory(int categoryID);
    void addAuthor(Author author);
    void deleteAuthor(int authorID);
    void saveBookNote(int bookID, String note);

    List<Book> getAllBooks();
    List<Author> getAllAuthors();
    List<Category> getAllCategories();
    List<String> getBookAuthConn();
    List<String> getBookCatConn();
    List<String> getAllBookNotes();

}
