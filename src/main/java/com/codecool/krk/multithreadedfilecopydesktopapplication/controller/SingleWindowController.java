package com.codecool.krk.multithreadedfilecopydesktopapplication.controller;

import com.codecool.krk.multithreadedfilecopydesktopapplication.MultiThreadedFileCopyDesktopApplication;
import com.codecool.krk.multithreadedfilecopydesktopapplication.fileStream.CustomFileStream;
import com.codecool.krk.multithreadedfilecopydesktopapplication.fileThread.SingleCopyThread;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.server.ExportException;


public class SingleWindowController {



    @FXML
    private Label fromtoid;

    SingleCopyThread singleThread;

    @FXML
    private ProgressBar bar;

    public SingleWindowController() {

    }

    public SingleWindowController(SingleCopyThread singleCopyThread) throws IOException {

        bar = new ProgressBar(0);
        this.singleThread = singleCopyThread;
        Label newLabel = new Label(singleThread.stream.getFileName() + "  to  "
                                   + singleThread.stream.getDestination());
        newLabel.setLayoutX(87);
        newLabel.setLayoutY(25);

        createTemplate(newLabel);

    }

    @FXML
    private void initialize() {

    }

    public void createTemplate(Label newLabel) throws IOException {
//        ff();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/SingleWindow.fxml"));

        Pane pane = fxmlLoader.load();

        Scene scene = new Scene(pane);
        Stage stage = new Stage();

        bar.setLayoutX(14);
        bar.setLayoutY(65);
        bar.setMinWidth(700);
        bar.progressProperty().unbind();
        bar.progressProperty().bind(singleThread.progressProperty());

        pane.getChildren().add(bar);
        pane.getChildren().add(newLabel);
        MultiThreadedFileCopyDesktopApplication.runThread(singleThread);



        stage.setScene(scene);
        stage.setOnHidden(event -> MainWindowController.shutdown());
        stage.setResizable(false);

        stage.show();

    }



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
