package org.algorithm.tabuSearch;

import java.util.ArrayList;
import java.util.List;

public class State {
    private double x;
    private double y;
    private double z;
    private List<State> neighbors;

    public State(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
        this.neighbors = new ArrayList<>();
    }

    public
}
