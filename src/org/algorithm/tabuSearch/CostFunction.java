package org.algorithm.tabuSearch;

public class CostFunction {

    // define the function that we want to optimize
    public static double f(double x, double y){
        return Math.exp(-x*x -y*y)*Math.sin(x);
    }

}
