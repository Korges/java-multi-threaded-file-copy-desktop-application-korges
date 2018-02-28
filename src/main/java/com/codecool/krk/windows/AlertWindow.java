package com.codecool.krk.windows;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;


public class AlertWindow {


    public void display(String title, String message) throws IOException {

        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setResizable(false);

        Parent root = FXMLLoader.load(getClass().getResource("/AlertWindow.fxml"));
        Label messageLabel = (Label) root.lookup("#message");
        messageLabel.setText(message);

        window.setScene(new Scene(root));
        window.show();
    }
}
