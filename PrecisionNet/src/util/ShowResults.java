package util;
import entry.*;
import java.util.*;

/**
 * show results
 * @author wangjue
 *
 */
public class ShowResults {
	
	/**
	 * show path as results
	 * @param path
	 */
	public void showPath(Vector<Path> paths){
		for(int i=0;i<paths.size();i++)
		{
			Path current=paths.get(i);
			for(int j=0;j<current.nodes.size();j++)
			{
				System.out.print(current.nodes.get(j).getNodename()+" ");
			}
			System.out.println();
		}
		
		//TODO
	}
	
	/**
	 * show path as results
	 * @param path
	 * @param filename
	 */
	public void showPath(Path path, String filename){
		//TODO
	}
	
	/**
	 * show network as results
	 * @param network
	 */
	public void showNetwork(Network network){
		//TODO
	}
	
	/**
	 * show network as results
	 * @param network
	 * @param filename
	 */
	public void showNetwork(Network network, String filename){
		//TODO
	}
	

}
