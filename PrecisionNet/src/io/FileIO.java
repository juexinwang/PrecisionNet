package io;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
	public void readIDmapping(String filename, Network net)
	{
		
		File file=new File(filename);
		BufferedReader reader = null;
		Hashtable ht=new Hashtable();
		try
    	{
//			FileWriter writer = new FileWriter("resource/Data for Juexin/idout.txt");
    	    reader = new BufferedReader(new FileReader(file));
    	    String tempString = null;
    	    while ((tempString = reader.readLine()) != null) 
    	    {
    	    	Pattern pattern=Pattern.compile("hsa:([^,]*),\"?(.*[^,\"])\"?,*$");
    	        Matcher m=pattern.matcher(tempString);
    	        if(m.find())
    	        {
    	        	ht.put(m.group(1), m.group(2));   	        	
    	        }
    	    }
    	    for(Map.Entry<String, Node> i : net.nodes.entrySet())
    	    {
    	    	Pattern pattern=Pattern.compile("#Abst_Gene_hsa_([^_]*)>");
    	    	Matcher m=pattern.matcher(i.getValue().getNodename());
    	    	if(m.find())
    	    	{
    	    		i.getValue().gene_name=i.getValue().gene_name+ht.get(m.group(1))+" ";
    	    		Vector tem=new Vector();
    	    	for(Node j : i.getValue().cla)
    	    	{
    	    		j.gene_name=j.gene_name+ht.get(m.group(1))+" ";
    	    		char[] t=j.flag.toCharArray();
    	    		for(char c : t)
    	    		{
    	    			if(tem.contains(c))
    	    			{}
    	    			else
    	    			{
    	    				i.getValue().flag=i.getValue().flag+c;
    	    				tem.add(c);
    	    			}
    	    		}
  //  	    		writer.write(j.getNodename()+" "+m.group(1)+" "+j.gene_name);
  //  	    		writer.write(String.valueOf(i.getValue().becla.size()));

    	    	}
    	    	}
    	    	
    	    }
//    	    for(Map.Entry<String, Node> i : net.nodes.entrySet())
//    	    {
 //   	    	writer.write(i.getValue().getNodename()+" "+i.getValue().cla.size()+" "+i.getValue().flag+" "+i.getValue().pathway+" "+i.getValue().gene_name);
//    	    	writer.write("\r\n");
//    	    }
    	    
//    	    writer.close();
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
	}
	
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
    				String pattern = "_(hsa[^_]+)_";
            		Pattern r=Pattern.compile(pattern);
            		Matcher m=r.matcher(care[0]);
            		if(m.find())
            		{
            			a.pathway=m.group(1);
            		}
    				
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
					String pattern = "_(hsa[^_]+)_";
            		Pattern r=Pattern.compile(pattern);
            		Matcher m=r.matcher(care[2]);
            		if(m.find())
            		{
            			b.pathway=m.group(1);
            		}
					
					b.setIndex(index);
					ht.put(care[2], index++);
					network.addNode(b);
				}
    			Interaction interab=new Interaction(a,b);
    			
    			
    			String pattern1="#cpd";
    			Pattern r1=Pattern.compile(pattern1);
    			Matcher m1=r1.matcher(care[2]);
    			String pattern2 = "#Abst_Gene_Group";
        		Pattern r2=Pattern.compile(pattern2);
        		Matcher m2=r2.matcher(care[2]);
        		String pattern3 = "#Abst_Gene_hsa";
        		Pattern r3=Pattern.compile(pattern3);
        		Matcher m3=r3.matcher(care[2]);
        		String pattern4 = "#Abst_Gene_ko";
        		Pattern r4=Pattern.compile(pattern4);
        		Matcher m4=r4.matcher(care[2]);
        		
        		if(m1.find())
        		{
        			interab.type=care[1];
        			network.addInteraction(interab, 0);
        			interab.weight=0;
    				a.adjNodes.put(b, 0);
    				b.cla.add(a);
    				a.becla.add(b);
    				
    				interab=new Interaction(b,a);
    				interab.type=care[1];
    				network.addInteraction(interab, 0);
    				interab.weight=0;
    				b.adjNodes.put(a, 0);
        		}
        		else if(m2.find())
        		{
        			interab.type=care[1];
        			network.addInteraction(interab, 0);
        			interab.weight=0;
    				a.adjNodes.put(b, 0);
    				b.cla.add(a);
    				a.becla.add(b);
    				
    				interab=new Interaction(b,a);
    				interab.type=care[1];
    				network.addInteraction(interab, 0);
    				interab.weight=0;
    				b.adjNodes.put(a, 0);
        		}
        		else if(m3.find())
        		{
        			String pattern = "#inst.*_Gene_Group";
            		Pattern r=Pattern.compile(pattern);
            		Matcher m=r.matcher(care[0]);
            		if(!m.find())
            		{
            			interab.type=care[1];
        				network.addInteraction(interab, 0);
        				interab.weight=0;
        				a.adjNodes.put(b, 0);
        				b.cla.add(a);
        				a.becla.add(b);
        				
        				interab=new Interaction(b,a);
        				interab.type=care[1];
        				network.addInteraction(interab, 0);
        				interab.weight=0;
        				b.adjNodes.put(a, 0);
            		}
            		else
            		{
            			interab.type=care[1];
        				network.addInteraction(interab, 0);
        				interab.weight=0;
        				a.adjNodes.put(b, 0);
        				
        				interab=new Interaction(b,a);
        				interab.type=care[1];
        				network.addInteraction(interab, 0);
        				interab.weight=0;
        				b.adjNodes.put(a, 0);
            		}
        		}
        		else if(m4.find())
        		{
        			interab.type=care[1];
        			network.addInteraction(interab, 0);
        			interab.weight=0;
    				a.adjNodes.put(b, 0);
    				b.cla.add(a);
    				a.becla.add(b);
    				
    				interab=new Interaction(b,a);
    				interab.type=care[1];
    				network.addInteraction(interab, 0);
    				interab.weight=0;
    				b.adjNodes.put(a, 0);
        		}
        		else
        		{
        			interab.type=care[1];
        			network.addInteraction(interab, 1);  
        			interab.weight=1;
        			a.adjNodes.put(b, 1);
        		}
/*        		
    			if(m.find())
    			{
    				interab.type=care[1];
    				network.addInteraction(interab, 0);
    				interab.weight=0;
    				a.adjNodes.put(b, 0);
    				b.cla.add(a);
    				
    				interab=new Interaction(b,a);
    				interab.type=care[1];
    				network.addInteraction(interab, 0);
    				interab.weight=0;
    				b.adjNodes.put(a, 0);
    			}
    			else
    			{
    				interab.type=care[1];
        			network.addInteraction(interab, 1);  
        			interab.weight=1;
        			a.adjNodes.put(b, 1);
    			}
*/ 			
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
	public Hashtable<Node,Integer> readConfidVectorfromFile(String cfilename, String nfilename, Network net){
		File cfile=new File(cfilename);
		BufferedReader creader = null;
		File nfile=new File(nfilename);
		BufferedReader nreader = null;
		Hashtable<Node,Integer> vec = new Hashtable();
		Hashtable hsa=new Hashtable();
		Hashtable part=new Hashtable();
		try
		{
			creader = new BufferedReader(new FileReader(cfile));
	    	String tempString = null;
	    	String pattern = ",(hsa:\\d*),";
    		Pattern r=Pattern.compile(pattern);
    		while ((tempString = creader.readLine()) != null) 
    		{
    			Matcher m = r.matcher(tempString);
    			if(m.find())
    			{
    				tempString=m.group(1);
    				String pattern2 =":";
    				String replace="_";
    				Pattern r2=Pattern.compile(pattern2);
    				Matcher m2=r2.matcher(tempString);
    				tempString=m2.replaceAll(replace);
    				hsa.put(tempString, 1);
    			}
    			else
    			{
    				continue;
    			}
    		}
    		
    		nreader = new BufferedReader(new FileReader(nfile));
    		tempString = null;
    		pattern = "(<.*>)\\s(<.*>)\\s(<.*>)\\s\\.";
    		r=Pattern.compile(pattern);   	
	    	while ((tempString = nreader.readLine()) != null) 
	    	{
	    		Matcher m = r.matcher(tempString);
	    		if(m.find())
    			{
    				String part1=m.group(1);
    				String part2=m.group(2);
    				String part3=m.group(3);
    				String pattern2="#inst";
    				Pattern r2=Pattern.compile(pattern2);
    				Matcher m1=r2.matcher(part1);
    				Matcher m2=r2.matcher(part2);
    				Matcher m3=r2.matcher(part3);
    				if((m1.find())&&(!part.containsKey(part1)))
    		        {
    					Pattern p=Pattern.compile("Gene_(hsa_[^_>]*?)[_>]");
    			        Matcher pm=p.matcher(part1);
    			        while(pm.find())
    			        {
    			        	if(hsa.containsKey(pm.group(1)))
    			        	{
    			        		part.put(part1, 1);
    	    		            vec.put(net.getByName(part1), 1);
    	    		    		net.getByName(part1).weight=1;
    	    		    		net.getByName(part1).flag=net.getByName(part1).flag+"C"; 
    	    		    		break;
    			        	}
    			        }
    		                 
    		        }
    				if((m2.find())&&(!part.containsKey(part2)))
    		        {
    					Pattern p=Pattern.compile("Gene_(hsa_[^_>]*?)[_>]");
    			        Matcher pm=p.matcher(part2);
    			        while(pm.find())
    			        {
    			        	if(hsa.containsKey(pm.group(1)))
    			        	{
    			        		part.put(part2, 1);
    	    		            vec.put(net.getByName(part2), 1);
    	    		    		net.getByName(part2).weight=1;
    	    		    		net.getByName(part2).flag=net.getByName(part2).flag+"C";
    	    		    		break;
    			        	}
    			        }
    		                  
    		        }
    				if((m3.find())&&(!part.containsKey(part3)))
    		        {
    					Pattern p=Pattern.compile("Gene_(hsa_[^_>]*?)[_>]");
    			        Matcher pm=p.matcher(part3);
    			        while(pm.find())
    			        {
    			        	if(hsa.containsKey(pm.group(1)))
    			        	{
    			        		part.put(part3, 1);
    	    		            vec.put(net.getByName(part3), 1);
    	    		    		net.getByName(part3).weight=1;
    	    		    		net.getByName(part3).flag=net.getByName(part3).flag+"C";   
    	    		    		break;
    			        	}
    			        }
    		               
    		        }
    			}
    			else
    			{
    				continue;
    			}
	    		
	    		
	    	}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (creader != null) {
            	try
            	{
            		creader.close();
            		nreader.close();
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
	    	String pattern = ",(<.*>).*$";
    		Pattern r=Pattern.compile(pattern);
	    	while ((tempString = reader.readLine()) != null) 
	    	{	
	    		Matcher m = r.matcher(tempString);
	    		if (m.find( ))
	    		{
	    			tempString=m.group(1);
	    			if(vec.contains(tempString))
	    			{}
	    			else
	    			{
	    				vec.add(tempString);
			    		net.getByName(tempString).flag=net.getByName(tempString).flag+"R";
	    			}
		    		
	    		}
	    		else
	    		{
	    			continue;
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
	
	public Vector readEndVectorfromFile(String filename,Network net){
		File file=new File(filename);
		BufferedReader reader = null;
		Vector vec = new Vector();
		try
		{
			reader = new BufferedReader(new FileReader(file));
	    	String tempString = null;
	    	String pattern = ",(<.*>).*$";
    		Pattern r=Pattern.compile(pattern);
	    	while ((tempString = reader.readLine()) != null) 
	    	{
	    		Matcher m = r.matcher(tempString);
	    		if (m.find( ))
	    		{
	    			tempString=m.group(1);
	    			if(vec.contains(tempString))
	    			{}
	    			else
	    			{
	    				vec.add(tempString);
			    		net.getByName(tempString).flag=net.getByName(tempString).flag+"L";
	    			}
	    			
	    		}
	    		else
	    		{
	    			continue;
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
