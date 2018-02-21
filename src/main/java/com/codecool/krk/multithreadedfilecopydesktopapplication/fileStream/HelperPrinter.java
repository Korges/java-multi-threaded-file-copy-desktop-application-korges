package com.codecool.krk.multithreadedfilecopydesktopapplication.fileStream;

import java.util.Collections;

public class HelperPrinter {

    public static void main(String[] args) throws InterruptedException {
        String toPrint = "[" + String.join("", Collections.nCopies(101, " ")) + "]";
        int current = 0;
        int percentage;
        StringBuilder str = new StringBuilder(toPrint).append("   %");
        System.out.println(str.toString());
        for (int i=0; i<1000; i++) {
            clearConsole();
            percentage = 100 * ++current / 1000;
            showStatusBar(str, percentage);
            Thread.sleep(5);
        }
    }

    private static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void showStatusBar(StringBuilder statusBar, int percentage) {
        statusBar.replace(percentage+1, percentage+2, "\u25A0")
                .replace(103, 106, String.format("%3s", percentage));
        System.out.println(statusBar);
    }
}

