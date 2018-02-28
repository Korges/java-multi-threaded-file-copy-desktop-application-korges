package com.codecool.krk.windows;

import com.codecool.krk.fileThread.SingleCopyThread;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.BorderPane;
import java.io.IOException;
import java.time.LocalTime;


public class SingleWindow {

    private ProgressBar bar;
    private ProgressIndicator progressLabel;
    private Button stop;
    private Label elapsedTime;
    private Label infoLabel;
    private SingleCopyThread singleThread;


    public SingleWindow(SingleCopyThread singleCopyThread) {

        this.singleThread = singleCopyThread;
    }


    public BorderPane createNewThread(String source, String destination) throws IOException {

        BorderPane root = FXMLLoader.load(getClass().getResource("/SingleWindow.fxml"));
        Label sourceLabel = (Label) root.lookup("#source");
        Label destinationLabel = (Label) root.lookup("#destination");

        progressLabel = (ProgressIndicator) root.lookup("#progress");
        bar = (ProgressBar) root.lookup("#bar");
        stop = (Button) root.lookup("#stopButton");
        elapsedTime = (Label) root.lookup("#elapsedTime");
        infoLabel = (Label) root.lookup("#infoLabel");

        sourceLabel.setText(source);
        destinationLabel.setText(destination);

        return root;
    }


    public void setProgress(double value) {

        Platform.runLater(new Runnable() {

            @Override public void run() {

                bar.setProgress(value);
                progressLabel.setProgress(value);
            }
        });
    }


    public void setStopButtonUnavalible() {

        this.stop.setDisable(true);
    }



    public void setInfoLabelStatus(String info) {

        Platform.runLater(new Runnable() {

            @Override public void run() {

                infoLabel.setText(info);
            }
        });
    }



    public void setElapsedTime(long seconds) {

        LocalTime timeOfDay = LocalTime.ofSecondOfDay(seconds);
        String time = timeOfDay.toString();

        Platform.runLater(new Runnable() {

            @Override public void run() {

                elapsedTime.setText(String.valueOf(time));
            }
        });
    }
}
