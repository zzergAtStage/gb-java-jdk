package com.zergatstage.seminars.s05;

public class BaseRunner {
    protected static Thread[] threads = new Thread[AppProperties.PHILOSOPHERS_COUNT];

    protected static void waitMillis(long millis) {
        if (millis <= 0) {
            return;
        }
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    protected static void stopThreads(Thread[] threads) {
        for (Thread thread : threads) {
            if (!thread.isInterrupted()) {
                thread.interrupt();
            }
        }
        waitMillis(100);
    }
}
