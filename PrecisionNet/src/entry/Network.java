package entry;
import java.util.*;
/**
 * Define Network
 * @author wangjue
 *
 */

public class Network {
	int[][] ajMatrix;
//	Node[] nodes;
	public Map<String,Node> nodes;
	Vector<Interaction> interactions;
	Vector<Path> pathes;
	int value;
	Vector valueVec;
	public Network()
	{
		nodes=new HashMap();
	}
	public boolean containsKey(String nodename)
	{
		if(nodes.containsKey(nodename))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public Node getByName(String nodename)
	{
		return nodes.get(nodename);
/*.		for(int i=0;i<nodes.length;i++)
		{
			if(nodes[i].getNodename().equals(nodename))
			{
				return nodes[i];
			}
		}
		Node newnode=new Node(nodename);
		return newnode;
.*/
	}
	public int[][] getAjMatrix() {
		return ajMatrix;
	}
	public void setAjMatrix(int[][] ajMatrix) {
		this.ajMatrix = ajMatrix;
	}
	public Vector<Node> getNodes() {
		Vector<Node> a=new Vector();
	    for(String s:nodes.keySet())
	    {
	    	a.add(nodes.get(s));
	    }
		return a;
	}
	public void addNode(Node node)
	{
		nodes.put(node.getNodename(), node);
/*.		Node[] temp=this.getNodes();
		if(temp==null)
		{
			Node[] current={node};
			this.nodes=current;
		}
		else
		{
			int num=temp.length;
			Node[] current=new Node[num+1];
			for(int i=0;i<num;i++)
			{
				current[i]=nodes[i];
			}
			current[num]=node;
			this.nodes=current;
		}
.*/
	}
//	public void setNodes(Node[] nodes) {
//		this.nodes = nodes;
//	}
	public Vector<Interaction> getInteractions() {
		return interactions;
	}
	public void addInteraction(Interaction ab)
	{
		Vector<Interaction> temp=this.getInteractions();
		if(temp==null)
		{
			Vector<Interaction> current=new Vector();
			current.add(ab);
			this.interactions=current;
		}
		else
		{
			this.interactions.add(ab);
		}
	}
	public void setInteractions(Vector<Interaction> interactions) {
		this.interactions = interactions;
	}
	public Vector<Path> getPathes() {
		return pathes;
	}
	public void setPathes(Vector<Path> pathes) {
		this.pathes = pathes;
	}
	public int getValue() {
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

	

}
