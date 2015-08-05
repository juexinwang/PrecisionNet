package com;

import java.math.*;
import java.util.*;

import entry.*;

/**
 * First step of the project, shortest path, without considering subjects
 * @author wangjue
 *
 */
public class ShortestPath {
	
	double alphavalue;
	double tmpOpvalue;
	double multiOpvalue;
	double maxpath;
	double confOpvalue;
	double theta;
	
	public ShortestPath(Network network, Hashtable confidenceSet){
		this.alphavalue = 1.0/(network.getNodes().size()+1);
		this.tmpOpvalue = network.getNodes().size();
		this.multiOpvalue=1.0/(confidenceSet.size()+alphavalue)/(Math.log(1/tmpOpvalue));
		this.maxpath=500;
		this.confOpvalue=confidenceSet.size();
		this.theta=1.0/(maxpath-1);
		
	}
	
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
	public Vector<Path> dijkstra(Network net, Hashtable confidenceSet, Vector startPoint, Vector endPoint, String categoryStr)
	{
		for(Map.Entry<Interaction,Integer> i:net.interactions.entrySet())
		{
//			if(confidenceSet.contains(i.getKey().getNodeA().getNodename())&&confidenceSet.contains(i.getKey().getNodeB().getNodename()))
//			{
//				i.getKey().getNodeA().adjNodes.put(i.getKey().getNodeB(), 0);
//			}
//			else if(confidenceSet.contains(i.getKey().getNodeA().getNodename())||confidenceSet.contains(i.getKey().getNodeB().getNodename()))
//			{
//				i.getKey().getNodeA().adjNodes.put(i.getKey().getNodeB(), 1);
//			}
//			else
//			{
//				i.getKey().getNodeA().adjNodes.put(i.getKey().getNodeB(), 2);
//			}
			i.getKey().getNodeA().down.add(i.getKey().getNodeB());
		}
		Vector<Path> paths = new Vector();
		int num=1;
		for(int i=0;i<startPoint.size();i++)
		{
			Node start=new Node();
			Node end=new Node();
			if (!net.containsKey(startPoint.get(i).toString()))
			{
				System.err.printf("Graph doesn't contain start node \"%s\"\n", startPoint.get(i).toString());
		    }
			else
			{
				start=net.getByName(startPoint.get(i).toString());
			}
			for(int j=0;j<endPoint.size();j++)
			{	
				if (!net.containsKey(endPoint.get(j).toString()))
				{
					System.err.printf("Graph doesn't contain end node \"%s\"\n", endPoint.get(j).toString());
			    }
				else
				{
					end=net.getByName(endPoint.get(j).toString());
				}
				Vector<Node> q =new Vector();
				for (Node v : net.nodes.values()) {
	//		         v.previous = v == source ? source : null;
					 v.previous = v.getNodename().equals(start.getNodename())? start : null;
	//				 v.value= v.getNodename().equals(start.getNodename())? 0.1 : 0;
	//				 v.value= (v.getNodename().equals(start.getNodename()))&&(confidenceSet.containsKey(v))? 1 : 0;
					 if(v.getNodename().equals(start.getNodename()))
					 {
						 //old version
						 //v.value=alphavalue;
						 //if(confidenceSet.containsKey(v))
						 //{
							// v.value=1+alphavalue;
						 //}
						 //Initial target function for start genes
						 v.value=targetFuncinitial(v, confidenceSet, categoryStr);
					 }
					 else
					 {
						 v.value=0;
					 }
			         v.num= v.getNodename().equals(start.getNodename())? 1 : 0;
	//		         v.dist = v == source ? 0 : Integer.MAX_VALUE;		         
			         q.add(v);
			      }
				dijkstra(q,categoryStr);
				System.out.println("getting path "+num);
				num++;
				Path p=new Path();
				p.getPath(net, end.getNodename(),confidenceSet);
				String from=p.nodes.get(p.nodes.size()-1).getNodename();
				if(from.equals(start.getNodename()))
				{
					p.value=end.value;
		//			System.out.println(p.value);
					paths.add(p);
				}
				
			}
		}
		
		//TODO
		return paths;
	}
	public void dijkstra(Vector<Node> q, String categoryStr)
	{
		
		Node u;
		while(!q.isEmpty())
		{
			u=pullHighest(q);
			if (u.value == 0) break;
	//		for(Map.Entry<Node, Integer> a : u.adjNodes.entrySet())
			for(Node v : u.down)
			{
				
				if(!q.contains(v))
				{
					continue;
				}
	//			v=a.getKey();
	//			int temp=u.value+a.getValue();
				double temp=targetFunc(u,v,categoryStr);
				if (temp > v.value) { // shorter path to neighbour found
		               q.remove(v);
		               v.value = temp;
		               v.previous = u;
		               v.num=u.num+u.adjNodes.get(v);
		               q.add(v);
		            } 
			}
		}
		
	}
	public Node pullHighest(Vector<Node> q)
	{
		
		int index=0;
		double value=0;
		for(int j=0;j<q.size();j++)
		{
			if(q.get(j).value>=value)
			{
				index=j;
				value=q.get(j).value;
			}
		}
		Node ans=q.get(index);
		q.remove(index);
		return ans;
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
	public Vector<Path> astar(Network net, Hashtable<Node,Integer> confidenceSet, Vector startPoint, Vector endPoint)
	{
		for(Map.Entry<Interaction,Integer> i:net.interactions.entrySet())
		{
			i.getKey().getNodeA().down.add(i.getKey().getNodeB());
			i.getKey().getNodeB().up.add(i.getKey().getNodeA());
		}
		Vector<Path> paths = new Vector();
		for(int i=0;i<startPoint.size();i++)
		{
			Node start=new Node();    
			Node end=new Node();
			if (!net.containsKey(startPoint.get(i).toString()))
			{
				System.err.printf("Graph doesn't contain start node \"%s\"\n", startPoint.get(i).toString());
		    }
			else
			{
				start=net.getByName(startPoint.get(i).toString());
			}
			for(int j=0;j<endPoint.size();j++)
			{		
	//			Vector<Node> tempH=net.getNodes();
	//			Vector<Node> tempG=net.getNodes();
				if (!net.containsKey(endPoint.get(j).toString()))
				{
					System.err.printf("Graph doesn't contain end node \"%s\"\n", endPoint.get(j).toString());
			    }
				else
				{
					end=net.getByName(endPoint.get(j).toString());
				}
				end.h=end.weight;
				end.num=1;
				start.g=start.weight;
				start.num=1;
				heuristicMethod(net, end);
				System.out.println("check1");
				for (Node v : net.nodes.values()) 
				{
					v.previous = v.getNodename().equals(start.getNodename())? start : null;
					v.value= v.g+v.h;
				}
				System.out.println("check2");
				astar(net, start, end, confidenceSet);
				System.out.println("check3");
				Path p=new Path();
				p.getPath(net, end.getNodename(), confidenceSet);
				String from=p.nodes.get(p.nodes.size()-1).getNodename();
				if(from.equals(start.getNodename()))
				{
					p.value=end.value;
					paths.add(p);
				}
						
			}
		}
		
		
		//TODO
		return paths;
	}
	
	public void heuristicMethod(Network net, Node endnode)
	{
		if(!(endnode.up.isEmpty()))
		{
			for(Node i:endnode.up)
			{
	//			int temp=endnode.h+1;
				double temp=targetFunc(endnode,i,"h");
				if(temp<=i.h){}
				else
				{
					i.h=temp;
					i.num=endnode.num+endnode.adjNodes.get(i);
					heuristicMethod(net, i);
				}
				
				
			}
		}
		//TODO
		
	}
	
	public void astar(Network net, Node S, Node E, Hashtable<Node,Integer> C)
	{
		Vector<Node> open=new Vector();
		Vector<Node> close=new Vector();
		open.add(S);
		while(!(close.contains(E))&&!(open.isEmpty()))
		{
			Node highF=new Node();
			highF.value=0;
			for(Node i:open)
			{
				if(i.value>=highF.value)
				{
					highF=i;
				}
			}
			open.remove(highF);
			close.add(highF);
			for(Node j:highF.down)
			{
				if(close.contains(j))continue;
				else if(!(open.contains(j)))
				{
					open.add(j);
					j.previous=highF;
			//		if(C.contains(j.getNodename()))
			//			j.g=lowF.g+0;
			//		else
			//			j.g=lowF.g+3;
					j.g=targetFunc(highF,j,"g");
					j.num=highF.num+1;
					j.value=j.g+j.h;
				}
				else if(open.contains(j))
				{
					double pass;
		//			if(C.contains(j.getNodename()))
		//				pass=lowF.g+0;
		//			else
		//				pass=lowF.g+3;
					pass=targetFunc(highF,j,"g");
					if(pass>j.g)
					{
						j.previous=highF;
						j.g=pass;
						j.num=highF.num+1;
						j.value=j.g+j.h;
					}
				}	
			}
		}
		
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
	public double targetFunc(Node S, Node E, String f)
	{
		double value =0.0;
		if(f.equals("v"))
		{
			if(S.cla.contains(E))
			{
				value=(S.value*S.num+0)/(S.num+S.adjNodes.get(E));
			}
			else
			{
				value=(S.value*S.num+E.weight)/(S.num+S.adjNodes.get(E));
			}
			
		}
		else if(f.equals("g"))
		{
			value=(S.g*S.num+E.weight)/(S.num+S.adjNodes.get(E));
		}
		else if(f.equals("h"))
		{
			value=(S.h*S.num+E.weight)/(S.num+S.adjNodes.get(E));
		}
		else if(f.equals("v1")){//ersoy's idea (Missunderstand)
			if(S.cla.contains(E))
			{
				value=((S.value+S.num/maxpath-theta)*confOpvalue+0)/confOpvalue-(S.num+S.adjNodes.get(E))/maxpath+theta;
			}
			else
			{
				value=((S.value+S.num/maxpath-theta)*confOpvalue+E.weight)/confOpvalue-(S.num+S.adjNodes.get(E))/maxpath+theta;
			}
			
		}
		else if(f.equals("v2")){//more delicate
			if(S.cla.contains(E))
			{
				value=S.value/(Math.log(S.num/tmpOpvalue)+0)*(Math.log((S.num+S.adjNodes.get(E))/tmpOpvalue));
			}
			else
			{
				value=S.value/(Math.log(S.num/tmpOpvalue)+E.weight)*(Math.log((S.num+S.adjNodes.get(E))/tmpOpvalue));
			}
			
		}
		else if(f.equals("v3")){//ersoy's idea
			if(S.cla.contains(E))
			{
				value=((S.value+S.num/maxpath-theta)*S.num+0)/(S.num+S.adjNodes.get(E))-(S.num+S.adjNodes.get(E))/maxpath+theta;
			}
			else
			{
				value=((S.value+S.num/maxpath-theta)*S.num+E.weight)/(S.num+S.adjNodes.get(E))-(S.num+S.adjNodes.get(E))/maxpath+theta;
			}
			
		}
		
	//	E.value=value;
	//	E.num=S.num+1;
		//TODO
		return value;
	}
	
	public double targetFuncinitial(Node v, Hashtable confidenceSet, String f){
		if(f.equals("v")){
			 v.value=alphavalue;
			 if(confidenceSet.containsKey(v))
			 {
				v.value=1+alphavalue;
			 }
			
		}
		else if(f.equals("v1")){
			v.value=1.0/(maxpath-1)-1.0/maxpath;
			 if(confidenceSet.containsKey(v))
			 {
				 v.value=1.0/(maxpath-1)+1.0/confOpvalue-1.0/maxpath;
			 }
		}
		else if(f.equals("v2")){
			v.value=alphavalue*multiOpvalue*(Math.log(1/tmpOpvalue));
			 if(confidenceSet.containsKey(v))
			 {
				 v.value=(1+alphavalue)*multiOpvalue*(Math.log(1/tmpOpvalue));
			 }
			
		}
		else if(f.equals("v3")){
			v.value=1.0/(maxpath-1)-1.0/maxpath;
			 if(confidenceSet.containsKey(v))
			 {
				 v.value=1.0/(maxpath-1)+1.0-1.0/maxpath;
			 }
		}
		return v.value;
		
	}
	
	
}
