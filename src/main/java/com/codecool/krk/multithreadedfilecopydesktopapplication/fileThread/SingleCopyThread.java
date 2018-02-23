package com.codecool.krk.multithreadedfilecopydesktopapplication.fileThread;

import com.codecool.krk.multithreadedfilecopydesktopapplication.fileStream.CustomFileStream;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.concurrent.Task;
import javafx.scene.control.ProgressBar;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class SingleCopyThread extends Task<Integer> implements Runnable {

    public CustomFileStream stream;

    private ProgressBar progressBar;
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

        try {
            System.out.println("Copying " + stream.getSimpleFileName() + " file.");
            call();
        } catch (IOException e) {}
    }

    protected Integer call() throws IOException {
        int iterations = 0;
        long bytesAvailable = stream.getInputStream().available();
        try {

            System.out.println("Reading file...");
            while (isAbleToRead()) {

                try {
                    stream.getOutputStream().write(stream.getBuffer(), 0, length);
                    Thread.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                iterations++;
                this.updateProgress(iterations, bytesAvailable / stream.getBytes());
            }
            this.stream.closeStreams();

            System.out.println("Reading finished!");

        } catch (FileNotFoundException e) {
            System.out.println("File not found");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return iterations;
    }

    public static SingleCopyThread getSingleThreadByName(String name) {
        for (SingleCopyThread el : threadList) {

            if (el.stream.getFileName().equals(name)) {
                return el;
            }
        }
        return null;
    }

    public boolean isAbleToRead() throws IOException {
        return (length = stream.inputStreamLength()) > 0 && isRunning;
    }

    public void interruptThread() throws NullPointerException {

        System.out.println(stream.getFileName() + " aborted!");
        isRunning = false;
    }

    public int getLength() {
        return length;
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }


}

