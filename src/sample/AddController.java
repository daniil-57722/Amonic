package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddController {

    @FXML
    private TextField birthField;

    @FXML
    private TextField emailField;

    @FXML
    private Button saveBtn;

    @FXML
    private ImageView exitBtn1;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField nameField;

    @FXML
    private Label nameLbl;

    @FXML
    private ComboBox<String> offices;

    @FXML
    private PasswordField passField;
    ObservableList<String> data = FXCollections.observableArrayList();
    DBHandler dbHandler = new DBHandler();



    @FXML
    void initialize() {
        ResultSet resultSet = dbHandler.querry("SELECT Title FROM Offices");
        try{
        while (resultSet.next()){
            data.add(resultSet.getString(1));
        }
        offices.setItems(data);}
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        saveBtn.setOnAction(actionEvent -> {
            String email = emailField.getText();
            String password = passField.getText();
            String name = nameField.getText();
            String lastName = lastNameField.getText();
            int officeID = 0;
            try {
                ResultSet res = dbHandler.querry("SELECT ID FROM offices WHERE Title = '" + offices.getSelectionModel().getSelectedItem()+"'");
                res.next();
                officeID = res.getInt(1);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            String birthDate = birthField.getText();
            dbHandler.addUser(email, password, name, lastName, officeID, birthDate);
        });

    }
}
