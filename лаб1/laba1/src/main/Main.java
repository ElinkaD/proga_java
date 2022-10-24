package main;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        final double e = Math.E;
//  объявление одномерного массива a типа int и его заполнение
         int [] a = {24,22,20,18,16,14,12,10,8,6};
//  объявление одномерного массива x типа double и его заполнение
         double [] x = new double [12];
         for (int i = 0; i < x.length; i++) {
             x[i] = Math.random() * 21 - 9;
         }
//  создание двумерного массива a и его заполение
         double [][] b = new double [10][12];
         for (int i = 0; i < 10; i++){
             for (int j = 0; j < 12; j++) {
                 if (a[i] == 14) {
                     b[i][j] = Math.atan(Math.PI / 4 * Math.pow(e, -Math.abs(x[j])));
                 } else if (a[i] == 6 || a[i] == 8 || a[i] == 12 || a[i] == 18 || a[i] == 20) {
                     b[i][j] = Math.asin(Math.pow(Math.pow(e, -Math.abs(x[j])), 2));
                 } else {
                     b[i][j] = Math.tan(Math.atan(Math.pow(e, -Math.abs(x[j]))));
                 }
             }
         }
//         вывод полученного двумерного массива
         for (int i = 0; i < 10; i++){
             for (int j = 0; j < 12; j++){
                 System.out.printf("%5f ",b[i][j]);
             }
             System.out.println();
         }
    }
}


