package com.codecool.krk.multithreadedfilecopydesktopapplication.controller;

import com.codecool.krk.multithreadedfilecopydesktopapplication.MultiThreadedFileCopyDesktopApplication;
import com.codecool.krk.multithreadedfilecopydesktopapplication.fileThread.SingleCopyThread;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;

import java.io.IOException;


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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/SingleWindow.fxml"));

        Pane pane = fxmlLoader.load();

        bar.setLayoutX(14);
        bar.setLayoutY(65);
        bar.setMinWidth(700);
        bar.progressProperty().unbind();
        bar.progressProperty().bind(singleThread.progressProperty());

        pane.getChildren().add(bar);
        pane.getChildren().add(newLabel);
        MultiThreadedFileCopyDesktopApplication.runThread(singleThread);

        ff(pane);
    }

    @FXML
    public void onActionCancel() throws IOException {

    }

    public void ff(Pane pane) throws IOException {
        Pane primaryPane = MultiThreadedFileCopyDesktopApplication.getPrimaryPane();
        SplitPane splitPane = (SplitPane) primaryPane.getChildren().get(1);
        Pane insidePane = (Pane) splitPane.getChildrenUnmodifiable().get(0);
        insidePane.getChildren().add(pane);
    }
}
