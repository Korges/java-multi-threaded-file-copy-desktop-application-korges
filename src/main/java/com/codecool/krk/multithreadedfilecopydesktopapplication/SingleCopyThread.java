package com.codecool.krk.multithreadedfilecopydesktopapplication;

import java.io.*;
import java.net.Socket;

public class SingleCopyThread implements Runnable {

    InputStream is;
    OutputStream os;
    int length;
    byte[] buffer = new byte[1024];
    boolean isRunning;

    public SingleCopyThread(String source, String destination) throws IOException {
        is = new FileInputStream(source);
        os = new FileOutputStream(destination);
        this.isRunning = true;
    }

    @Override
    public void run() {
        try {
            while ((length = is.read(buffer)) > 0 && isRunning) {
                os.write(buffer, 0, length);
            }
            is.close();
            os.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void interruptThread() {

        isRunning = false;
    }
}

