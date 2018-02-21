package com.codecool.krk.multithreadedfilecopydesktopapplication.fileThread;

import com.codecool.krk.multithreadedfilecopydesktopapplication.fileStream.CustomFileStream;

import java.io.*;
import java.util.Collections;

public class SingleCopyThread implements Runnable {

    public CustomFileStream stream;
    private int length;
    private boolean isRunning;

    public SingleCopyThread(CustomFileStream stream) {
        this.stream = stream;
        this.isRunning = true;
    }

    @Override
    public void run() {

        try {
            String toPrint = "[" + String.join("", Collections.nCopies(102, " ")) + "]";
            StringBuilder str = new StringBuilder(toPrint).append("   %");
            long percentage;
            long fileSizeBytes = stream.getInputStream().available();
            long current = 0;

            while (isAbleToRead()) {
                clearConsole();
                stream.getOutputStream().write(stream.getBuffer(), 0, length);
                current += length;
                percentage = 100 * current / fileSizeBytes;
                showStatusBar(str, percentage);
            }
            this.stream.closeStreams();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void clearConsole() {
        System.out.print(String.format("\033[%dA",1));
    }

    private static void showStatusBar(StringBuilder statusBar, long percentage) {
        int percent = (int) percentage;
        statusBar.replace(percent+1, percent+2, "\u25A0")
                .replace(104, 107, String.format("%3s", percent));
        System.out.println(statusBar);
    }

    private boolean isAbleToRead() throws IOException {
        return (length = stream.inputStreamLength()) > 0 && isRunning;
    }

    public void interruptThread() {
        isRunning = false;
    }
}

