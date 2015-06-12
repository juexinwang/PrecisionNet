package com;
import util.*;
import entry.*;
import io.*;
import java.util.*;

/**
 * Main process of precision network
 * @author wangjue
 *
 */
public class PrecisionNetwork {
	
	/**
	 * main process
	 */
	void dowork(){
		//get input
		FileIO fi = new FileIO();
		Network inNetwork = fi.readNetworkfromFile("", "");
		int[][] adjMatrix = inNetwork.getAjMatrix();
		Node[] nodes = inNetwork.getNodes();
		HashMap confidenceSet = fi.readHashMapfromFile("", "");
		Vector startPoint = fi.readVectorfromFile("", "");
		Vector endPoint = fi.readVectorfromFile("", "");
		boolean storeTag = false;
		int targetFuncTag = 1;	
		
		//1->1
		ShortestPath sp = new ShortestPath();
		Path path = sp.dijkstra(adjMatrix, nodes, confidenceSet, startPoint, endPoint, storeTag, targetFuncTag);
		
		//output
		ShowResults sr = new ShowResults();
		sr.showPath(path);
		
		//n->n
		CalculateNetwork cn = new CalculateNetwork();
		Network outNetwork = cn.calculateN2Nalgorithm(adjMatrix, nodes, confidenceSet, startPoint, endPoint, storeTag, targetFuncTag);
		
		//output
		sr.showNetwork(outNetwork);
	}
	
	
	public static void main(String args[]){
		PrecisionNetwork app = new PrecisionNetwork();
		app.dowork();
	}

}
