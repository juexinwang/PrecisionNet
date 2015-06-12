package com;
import java.util.*;
import entry.*;

/**
 * Calculate the network, N->N problems
 * @author wangjue
 *
 */

public class CalculateNetwork {
	
	/**
	 * calculate the network by N*N shortest path
	 * @param adjMatrix
	 * @param nodes
	 * @param confidenceSet
	 * @param startPoint
	 * @param endPoint
	 * @param storeTag: 0 for 1->1, 1 for N->N
	 * @param targetFuncTag: 0 for simplest distance, 1 for confidence/nodesnumber
	 * @return shortest path
	 */
	public Network calculateN2Nalgorithm(int[][] adjMatrix, Node[] nodes, HashMap confidenceSet, Vector startPoint, Vector endPoint, boolean storeTag, int targetFuncTag){
		Network network = new Network();
		//TODO
		return network;
	}

}
