package com.zergatstage.lessons.s03;

import java.io.DataInput;
import java.io.InputStream;
import java.io.Serializable;

public class MyGenericsClass<T extends Comparable, V extends InputStream & DataInput, K extends Number> {
    T t;
    V v;
    K k;

    public T getT() {
        return t;
    }

    public V getV() {
        return v;
    }

    public K getK() {
        return k;
    }

    //constructor
    public MyGenericsClass(T t, V v, K k) {
        this.t = t;
        this.v = v;
        this.k = k;
    }
    void print(){
        System.out.printf("t = %s, v = %s, k = %s",
                t.getClass().getName(),
                v.getClass().getName(),
                k.getClass().getName());
    }
}
