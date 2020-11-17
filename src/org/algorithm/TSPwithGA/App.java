package org.algorithm.TSPwithGA;

import java.util.Random;

public class App {
    public static void main(String[] args){
        int numberOfCities = 3;
        int[][] travelPrices = new int[numberOfCities][numberOfCities];
        for (int i = 0; i < numberOfCities; i++){
            for (int j = 0; j < numberOfCities; j++){
                Random rand = new Random();
                if (i==j){
                    travelPrices[i][j] = 0;
                }
                else {
                    travelPrices[i][j] = rand.nextInt(100);
                    travelPrices[j][i] = travelPrices[i][j];
                }
            }
        }

        printTravelPrices(travelPrices, numberOfCities);
    }

    public static void printTravelPrices(int[][] travelPrices,
                                         int numberOfCities){
        for (int i = 0; i < numberOfCities; i++){
            for (int j = 0; j < numberOfCities; j++){
                System.out.println(travelPrices[i][j]);

            }
        }
    }
}
