package com.codecool.krk.windows;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;


public class CopyWindow {


    public void display() throws IOException {

        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("File Paths");
        window.setResizable(false);

        Parent root = FXMLLoader.load(getClass().getResource("/CopyWindow.fxml"));
        window.setScene(new Scene(root));
        window.show();
    }
}
