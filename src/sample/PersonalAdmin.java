package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import static sample.Main.fill;

public class PersonalAdmin {

    @FXML
    private Button ExitBtn;

    @FXML
    private Button ExitBtn1;
    private ObservableList<ObservableList> data;
    @FXML private TableView<ObservableList> userTable;

    @FXML
    private ImageView exitBtn;
    @FXML
    public void initialize() {
        try {
            fill("SELECT * FROM users", userTable);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        ExitBtn.setOnAction(event -> {
            ExitBtn.getScene().getWindow().hide();
            Parent root = null;
            try {
                root = FXMLLoader.load(Main.class.getResource("sample.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.show();
        });
        ExitBtn1.setOnAction(event -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(Main.class.getResource("add.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.show();
        });
    }

}
