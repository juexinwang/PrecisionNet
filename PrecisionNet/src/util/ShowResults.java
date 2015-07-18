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
			String[] t=file.split(".txt");
			String newname=t[0]+"_attri.txt";
			FileWriter writer2 = new FileWriter(newname);
			Hashtable ht=new Hashtable();
		    for(int i=0;i<paths.size();i++)
		    {
			Path current=paths.get(i);
			
			
	            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
	            
//	            for(int j=current.nodes.size()-1;j>=0;j--)
//				{
//					writer.write(current.nodes.get(j).getNodename()+" ");
//				}
	            writer.write("\r\n");
	            
	            int length=current.nodes.size();
	            if (length==1)
	            {
	            	writer.write(current.nodes.get(0).getNodename()+" "+current.nodes.get(0).getNodename());
	            	writer.write("\r\n");
	            	if(ht.containsKey(current.nodes.get(0)))
	            	{}
	            	else
	            	{
	            		ht.put(current.nodes.get(0), 1);
	            		writer2.write(current.nodes.get(0).getNodename()+" "+current.nodes.get(0).flag);
	            		writer2.write("\r\n");
	            	}
	            } 
	            else
	            {
	            	for(int j=length-1;j>=1;j--)
		            {
		            	writer.write(current.nodes.get(j).getNodename()+" "+current.nodes.get(j-1).getNodename());
		            	writer.write("\r\n");
		            	if(ht.containsKey(current.nodes.get(j)))
		            	{}
		            	else
		            	{
		            		ht.put(current.nodes.get(j), 1);
		            		writer2.write(current.nodes.get(j).getNodename()+" "+current.nodes.get(j).flag);
		            		writer2.write("\r\n");
		            	}
		            	if(ht.containsKey(current.nodes.get(j-1)))
		            	{}
		            	else
		            	{
		            		ht.put(current.nodes.get(j-1), 1);
		            		writer2.write(current.nodes.get(j-1).getNodename()+" "+current.nodes.get(j-1).flag);
		            		writer2.write("\r\n");
		            	}
		            }
	            }
	            
	            
	        
			
	//		for(int j=current.nodes.size()-1;j>=0;j--)
	//		{
	//			System.out.print(current.nodes.get(j).getNodename()+" ");
	//		}
	//		System.out.println();
		    }
		    writer2.close();
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
