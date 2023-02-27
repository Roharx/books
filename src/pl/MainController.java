package pl;

import be.Author;
import be.Book;
import be.Category;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.PopupWindow;
import javafx.util.Duration;

import javax.swing.*;
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
    @FXML
    public Label lblSelectedItem;

    private final MainModel mainModel;
    private final int tooltipX = 650;
    private final int tooltipY = 115;

    public MainController() {
        mainModel = new MainModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTableCells();
        displayHome();
    }

    private void setTableCells() {
        tbcCategories.setCellValueFactory(new PropertyValueFactory<Category, String>("name"));
        tbcAuthors.setCellValueFactory(new PropertyValueFactory<Author, String>("name"));

        tbvCategories.setOnMouseClicked(e -> {
            Category selectedCategory = (Category) tbvCategories.getSelectionModel().getSelectedItem();
            lblSelectedItem.setText(selectedCategory.getName());
        });

        tbvAuthors.setOnMouseClicked(e -> {
            Author selectedAuthor = (Author) tbvAuthors.getSelectionModel().getSelectedItem();
            lblSelectedItem.setText(selectedAuthor.getName());
        });

        tbcISBN.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));
        tbcTitle.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        tbcRelease.setCellValueFactory(new PropertyValueFactory<Book, String>("releaseDate"));
        tbcRented.setCellValueFactory(new PropertyValueFactory<Book, Boolean>("rented"));
        tbcRating.setCellValueFactory(new PropertyValueFactory<Book, Integer>("rating"));

        tbvBooks.setOnMouseClicked(e -> {
            Book selectedBook = (Book) tbvBooks.getSelectionModel().getSelectedItem();
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

    public void btnCancelNoteClicked(ActionEvent actionEvent) {
        Book selectedBook = (Book) tbvBooks.getSelectionModel().getSelectedItem();
        txaNote.setText(mainModel.getBookNote(selectedBook.getId()));
    }

    public void btnSaveNoteClicked(ActionEvent actionEvent) {
        Book selectedBook = (Book) tbvBooks.getSelectionModel().getSelectedItem();
        mainModel.saveBookNote(selectedBook.getId(), txaNote.getText());
        JOptionPane.showMessageDialog(null, "Note saved to " + selectedBook.getTitle());
    }
}
