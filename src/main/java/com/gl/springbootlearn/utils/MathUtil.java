package com.gl.springbootlearn.utils;

public class MathUtil {

    private static final Double MONEY_RANGE = 0.01;

    public static Boolean equals(Double d1, Double d2) {
        double result = Math.abs(d1 - d2);
        return result < MONEY_RANGE;
    }
}
