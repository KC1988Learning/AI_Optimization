package org.algorithm.SimAnneal;

import java.util.Random;

public class City {
    private int x;
    private int y;

    public City(){
        Random random = new Random();
        this.x = random.nextInt(100);
        this.y = random.nextInt(100);
    }

    public City(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString(){
        return "x: " + this.x + " ; y:" + this.y;
    }

    public double distanceTo(City city){
        return Math.sqrt(Math.pow(this.x - city.getX(),2) +
                         Math.pow(this.y - city.getY(),2));
    }
}
