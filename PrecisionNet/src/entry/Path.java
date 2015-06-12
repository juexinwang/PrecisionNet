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
	LinkedList nodes;
	double value;
	Vector valueVec;
	public LinkedList getNodes() {
		return nodes;
	}
	public void setNodes(LinkedList nodes) {
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

}
