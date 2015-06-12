package entry;
import java.util.*;

/**
 * Define interaction
 * @author wangjue
 *
 */
public class Interaction {
	String nodeA;
	String nodeB;
	int nodeAIndex;
	int nodeBIndex;
	int value;
	Vector valueVec;
	
	public String getNodeA() {
		return nodeA;
	}
	public void setNodeA(String nodeA) {
		this.nodeA = nodeA;
	}
	public String getNodeB() {
		return nodeB;
	}
	public void setNodeB(String nodeB) {
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
