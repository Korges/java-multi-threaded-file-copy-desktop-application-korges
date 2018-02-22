package com.codecool.krk.multithreadedfilecopydesktopapplication.controller;

import com.codecool.krk.multithreadedfilecopydesktopapplication.MultiThreadedFileCopyDesktopApplication;
import com.codecool.krk.multithreadedfilecopydesktopapplication.fileThread.SingleCopyThread;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CopyWindowController {



    private String sourcePath;
    private String destinationPath;

    public CopyWindowController() {
    }

    @FXML
    private Button cancel;

    @FXML
    private Button ok;

    @FXML
    private TextField source;

    @FXML
    private TextField destination;

    @FXML
    private CheckBox overwrite;

    @FXML
    public void onActionOK() throws IOException {

        sourcePath = source.textProperty().getValue();
        destinationPath = destination.textProperty().getValue();
        String[] splittedSource = sourcePath.split("/");
        destinationPath = destinationPath + "/" + splittedSource[splittedSource.length-1];
        MultiThreadedFileCopyDesktopApplication.copyFile(sourcePath, destinationPath);
        new SingleWindowController(sourcePath, destinationPath);
        Stage stage = (Stage) ok.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void onActionCancel() throws IOException {

        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }
}
