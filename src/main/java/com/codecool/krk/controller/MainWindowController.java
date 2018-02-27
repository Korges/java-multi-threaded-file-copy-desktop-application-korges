package com.codecool.krk.controller;

import com.codecool.krk.fileThread.SingleCopyThread;
import com.codecool.krk.windows.CopyWindow;
import javafx.fxml.FXML;

import java.io.IOException;
import java.util.ArrayList;

public class MainWindowController {



    @FXML
    public void copyOnAction() throws IOException {
        CopyWindow copyBox = new CopyWindow();
        copyBox.display();
    }

    @FXML
    public void stopAllOnAction() {
        ArrayList<SingleCopyThread> list = SingleCopyThread.getAllThreads();
        for(SingleCopyThread thread : list) {
            thread.interruptThread();
        }
    }


}
