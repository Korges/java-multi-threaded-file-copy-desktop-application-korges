package com.codecool.krk.multithreadedfilecopydesktopapplication.controller;

import com.codecool.krk.multithreadedfilecopydesktopapplication.MultiThreadedFileCopyDesktopApplication;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
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

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/SingleWindow.fxml"));

        Pane pane = fxmlLoader.load();

        pane.getChildren().add(newLabel);

        ff(pane);

    }


    @FXML
    private Label progress;


    @FXML
    public void onActionCancel() throws IOException {


    }

    public void ff(Pane pane) throws IOException {
        Pane primaryPane = MultiThreadedFileCopyDesktopApplication.getPrimaryPane();
        SplitPane splitPane = (SplitPane) primaryPane.getChildren().get(1);
        Pane insidePane = (Pane) splitPane.getChildrenUnmodifiable().get(0);
        insidePane.getChildren().add(pane);
//        MultiThreadedFileCopyDesktopApplication.setPrimaryPane(primaryPane);
    }
}
