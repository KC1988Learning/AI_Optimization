package org.algorithm.tabuSearch;

import java.util.List;

public class TabuSearch {

    private State[][] states;
    private TabuList tabuList;
    private NeighborSolutionHandler neighborSolutionHandler;

    public TabuSearch(State[][] states){
        this.states = states;
        this.tabuList = new TabuList();
        this.neighborSolutionHandler = new NeighborSolutionHandler();
    }

    public State solve(State initialSolution){
        State bestState = initialSolution;
        State currentState = initialSolution;

        int iterationCounter = 0;

        // make a pre-defined number of iterations
        while (iterationCounter < Constants.NUM_ITERATIONS){

            // get all the neighbor states of the current state
            List<State> candidateNeighbors = currentState.getNeighbors();

            // get the tabu list
            List<State> solutionsTabu = tabuList.getTabuItems();

            // get the best neighbor (lowest f(x) value) and make sure it is
            // not in the tabu list
            State bestNeighborFound = neighborSolutionHandler.getBestNeighbor(
                    states, candidateNeighbors, solutionsTabu);

            // looking for a minimum in this case
            if (bestNeighborFound.getZ() < bestState.getZ()){
                bestState = bestNeighborFound;
            }

            // add current state to the tabu list
            tabuList.add(currentState);

            // hop to the next state
            currentState = bestNeighborFound;

            iterationCounter++;
        }

        // return solution of the algorithm
        return bestState;
    }
}
