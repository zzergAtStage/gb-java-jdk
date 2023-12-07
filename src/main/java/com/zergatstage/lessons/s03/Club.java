package com.zergatstage.lessons.s03;

public class Club <T extends Person>{
    T[] arr;

    public Club(T... arr) {
        this.arr = arr;
    }

    public void chill(){
        for(T ent: arr){
            ent.haveRest();
        }
    }
}

