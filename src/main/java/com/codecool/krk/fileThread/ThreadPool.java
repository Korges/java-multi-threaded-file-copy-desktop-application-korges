package com.codecool.krk.fileThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ThreadPool {


    static ExecutorService executor = Executors.newFixedThreadPool(2);


    public static void createThreadPool(SingleCopyThread thread) {

            Runnable worker = thread;
            executor.execute(worker);
    }
}

