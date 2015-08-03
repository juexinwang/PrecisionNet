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
//	Vector<Interaction> interactions;
	public Hashtable<Interaction,Integer> interactions=new Hashtable();
	Vector<Path> pathes;
	int value;
	Vector valueVec;
	public Network()
	{
		nodes=new HashMap();
	}
	public Interaction getInteraction(Node a, Node b)
	{
		Interaction r=new Interaction();
		for(Map.Entry<Interaction, Integer> i : this.interactions.entrySet())
		{
			if((i.getKey().getNodeA()==a)&&(i.getKey().getNodeB()==b))
			{
				r=i.getKey();
			}
		}
		return r;
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
	public Map getNodes() {
		return this.nodes;
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
//	public Set<Interaction> getInteractions() {
//		return interactions.keySet();
//		
//	}
	public void addInteraction(Interaction ab, int i)
	{
		
		if(this.interactions.containsKey(ab))
		{
		}
		else
		{
			this.interactions.put(ab, i);
		}
	}
//	public void setInteractions(Vector<Interaction> interactions) {
//		this.interactions = interactions;
//	}
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
