package com.zergatstage.seminars.s05.splitResource;

import com.zergatstage.seminars.s05.BaseFork;
import com.zergatstage.seminars.s05.BaseRunner;
import com.zergatstage.seminars.s05.AppProperties;

public class Runner extends BaseRunner {
    public static void main(String[] args) {
        System.out.println(System.nanoTime() + ": Dinner is started");

        BaseFork[] forks = new BaseFork[AppProperties.PHILOSOPHERS_COUNT];
        for (int i = 0; i < forks.length; i++) {
            forks[i] = new BaseFork(i + 1);
        }

        Philosopher[] philosophers = new Philosopher[AppProperties.PHILOSOPHERS_COUNT];
        for (int i = 0; i < philosophers.length; i++) {
            BaseFork leftFork = forks[i];
            BaseFork rightFork = forks[(i + 1) % forks.length];

            if (leftFork.getId() < rightFork.getId()) {
                philosophers[i] = new Philosopher(leftFork, rightFork);
            } else {
                philosophers[i] = new Philosopher(rightFork, leftFork);
            }

            threads[i] = new Thread(philosophers[i], "Philosopher " + (i + 1));
            threads[i].start();
        }

        waitMillis(AppProperties.DINNER_DURATION_IN_MS);
        stopThreads(threads);

        System.out.println(System.nanoTime() + ": Dinner is finished");

        printStats(philosophers);
    }

    protected static void printStats(Philosopher[] philosophers) {
        int totalCount = 0;
        for (Philosopher philosopher : philosophers) {
            totalCount += philosopher.eatCount.intValue();
        }
        if (totalCount > 0) {
            System.out.println("Eating stats:");
            System.out.println("Total: " + totalCount);
            for (int i = 0; i < philosophers.length; i++) {
                System.out.println(
                        "Philosopher " + (i + 1) + ": " + (100.0 * philosophers[i].eatCount.intValue() / totalCount) + "%");
            }
        }
    }
}
