package sample;
//j.doe@amonic.com
//123
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller {

    @FXML private Button loginBtn;
    @FXML private TextField loginField;
    @FXML private PasswordField passwordField;
    @FXML private Button exitBtn;
    @FXML private Label successLbl;

    @FXML
    public void initialize(){
        loginBtn.setOnAction(event ->{

            DBHandler dbHandler = new DBHandler();
            try {
                ResultSet rsset = dbHandler.loginUser(loginField.getText(), passwordField.getText());
                if (rsset.next()){
                    User user = new User(rsset.getString(4), rsset.getString(5), rsset.getInt(2));
                    loginBtn.getScene().getWindow().hide();
                    if (user.getRole() == 2){
                        FXMLLoader loader = new FXMLLoader(Main.class.getResource("personalUser.fxml"));
                        Stage stage = new Stage();
                        stage.setResizable(false);
                        stage.setScene(new Scene(loader.load()));
                        PersonalArea personalArea = loader.getController();
                        personalArea.initData(user);
                        stage.show();
                    }
                    else {
                        Parent root = FXMLLoader.load(Main.class.getResource("personalAdmin.fxml"));
                        Stage stage = new Stage();
                        stage.setResizable(false);
                        stage.setScene(new Scene(root));
                        stage.show();
                    }
                }
                else{
                    successLbl.setText("Неверный логин или пароль");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        exitBtn.setOnAction(event ->{
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
    }

}
