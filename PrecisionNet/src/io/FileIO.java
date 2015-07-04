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
		try
    	{
    	reader = new BufferedReader(new FileReader(file));
    	String tempString = null;
    	while ((tempString = reader.readLine()) != null) {
    		String head=tempString.split(":")[0];
    		String tail=tempString.split(":")[1];
    		if(head.equals("network"))
    		{
    			Hashtable ht=new Hashtable();
    			String[] care=tail.split(",");
    			int index=0;
    			for(String each:care)
    			{
    				String[] interac=each.split("#");
    				Node a=new Node(interac[0]);
    				Node b=new Node(interac[1]);    				
    				if(ht.containsKey(interac[0]))
    				{
    					a=network.getByName(interac[0]);
    				}
    				else
    				{
    					a.setIndex(index);
    					ht.put(interac[0], index++);
    					network.addNode(a);
    				}
    				if(ht.containsKey(interac[1]))
    				{
    					b=network.getByName(interac[1]);
    				}
    				else
    				{
    					b.setIndex(index);
    					ht.put(interac[1], index++);
    					network.addNode(b);
    				}
    				Interaction interab=new Interaction(a,b);
    				if(interac.length==3)
    				{
    					network.addInteraction(interab,Integer.valueOf(interac[2]));
    					interab.weight=Integer.valueOf(interac[2]);
    				}
    				else
    				{
    					network.addInteraction(interab,0);
    					interab.weight=0;
    				}
    				
    			}
    			int num=ht.size();
    			int[][] matrix=new int[num][num];
    			for(Map.Entry<Interaction,Integer> i:network.interactions.entrySet())
    			{
    				int index_node1=i.getKey().getNodeAIndex();
    				int index_node2=i.getKey().getNodeBIndex();
    				matrix[index_node1][index_node2]=1;
    			}
 //   			Vector<Interaction> inters=network.getInteractions();
 //   			for(int i=0;i<inters.size();i++)
 //   			{
  //  				int index_node1=inters.get(i).getNodeAIndex();
  //  				int index_node2=inters.get(i).getNodeBIndex();
  //  				matrix[index_node1][index_node2]=1;
  //  			}
    			network.setAjMatrix(matrix);
    		}
            
        }
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
	    		String head=tempString.split(":")[0];
	    		String tail=tempString.split(":")[1];
	    		if(head.equals("confidenceSet"))
	    		{
	    			String[] care=tail.split(",");
	    			for(String each:care)
	    			{
	    				String[] com=each.split("#");
	    				if(com.length==2)
	    				{
	    					vec.put(net.getByName(com[0]),Integer.valueOf(com[1]));
	   // 					vec.put(com[0], Integer.valueOf(com[1]));
	    					net.getByName(com[0]).weight=Integer.valueOf(com[1]);
	    				}
	    				else
	    				{
	    					vec.put(net.getByName(com[0]),1);
	  //  					vec.put(com[0], 1);
	    					net.getByName(com[0]).weight=1;
	    				}
	    			}
	    		}
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
	
	public Vector readStartVectorfromFile(String filename){
		File file=new File(filename);
		BufferedReader reader = null;
		Vector vec = new Vector();
		try
		{
			reader = new BufferedReader(new FileReader(file));
	    	String tempString = null;
	    	while ((tempString = reader.readLine()) != null) 
	    	{
	    		String head=tempString.split(":")[0];
	    		String tail=tempString.split(":")[1];
	    		if(head.equals("startPoint"))
	    		{
	    			String[] care=tail.split(",");
	    			for(String each:care)
	    			{
	    				vec.add(each);
	    			}
	    		}
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
	
	public Vector readEndVectorfromFile(String filename){
		File file=new File(filename);
		BufferedReader reader = null;
		Vector vec = new Vector();
		try
		{
			reader = new BufferedReader(new FileReader(file));
	    	String tempString = null;
	    	while ((tempString = reader.readLine()) != null) 
	    	{
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
