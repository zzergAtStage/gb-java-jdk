package com.zergatstage.seminars.s03;

import lombok.Getter;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * MyList custom collection for homework S03
 *
 * @param <E> Iterable array class with its own iterator
 */
public class MyList<E> implements Iterable<E> {

    //internal class iterator
    class MyListIterator<E> implements Iterator<E> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < count;
        }

        @Override
        public E next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return (E) array[index++];
        }
    }
    MyList.MyListIterator iterator;
    private E[] array; // массив для хранения элементов
    int count;
    @Getter
    private int size; // текущий размер списка

    @Getter
    private int capacity; // максимальная вместимость массива

    // конструктор, который принимает начальную вместимость массива
    public MyList(int capacity) {
        this.capacity = capacity;
        array = (E[]) new Object[capacity]; // создаем массив типа Object и приводим его к типу E[]
        size = 0; // изначально список пуст
        this.iterator = new MyListIterator<E>();
    }

    public void add(E element) {
        if (size == capacity) { // если массив полон
            capacity *= 2; // увеличиваем вместимость в два раза
            E[] newArray = (E[]) new Object[capacity]; // создаем новый массив большего размера
            for (int i = 0; i < size; i++) { // копируем элементы из старого массива в новый
                newArray[i] = array[i];
            }
            array = newArray; // заменяем старый массив новым
        }
        array[size] = element; // добавляем элемент в конец списка
        size++; // увеличиваем размер списка
    }


    public E get(int index) {
        return array[index];
    }


    @Override
    public Iterator<E> iterator() {
        return iterator;
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<E> spliterator() {
        return Iterable.super.spliterator();
    }
}

