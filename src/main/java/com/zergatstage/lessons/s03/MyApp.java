package com.zergatstage.lessons.s03;

import java.io.DataInputStream;

public class MyApp {
    public static void main(String[] args) {
        String valueT = "Some T";

        DataInputStream valueV = new DataInputStream(System.in);

        int valueK = 5;

        //create megaBox
        MyGenericsClass<String, DataInputStream, Integer> newBox =
                new MyGenericsClass<String, DataInputStream, Integer>(valueT, valueV, valueK);
        newBox.print();
        System.out.println("==================== task 02 =====================");
        //task02

        MyList<Integer> arr = new MyList<>(4);
        for (int i = 0; i < 5; i++) {
            arr.add(i);
        }
        System.out.println("After creation and filling: " + arr.getClass().getName() + ": length = " + arr.getSize());
        MyListIterator<Integer> myListMyListIterator = new MyListIterator<>(arr);
        while (myListMyListIterator.hasNext()){
            System.out.println(myListMyListIterator.next() + " ");
        }

        // task04
        System.out.println("==================== task 04 =====================");
        Workplace<Person> w = new Workplace<>(new Worker(), new Worker(), new IdleWorker());
        Club<Person> c = new Club<>(new Worker(), new Worker(), new IdleWorker());
        w.work();
        c.chill();
    }
}
