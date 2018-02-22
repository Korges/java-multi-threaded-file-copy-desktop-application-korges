package com.codecool.krk.multithreadedfilecopydesktopapplication;

import com.codecool.krk.multithreadedfilecopydesktopapplication.windows.WindowViewer;
import javafx.application.Application;
import javafx.stage.Stage;

public class CopyingApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        new WindowViewer(primaryStage);
    }
}
