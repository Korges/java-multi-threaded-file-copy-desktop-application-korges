package com.codecool.krk.multithreadedfilecopydesktopapplication.windows;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.util.Optional;


public class CopyWindow {

    Dialog<Pair<String,String>> dialog;
    GridPane grid;
    ButtonType confirm;
    TextField copyTo, copyFrom;

    public CopyWindow() {
        grid = new GridPane();
    }

    public Optional<Pair<String, String>> getDirectories() {

        setDialog();
        setGrid();
        setButtons();
        addTextAreas();

        dialog.getDialogPane().setContent(grid);

        Platform.runLater(() -> copyFrom.requestFocus());

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == confirm) {
                return new Pair<>(copyFrom.getText(), copyTo.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(directories -> {
            System.out.println("base directory=" + directories.getKey() + ", destination directory=" + directories.getValue());
        });

        return result;
    }

    private void setDialog() {
        dialog = new Dialog<>();
        dialog.setTitle("Select file");
        dialog.setHeaderText("chuj do dupy");
    }

    private void setGrid() {
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
    }

    private void setButtons() {
        confirm = new ButtonType("Confirm", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(confirm, ButtonType.CANCEL);
    }

    private void addTextAreas() {
        copyFrom = new TextField();
        copyFrom.setPromptText("base directory");
        copyTo = new TextField();
        copyTo.setPromptText("destination directory");
        grid.add(new Label("Copy from:"), 0, 0);
        grid.add(copyFrom, 1, 0);
        grid.add(new Label("Copy to:"), 0, 1);
        grid.add(copyTo, 1, 1);
    }
}
