package entry;
import java.util.*;

/**
 * Define interaction
 * @author wangjue
 *
 */
public class Interaction {
	Node nodeA;
	Node nodeB;
	public String type;
	int nodeAIndex;
	int nodeBIndex;
	public int weight;
	int value;
	Vector valueVec;
	public Interaction()
	{
	}
	public Interaction(Node a, Node b)
	{
		this.nodeA=a;
		this.nodeB=b;
		this.nodeAIndex=a.getIndex();
		this.nodeBIndex=b.getIndex();
	}
	public Node getNodeA() {
		return nodeA;
	}
	public void setNodeA(Node nodeA) {
		this.nodeA = nodeA;
	}
	public Node getNodeB() {
		return nodeB;
	}
	public void setNodeB(Node nodeB) {
		this.nodeB = nodeB;
	}
	public int getNodeAIndex() {
		return nodeAIndex;
	}
	public void setNodeAIndex(int nodeAIndex) {
		this.nodeAIndex = nodeAIndex;
	}
	public int getNodeBIndex() {
		return nodeBIndex;
	}
	public void setNodeBIndex(int nodeBIndex) {
		this.nodeBIndex = nodeBIndex;
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
