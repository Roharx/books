package pl;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
    tbvCategories;


    private MainModel mainModel;

    public MainController() {
        mainModel = new MainModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        displayHome();
    }

    private void displayHome() {

    }

}
