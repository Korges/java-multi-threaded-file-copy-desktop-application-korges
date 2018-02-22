package com.codecool.krk.multithreadedfilecopydesktopapplication.windows;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.beans.EventHandler;
import java.io.IOException;
import java.util.Optional;

public class WindowViewer extends Application {

    Button copy;
    Button stopAll;

    private CopyWindow dialog;

    public WindowViewer(Stage stage) {

        copy = new Button("Copy");

        stopAll = new Button("Stop all");

        copy.setAlignment(Pos.BASELINE_LEFT);
        stopAll.setAlignment(Pos.CENTER_RIGHT);

        Group root = new Group();
        Scene scene = new Scene(root, 500, 500);

        HBox box = new HBox(20);
        box.setAlignment(Pos.BOTTOM_CENTER);
        box.getChildren().addAll(copy, stopAll);

        stage.setScene(scene);
        stage.setTitle("Main window");
        scene.setRoot(box);
        stage.show();

        copy.addEventHandler(MouseEvent.MOUSE_PRESSED, new javafx.event.EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dialog = new CopyWindow();
                dialog.getDirectories();
            }
        });
    }

    @Override
    public void start(Stage primaryStage) throws Exception {


    }
}
