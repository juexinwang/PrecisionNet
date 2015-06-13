package entry;
import io.*;
import java.util.*;
import entry.*;

/**
 * Define path
 * @author wangjue
 *
 */

public class Path {
	LinkedList<Node> nodes;
	double value;
	Vector valueVec;
	Vector<Interaction> interactions;
	
	public LinkedList<Node> getNodes() {
		return nodes;
	}
	public void setNodes(LinkedList<Node> nodes) {
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
