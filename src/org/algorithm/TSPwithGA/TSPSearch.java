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
        this.genomeSize = numberOfCities-1;
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
     * Intialize a population of genome
     */
    public List<SalesmanGenome> initialPopulation(){
        List<SalesmanGenome> population = new ArrayList<>();
        for (int i=0; i < generationSize; i++){
            population.add(new SalesmanGenome(numberOfCities,
                    travelPrices, startingCity));
        }
        return population;
    }

    /**
     * Select genomes for cross-over based on the selection type
     */
    public List<SalesmanGenome> selection(List<SalesmanGenome> population){
        List<SalesmanGenome> selected = new ArrayList<>();
        SalesmanGenome winner;

        for (int i=0 ; i<reproductionSize ; i++){
            if (selectType==SelectType.ROULETTE){
                selected.add(rouletteSelection(population));
            }
            else if (selectType==SelectType.TOURNAMENT){
                selected.add(tournamentSelection(population));
            }
        }

        return  selected;
    }


    /**
     * Implement roulette selection for cross-over
     */
    public SalesmanGenome rouletteSelection(List<SalesmanGenome> population){
        // compute total fitness of the population
        float totalRecFitness = 0;
        for (SalesmanGenome genome : population){
            totalRecFitness += (float) 1/genome.getFitness();
        }

        // get a random value between 0 and totalRecFitness
        // because we are doing minimization, we need to use reciprocal
        // as the probability to its fitness - the smaller the fitness
        // the higher the probability
        Random random = new Random();
        float selectedValue = random.nextFloat()*totalRecFitness;

        float currentSum = 0;
        for (SalesmanGenome genome : population){
            currentSum += (float) 1/genome.getFitness();
            if (currentSum >= selectedValue){
                return genome;
            }
        }

        // In case genome is not returned above, return a
        // random genome
        return population.get(random.nextInt(population.size()));
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
    public static <T> List<T> pickNRandomElements(List<T> list, int n){
        Random r = new Random();
        int length = list.size();

        if (length < n) return null;

        Collections.shuffle(list); // shuffle the list
        return list.subList(0, n);
    }

    /**
     * Cross-over
     */
    public List<SalesmanGenome> crossover(List<SalesmanGenome> parents){
        // Get crossover point
        Random random  = new Random();
        int crosspoint = random.nextInt(genomeSize);
        List <SalesmanGenome> children = new ArrayList<>();

        // Copy parent genomes in case they are chosen to participate
        // in crossover multiple times
        List<Integer> parentGenome1 = new ArrayList<>(parents.get(0).getGenome());
        List<Integer> parentGenome2 = new ArrayList<>(parents.get(1).getGenome());

        // Creating child 1
        for (int i = 0; i < crosspoint; i++){
            int newVal;
            newVal = parentGenome2.get(i);
            Collections.swap(parentGenome1, i, parentGenome1.indexOf(newVal));
        }

        children.add(new SalesmanGenome(parentGenome1, numberOfCities, travelPrices,
                startingCity));

        // Creating child 2
        for (int i=0; i < crosspoint; i++){
            int newVal;
            newVal = parentGenome1.get(i);
            Collections.swap(parentGenome2, i, parentGenome2.indexOf(newVal));
        }

        children.add(new SalesmanGenome(parentGenome2, numberOfCities, travelPrices,
                    startingCity));

        return children;
    }

    /**
     * Mutation of a genome
     * If a probability check was passed, we mutate by swapping two cities
     * Otherwise, return the original genome.
     */
    public SalesmanGenome mutate(SalesmanGenome salesman){
        Random random = new Random();
        float mutate = random.nextFloat();
        if (mutate < mutationRate){
            List<Integer> genome = salesman.getGenome();
            Collections.swap(genome, random.nextInt(genomeSize),
                    random.nextInt(genomeSize));
            return new SalesmanGenome(genome, numberOfCities, travelPrices,
                    startingCity);
        }
        return salesman;
    }

    /**
     * Make a new generation of children
     */
    public List<SalesmanGenome> createGeneration(List<SalesmanGenome> population){
        List<SalesmanGenome> generation = new ArrayList<>();
        int currentGenerationSize = 0;
        while (currentGenerationSize < generationSize){
            List<SalesmanGenome> parents = pickNRandomElements(population, 2);
            List<SalesmanGenome> children = crossover(parents);
            children.set(0, mutate(children.get(0)));
            children.set(1, mutate(children.get(1)));
            generation.addAll(children);
            currentGenerationSize += 2;
        }
        return generation;
    }

    /**
     * Termination
     */
    public SalesmanGenome optimize(){
        List<SalesmanGenome> population = initialPopulation();
        SalesmanGenome globalBestGenome = population.get(0);
        for (int i=0; i<maxIterations; i++){
            List<SalesmanGenome> selected = selection(population);
            population = createGeneration(selected);
            globalBestGenome = Collections.min(population);
            if (globalBestGenome.getFitness() < targetFitness) break;
        }
        return globalBestGenome;
    }

}

