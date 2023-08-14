package com.company;
//import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;

public abstract class Series {
    protected double koef;
    protected double firstElement;
    protected int n;
    private String getPath(){
        return "C:\\download\\JavaProjects\\FourthJavaLab\\output.txt";
    }

    public Series(double firstElement, double koef, int n){
        this.koef = koef;
        this.firstElement = firstElement;
        this.n = n;
    }
    public abstract double calculateJElementOfSeries(int j);

    public double calculateSum(int n){
        double sum = 0;
        for(int i = 0; i < n; i++){
            sum += calculateJElementOfSeries(i);
        }
        return sum;
    }
    public String toString(int n){
        StringBuffer str = new StringBuffer();
        for(int i = 1; i <= n; i++){
            str.append(calculateJElementOfSeries(i) + " ");
        }
        return str.toString();
    }

    public void saveSeriesInFile() throws IOException {
        FileWriter filewriter = new FileWriter(getPath());
        filewriter.write(toString(n));
        filewriter.write("Sum = " + String.valueOf(calculateSum(n)));
        filewriter.close();
    }
}

