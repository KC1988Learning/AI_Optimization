package org.algorithm.tabuSearch;

import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.collections4.queue.CircularFifoQueue;

import java.util.List;
import java.util.Queue;

public class TabuList {

    private Queue<State> tabuList;

    public TabuList(){
        this.tabuList = new CircularFifoQueue<>(Constants.TABU_TENURE);
    }

    // The next few methods define the possible operations on the
    // Tabu list.

    // Adding state to the Tabu List
    public void add(State solution){
        this.tabuList.add(solution);
    }

    // Check if a state is already in the Tabu list
    public boolean contains(State solution){
        return this.tabuList.contains(solution);
    }


    public List<State> getTabuItems(){
        return IteratorUtils.toList(this.tabuList.iterator());
    }
}
