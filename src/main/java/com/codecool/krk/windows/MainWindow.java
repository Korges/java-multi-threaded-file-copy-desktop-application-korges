package com.codecool.krk.windows;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

public class MainWindow {

    static Parent  root;
    static VBox vBox;



    public void display(Stage primaryStage) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/MainWindow.fxml"));
        primaryStage.setTitle("Multi-threaded File Copy Desktop Application");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        ScrollPane scrollPane = (ScrollPane) root.getChildrenUnmodifiable().get(1);
        vBox = new VBox();
        vBox.setSpacing(10);
        scrollPane.setContent(vBox);
        primaryStage.show();

    }

    public static void addPaneToVBox(BorderPane pane) {
        vBox.getChildren().add(pane);
    }



}





