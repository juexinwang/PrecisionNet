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
		Network inNetwork = fi.readNetworkfromFile("C:/Users/JiangYX/git/testNetwork.txt");
		
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
		Hashtable<Node,Integer> confidenceSet = fi.readConfidVectorfromFile("C:/Users/JiangYX/git/testNetwork.txt",inNetwork);
//		for(int i=0;i<confidenceSet.size();i++)
		for(Map.Entry<Node,Integer> i : confidenceSet.entrySet())
		{
			System.out.println(i.getKey().getNodename());
		}
		
		//test startPoint
		Vector startPoint = fi.readStartVectorfromFile("C:/Users/JiangYX/git/testNetwork.txt");
		for(int i=0;i<startPoint.size();i++)
		{
			System.out.println(startPoint.get(i));
		}
		
		//test endPoint
		Vector endPoint = fi.readEndVectorfromFile("C:/Users/JiangYX/git/testNetwork.txt");
		for(int i=0;i<endPoint.size();i++)
		{
			System.out.println(endPoint.get(i));
		}
		
		//test Dijstra
		ShortestPath sp = new ShortestPath();
		Vector<Path> paths=sp.dijkstra(inNetwork, confidenceSet, startPoint, endPoint);
		ShowResults sh=new ShowResults();
		sh.showPath(paths);
		
		//test astar
		ShortestPath sp2 = new ShortestPath();
		System.out.println("abc");
		Vector<Path> paths2=sp2.astar(inNetwork, confidenceSet, startPoint, endPoint);
		
		for(Node p:inNetwork.nodes.values())
		{
			System.out.println(p.getNodename()+" "+p.g+" "+p.h);
		}
		ShowResults sh2=new ShowResults();
		sh2.showPath(paths2);
		
	}
	
	public static void main(String args[]){
		SimpleTest app = new SimpleTest();
		app.doTest();
		//System.out.println("Hello");
	}

}
