package entry;
import java.util.*;
/**
 * Define Network
 * @author wangjue
 *
 */

public class Network {
	int[][] ajMatrix;
	Node[] nodes;
	Vector interfaces;
	Vector pathes;
	int value;
	Vector valueVec;
	
	public int[][] getAjMatrix() {
		return ajMatrix;
	}
	public void setAjMatrix(int[][] ajMatrix) {
		this.ajMatrix = ajMatrix;
	}
	public Node[] getNodes() {
		return nodes;
	}
	public void setNodes(Node[] nodes) {
		this.nodes = nodes;
	}
	public Vector getInterfaces() {
		return interfaces;
	}
	public void setInterfaces(Vector interfaces) {
		interfaces = interfaces;
	}
	public Vector getPathes() {
		return pathes;
	}
	public void setPathes(Vector pathes) {
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
