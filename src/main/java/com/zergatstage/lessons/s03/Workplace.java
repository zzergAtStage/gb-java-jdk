package com.zergatstage.lessons.s03;

public class Workplace<T extends Person>{
    T[]    arr;
    public Workplace(T... people) {
        arr = people;
    }
    public void work(){
        for(T ent: arr){
            ent.doWork();
        }
    }

}
