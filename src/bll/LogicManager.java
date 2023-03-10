package bll;

import be.Author;
import be.Book;
import be.Category;
import dal.DataAccess;
import dal.IDataAccess;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
/*
@author Bálint Farkas
 */

public class LogicManager implements Initializable, ILogicManager {


    private IDataAccess dataAccess = new DataAccess();
    private List<Book> allBooks;
    private List<Category> allCategories;
    private List<Author> allAuthors;
    private List<String> bookAuthorConn;
    private List<String> bookCatConn;
    private List<String> allBookNotes;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataAccess = new DataAccess();
        fillAllBooks();
        fillAllCategories();
        fillAllAuthors();
        fillBookAuthorConn();
        fillBookCatConn();
        fillAllBookNotes();
    }

    private void fillAllBooks() {
        allBooks = dataAccess.getAllBooks();
    }

    private void fillAllCategories() {
        allCategories = dataAccess.getAllCategories();
    }

    private void fillAllAuthors() {
        allAuthors = dataAccess.getAllAuthors();
    }

    private void fillBookAuthorConn() {
        bookAuthorConn = dataAccess.getBookAuthConn();
    }

    private void fillBookCatConn() {
        bookCatConn = dataAccess.getBookCatConn();
    }

    private void fillAllBookNotes() {
        allBookNotes = dataAccess.getAllBookNotes();
    }

    @Override
    public ObservableList<Book> getAllBooks() {
        fillAllBooks();
        return FXCollections.observableArrayList(allBooks);
    }

    @Override
    public ObservableList<Author> getAllAuthors() {
        fillAllAuthors();
        return FXCollections.observableArrayList(allAuthors);
    }

    @Override
    public ObservableList<Category> getAllCategories() {
        fillAllCategories();
        return FXCollections.observableArrayList(allCategories);
    }

    @Override
    public void addBook(Book book) {
        dataAccess.addBook(book);
    }

    @Override
    public void editBook(int id, Book book) {
        dataAccess.editBook(id, book);
    }

    @Override
    public void deleteBook(int id) {
        dataAccess.deleteBook(id);
    }

    @Override
    public Author getAuthorByID(int id) {
        Author result = null;

        for (Author a : allAuthors) {
            if (a.getId() == id)
                result = a;
        }

        return result;
    }

    @Override
    public ObservableList<Book> getBooksByCategory(int categoryID) {
        return FXCollections.observableArrayList(getBooksByConnID(bookCatConn, categoryID));
    }

    @Override
    public List<Book> getAllBooksByAuthor(int authorID) {
        return FXCollections.observableArrayList(getBooksByConnID(bookAuthorConn, authorID));
    }

    private List<Book> getBooksByConnID(List<String> list, int id) {
        List<Book> results = new ArrayList<>();

        for (String line : list) {
            String[] lineContent = line.split(",");
            if (Integer.parseInt(lineContent[1]) == id)
                results.add(getBookById(Integer.parseInt(lineContent[0])));
        }
        return results;
    }

    @Override
    public Book getBookById(int id) {
        for (Book b : allBooks) {
            if (b.getId() == id)
                return b;
        }
        return null;
    }

    @Override
    public Category getCategoryByID(int id) {
        for (Category c : allCategories) {
            if (c.getId() == id)
                return c;
        }
        return null;
    }

    @Override
    public String getNoteForBook(int bookID) {
        fillAllBookNotes();
        for (String line : allBookNotes) {
            String[] lineContent = line.split(",");
            if (Integer.parseInt(lineContent[0]) == bookID)
                return line.substring(line.indexOf(',') + 1);
        }

        return null;
    }

    @Override
    public void saveBookNote(int bookID, String note) {
        dataAccess.saveBookNote(bookID, note);
    }

}
