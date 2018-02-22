package com.codecool.krk.multithreadedfilecopydesktopapplication.controller;

import com.codecool.krk.multithreadedfilecopydesktopapplication.MultiThreadedFileCopyDesktopApplication;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;


public class SingleWindowController {



    @FXML
    private Label fromtoid;

    public SingleWindowController() {

    }

    public SingleWindowController(String sourcePath, String destinationPath) throws IOException {

        Label newLabel = new Label(sourcePath + "  to  " + destinationPath);
        newLabel.setLayoutX(87);
        newLabel.setLayoutY(25);
        createTemplate(newLabel);

    }

    @FXML
    private void initialize() {

    }

    public void createTemplate(Label newLabel) throws IOException {
        ff();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/SingleWindow.fxml"));

        Pane pane = fxmlLoader.load();

        Scene scene = new Scene(pane);
        Stage stage = new Stage();

        pane.getChildren().add(newLabel);


        stage.setScene(scene);
        stage.setOnHidden(event -> MainWindowController.shutdown());
        stage.setResizable(false);

        stage.show();
    }


    @FXML
    private Label progress;


    @FXML
    public void onActionCancel() throws IOException {


    }

    public void ff() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/fxml/MainWindow.fxml"));
        Pane stackPane = loader.load();
        Label label = new Label("CHUhgfhfhfghfghfJ");
        stackPane.getChildren().add(label);
        Stage stage = MultiThreadedFileCopyDesktopApplication.getPrimaryStage();
        Scene scene = new Scene(stackPane);


        stage.setScene(scene);
        stage.setTitle("Copy Manager");
        stage.setResizable(false);
        stage.show();
    }
}
