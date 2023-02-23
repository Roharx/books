package pl;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    public AnchorPane anpMain,
    anpUserControls,
    anpContent;

    MainModel mainModel;

    public MainController(){
        mainModel = new MainModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayHome();
    }

    private void displayHome(){

    }

}
