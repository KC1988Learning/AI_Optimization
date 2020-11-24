package org.algorithm.PSO;

public class Particle {
    private double[] position; // r_i -> (x, y)
    private double[] velocity; // v_i -> (vx, vy)
    private double[] bestPosition;

    public Particle(double[] position, double[] velocity, double[] bestPosition){
        this.position = new double[Constants.NUM_DIMENSIONS];
        this.velocity = new double[Constants.NUM_DIMENSIONS];
        this.bestPosition = new double[Constants.NUM_DIMENSIONS];


    }


}
