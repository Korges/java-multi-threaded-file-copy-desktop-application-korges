package com.codecool.krk.multithreadedfilecopydesktopapplication;

import com.codecool.krk.multithreadedfilecopydesktopapplication.controller.MainWindowController;
import com.codecool.krk.multithreadedfilecopydesktopapplication.fileStream.CustomFileStream;
import com.codecool.krk.multithreadedfilecopydesktopapplication.fileThread.SingleCopyThread;
import com.codecool.krk.multithreadedfilecopydesktopapplication.fileThread.ThreadPool;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class MultiThreadedFileCopyDesktopApplication extends Application {

    static ThreadPool  pool = new ThreadPool();
    private static Stage primaryStage;
    private static Stage primaryScene;

    public static void main(String[] args) {

        launch(args);

    }

    public static void stopCopying(String threadToStop) {




    }

    public static void cancelSingleCopy(String threadToStop) {

        try {
            SingleCopyThread singleThread = SingleCopyThread.getSingleThreadByName(threadToStop);
            singleThread.interruptThread();
        } catch (NullPointerException e) {
            System.out.println("STOP");
        }
    }


    public static void copyFile(String source, String destination) {

        try { ;
            CustomFileStream cs = new CustomFileStream(source, destination);
            SingleCopyThread singleThread = new SingleCopyThread(cs);
            pool.createThreadPool(singleThread);
        } catch (FileNotFoundException e) {
            System.out.println("STOP");
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        setPrimaryStage(primaryStage);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/fxml/MainWindow.fxml"));


        Pane stackPane = loader.load();

        Scene scene = new Scene(stackPane);


        primaryStage.setScene(scene);
        primaryStage.setTitle("Copy Manager");
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> Platform.exit());
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setPrimaryStage(Stage primaryStage) {
        MultiThreadedFileCopyDesktopApplication.primaryStage = primaryStage;
    }
}