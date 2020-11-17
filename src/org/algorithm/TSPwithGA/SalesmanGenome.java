package org.algorithm.TSPwithGA;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SalesmanGenome implements Comparable{

    List<Integer> genome;
    int[][] travelPrices;
    int startingCity;
    int numberOfCities = 0;
    int fitness;

    public SalesmanGenome(int numberOfCities, int[][] travelPrices,
                          int StartingCity){
        this.numberOfCities = numberOfCities;
        this.travelPrices = travelPrices;
        this.startingCity = StartingCity;
        this.genome = this.randomSalesman();
        this.fitness = this.calculateFitness();
    }

    public SalesmanGenome(List<Integer> permutatonOfCities,
                          int numberOfCities,
                          int[][] travelPrices,
                          int startingCity){
        this.genome = permutatonOfCities;
        this.travelPrices = travelPrices;
        this.numberOfCities = numberOfCities;
        this.startingCity = startingCity;
        this.fitness = calculateFitness();
    }

    public List<Integer> getGenome() {
        return genome;
    }

    public void setGenome(List<Integer> genome) {
        this.genome = genome;
    }

    public int[][] getTravelPrices() {
        return travelPrices;
    }

    public void setTravelPrices(int[][] travelPrices) {
        this.travelPrices = travelPrices;
    }

    public int getStartingCity() {
        return startingCity;
    }

    public void setStartingCity(int startingCity) {
        this.startingCity = startingCity;
    }

    public int getNumberOfCities() {
        return numberOfCities;
    }

    public void setNumberOfCities(int numberOfCities) {
        this.numberOfCities = numberOfCities;
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    // compute fitness value of the genome
    public int calculateFitness(){
        int fitness = 0;
        int currentCity = this.startingCity;
        for (int gene : this.genome){
            fitness += this.travelPrices[currentCity][gene];
            currentCity = gene;
        }

        // add the cost of going back to the starting city to complete
        // the circle. The genome excludes the starting city, and indexing
        // starts at 0, which is why we subtract 2
        fitness += travelPrices[genome.get(numberOfCities-2)][startingCity];

        return fitness;
    }

    // randomly generate a genome
    private List<Integer> randomSalesman(){
        List<Integer> result = new ArrayList<Integer>();
        for (int i = 0 ; i <numberOfCities; i++){
            if (i != this.startingCity){
                result.add(i);
            }
        }
        Collections.shuffle(result);
        return result;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Path: ");
        sb.append(startingCity);
        for (int gene : genome){
            sb.append(" ");
            sb.append(gene);
        }
        sb.append(" ");
        sb.append(startingCity);
        sb.append("\nDistance: ");
        sb.append(this.fitness);
        return sb.toString();
    }

    @Override
    public int compareTo(Object o){
        SalesmanGenome genome = (SalesmanGenome) o;
        if (this.fitness > genome.getFitness()){
            return 1;
        } else if (this.fitness < genome.getFitness()) {
            return -1;
        } else {
            return 0;
        }
    }
}
