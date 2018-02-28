package com.codecool.krk.controller;

import com.codecool.krk.exception.WrongDestinationException;
import com.codecool.krk.fileStream.CustomFileStream;
import com.codecool.krk.fileThread.SingleCopyThread;
import com.codecool.krk.fileThread.ThreadPool;
import com.codecool.krk.windows.AlertWindow;
import com.codecool.krk.windows.MainWindow;
import com.codecool.krk.windows.SingleWindow;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class CopyWindowController {

    String sourcePath;
    String destinationPath;
    static Integer amount = 0;
    AlertWindow alert;

    @FXML
    private BorderPane borderPane;

    @FXML
    private TextField source;

    @FXML
    private TextField destination;

    @FXML
    private CheckBox overwrite;

    public CopyWindowController() {

        this.alert = new AlertWindow();
    }

    @FXML
    public void okOnAction() throws IOException {

        try {
            SingleCopyThread thread = createThread();
            SingleWindow singleWindow = new SingleWindow(thread);
            thread.setSingleWindow(singleWindow);
            BorderPane pane = singleWindow.createNewThread(sourcePath, destinationPath);
            runThread(thread);
            MainWindow.addPaneToVBox(pane);
            cancelOnAction();
        } catch (NullPointerException e) {
            alert.display("Warning", "There exist such file! Select Override");
        } catch (FileNotFoundException e) {
            alert.display("Warning", "This file doesn't exist!");
        } catch (WrongDestinationException e) {
            alert.display("Warning", "Destination Path cannot be empty!");
        }
    }

    @FXML
    public void cancelOnAction() {
        Stage stage = (Stage) borderPane.getScene().getWindow();
        stage.close();
    }


    public SingleCopyThread createThread() throws FileNotFoundException, WrongDestinationException {

        createSourceAndDestinationPath();
        SingleCopyThread thread = null;

        if(!checkIfDestinationExists() || (checkIfDestinationExists() && overwrite.isSelected())) {

                CustomFileStream cs = new CustomFileStream(sourcePath, destinationPath);
                thread = new SingleCopyThread(cs);


        }
        return thread;
    }


    private boolean checkIfDestinationExists() {

        return new File(destinationPath).isFile();
    }


    public void runThread(SingleCopyThread thread) {

        ThreadPool.createThreadPool(thread);
    }


    public void createSourceAndDestinationPath() throws FileNotFoundException, WrongDestinationException {


        sourcePath = source.textProperty().getValue();
        destinationPath = destination.textProperty().getValue();

        checkIfCorrectSourceAndDestination();

        String[] splittedSource = sourcePath.split("/");
        destinationPath = destinationPath + "/" + splittedSource[splittedSource.length-1];
        if(overwrite.isSelected() & checkIfDestinationExists()) {
            this.amount++;
            String[] splittedDestination = destinationPath.split("\\.");
            destinationPath = splittedDestination[0] + "(" + amount + ")" + splittedDestination[1];
        }
    }


    public void checkIfCorrectSourceAndDestination() throws FileNotFoundException, WrongDestinationException {

        if (sourcePath.equals("")) {
            throw new FileNotFoundException();
        }
        if (destinationPath.equals("")) {
            throw new WrongDestinationException();
        }
    }
}
