package org.algorithm.TSPwithGA;

import java.util.*;

public class TSPSearch {
    private int generationSize;
    private int genomeSize;
    private int numberOfCities;
    private int reproductionSize;
    private int maxIterations;
    private float mutationRate;
    private int tournamentSize;
    private SelectType selectType;
    private int[][] travelPrices;
    private int startingCity;
    private int targetFitness;

    public TSPSearch(int numberOfCities,
                     SelectType selectType,
                     int[][] travelPrices,
                     int startingCity,
                     int targetFitness){
        this.numberOfCities = numberOfCities;
        this.selectType = selectType;
        this.travelPrices = travelPrices;
        this.startingCity = startingCity;
        this.targetFitness = targetFitness;

        generationSize = 5000;
        reproductionSize = 200;
        maxIterations = 1000;
        mutationRate = 0.1f;
        tournamentSize = 40;
    }

    /**
     * Implement roulette selection for cross-over
     */
    public SalesmanGenome rouletteSelection(List<SalesmanGenome> population){
        // compute total fitness of the population
        int totalFitness = 0;
        for (SalesmanGenome genome : population){
            totalFitness += genome.getFitness();
        }

        Random random = new Random();
        // get a random integer between 0 and totalFitness
        int selectedValue = random.nextInt(totalFitness);

        // reciprocal of the selected random number
        float recValue = (float) 1/selectedValue;

        float currentSum = 0;
        for (SalesmanGenome genome : population){
            currentSum += (float) 1/genome.getFitness();
            if (currentSum >= recValue){
                return genome;
            }
        }
        int selectRandom = random.nextInt(generationSize);
        return population.get(selectRandom);
    }

    /**
     * Implement tournament selection for cross-over
     */
    public SalesmanGenome tournamentSelection(
            List<SalesmanGenome> population
    ){
        List<SalesmanGenome> selected = pickNRandomElements(population,
                tournamentSize);
        return Collections.min(selected);
    }

    /**
     * Pick n random elements from the list
     */
    private static <T> List<T> pickNRandomElements(List<T> list, int n){
        Random r = new Random();
        int length = list.size();

        if (length < n) return null;

        Collections.shuffle(list); // shuffle the list
        return list.subList(0, n-1);
    }

}

