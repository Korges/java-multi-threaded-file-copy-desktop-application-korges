package com.codecool.krk.multithreadedfilecopydesktopapplication;

import com.codecool.krk.multithreadedfilecopydesktopapplication.fileStream.CustomFileStream;
import com.codecool.krk.multithreadedfilecopydesktopapplication.fileThread.SingleCopyThread;
import com.codecool.krk.multithreadedfilecopydesktopapplication.fileThread.ThreadPool;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class MultiThreadedFileCopyDesktopApplication {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ThreadPool pool = new ThreadPool();
        String method;

            do {
                method = sc.nextLine().toUpperCase();
                try {
                    if (method.equals("COPY")) {

                        SingleCopyThread singleThread = new SingleCopyThread(createStream(sc));
                        pool.createThreadPool(singleThread);

                    } else if (method.equals("STOP")) {

                        String threadToStop = sc.nextLine();
                        SingleCopyThread singleThread = SingleCopyThread.getSingleThreadByName(threadToStop);
                        singleThread.interruptThread();
                    }
                } catch (NullPointerException e) {
                    System.out.println("File not found");
                } catch (FileNotFoundException e) {
                    System.out.println("Wrong source path");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } while (!method.equals("EXIT"));

    }


    private static CustomFileStream createStream(Scanner sc) throws IOException {
        String source = sc.nextLine();
        String[] splittedSource = source.split("/");
        String destination = sc.nextLine() + "/" + splittedSource[splittedSource.length - 1];
        return new CustomFileStream(source, destination);
    }
}