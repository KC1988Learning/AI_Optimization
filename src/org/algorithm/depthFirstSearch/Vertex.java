package org.algorithm.depthFirstSearch;

import java.util.ArrayList;
import java.util.List;

public class Vertex {

    private String name;
    private boolean visited;
    private List<Vertex> neighborList;

    public Vertex(String name){
        this.name = name;
        this.neighborList = new ArrayList<Vertex>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public List<Vertex> getNeighborList() {
        return neighborList;
    }

    public void addNeighbor(Vertex vertex) {
        this.neighborList.add(vertex);
    }

    @Override
    public String toString(){
        return this.name;
    }



}
