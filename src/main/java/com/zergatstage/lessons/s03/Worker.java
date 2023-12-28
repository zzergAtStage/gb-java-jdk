package com.zergatstage.lessons.s03;

import java.sql.SQLOutput;

public class Worker implements Person {
    @Override
    public void doWork() {
        System.out.println("Worker - Do  work.");
    }

    @Override
    public void haveRest() {
        System.out.println("Worker can't chill");
    }
}
