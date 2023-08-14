package com.company;

public class Exponential extends Series {
    public Exponential(double firstElement, double koef, int n){
        super(firstElement, koef, n);
    }

    @Override
    public double calculateJElementOfSeries(int j) {
        double element = firstElement;
        for(int i = 1; i < j; i++){
            element *= koef;
        }
        return element;
    }
    @Override
    public double calculateSum(int n){
        if(koef == 1)
            return firstElement * n;
        double koefInNDegree = Math.pow(koef, n);
        return firstElement * (1 - koefInNDegree) / (1 - koef);
    }
}
