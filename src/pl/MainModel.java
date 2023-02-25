package pl;

import be.Author;
import be.Book;
import be.Category;
import bll.ILogicManager;
import bll.LogicManager;
import javafx.collections.ObservableList;

public class MainModel {

    private ILogicManager logicManager;

    public MainModel(){
        this.logicManager = new LogicManager();
    }

    public ObservableList<Book> getAllBooks(){
        return logicManager.getAllBooks();
    }
    public ObservableList<Author> getAllAuthors(){
        return logicManager.getAllAuthors();
    }
    public ObservableList<Category> getAllCategories(){
        return logicManager.getAllCategories();
    }


    public void addBook(Book book){
       logicManager.addBook(book);
    }
    public void editBook(int id, Book book){
        logicManager.editBook(id, book);
    }
    public void deleteBook(int id){
        logicManager.deleteBook(id);
    }
    public void editNote(String note){

    }
    public void addCategory(Category category){

    }
    public void editCategory(Category category){

    }
    public void deleteCategory(int categoryID){

    }
    public void addAuthor(Author author){

    }
    public void editAuthor(Author author){

    }
    public void deleteAuthor(int authorID){

    }

}
