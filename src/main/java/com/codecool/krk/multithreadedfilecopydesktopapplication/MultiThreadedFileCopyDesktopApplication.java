package com.codecool.krk.multithreadedfilecopydesktopapplication;

import com.codecool.krk.multithreadedfilecopydesktopapplication.fileStream.CustomFileStream;
import com.codecool.krk.multithreadedfilecopydesktopapplication.fileThread.SingleCopyThread;
import com.codecool.krk.multithreadedfilecopydesktopapplication.fileThread.ThreadPool;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.FileNotFoundException;



public class MultiThreadedFileCopyDesktopApplication extends Application {

    static ThreadPool  pool = new ThreadPool();
    private static Stage primaryStage;
    private static Scene primaryScene;

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


    public static SingleCopyThread createThread(String source, String destination) {

        SingleCopyThread thread = null;
        try {
            CustomFileStream cs = new CustomFileStream(source, destination);
            thread = new SingleCopyThread(cs);
        } catch (FileNotFoundException e) {
            System.out.println("STOP");
        }
        return thread;
    }

    public static void runThread(SingleCopyThread thread) {
        pool.createThreadPool(thread);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        setPrimaryStage(primaryStage);
        setPrimaryScene(primaryScene);
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



    public static Scene getPrimaryScene() {
        return primaryScene;
    }

    private void setPrimaryScene(Scene primaryScene) {
        MultiThreadedFileCopyDesktopApplication.primaryScene = primaryScene;
    }
}