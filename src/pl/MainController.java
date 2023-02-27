package pl;

import be.Author;
import be.Book;
import be.Category;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

/*
@author Bálint Farkas
 */

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
    public TableView<Book> tbvBooks;
    @FXML
    public TableView<Category> tbvCategories;
    @FXML
    public TableView<Author> tbvAuthors;
    @FXML
    public TableColumn<Category, String> tbcCategories;
    @FXML
    public TableColumn<Author, String> tbcAuthors;
    @FXML
    public TableColumn<Book, String> tbcISBN;
    @FXML
    public TableColumn<Book, String> tbcTitle;
    @FXML
    public TableColumn<Book, String> tbcRelease;
    @FXML
    public TableColumn<Book, Boolean> tbcRented;
    @FXML
    public TableColumn<Book, Integer> tbcRating;
    @FXML
    public Label lblSelectedItem;

    private final MainModel mainModel;

    public MainController() {
        mainModel = new MainModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTableCells();
        displayHome();
    }

    private void setTableCells() {
        tbcCategories.setCellValueFactory(new PropertyValueFactory<>("name"));
        tbcAuthors.setCellValueFactory(new PropertyValueFactory<>("name"));

        tbvCategories.setOnMouseClicked(e -> {
            Category selectedCategory = tbvCategories.getSelectionModel().getSelectedItem();
            lblSelectedItem.setText(selectedCategory.getName());
        });

        tbvAuthors.setOnMouseClicked(e -> {
            Author selectedAuthor = tbvAuthors.getSelectionModel().getSelectedItem();
            lblSelectedItem.setText(selectedAuthor.getName());
        });

        tbcISBN.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        tbcTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tbcRelease.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));
        tbcRented.setCellValueFactory(new PropertyValueFactory<>("rented"));
        tbcRating.setCellValueFactory(new PropertyValueFactory<>("rating"));

        tbvBooks.setOnMouseClicked(e -> {
            Book selectedBook = tbvBooks.getSelectionModel().getSelectedItem();
            lblSelectedItem.setText(selectedBook.getTitle());

            String noteText = mainModel.getBookNote(selectedBook.getId());
            txaNote.setText(noteText);
        });
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
        fillCategoryTable();
        fillAuthorTable();
        fillBookTable();
    }

    public void btnCancelNoteClicked() {
        Book selectedBook = tbvBooks.getSelectionModel().getSelectedItem();
        txaNote.setText(mainModel.getBookNote(selectedBook.getId()));
    }

    public void btnSaveNoteClicked() {
        Book selectedBook = tbvBooks.getSelectionModel().getSelectedItem();
        mainModel.saveBookNote(selectedBook.getId(), txaNote.getText());
        JOptionPane.showMessageDialog(null, "Note saved to " + selectedBook.getTitle());
    }
}
