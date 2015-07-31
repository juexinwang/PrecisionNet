package entry;

import io.*;

import java.util.*;

import entry.*;

/**
 * Define path
 * @author wangjue
 *
 */

public class Path implements Comparable<Path>{
//	LinkedList<Node> nodes;
	public Vector<Node> nodes=new Vector();
	public double value;
	Vector valueVec;
	Vector<Interaction> interactions;
	
	public int compareTo(Path path)
	{  
		double tem=(path.value - this.value)*10000000;
        return (int)tem;  
    } 
	
	public Vector<Node> getPath(Network net, String nodename) {
		Node end=net.getByName(nodename);
		
		if (end == end.previous) {
            nodes.add(end);
         } else if (end.previous == null) {
            nodes.add(end);
         } else {
        	nodes.add(end);
            this.getPath(net,end.previous.getNodename());
            
         }
		
		return nodes;
	}
	public void setNodes(Vector<Node> nodes) {
		this.nodes = nodes;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public Vector getValueVec() {
		return valueVec;
	}
	public void setValueVec(Vector valueVec) {
		this.valueVec = valueVec;
	}
	public Vector<Interaction> getInteractions() {
		return interactions;
	}
	public void setInteractions(Vector<Interaction> interactions) {
		this.interactions = interactions;
	}

}
