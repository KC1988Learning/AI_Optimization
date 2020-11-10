package org.algorithm.AStarSearch;

import javafx.scene.layout.Priority;

import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class AStarAlgorithm {

    private Node[][] searchSpace; // all the nodes/states on the grid
    private Node startNode; // where we start
    private Node finalNode; // where we want to go
    private List<Node> closedSet; // the set of nodes already evaluated
    private Queue<Node> openSet; // the set of nodes yet to be evaluated

    public AStarAlgorithm(){
        this.searchSpace = new Node[Constants.NUM_ROWS][Constants.NUM_COLS];
        this.openSet = new PriorityQueue<>();
        this.closedSet = new ArrayList<>();
        initializeSearchSpace();
    }

    private void initializeSearchSpace(){
        // initialize all the nodes on the grid
        for (int rowIndex=0 ; rowIndex < Constants.NUM_ROWS ; rowIndex++){
            for (int colIndex=0 ; colIndex < Constants.NUM_COLS ; colIndex++){
                Node node = new Node(rowIndex, colIndex);
                this.searchSpace[rowIndex][colIndex] = node;
            }
        }

        // set obstacles or blocks
        this.searchSpace[1][7].setBlock(true);
        this.searchSpace[2][3].setBlock(true);
        this.searchSpace[2][4].setBlock(true);
        this.searchSpace[2][5].setBlock(true);
        this.searchSpace[2][6].setBlock(true);
        this.searchSpace[2][7].setBlock(true);

        // start node and final node
        this.startNode= this.searchSpace[3][3];
        this.finalNode = this.searchSpace[1][6];
    }

    public void search(){
        // start with the start node
        startNode.setH(manhattanHeuristic(startNode, finalNode));
        openSet.add(startNode);

        // the algorithm terminates when there is no item left in the
        // open set
        while (!openSet.isEmpty()){
            // poll: retursn the node with the smalleest f=h+g value
            Node currentNode = openSet.poll();
            System.out.println(currentNode +
                    " Predecessor is: " +
                    currentNode.getPredecessor());

            // if we find the terminal state we are done
            if (currentNode.equals(finalNode)) return;

            // update the sets
            openSet.remove(currentNode);
            closedSet.add(currentNode);

            // visit all the neighbors of the actual node
//            for (Node neighbor : getAllNeighbors(currentNode)){
//                // skip node which has been visited
//                if (closedSet.contains(neighbor)) continue;
//                // add to openSet if we have visited the node
//                if (!openSet.contains(neighbor)) openSet.add(neighbor);
//
//                // set the predecessor
//                neighbor.setPredecessor(currentNode);
//            }

            // visit all the neighbors of the actual node
            int current_row = currentNode.getRowIndex();
            int current_col = currentNode.getColIndex();

            // look for node above which is neither blocked nor visited before
            if (current_row >= 1 && !searchSpace[current_row-1][current_col].isBlock() &&
            !closedSet.contains(searchSpace[current_row-1][current_col])){
                // get the cost and estimated cost of the node above
                int G_above = currentNode.getG() + Constants.HORIZONTAL_VERTICAL_COST;
                int H_above = manhattanHeuristic(searchSpace[current_row-1][current_col], finalNode);

                // add to openset for fresh node and then update the predecessor (parent node)
                // otherwise, compare the old and new cost value and update to the smaller one, then
                // again update the predecessor
                if (!openSet.contains(searchSpace[current_row-1][current_col])){
                    searchSpace[current_row-1][current_col].setG(G_above);
                    searchSpace[current_row-1][current_col].setH(H_above);
                    searchSpace[current_row-1][current_col].setPredecessor(currentNode);
                    openSet.add(searchSpace[current_row-1][current_col]);
                } else if (openSet.contains(searchSpace[current_row-1][current_col]) &&
                searchSpace[current_row-1][current_col].getG()>G_above){
                    searchSpace[current_row-1][current_col].setG(G_above);
                    searchSpace[current_row-1][current_col].setG(H_above);
                    searchSpace[current_row-1][current_col].setPredecessor(currentNode);
                }
            }

            // look for node below which is neither blocked nor visited before
            if (current_row <= Constants.NUM_ROWS-1 && !searchSpace[current_row+1][current_col].isBlock() &&
                    !closedSet.contains(searchSpace[current_row+1][current_col])){
                // get the cost and estimated cost of the node above
                int G_below = currentNode.getG() + Constants.HORIZONTAL_VERTICAL_COST;
                int H_below = manhattanHeuristic(searchSpace[current_row+1][current_col], finalNode);

                // add to openset for fresh node and then update the predecessor (parent node)
                // otherwise, compare the old and new cost value and update to the smaller one, then
                // again update the predecessor
                if (!openSet.contains(searchSpace[current_row+1][current_col])){
                    searchSpace[current_row+1][current_col].setG(G_below);
                    searchSpace[current_row+1][current_col].setH(H_below);
                    searchSpace[current_row+1][current_col].setPredecessor(currentNode);
                    openSet.add(searchSpace[current_row+1][current_col]);
                } else if (openSet.contains(searchSpace[current_row+1][current_col]) &&
                        searchSpace[current_row+1][current_col].getG()>G_below){
                    searchSpace[current_row+1][current_col].setG(G_below);
                    searchSpace[current_row+1][current_col].setH((H_below));
                    searchSpace[current_row+1][current_col].setPredecessor(currentNode);
                }
            }

            // look for left node which is neither blocked nor visited before
            if (current_col >= 1 && !searchSpace[current_row][current_col-1].isBlock() &&
            !closedSet.contains(searchSpace[current_row][current_col-1])){
                int G_left = currentNode.getG() + Constants.HORIZONTAL_VERTICAL_COST;
                int H_left = manhattanHeuristic(searchSpace[current_row][current_col-1], finalNode);

                if (!openSet.contains(searchSpace[current_row][current_col-1])){
                    searchSpace[current_row][current_col-1].setG(G_left);
                    searchSpace[current_row][current_col-1].setH(H_left);
                    searchSpace[current_row][current_col-1].setPredecessor(currentNode);
                    openSet.add(searchSpace[current_row][current_col-1]);
                } else if (openSet.contains(searchSpace[current_row][current_col-1]) &&
                searchSpace[current_row][current_col-1].getG() > G_left){
                    searchSpace[current_row][current_col-1].setG(G_left);
                    searchSpace[current_row][current_col-1].setH(H_left);
                    searchSpace[current_row][current_col-1].setPredecessor(currentNode);
                }
            }

            // look for right node which is neither blocked nor visited before
            if (current_row <= Constants.NUM_COLS-1 && !searchSpace[current_row][current_col+1].isBlock() &&
            !closedSet.contains(searchSpace[current_row][current_col+1])){
                int G_right = currentNode.getG() + Constants.HORIZONTAL_VERTICAL_COST;
                int H_right = manhattanHeuristic(searchSpace[current_row][current_col+1], finalNode);

                if (!openSet.contains(searchSpace[current_row][current_col+1])){
                    searchSpace[current_row][current_col+1].setG((G_right));
                    searchSpace[current_row][current_col+1].setH(H_right);
                    searchSpace[current_row][current_col+1].setPredecessor(currentNode);
                    openSet.add(searchSpace[current_row][current_col+1]);
                } else if (openSet.contains(searchSpace[current_row][current_col+1]) &&
                searchSpace[current_row][current_col+1].getG() > G_right){
                    searchSpace[current_row][current_col+1].setG(G_right);
                    searchSpace[current_row][current_col+1].setH(H_right);
                    searchSpace[current_row][current_col+1].setPredecessor(currentNode);
                }
            }
        }
    }

//    private List<Node> getAllNeighbors(Node node) {
//
//        // store the neighbors in a list
//        // NOTE: in this implementation every node can have
//        // 4 neighbors at most (above, below, left, right)
//        List<Node> neighbors = new ArrayList<>();
//
//        int row = node.getRowIndex();
//        int col = node.getColIndex();
//
//        // block above
//        if (row >= 1 && !this.searchSpace[row-1][col].isBlock()){
//            searchSpace[row-1][col].setG(node.getG() + Constants.HORIZONTAL_VERTICAL_COST);
//            searchSpace[row-1][col].setH(manhattanHeuristic(searchSpace[row-1][col], finalNode));
//            neighbors.add(searchSpace[row-1][col]);
//        }
//
//        // block below
//        if (row <= Constants.NUM_ROWS-1 && !this.searchSpace[row+1][col].isBlock()){
//            searchSpace[row+1][col].setG(node.getG() + Constants.HORIZONTAL_VERTICAL_COST);
//            searchSpace[row+1][col].setH(manhattanHeuristic(searchSpace[row+1][col], finalNode));
//            neighbors.add(searchSpace[row+1][col]);
//        }
//
//        // block to the left
//        if (col >= 1 && !this.searchSpace[row][col-1].isBlock()){
//            searchSpace[row][col-1].setG(node.getG()+Constants.HORIZONTAL_VERTICAL_COST);
//            searchSpace[row][col-1].setH(manhattanHeuristic(searchSpace[row][col-1], finalNode));
//            neighbors.add(searchSpace[row][col-1]);
//        }
//
//        // block to the right
//        if (col <= Constants.NUM_COLS-1 && !this.searchSpace[row][col+1].isBlock()){
//            searchSpace[row][col+1].setG(node.getG()+Constants.HORIZONTAL_VERTICAL_COST);
//            searchSpace[row][col+1].setH(manhattanHeuristic(searchSpace[row][col+1], finalNode));
//            neighbors.add(searchSpace[row][col+1]);
//        }
//
//        return neighbors;
//    }

    // compute the manhattan distance between two nodes
    private int manhattanHeuristic(Node node1, Node node2) {
        return (Math.abs(node1.getRowIndex() - node2.getRowIndex()) +
                Math.abs(node1.getColIndex() - node2.getColIndex()))*10;
    }

}
