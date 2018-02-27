package com.codecool.krk.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AlertWindowController {

    @FXML
    private VBox vBox;


    @FXML
    public void okOnAction() {
        Stage stage = (Stage) vBox.getScene().getWindow();
        stage.close();
    }
}
