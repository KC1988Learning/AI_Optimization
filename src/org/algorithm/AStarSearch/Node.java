package org.algorithm.AStarSearch;

public class Node {

    private int g; // shortest cost from starting point
    private int h: // estimated cost from current to destination node
    private int rowIndex;
    private int colIndex;
    private Node predecessor; // this is how we track the shortest path
    private boolean isBlock; // the node may be an obstacle/block

    //constructor
    public Node(int rowIndex, int colIndex) {
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getColIndex() {
        return colIndex;
    }

    public void setColIndex(int colIndex) {
        this.colIndex = colIndex;
    }

    public Node getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(Node predecessor) {
        this.predecessor = predecessor;
    }

    public boolean isBlock() {
        return isBlock;
    }

    public void setBlock(boolean block) {
        isBlock = block;
    }

    // method to compute the F(x) function
    public int getF(){
        return this.g + this.h;
    }

    // override the equal method to compare two nodes
    @Override
    public boolean equals(Object node2){
        Node otherNode = (Node) node2;
        return this.getRowIndex()==otherNode.getRowIndex() &&
                this.getColIndex()==otherNode.getColIndex();
    }

    // override the toString method
    @Override
    public String toString(){
        return "Node ( " + this.rowIndex + ";" + this.colIndex +
                " ) h:" + this.h +
                " - g: " + this.g +
                " - f: " + this.getF();
    }


}
