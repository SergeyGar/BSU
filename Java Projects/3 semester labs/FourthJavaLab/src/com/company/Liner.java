package com.company;

public class Liner extends Series {
    public Liner(double firstElement, double koef, int n){
        super(firstElement, koef, n);
    }

    @Override
    public double calculateJElementOfSeries(int j) {
        return firstElement + koef * (j - 1);
    }

    @Override
    public double calculateSum(int n){
        return (2 * firstElement + koef * (n  - 1)) / 2 * n;
    }
}
