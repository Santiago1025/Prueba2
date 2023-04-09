package com.example.prueba2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class IniciarSesionController {
    @FXML
    private Button cancelButton;
    @FXML
    private Button loginButton;
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

                    //1. Crear objeto de FXML (Interfaz)
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MenuPrincipal.fxml"));
                    //2. Nueva scene y nuevo stage
                    Scene scene = new Scene(fxmlLoader.load());
                    Stage stage = new Stage();
                    //3.Colocar scene al stage y mostrar stage
                    stage.setScene(scene);
                    stage.show();
                    //4. Cerrar ventana Actual
                    Stage myStage = (Stage) loginButton.getScene().getWindow();
                    myStage.close();

                }else {
                    loginMessageLabel.setText("Invalid login");
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}