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
		//String inputfolder= "resource/Data for Juexin/";
		String inputfolder= "C:\\Users\\wangjue\\Dropbox\\TCBI-DigitalBiology-Labs\\Data for Juexin 18 maps\\";
		
		
		//input file
		FileIO fi = new FileIO();
		//Network inNetwork = fi.readNetworkfromFile(inputfolder+"graph_only7_unique.txt");
		Network inNetwork = fi.readNetworkfromFile(inputfolder+"graph_only18_unique.txt");
		
		
		
		//test nodes in the network
//		Vector<Node> a=inNetwork.getNodes();
//		for(int i=0;i<a.size();i++)
//		{
//			System.out.print(a.get(i).getNodename());
//			System.out.println(a.get(i).getIndex());
//		}
		
		//test AjMatrix in the network
//		int[][] b=inNetwork.getAjMatrix();
//		for(int i=0;i<b.length;i++)
//		{
//			for(int j=0;j<b.length;j++)
//			{
//				System.out.print(b[i][j]);
///				System.out.print(" ");
//			}
//			System.out.println("\n");
//		}
		
		//test confidenceSet
		//Hashtable<Node,Integer> confidenceSet = fi.readConfidVectorfromFile(inputfolder+"5 set_of_genes_for_chronic_myeloid_leukemia.csv",inputfolder+"graph_only7_unique.txt",inNetwork);
		Hashtable<Node,Integer> confidenceSet = fi.readConfidVectorfromFile(inputfolder+"5 set_of_genes_for_chronic_myeloid_leukemia.csv",inputfolder+"graph_only18_unique.txt",inNetwork);
//		for(int i=0;i<confidenceSet.size();i++)
//		for(Map.Entry<Node,Integer> i : confidenceSet.entrySet())
//		{
//			System.out.println(i.getKey().getNodename());
//		}
		
		//test startPoint
		//Vector startPoint = fi.readStartVectorfromFile("resource/Data for Juexin/1 root_nodes_7maps.csv",inNetwork);
		Vector startPoint = fi.readStartVectorfromFile(inputfolder+"1 root_nodes_18maps.csv",inNetwork);
//		for(int i=0;i<startPoint.size();i++)
//		{
//			System.out.println(startPoint.get(i));
//		}
		
		//test endPoint
		//Vector endPoint = fi.readEndVectorfromFile("resource/Data for Juexin/2 leaf_nodes_7maps.csv",inNetwork);
		Vector endPoint = fi.readEndVectorfromFile(inputfolder+"2 leaf_nodes_18maps.csv",inNetwork);
//		for(int i=0;i<endPoint.size();i++)
//		{
//			System.out.println(endPoint.get(i));
//		}
		//fi.readIDmapping("resource/Data for Juexin/7 List of all gene IDs and Names.csv", inNetwork);
		fi.readIDmapping(inputfolder+"7 List of all gene IDs and Names.csv", inNetwork);
		
		//test Dijstra
		ShortestPath sp = new ShortestPath(inNetwork, confidenceSet);
		//Use different target function;
		String str = "v2";
		Vector<Path> paths=sp.dijkstra(inNetwork, confidenceSet, startPoint, endPoint, str);
		ShowResults sh=new ShowResults();
		//sh.showPath(paths,"resource/Data for Juexin/results/top10percent_collapse.txt",inNetwork,true,0.1);
		double[] sz={0.1,0.2,0.3,0.4,0.5,1};
//		sh.showMulti(paths,"resource/Data for Juexin/newresults/", "collapse.txt", inNetwork, true, sz);
		sh.showMulti(paths,"resource/Data for Juexin/newresults/", "uncollapse.txt", inNetwork, false, sz);
		/*	
		//test astar
		ShortestPath sp2 = new ShortestPath();
		Vector<Path> paths2=sp2.astar(inNetwork, confidenceSet, startPoint, endPoint);
		ShowResults sh2=new ShowResults();
		sh2.showPath(paths2,"resource/hahahahaha.txt",iNetwork,false);
*/
		//
	}
	
	public static void main(String args[]){
		SimpleTest app = new SimpleTest();
		app.doTest();
		//
		//System.out.println("Hello");
	}

}
