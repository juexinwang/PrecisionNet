package test;
import com.*;

import entry.*;
import io.*;
import util.*;

import java.util.*;

/**
 * Used for Test
 * @author wangjue
 *
 */

public class SimpleTest {
	
	void doTest(){
		//input file
		FileIO fi = new FileIO();
		Network inNetwork = fi.readNetworkfromFile("resource/testNetwork.txt");
		
		//test nodes in the network
		Vector<Node> a=inNetwork.getNodes();
		for(int i=0;i<a.size();i++)
		{
			System.out.print(a.get(i).getNodename());
			System.out.println(a.get(i).getIndex());
		}
		
		//test AjMatrix in the network
		int[][] b=inNetwork.getAjMatrix();
		for(int i=0;i<b.length;i++)
		{
			for(int j=0;j<b.length;j++)
			{
				System.out.print(b[i][j]);
				System.out.print(" ");
			}
			System.out.println("\n");
		}
		
		//test confidenceSet
		Vector confidenceSet = fi.readConfidVectorfromFile("resource/testNetwork.txt");
		for(int i=0;i<confidenceSet.size();i++)
		{
			System.out.println(confidenceSet.get(i));
		}
		
		//test startPoint
		Vector startPoint = fi.readStartVectorfromFile("resource/testNetwork.txt");
		for(int i=0;i<startPoint.size();i++)
		{
			System.out.println(startPoint.get(i));
		}
		
		//test endPoint
		Vector endPoint = fi.readEndVectorfromFile("resource/testNetwork.txt");
		for(int i=0;i<endPoint.size();i++)
		{
			System.out.println(endPoint.get(i));
		}
		
		//test Dijstra
		ShortestPath sp = new ShortestPath();
		Vector<Path> paths=sp.dijkstra(inNetwork, confidenceSet, startPoint, endPoint);
		ShowResults sh=new ShowResults();
		sh.showPath(paths);
	}
	
	public static void main(String args[]){
		SimpleTest app = new SimpleTest();
		app.doTest();
		//System.out.println("Hello");
	}

}
