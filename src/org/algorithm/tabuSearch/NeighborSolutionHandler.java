package org.algorithm.tabuSearch;

import java.util.List;

public class NeighborSolutionHandler {

    /**
     * In every iteration we have to find the next state.
     * We have to consider the neighbors but we also have
     * to avoid states in the tabu list (forbidden states)
     */

    public State getBestNeighbor(State[][] states,
                                 List<State> neighborStates,
                                 List<State> tabuStates){

        // Remove all the items present in tabuStates from neighborStates lists
        neighborStates.removeAll(tabuStates);

        // if all the neighbor states are in the tabu list, go to the
        // middle state
        if (neighborStates.size()==0) return states[100][100];

        // simple linear search to find the neighbor with smallest f(x) value
        State bestSolution = neighborStates.get(0);

        for (int i=1 ; i<neighborStates.size(); i++){
            if (neighborStates.get(i).getZ() < bestSolution.getZ()) {
                bestSolution = neighborStates.get(i);
            }
        }

        System.out.println("Best solution is: " + bestSolution);

        return bestSolution;
    }
}
