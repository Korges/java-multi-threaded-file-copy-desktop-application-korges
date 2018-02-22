package com.codecool.krk.multithreadedfilecopydesktopapplication.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindowController {

    private static boolean isOpen = true;

    @FXML
    private Button copy;

    public MainWindowController() {

    }

    public static void shutdown() {
        isOpen = true;
    }


    @FXML
    public void onActionCopy() throws IOException {



        if(isOpen) {
            isOpen = false;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/CopyWindow.fxml"));
            Pane pane = fxmlLoader.load();
            Scene scene = new Scene(pane);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.setOnHidden(event -> MainWindowController.shutdown());
            stage.setResizable(false);
            stage.show();
        }




    }
}
