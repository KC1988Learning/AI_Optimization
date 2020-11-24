package org.algorithm.SimAnneal;

public class App {
    public static void main(String[] args){
        for (int i = 0; i < 10; i++){
            City city = new City();
            Repository.addCity(city);
        }

        SimulatedAnnealing annealing = new SimulatedAnnealing();
        annealing.simulation();

        System.out.println("Distance of final approximated solution's is: " + annealing.getBest().getDistance());
        System.out.println("Tour: " + annealing.getBest());
    }
}
