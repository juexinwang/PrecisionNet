package test;
import com.*;
import entry.*;
import io.*;
import util.*;
import java.util.*;

/**
 * Used for Test
 * @author wangjueab
 *
 */

public class SimpleTest {
	
	void doTest(){
		Node nodeA = new Node();
		nodeA.setNodename("A");
		Node nodeB = new Node();
		nodeB.setNodename("B");
		Node nodeC = new Node();
		nodeC.setNodename("C");
		Node nodeD = new Node();
		nodeD.setNodename("D");
		Interaction iAB= new Interaction();
		iAB.setNodeA(nodeA);
		iAB.setNodeB(nodeB);
		Interaction iBC= new Interaction();
		iBC.setNodeA(nodeB);
		iBC.setNodeB(nodeC);
		Interaction iBD= new Interaction();
		iBD.setNodeA(nodeB);
		iBD.setNodeB(nodeD);
		Network network = new Network();
		Node nodes[]={nodeA, nodeB, nodeC, nodeD};
		network.setNodes(nodes);
		System.out.println(network.getNodes()[0].getNodename());
	}
	
	public static void main(String args[]){
		SimpleTest app = new SimpleTest();
		app.doTest();
		//System.out.println("Hello");
	}

}
