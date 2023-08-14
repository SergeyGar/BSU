package com.company;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        try{
            Scanner scanner = new Scanner(System.in);
            double firstElement = scanner.nextDouble();
            double koef = scanner.nextDouble();
            int n = scanner.nextInt();
            int number = scanner.nextInt();
            Series series1;
            if(number == 1)
                series1 = new Liner(firstElement, koef, n);
            else
                series1 = new Exponential(firstElement, koef, n);
            series1.saveSeriesInFile();
            scanner.close();
        }
        catch (IOException e){
            System.out.println("File not found!");
        }
    }
}
