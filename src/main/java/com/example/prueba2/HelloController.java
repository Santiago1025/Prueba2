package com.example.prueba2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class HelloController {
    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;

    public void cancelButtonOnAction(ActionEvent e){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void loginButtonOnAction(ActionEvent e){

        if(usernameTextField.getText().isBlank() == false && passwordField.getText().isBlank() == false){
            loginMessageLabel.setText("You try to login!");
            validateLogin();
        }else{
            loginMessageLabel.setText("Por favor, ingresa nombre de usuario y contraseña");
        }
    }

    //Nuevo método
    public void validateLogin(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM user_account WHERE NombreUsario = '"+ usernameTextField.getText() + "' AND Contraseña = '"+  passwordField.getText() + "';";

        try{

            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()){

                if(queryResult.getInt(1) == 1){
                    loginMessageLabel.setText("Bienvenido");
                }else {
                    loginMessageLabel.setText("Invalid login");
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}