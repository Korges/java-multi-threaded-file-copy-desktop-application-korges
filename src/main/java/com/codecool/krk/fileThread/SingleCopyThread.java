package com.codecool.krk.fileThread;


import com.codecool.krk.fileStream.CustomFileStream;
import com.codecool.krk.windows.SingleWindow;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class SingleCopyThread implements Runnable {

    private SingleWindow singleWindow;
    private CustomFileStream stream;
    private int length;
    private boolean isRunning;
    private long startTime;
    private long elapsedTime;
    private long start;
    private long onePercent;
    private long size = 0;
    private double progress = 0.01F;
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
        } catch (IOException e) {

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void call() throws IOException, InterruptedException {

        onePercent = stream.getInputStream().available()/100;

        start = System.currentTimeMillis();

        try {
            while (isAbleToRead()) {

//                Thread.sleep(1);
                executeMethodOncePerSecond(start);
                try {
                    setProgressBars();
                    stream.getOutputStream().write(stream.getBuffer(), 0, length);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            singleWindow.showDoneLabel();
            singleWindow.setStopButtonUnavalible();
            this.stream.closeStreams();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<SingleCopyThread> getAllThreads() {
        return threadList;
    }

    public static SingleCopyThread getSingleThreadByName(String name) {
        for (SingleCopyThread el : threadList) {
            if (el.stream.getFileName().equals(name)) {
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

            this.elapsedTime = (System.currentTimeMillis()-startTime)/1000;
            this.start = System.currentTimeMillis();

            singleWindow.setElapsedTime(elapsedTime);
        }
    }

    private void setProgressBars() {
        size += length;
        if(size >= onePercent) {
            size = 0;
            progress += 0.01F;
            singleWindow.setProgress(progress);

        }
    }
}

