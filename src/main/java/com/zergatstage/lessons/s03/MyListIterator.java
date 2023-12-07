package com.zergatstage.lessons.s03;

import java.util.NoSuchElementException;

public class MyListIterator<E>{
    private final MyList<E> array; //итерируемый массив
    private int index = 0;//текущий элемент итератора
    public MyListIterator(MyList<E> array) {
        this.array = array;
    }
    public boolean hasNext(){
        return index < array.getCapacity();
    }

    public E next(){
        if (!hasNext()) {
            throw new NoSuchElementException("Reached the last element of collection");
        }
        return array.get(index++);
    }
}
