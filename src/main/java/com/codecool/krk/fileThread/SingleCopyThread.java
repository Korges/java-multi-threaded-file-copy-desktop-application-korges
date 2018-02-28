package com.codecool.krk.fileThread;

import com.codecool.krk.fileStream.CustomFileStream;
import com.codecool.krk.windows.SingleWindow;
import java.io.IOException;
import java.util.ArrayList;


public class SingleCopyThread implements Runnable {

    private SingleWindow singleWindow;
    private CustomFileStream stream;
    private int length;
    private boolean isRunning;
    private long startTime;
    private long start;
    private long onePercent;
    private long size = 0;
    private double progress = 0F;
    private static ArrayList<SingleCopyThread> threadList = new ArrayList<>();


    public void setSingleWindow(SingleWindow singleWindow) {

        this.singleWindow = singleWindow;
    }


    public SingleCopyThread(CustomFileStream stream) {

        this.stream = stream;
        this.isRunning = true;
        threadList.add(this);
    }


    @Override
    public void run() {

        startTime = System.currentTimeMillis();

        try {
            call();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void call() throws IOException, InterruptedException {

        singleWindow.setInfoLabelStatus("");
        onePercent = stream.getInputStream().available()/100;

        start = System.currentTimeMillis();

        try {
            while (isAbleToRead()) {

                Thread.sleep(1);
                executeMethodOncePerSecond(start);
                try {
                    setProgressBars();
                    stream.getOutputStream().write(stream.getBuffer(), 0, length);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            setFinalStatusElements();
            this.stream.closeStreams();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static ArrayList<SingleCopyThread> getAllThreads() {

        return threadList;
    }


    public static SingleCopyThread getSingleThreadByName(String destination) {

        for (SingleCopyThread el : threadList) {
            if (el.stream.getDestination().equals(destination)) {
                return el;
            }
        }
        return null;
    }


    private boolean isAbleToRead() throws IOException {

        return (length = stream.inputStreamLength()) > 0 && isRunning;
    }


    public void interruptThread() throws NullPointerException {

        isRunning = false;
    }


    private void executeMethodOncePerSecond(long start) {

        if((System.currentTimeMillis()-start)/1000==1) {

            long elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
            this.start = System.currentTimeMillis();

            singleWindow.setElapsedTime(elapsedTime);
        }
    }


    private void setProgressBars() {
        size += length;
        if (size >= onePercent) {
            size = 0;
            progress += 0.01F;
            singleWindow.setProgress(progress);
        }
    }


    private void setFinalStatusElements() {

        singleWindow.setStopButtonUnavalible();

        if (isRunning) {
            singleWindow.setInfoLabelStatus("Operation finished successfully");
            singleWindow.setProgress(1F);
        } else {
            singleWindow.setInfoLabelStatus("Operation interrupted");
        }
    }
}

