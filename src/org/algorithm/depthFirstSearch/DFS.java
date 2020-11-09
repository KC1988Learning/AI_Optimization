package org.algorithm.depthFirstSearch;

import java.util.List;
import java.util.Stack;

public class DFS {

    private Stack<Vertex> stack;

    public DFS(){
        this.stack = new Stack<>();
    }

    public void dfs(List<Vertex> vertexList){
        for (Vertex v : vertexList){  // so that vertices in different clusters could be visited
            if (!v.isVisited()){
                v.setVisited(true);
                //dfsWithStack(v);
                dfsRecurvise(v);
            }
        }
    }

    private void dfsWithStack(Vertex rootVertex){
        stack.add(rootVertex);
        rootVertex.setVisited(true);

        while(!stack.isEmpty()){
            Vertex actualVertex = stack.pop();
            System.out.println(actualVertex+" ");

            for (Vertex v : actualVertex.getNeighborList()){
                if (!v.isVisited()){
                    v.setVisited(true);
                    stack.push(v);
                }
            }
        }
    }

    private void dfsRecurvise(Vertex v){
        System.out.println(v + " ");

        for (Vertex vertex : v.getNeighborList()){
            if (!vertex.isVisited()){
                vertex.setVisited(true);
                dfsRecurvise(vertex);
            }
        }
    }

}
