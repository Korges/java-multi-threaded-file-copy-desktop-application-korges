package com.codecool.krk.fileThread;


import com.codecool.krk.fileStream.CustomFileStream;
import com.codecool.krk.windows.SingleWindow;

import java.io.IOException;
import java.util.ArrayList;

public class SingleCopyThread implements Runnable {

    private SingleWindow singleWindow;
    public CustomFileStream stream;
    private int length;
    private boolean isRunning;
    private static ArrayList<SingleCopyThread> threadList = new ArrayList<>();

    public void setSingleWindow(SingleWindow singleWindow) {
        this.singleWindow = singleWindow;
    }

    public SingleCopyThread(CustomFileStream stream) {
        this.stream = stream;
        this.isRunning = true;
        this.threadList.add(this);
    }

    @Override
    public void run() {

        try {
            call();
        } catch (IOException e) {

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void call() throws IOException, InterruptedException {

        long onePercent = stream.getInputStream().available()/100;
        long size = 0;
        double progress = 0.01F;

        try {

            while (isAbleToRead()) {






                try {
                    size += length;
                    if(size >= onePercent) { ;
                        size = 0;
                        progress += 0.01F;
                        singleWindow.setProgress(progress);

                    }
                    stream.getOutputStream().write(stream.getBuffer(), 0, length);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
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

    public boolean isAbleToRead() throws IOException {
        return (length = stream.inputStreamLength()) > 0 && isRunning;
    }

    public void interruptThread() throws NullPointerException {

        isRunning = false;
    }



}

