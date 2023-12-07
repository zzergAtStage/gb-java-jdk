package com.zergatstage.seminars.s03;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class MyListTest {
    int[] arr = new int[6];
    MyList<Object> myList;

    int capacity;
    @BeforeEach
    void setUp() {
        arr = new int[]{0, 1, 2, 3, 4, 5};
        capacity = 6;
        myList = new MyList<>(capacity);
    }

    @AfterEach
    void tearDown() {
        arr =null;
        myList = null;

    }

    @Test
    public void testMyListConstructor() {
        // Create an instance of the MyList class with the given capacity
        MyList<Object> myListTest = new MyList<>(capacity);
        assertEquals(capacity, myListTest.getCapacity());
        assertNotNull(myListTest);
        assertEquals(0, myListTest.getSize());
    }

    @Test
    void add() {
        myList.add(arr[1]);
        assertEquals(1, myList.getSize());
    }

    @Test
    void get() {
        myList.add(arr[2]);
        assertEquals(2, myList.get(0));
    }

    @Test
    void iterator() {
        myList.add(arr[0]);
        myList.add(arr[1]);
        assertTrue(myList.iterator.hasNext());
    }

    @Test
    void forEach() {
    }

    @Test
    void spliterator() {
    }

    @Test
    void getSize() {
    }

    @Test
    void getCapacity() {
    }
}