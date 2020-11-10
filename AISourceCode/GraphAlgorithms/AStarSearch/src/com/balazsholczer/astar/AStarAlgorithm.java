package com.balazsholczer.astar;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class AStarAlgorithm {
	
	//all the nodes/states on the grid
	private Node[][] searchSpace;
	//where we start
	private Node startNode;
	//this is what we are after
	private Node finalNode;
	//the set of nodes that are already evaluated
	private List<Node> closedSet;
	//not yet evaluated
	private Queue<Node> openSet;
	
	public AStarAlgorithm() {
		this.searchSpace = new Node[Constants.NUM_ROWS][Constants.NUM_COLS];
		this.openSet = new PriorityQueue<>(new NodeComparator());
		this.closedSet = new ArrayList<>();
		initializeSearchSpace();
	}

	private void initializeSearchSpace() {
		
		//initialize all the nodes (states) on the grid
		for(int rowIndex=0;rowIndex<Constants.NUM_ROWS;rowIndex++) {
			for(int colIndex=0;colIndex<Constants.NUM_COLS;colIndex++) {
				Node node = new Node(rowIndex,colIndex);
				this.searchSpace[rowIndex][colIndex] = node;
			}
		}
		
		//set obstacles or blocks
		this.searchSpace[1][7].setBlock(true);
		this.searchSpace[2][3].setBlock(true);
		this.searchSpace[2][4].setBlock(true);
		this.searchSpace[2][5].setBlock(true);
		this.searchSpace[2][6].setBlock(true);
		this.searchSpace[2][7].setBlock(true);
		
		//start node and final node
		this.startNode = this.searchSpace[3][3];
		this.finalNode = this.searchSpace[1][6];
	}
	
	public void search() {
		
		//start with the start node
		startNode.setH(manhattanHeuristic(startNode, finalNode));
		openSet.add(startNode);
		
		//the algorithm terminates when there is no item left in the open set
		while(!openSet.isEmpty()) {
			
			//poll: returns the node with the smallest f=h+g value
			Node currentNode = openSet.poll();
			System.out.println(currentNode+" Predecessor is: "+currentNode.getPredecessor());
		
			//if we find the terminal state we've done
			if(currentNode.equals(finalNode)) return;
			
			//of course we have to update the sets
			openSet.remove(currentNode);
			closedSet.add(currentNode);


			//visit all the neighbors of the actual node
			for(Node neighbor : getAllNeigbors(currentNode)) {
				
				//we have already considered that state so go on
				if(closedSet.contains(neighbor)) continue;
				//we consider the state so we've done with that one
				if(!openSet.contains(neighbor)) openSet.add(neighbor);
				
				//set the predecessor to be able to track the shortest path
				neighbor.setPredecessor(currentNode);
			}
		}
	}

	private List<Node> getAllNeigbors(Node node) {
		
		//store the neighbors in a list
		//NOTE: in this implementation every node can have 4 neighbors at most (above,below,left,right)
		List<Node> neighbors = new ArrayList<>();
		
		int row = node.getRowIndex();
		int col = node.getColIndex();
		
		//block above
		if(row-1>=0 && !this.searchSpace[row-1][col].isBlock()) {
			searchSpace[row-1][col].setG(node.getG()+Constants.HORIZONTAL_VERTICAL_COST);
			searchSpace[row-1][col].setH(manhattanHeuristic(searchSpace[row-1][col], finalNode));
			neighbors.add(this.searchSpace[row-1][col]);
		}
		//block below
		if(row+1<Constants.NUM_ROWS && !this.searchSpace[row+1][col].isBlock()) {
			searchSpace[row+1][col].setH(manhattanHeuristic(searchSpace[row+1][col], finalNode));
			searchSpace[row+1][col].setG(node.getG()+Constants.HORIZONTAL_VERTICAL_COST);
			neighbors.add(this.searchSpace[row+1][col]);
		}
		//block to the left
		if(col-1>=0 && !this.searchSpace[row][col-1].isBlock()) {
			searchSpace[row][col-1].setH(manhattanHeuristic(searchSpace[row][col-1], finalNode));
			searchSpace[row][col-1].setG(node.getG()+Constants.HORIZONTAL_VERTICAL_COST);
			neighbors.add(this.searchSpace[row][col-1]);
		}
		//block to the right
		if(col+1<Constants.NUM_COLS && !this.searchSpace[row][col+1].isBlock()) {
			searchSpace[row][col+1].setH(manhattanHeuristic(searchSpace[row][col+1], finalNode));
			searchSpace[row][col+1].setG(node.getG()+Constants.HORIZONTAL_VERTICAL_COST);
			neighbors.add(this.searchSpace[row][col+1]);
		}
	
		return neighbors;
	}
	
	//manhattan distance
	public int manhattanHeuristic(Node node1, Node node2) {
		return (Math.abs(node1.getRowIndex()-node2.getRowIndex()) + Math.abs(node1.getColIndex()-node2.getColIndex()))*10;
	}

	public void showPath() {
		
		System.out.println("SHORTEST PATH WITH A* SEARCH:");
		
		Node node = this.finalNode;
		
		while(node!=null) {
			System.out.println(node);
			node=node.getPredecessor();
		}
	}
}
