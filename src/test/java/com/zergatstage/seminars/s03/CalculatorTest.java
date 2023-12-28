package com.zergatstage.seminars.s03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void sumTest() {
        int first = 2;
        double second = 2.0;
        assertEquals(4.0, Calculator.sum(first,second));
    }

    @Test
    void divideTest_toZero() {
        int first = 2;
        double second = 0;
        assertThrows(IllegalArgumentException.class, () -> Calculator.divide(first,second));
    }

    @Test
    void divideTest(){
        float first = 2.0f;
        double second = 2.0;
        assertEquals(1, Calculator.divide(first,second),0.000000001);
    }
    @Test
    void subtractionTest() {
        float first = 2.0f;
        int second = 2;
        assertEquals(0, Calculator.subtraction(first,second));
    }

    @Test
    void multiplyTest() {
        float first = 2.0f;
        int second = 2;
        assertEquals(4, Calculator.multiply(first,second));
    }
}