package com.zergatstage.lessons.s03;

public class MyList<E> {
    private E[] array; // массив для хранения элементов

    public int getSize() {
        return size;
    }

    private int size; // текущий размер списка

    public int getCapacity() {
        return capacity;
    }

    private int capacity; // максимальная вместимость массива

    // конструктор, который принимает начальную вместимость массива
    public MyList(int capacity) {
        this.capacity = capacity;
        array = (E[]) new Object[capacity]; // создаем массив типа Object и приводим его к типу E[]
        size = 0; // изначально список пуст
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

    // метод, который удаляет и возвращает последний элемент из списка
    public E remove() {
        if (size == 0) { // если список пуст
            return null; // возвращаем null
        }
        size--; // уменьшаем размер списка
        return array[size]; // возвращаем последний элемент
    }

    public E get(int index) {
        return array[index];
    }
}

