package com.codecool.krk.multithreadedfilecopydesktopapplication.fileThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ThreadPool {


    ExecutorService executor = Executors.newFixedThreadPool(2);


    public void createThreadPool(SingleCopyThread thread) {

            Runnable worker = thread;
            executor.execute(worker);
    }
}

