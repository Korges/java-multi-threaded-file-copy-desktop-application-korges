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

            while (isAbleToRead()) {

                try {
                    stream.getOutputStream().write(stream.getBuffer(), 0, length);
                } catch (Exception e) {
                    e.printStackTrace();
                }
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

    private boolean isAbleToRead() throws IOException {
        return (length = stream.inputStreamLength()) > 0 && isRunning;
    }

    public void interruptThread() throws NullPointerException {

        System.out.println(stream.getFileName() + " aborted!");
        isRunning = false;
    }
}

