package com;
import java.util.*;
import entry.*;

/**
 * First step of the project, shortest path, without considering subjects
 * @author wangjue
 *
 */
public class ShortestPath {
	/**
	 * dijkstra algorithm
	 * @param adjmatrix
	 * @param nodes
	 * @param confidenceSet
	 * @param startPoint
	 * @param endPoint
	 * @param storeTag: 0 for 1->1, 1 for N->N
	 * @param targetFuncTag: 0 for simplest distance, 1 for confidence/nodesnumber
	 * @return shortest path
	 */
	public Path dijkstra(int[][] adjmatrix, Node[] nodes, HashMap confidenceSet, Vector startPoint, Vector endPoint, boolean storeTag, int targetFuncTag){
		Path path = new Path();
		//TODO
		return path;
	}
	
	/**
	 * A star algorithm
	 * @param adjMatrix
	 * @param nodes
	 * @param confidenceSet
	 * @param startPoint
	 * @param endPoint
	 * @param storeTag
	 * @param targetFuncTag
	 * @param heuristicTag
	 * @return
	 */
	public Path astar(int[][] adjMatrix, Node[] nodes, HashMap confidenceSet, Vector startPoint, Vector endPoint, boolean storeTag, int targetFuncTag, int heuristicTag){
		Path path = new Path();
		//TODO
		return path;
	}
	
	
	
	/**
	 * heuristic method
	 * @param adjMatrix
	 * @param nodes
	 * @param confidenceSet
	 * @param startPoint
	 * @param endPoint
	 * @param node: input, specific nodes
	 * @param targetFuncTag
	 * @param heuristicTag: define different types of heuristic method
	 * @return heruistic value for speicific node
	 */
	public double heuristicMethod(int[][] adjMatrix, Node[] nodes, HashMap confidenceSet, Vector startPoint, Vector endPoint, Node node, int targetFuncTag, int heuristicTag){
		double hvalue = 0.0;
		//TODO
		return hvalue;
	}
	
	/**
	 * target function of the shortest path
	 * @param adjMatrix
	 * @param nodes
	 * @param confidenceSet
	 * @param startPoint
	 * @param endPoint
	 * @param path: input, the specific path
	 * @param targetFuncTag: 0 for simplest distance, 1 for confidence/nodesnumber
	 * @return target function of specific path
	 */
	public double targetFunc(int[][] adjMatrix, Node[] nodes, HashMap confidenceSet, Vector startPoint, Vector endPoint, Path path, int targetFuncTag){
		double value =0.0;
		//TODO
		return value;
	}
	
	
}
