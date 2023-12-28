package com.zergatstage.seminars.s04.model;

import lombok.Getter;

@Getter
public class Person {
    private final String name;
    private final int id;
    private static int counter;
    public Person(String name){
        this.name = name;
        this.id = ++counter;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
