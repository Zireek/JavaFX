package com.example.juegojavafx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class GameOver implements Initializable {
    @FXML
    Button volverajugar;

    HelloApplication helloApplication;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void volverajugar(){
        helloApplication.pantalla();
    }

    public void puntuaciones(){

    }

    public void setMain(HelloApplication helloApplication) {
        this.helloApplication = helloApplication;
    }
}
