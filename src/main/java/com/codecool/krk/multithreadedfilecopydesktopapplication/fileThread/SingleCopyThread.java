package com.codecool.krk.multithreadedfilecopydesktopapplication.fileThread;

import com.codecool.krk.multithreadedfilecopydesktopapplication.fileStream.CustomFileStream;

import java.io.*;
import java.net.Socket;

public class SingleCopyThread implements Runnable {

    public CustomFileStream stream;
    int length;
    boolean isRunning;

    public SingleCopyThread(CustomFileStream stream) throws IOException {
        this.stream = stream;
        this.isRunning = true;
    }

    @Override
    public void run() {
        try {
            while (isAbleToRead()) {
                stream.getOutputStream().write(stream.getBuffer(), 0, length);
            }
            this.stream.closeStreams();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isAbleToRead() throws IOException {
        return (length = stream.inputStreamLength()) > 0 && isRunning;
    }

    public void interruptThread() {
        isRunning = false;
    }
}

