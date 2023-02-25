package pl;

import be.Author;
import be.Book;
import be.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    public AnchorPane anpMain,
            anpUserControls,
            anpContent;
    @FXML
    public TextArea txaNote;
    @FXML
    public TextField txfSearch;
    @FXML
    public Button btnCancelNote,
            btnSaveNote,
            btnSearch,
            btnHistory,
            btnMenu;

    @FXML
    public TableView tbvBooks,
            tbvCategories,
            tbvAuthors;
    @FXML
    public TableColumn tbcCategories,
            tbcAuthors;
    @FXML
    public TableColumn tbcISBN,
            tbcTitle,
            tbcRelease,
            tbcRented,
            tbcRating;

    private final MainModel mainModel;

    public MainController() {
        mainModel = new MainModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tbcCategories.setCellValueFactory(new PropertyValueFactory<Category, String>("name"));
        tbcAuthors.setCellValueFactory(new PropertyValueFactory<Author, String>("name"));

        tbcISBN.setCellValueFactory(new PropertyValueFactory<Book,String>("isbn"));
        tbcTitle.setCellValueFactory(new PropertyValueFactory<Book,String>("title"));
        tbcRelease.setCellValueFactory(new PropertyValueFactory<Book,String>("releaseDate"));
        tbcRented.setCellValueFactory(new PropertyValueFactory<Book,Boolean>("rented"));
        tbcRating.setCellValueFactory(new PropertyValueFactory<Book,Integer>("rating"));

        displayHome();
        fillCategoryTable();
        fillAuthorTable();
        fillBookTable();
    }



    private void fillCategoryTable() {
        tbvCategories.setItems(mainModel.getAllCategories());
    }
    private void fillAuthorTable() {
        tbvAuthors.setItems(mainModel.getAllAuthors());
    }
    private void fillBookTable() {
        tbvBooks.setItems(mainModel.getAllBooks());
    }

    private void displayHome() {


    }

}
