package entry;
import io.*;
import java.util.*;
import entry.*;

/**
 * Define Node
 * @author wangjue
 *
 */

public class Node {
	public String flag="";
	String nodename;
	String annotation;
	public int weight;
	public int num;
	public int index;
	public double value;
	public double g;
	public double h;
	Vector valueVec;
	public Node previous;
	public Vector<Node> down=new Vector();
	public Vector<Node> up=new Vector();
	public Map<Node,Integer> adjNodes=new HashMap(); //adjecent nodes
	public Node(String name)
	{
		this.nodename=name;
	}
	public Node()
	{
		
	}
	public int getIndex()
	{
		return this.index;
	}
	public void setIndex(int index)
	{
		this.index=index;
	}
	public String getNodename() {
		return nodename;
	}
	public void setNodename(String nodename) {
		this.nodename = nodename;
	}
	public String getAnnotation() {
		return annotation;
	}
	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}
	public double getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public Vector getValueVec() {
		return valueVec;
	}
	public void setValueVec(Vector valueVec) {
		this.valueVec = valueVec;
	}
//	public Vector getAdjNodes() {
//		return adjNodes;
//	}
//	public void setAdNodes(Vector adjNodes) {
//		this.adjNodes = adjNodes;
//	}

}
