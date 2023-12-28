package com.zergatstage.seminars.s03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MyArraysComparatorTest {

    @Test
    @DisplayName("Test the same size and different data types")
    void compareArraySizeAndEntity_TestFalse() {
        Integer[] ints = new Integer[]{1,2,3};
        Double[] doubles = new Double[]{0.1,0.5,2.1};
        assertFalse(MyArraysComparator.compareArraySizeAndEntity(ints,doubles));
    }
    @Test
    @DisplayName("Test the different size and the same data types")
    void compareArraySizeAndEntity_TestFalseSize() {
        Integer[] ints = new Integer[]{1,2,3};
        Integer[] doubles = new Integer[]{2,4,8,4};
        assertFalse(MyArraysComparator.compareArraySizeAndEntity(ints,doubles));
    }

    @Test
    @DisplayName("Test the same size and the same data types (true expected)")
    void compareArraySizeAndEntity_TestTrue() {
        Integer[] ints = new Integer[]{1,2,3};
        Integer[] doubles = new Integer[]{2,4,8};
        assertTrue(MyArraysComparator.compareArraySizeAndEntity(ints,doubles));
    }

    @Test
    void compareArraySizeAndEntity_TestThrows(){
        Integer[] ints = new Integer[]{};
        Integer[] doubles = new Integer[]{2,4,8};
        RuntimeException actualException =
        assertThrows(IllegalArgumentException.class,
                () -> MyArraysComparator.compareArraySizeAndEntity(ints,doubles));
        assertEquals(actualException.getClass(), IllegalArgumentException.class, "Expected " + IllegalArgumentException.class.getName());
    }

}