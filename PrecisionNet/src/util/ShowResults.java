package util;
import entry.*;
import java.util.*;
import java.io.*;

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
	public void showPath(Vector<Path> paths, String file){
		try {
			FileWriter writer = new FileWriter(file);
		    for(int i=0;i<paths.size();i++)
		    {
			Path current=paths.get(i);
			
			
	            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
	            
	            for(int j=current.nodes.size()-1;j>=0;j--)
				{
					writer.write(current.nodes.get(j).getNodename()+" ");
				}
	            writer.write("\r\n");
	            
	        
			
	//		for(int j=current.nodes.size()-1;j>=0;j--)
	//		{
	//			System.out.print(current.nodes.get(j).getNodename()+" ");
	//		}
	//		System.out.println();
		    }
		    writer.close();
		} catch (IOException e) {
            e.printStackTrace();
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
