package com.example.prueba2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class MenuPrincipalController {

    @FXML
    private Button salirButton;

    public void salirButtonOnAction(ActionEvent e) throws IOException {
        //1. Crear objeto de FXML (Interfaz)
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        //2. Nueva scene y nuevo stage
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        //3.Colocar scene al stage y mostrar stage
        stage.setScene(scene);
        stage.show();
        //4. Cerrar ventana Actual
        Stage myStage = (Stage) salirButton.getScene().getWindow();
        myStage.close();
    }

    public void closeWindows(){


    }
}
