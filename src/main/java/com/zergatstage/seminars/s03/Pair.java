package com.zergatstage.seminars.s03;

/**
 * Напишите обобщенный класс Pair, который представляет собой пару значений разного типа.
 * Класс должен иметь методы getFirst(), getSecond() для получения значений пары,
 * а также переопределение метода toString()
 * , возвращающее строковое представление пары.
 */
public class Pair<T1, T2> {
    private T1 first;
    private T2 second;

    public Pair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }

    public T1 getFirst() {
        return first;
    }

    public T2 getSecond() {
        return second;
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }

    public static void main(String[] args) {
        Pair<Integer, int[]> pair1 = new Pair<>(22, new int[]{1,2,3});
        System.out.println(pair1.getFirst()); // 1
        System.out.println(pair1.getSecond()); // "one"
        System.out.println(pair1); // "(1, one)"
    }
}