package pl;

import be.Book;
import bll.ILogicManager;
import bll.LogicManager;

public class MainModel {

    private ILogicManager logicManager;

    public MainModel(){
        this.logicManager = new LogicManager();
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

}
