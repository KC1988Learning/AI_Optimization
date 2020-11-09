package org.algorithm.depthFirstSearch;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args){

        Vertex v1 = new Vertex("1");
        Vertex v2 = new Vertex("2");
        Vertex v3 = new Vertex("3");
        Vertex v4 = new Vertex("4");
        Vertex v5 = new Vertex("5");
        Vertex v6 = new Vertex("6");
        Vertex v7 = new Vertex("7");
        Vertex v8 = new Vertex("8");
        Vertex v9 = new Vertex("9");
        Vertex v10 = new Vertex("10");
        Vertex v11 = new Vertex("11");

        List<Vertex> list = new ArrayList<>();

        v1.addNeighbor(v2);
        v1.addNeighbor(v3);

        v3.addNeighbor(v4);

        v4.addNeighbor(v5);

        v6.addNeighbor(v8);
        v6.addNeighbor(v10);
        v8.addNeighbor(v7);
        v8.addNeighbor(v9);
        v9.addNeighbor(v11);


        list.add(v1);
        list.add(v2);
        list.add(v3);
        list.add(v4);
        list.add(v5);
        list.add(v6);
        list.add(v7);
        list.add(v8);
        list.add(v9);
        list.add(v10);
        list.add(v11);

        DFS dfs = new DFS();
        dfs.dfs(list);
    }
}
