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
	String nodename;
	String annotation;
	int value;
	Vector valueVec;
	Vector adjNodes; //adjecent nodes
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
	public Vector getAdjNodes() {
		return adjNodes;
	}
	public void setAdNodes(Vector adjNodes) {
		this.adjNodes = adjNodes;
	}

}
