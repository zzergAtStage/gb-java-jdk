package com.zergatstage.lessons.s03;

public class IdleWorker implements Person{
    @Override
    public void doWork() {
        System.out.println("Can't work, needs rest!");
    }

    @Override
    public void haveRest() {
        System.out.println("Have rest, can't work");
    }
}
