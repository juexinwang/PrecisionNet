package com;




import java.util.*;

import entry.*;

/**
 * First step of the project, shortest path, without considering subjects
 * @author wangjue
 *
 */
public class ShortestPath {
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
	public Vector<Path> dijkstra(Network net, Vector confidenceSet, Vector startPoint, Vector endPoint)
	{
		for(Interaction i : net.getInteractions())
		{
			if(confidenceSet.contains(i.getNodeA().getNodename())&&confidenceSet.contains(i.getNodeB().getNodename()))
			{
				i.getNodeA().adjNodes.put(i.getNodeB(), 0);
			}
			else if(confidenceSet.contains(i.getNodeA().getNodename())||confidenceSet.contains(i.getNodeB().getNodename()))
			{
				i.getNodeA().adjNodes.put(i.getNodeB(), 1);
			}
			else
			{
				i.getNodeA().adjNodes.put(i.getNodeB(), 2);
			}
			
		}
		Vector<Path> paths = new Vector();
		int num=0;
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
			         v.value= v.getNodename().equals(start.getNodename())? 0 : Integer.MAX_VALUE;
	//		         v.dist = v == source ? 0 : Integer.MAX_VALUE;		         
			         q.add(v);
			      }
				dijkstra(q);
				
				Path p=new Path();
				p.getPath(net, end.getNodename());
				paths.add(p);
			}
		}
		
		//TODO
		return paths;
	}
	public void dijkstra(Vector<Node> q)
	{
		Node u, v;
		while(!q.isEmpty())
		{
			u=pullLowest(q);
			if (u.value == Integer.MAX_VALUE) break;
			for(Map.Entry<Node, Integer> a : u.adjNodes.entrySet())
			{
				v=a.getKey();
				int temp=u.value+a.getValue();
				if (temp < v.value) { // shorter path to neighbour found
		               q.remove(v);
		               v.value = temp;
		               v.previous = u;
		               q.add(v);
		            } 
			}
		}
		
	}
	public Node pullLowest(Vector<Node> q)
	{
		
		int index=0;
		int value=Integer.MAX_VALUE;
		for(int j=0;j<q.size();j++)
		{
			if(q.get(j).value<=value)
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
	public Vector<Path> astar(Network net, Vector confidenceSet, Vector startPoint, Vector endPoint)
	{
		for(Interaction i : net.getInteractions())
		{
			i.getNodeA().down.add(i.getNodeB());
			i.getNodeB().up.add(i.getNodeA());
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
			for(int j=0;j<startPoint.size();j++)
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
				end.h=0;
				start.g=0;
				heuristicMethod(net, end);
				for (Node v : net.nodes.values()) 
				{
					v.previous = v.getNodename().equals(start.getNodename())? start : null;
					v.value= v.g+v.h;
				}
				astar(net, start, end, confidenceSet);
				
				Path p=new Path();
				p.getPath(net, end.getNodename());
				paths.add(p);			
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
				int temp=endnode.h+1;
				if(temp>=i.h){}
				else
				{
					i.h=temp;
					heuristicMethod(net, i);
				}
				
				
			}
		}
		//TODO
		
	}
	
	public void astar(Network net, Node S, Node E, Vector C)
	{
		Vector<Node> open=new Vector();
		Vector<Node> close=new Vector();
		open.add(S);
		while(!(close.contains(E))&&!(open.isEmpty()))
		{
			Node lowF=new Node();
			lowF.value=Integer.MAX_VALUE;
			for(Node i:open)
			{
				if(i.value<=lowF.value)
				{
					lowF=i;
				}
			}
			open.remove(lowF);
			close.add(lowF);
			for(Node j:lowF.down)
			{
				if(close.contains(j))continue;
				else if(!(open.contains(j)))
				{
					open.add(j);
					j.previous=lowF;
					if(C.contains(j.getNodename()))
						j.g=lowF.g+0;
					else
						j.g=lowF.g+3;
					j.value=j.g+j.h;
				}
				else if(open.contains(j))
				{
					int pass;
					if(C.contains(j.getNodename()))
						pass=lowF.g+0;
					else
						pass=lowF.g+3;
					if(pass<j.g)
					{
						j.previous=lowF;
						j.g=pass;
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
	public double targetFunc(int[][] adjMatrix, Node[] nodes, Vector confidenceSet, Vector startPoint, Vector endPoint, Path path, int targetFuncTag){
		double value =0.0;
		//TODO
		return value;
	}
	
	
}
