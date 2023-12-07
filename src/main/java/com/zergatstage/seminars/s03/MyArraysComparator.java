package com.zergatstage.seminars.s03;

public class MyArraysComparator {
    public static <T,U> boolean  compareArraySizeAndEntity(T[] firstArray, U[] secondArray){
        if (firstArray.length == 0 || secondArray.length == 0) throw new IllegalArgumentException("Null size arrays");
        return (firstArray.length == secondArray.length) && (firstArray[0].getClass().equals(secondArray[0].getClass()));
    }
}
