package com.codecool.krk.windows;

import com.codecool.krk.controller.SingleWindowController;
import com.codecool.krk.fileThread.SingleCopyThread;
import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;


import java.io.IOException;


public class SingleWindow {


    private ProgressBar bar;


    private ProgressIndicator progressLabel;

    private Button stop;




    SingleCopyThread singleThread;

    public SingleWindow(SingleCopyThread singleCopyThread) {
        this.singleThread = singleCopyThread;
    }

    public BorderPane createNewThread(String source, String destination) throws IOException {



        BorderPane root = FXMLLoader.load(getClass().getResource("/SingleWindow.fxml"));

        Label sourceLabel = (Label) root.lookup("#source");
        Label destinationLabel = (Label) root.lookup("#destination");

        progressLabel = (ProgressIndicator) root.lookup("#progress");
        bar = (ProgressBar) root.lookup("#bar");
        stop = (Button) root.lookup("#stop");

        sourceLabel.setText(source);
        destinationLabel.setText(destination);

        return root;
    }

    public void setProgress(double value) {
        this.bar.setProgress(value);
        this.progressLabel.setProgress(value);
    }

    public void setStopButtonUnavalible() {
        this.stop.setDisable(true);
    }
}
