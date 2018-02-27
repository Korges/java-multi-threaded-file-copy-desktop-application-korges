package com.codecool.krk.fileThread;


import com.codecool.krk.fileStream.CustomFileStream;
import com.codecool.krk.windows.SingleWindow;
import javafx.concurrent.Task;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class SingleCopyThread extends Task<Integer> implements Runnable {

    private SingleWindow singleWindow;
    public CustomFileStream stream;
    private int length;
    private boolean isRunning;
    private static ArrayList<SingleCopyThread> threadList = new ArrayList<SingleCopyThread>();


    public SingleCopyThread(CustomFileStream stream) {
        this.stream = stream;
        this.isRunning = true;
        this.threadList.add(this);
        this.singleWindow = new SingleWindow(this);
    }

    @Override
    public void run() {

        try {
            call();
        } catch (IOException e) {}
    }

    protected Integer call() throws IOException {
        int iterations = 0;
        long bytesAvailable = stream.getInputStream().available();


        try {

            while (isAbleToRead()) {


                try {
                    Thread.sleep(0,1);
                    stream.getOutputStream().write(stream.getBuffer(), 0, length);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                iterations++;
                this.updateProgress(iterations, bytesAvailable / stream.getBytes());

            }
            this.stream.closeStreams();



        } catch (FileNotFoundException e) {
            System.out.println("File not found");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return iterations;
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

