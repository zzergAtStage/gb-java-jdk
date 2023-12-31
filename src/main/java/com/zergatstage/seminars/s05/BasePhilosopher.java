package com.zergatstage.seminars.s05;

import java.util.concurrent.atomic.AtomicInteger;

public class BasePhilosopher {

    protected final BaseFork leftFork;
    protected final BaseFork rightFork;

    public AtomicInteger eatCount = new AtomicInteger();

    protected BasePhilosopher(BaseFork leftFork, BaseFork rightFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    protected void think() throws InterruptedException {
        long t = System.nanoTime();
        if (AppProperties.DEBUG) {
            System.out.println(t + ": " + Thread.currentThread().getName() + " is thinking");
        }
        if (AppProperties.MAX_WAIT_MS > 0) {
            Thread.sleep(getRandomInt());
        }
    }

    protected void eat() throws InterruptedException {
        long t = System.nanoTime();
        if (AppProperties.DEBUG) {
            System.out.println(t + ": " + Thread.currentThread().getName() + " is eating");
        }
        if (AppProperties.MAX_WAIT_MS > 0) {
            Thread.sleep(getRandomInt());
        }
        eatCount.incrementAndGet();
    }

    private int getRandomInt() {
        return (int) (Math.random() * AppProperties.MAX_WAIT_MS);
    }
}
