package com.zergatstage.seminars.s05;

public class BaseFork {
    private int id;

    public BaseFork(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void take() {
        long t = System.nanoTime();
        if (AppProperties.DEBUG) {
            System.out.println(t + ": Fork " + id + " is taken");
        }
    }

    public void put() {
        long t = System.nanoTime();
        if (AppProperties.DEBUG) {
            System.out.println(t + ": Fork " + id + " is put");
        }
    }
}
