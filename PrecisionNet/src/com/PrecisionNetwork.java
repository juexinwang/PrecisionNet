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
		Network inNetwork = fi.readNetworkfromFile("C:/Users/JiangYX/git/testNetwork.txt");
/*		
		//Alternative input
		//DatabaseIO di = new DatabaseIO();
		//Network inNetwork1 = di.readNetworkfromDB();
		
		int[][] adjMatrix = inNetwork.getAjMatrix();
//		Node[] nodes = inNetwork.getNodes();
		Vector confidenceSet = fi.readConfidVectorfromFile("");
		Vector startPoint = fi.readStartVectorfromFile("");
		Vector endPoint = fi.readEndVectorfromFile("");
		boolean storeTag = false;
		int targetFuncTag = 1;	
		
		//1->1
		ShortestPath sp = new ShortestPath();
		Vector<Path> paths = sp.dijkstra(inNetwork, confidenceSet, startPoint, endPoint);
		
		//output
		ShowResults sr = new ShowResults();
		sr.showPath(paths);
		
		//n->n
		CalculateNetwork cn = new CalculateNetwork();
		Network outNetwork = cn.calculateN2Nalgorithm(adjMatrix,confidenceSet, startPoint, endPoint, storeTag, targetFuncTag);
		
		//output
		sr.showNetwork(outNetwork);
	}
	
	
	public static void main(String args[]){
		PrecisionNetwork app = new PrecisionNetwork();
		app.dowork();
		*/
	}

}
