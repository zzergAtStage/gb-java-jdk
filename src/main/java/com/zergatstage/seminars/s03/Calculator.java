package com.zergatstage.seminars.s03;

/**
 * 2. Написать класс Калькулятор (необобщенный), который содержит обобщенные статические методы:
 * sum(), multiply(), divide(), subtract().
 * Параметры этих методов – два
 * числа разного типа, над которыми должна быть произведена операция.
 */
public class Calculator {
    public static <T extends Number, E extends Number> double sum(T parameterFirst, E parameterSecond){
        return parameterFirst.doubleValue() + parameterSecond.doubleValue();
    }
    public static <T extends Number, E extends Number> double subtraction(T parameterFirst, E parameterSecond){
        return parameterFirst.doubleValue() - parameterSecond.doubleValue();
    }

    public static <T extends Number, E extends Number> double divide(T parameterFirst, E parameterSecond) throws IllegalArgumentException{
        if (parameterSecond == null || parameterSecond.doubleValue()==0) throw new IllegalArgumentException("Zero dividing!");
        return parameterFirst.doubleValue() / parameterSecond.doubleValue();
    }
    public static <T extends Number, E extends Number> double multiply(T parameterFirst, E parameterSecond){
        return parameterFirst.doubleValue() * parameterSecond.doubleValue();
    }


}
