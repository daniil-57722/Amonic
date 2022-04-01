package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class PersonalArea extends Controller {
    @FXML
    private Button exitBtn;

    @FXML
    private Label intro;

    @FXML
    private Label nameLbl;

    public void initData(User user){
        intro.setText("Hi, " + user.getName() + ", welcome to Amonic Airlines");
    }

    @FXML
    public void initialize() {
        exitBtn.setOnAction(event ->{
            exitBtn.getScene().getWindow().hide();
        });
    }
}
