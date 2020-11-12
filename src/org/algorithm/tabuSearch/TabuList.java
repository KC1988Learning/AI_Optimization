package org.algorithm.tabuSearch;

import org.apache.commons.collections4.queue.CircularFifoQueue;

import java.util.Queue;

public class TabuList {

    private Queue<State> tabuList;

    public TabuList(){
        this.tabuList = new CircularFifoQueue<>();
    }
}
