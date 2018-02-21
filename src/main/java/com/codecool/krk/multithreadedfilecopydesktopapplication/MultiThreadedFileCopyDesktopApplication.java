package com.codecool.krk.multithreadedfilecopydesktopapplication;

import com.codecool.krk.multithreadedfilecopydesktopapplication.fileStream.CustomFileStream;
import com.codecool.krk.multithreadedfilecopydesktopapplication.fileThread.SingleCopyThread;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Scanner;

@SpringBootApplication
public class MultiThreadedFileCopyDesktopApplication {

    public static void main(String[] args) {

        SpringApplication.run(MultiThreadedFileCopyDesktopApplication.class, args);

        SingleCopyThread copyFile = null;

        try {
            copyFile = new SingleCopyThread(createStream());
            System.out.println(copyFile.stream.getInputStream().available());
            Thread thread = new Thread(copyFile);
            thread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
        Scanner sc = null;
        manageInputs(sc, copyFile);
        sc.close();
    }

    private static CustomFileStream createStream() throws IOException {
        String source = "copy-from/inteliJ.tar.gz";
        String[] splittedSource = source.split("/");
        String destination = "copy-to/" + splittedSource[splittedSource.length - 1];
        return new CustomFileStream(source, destination);
    }

    private static void manageInputs(Scanner sc, SingleCopyThread copyFile) {
        sc = new Scanner(System.in);
        String input = null;
        do {
            input = sc.nextLine();
            if(input.equals("stop")) {
                copyFile.interruptThread();
            }
        } while (input.equals("exit"));
    }
}