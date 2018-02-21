package com.codecool.krk.multithreadedfilecopydesktopapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


@SpringBootApplication
public class MultiThreadedFileCopyDesktopApplication {

	public static void main(String[] args) {

		SpringApplication.run(MultiThreadedFileCopyDesktopApplication.class, args);


		String source = "copy-from/ave_2011.mp4";
		String[] splittedName = source.split("/");
		String destination = "copy-to/" + splittedName[splittedName.length-1];
		SingleCopyThread copyFile = null;
		try {
			copyFile = new SingleCopyThread(source, destination);
			Thread thread = new Thread(copyFile);
			thread.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scanner sc = new Scanner(System.in);
		String input = null;
		do {
			input = sc.nextLine();
			if(input.equals("stop")) {
				copyFile.interruptThread();
			}
		} while (input.equals("exit"));
	}
}