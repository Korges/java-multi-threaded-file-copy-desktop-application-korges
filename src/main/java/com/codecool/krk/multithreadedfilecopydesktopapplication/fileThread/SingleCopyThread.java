package com.codecool.krk.multithreadedfilecopydesktopapplication.fileThread;

import com.codecool.krk.multithreadedfilecopydesktopapplication.fileStream.CustomFileStream;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class SingleCopyThread implements Runnable {

    public CustomFileStream stream;

    private int length;
    private boolean isRunning;
    private static ArrayList<SingleCopyThread> threadList = new ArrayList<SingleCopyThread>();
    private long time = System.currentTimeMillis();

    public SingleCopyThread(CustomFileStream stream) {
        this.stream = stream;
        this.isRunning = true;
        this.threadList.add(this);
    }

    @Override
    public void run() {


        System.out.println("Copying " + stream.getSimpleFileName() + " file.");

        try {
//            String toPrint = "[" + String.join("", Collections.nCopies(102, " ")) + "]";
//            StringBuilder str = new StringBuilder(toPrint).append("   %");
            long percentage;
            long fileSizeBytes = stream.getInputStream().available();
            long current = 0;

            while (isAbleToRead()) {

                try {
                    stream.getOutputStream().write(stream.getBuffer(), 0, length);
                    Thread.sleep(0,1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                clearConsole();
//                current += length;
//                percentage = 100 * current / fileSizeBytes;
//                showStatusBar(str, percentage);
            }
            System.out.println("Copying " + stream.getSimpleFileName() + " finished after " + (System.currentTimeMillis() - time)/1000);
            this.stream.closeStreams();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SingleCopyThread getSingleThreadByName(String name) {
        for (SingleCopyThread el : threadList) {

            if (el.stream.getFileName().equals(name)) {
                return el;
            }
        }
        return null;
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

    public void interruptThread() throws IOException {

        System.out.println(stream.getFileName() + " aborted!");
        isRunning = false;
    }
}

