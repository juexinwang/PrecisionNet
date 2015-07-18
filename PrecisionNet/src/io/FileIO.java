package io;
import java.util.*;
import java.io.*;

import entry.*;


/**
 * IO via files
 * @author wangjue
 *
 */
public class FileIO {
	
	/**
	 * Read network from file
	 * @param folder
	 * @param filename
	 * @return
	 */
	public Network readNetworkfromFile(String filename){
		File file=new File(filename);
		BufferedReader reader = null;
		Network network=new Network();
		Hashtable ht=new Hashtable();
		int index=0;
		try
    	{
    	reader = new BufferedReader(new FileReader(file));
    	String tempString = null;
    	while ((tempString = reader.readLine()) != null) {

    			String[] care=tempString.split(" ");
    			
    			Node a=new Node(care[0]);
    			Node b=new Node(care[2]);
    			if(ht.containsKey(care[0]))
				{
					a=network.getByName(care[0]);
				}
    			else
				{
					a.setIndex(index);
					ht.put(care[0], index++);
					network.addNode(a);
				}
    			if(ht.containsKey(care[2]))
				{
					b=network.getByName(care[2]);
				}
				else
				{
					b.setIndex(index);
					ht.put(care[2], index++);
					network.addNode(b);
				}
    			Interaction interab=new Interaction(a,b);
    			interab.type=care[1];
    			network.addInteraction(interab, 0);      
    			
        }
    	int num=ht.size();
		int[][] matrix=new int[num][num];
		for(Map.Entry<Interaction,Integer> i:network.interactions.entrySet())
		{
			int index_node1=i.getKey().getNodeAIndex();
			int index_node2=i.getKey().getNodeBIndex();
			matrix[index_node1][index_node2]=1;
		}
		network.setAjMatrix(matrix);
    	reader.close();
    	}
		catch(IOException e)
		{
			e.printStackTrace();
		}
    	finally 
    	{
            if (reader != null) {
            	try
            	{
            		reader.close();
            	}
            	catch(IOException e1)
            	{
            		
            	}
               
            }
    	}
		
		
		//TODO
		return network;
	}
	
	
	/**
	 * Read hashmap from file, specify the confidenceSet in the project
	 * @param folder
	 * @param filename
	 * @return
	 */
	public Vector readHashMapfromFile(String folder, String filename){
		Vector hm = new Vector();
		//TODO
		return hm;
	}
	
	/**
	 * Read vector from file
	 * @param folder
	 * @param filename
	 * @return
	 */
	public Hashtable<Node,Integer> readConfidVectorfromFile(String filename, Network net){
		File file=new File(filename);
		BufferedReader reader = null;
		Hashtable<Node,Integer> vec = new Hashtable();
		try
		{
			reader = new BufferedReader(new FileReader(file));
	    	String tempString = null;
	    	while ((tempString = reader.readLine()) != null) 
	    	{
	    		vec.put(net.getByName(tempString), 1);
	    		net.getByName(tempString).weight=1;
	    		net.getByName(tempString).flag=net.getByName(tempString).flag+"C";
	/*    		String head=tempString.split(":")[0];
	    		String tail=tempString.split(":")[1];
	    		if(head.equals("confidenceSet"))
	    		{
	    			String[] care=tempString.split(",");
	    			for(String each:care)
	    			{
	    				String[] com=each.split("#");
	    				if(com.length==2)
	    				{
	    					vec.put(net.getByName(com[0]),Integer.valueOf(com[1]));
	    					net.getByName(com[0]).weight=Integer.valueOf(com[1]);
	    				}
	    				else
	    				{
	    					vec.put(net.getByName(com[0]),1);
	    					net.getByName(com[0]).weight=1;
	    				}
	    			}
	    		}
	  */
	    	}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (reader != null) {
            	try
            	{
            		reader.close();
            	}
            	catch(IOException e1)
            	{         		
            	}
            }
		}
		//TODO
		return vec;
	}
	
	public Vector readStartVectorfromFile(String filename,Network net){
		File file=new File(filename);
		BufferedReader reader = null;
		Vector vec = new Vector();
		try
		{
			reader = new BufferedReader(new FileReader(file));
	    	String tempString = null;
	    	while ((tempString = reader.readLine()) != null) 
	    	{
	    		vec.add(tempString);
	    		net.getByName(tempString).flag=net.getByName(tempString).flag+"R";
	/*    		String head=tempString.split(":")[0];
	    		String tail=tempString.split(":")[1];
	    		if(head.equals("startPoint"))
	    		{
	    			String[] care=tail.split(",");
	    			for(String each:care)
	    			{
	    				vec.add(each);
	    			}
	    		}
	*/
	    	}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (reader != null) {
            	try
            	{
            		reader.close();
            	}
            	catch(IOException e1)
            	{         		
            	}
            }
		}
		//TODO
		return vec;
	}
	
	public Vector readEndVectorfromFile(String filename,Network net){
		File file=new File(filename);
		BufferedReader reader = null;
		Vector vec = new Vector();
		try
		{
			reader = new BufferedReader(new FileReader(file));
	    	String tempString = null;
	    	while ((tempString = reader.readLine()) != null) 
	    	{
	    		vec.add(tempString);
	    		net.getByName(tempString).flag=net.getByName(tempString).flag+"L";
     /*
	    		String head=tempString.split(":")[0];
	    		String tail=tempString.split(":")[1];
	    		if(head.equals("endPoint"))
	    		{
	    			String[] care=tail.split(",");
	    			for(String each:care)
	    			{
	    				vec.add(each);
	    			}
	    		}
	 */
	    	}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (reader != null) {
            	try
            	{
            		reader.close();
            	}
            	catch(IOException e1)
            	{         		
            	}
            }
		}
		//TODO
		return vec;
	}

}
