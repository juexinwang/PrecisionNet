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
	public void showPath(Vector<Path> paths, String file, Network net, boolean collapse, double percent){
		try {
			Collections.sort(paths);
			
			int per=(int)(paths.size()*percent);
			List<Path> subpaths=paths.subList(0, per);
			
			
			FileWriter writer = new FileWriter(file);
			String[] t=file.split(".txt");
			String newname=t[0]+"_attri.txt";
			FileWriter writer2 = new FileWriter(newname);
			String infoname=t[0]+"_info.txt";
			FileWriter writer3 = new FileWriter(infoname);
			Hashtable ht=new Hashtable();
			
			for(Map.Entry<String, Node> i : net.nodes.entrySet())
			{
			    writer2.write(i.getValue().getNodename()+"&"+i.getValue().flag+"&"+i.getValue().pathway+"&"+i.getValue().gene_name);
			    writer2.write("\r\n");
			}
			
			//no collapse
			if(collapse==false)
			{
				for(int i=0;i<subpaths.size();i++)
			    {
				    Path current=subpaths.get(i);
				    int line=0;
		            int length=current.nodes.size();
		            if (length==1)
		            {
		            	writer.write(current.nodes.get(0).getNodename()+" "+"<self>"+" "+current.nodes.get(0).getNodename()+" .");
		            	writer.write("\r\n");
		            	line++;
		            	
		            } 
		            else
		            {
		            	for(int j=length-1;j>=1;j--)
			            {
		            		if(current.nodes.get(j-1).cla.isEmpty())
		            		{		            		
			            	  writer.write(current.nodes.get(j).getNodename()+" "+net.getInteraction(current.nodes.get(j), current.nodes.get(j-1)).type+" "+current.nodes.get(j-1).getNodename()+" .");
			            	  writer.write("\r\n");
			            	  line++;
		            		}
		            		else
		            		{
		            			int u=j-2;
		            			writer.write(current.nodes.get(j).getNodename()+" "+"<same_class>"+" "+current.nodes.get(u).getNodename()+" .");
				            	writer.write("\r\n");
				            	line++;				            	
				            	j--;
		            		}
			            }
		            }
		            writer3.write(line+" "+String.valueOf(current.value));
		            writer3.write("\r\n");
			    }
			}
			//collapse
			else
			{
				for(int i=0;i<subpaths.size();i++)
			    {
				    Path current=subpaths.get(i);
				    int line=0;
		            int length=current.nodes.size();
					//the path's length is 1
		            if (length==1)
		            {
		            	for(Node n : current.nodes.get(0).becla)
		            	{
		            		writer.write(n.getNodename()+" "+"<cc>"+" "+n.getNodename()+" .");
		            		writer.write("\r\n");
		            		line++;		            		
		            	}		            	
		            } 
		            //the path's length is longer than 1
					else
		            {
		            	Vector<Integer> ind=new Vector();
		            	for(int j=length-1;j>=0;j--)
		            	{
		            		if(!current.nodes.get(j).cla.isEmpty())
		            		{
		            			ind.add(j);
		            		}		
		            	}
		            	if(ind.size()==0)
		            	{
		            		for(int k=length-1;k>0;k--)
			                {
			            	    for(Node n : current.nodes.get(k).becla)
			            		{
			            			for(Node m : current.nodes.get(k-1).becla)
			            		    {
			            				writer.write(n.getNodename()+" "+net.getInteraction(current.nodes.get(k), current.nodes.get(k-1)).type+" "+m.getNodename()+" .");
	        						    writer.write("\r\n");
	        						    line++;
			            			}
			            	    }
			                }
		            	}
		            	if(ind.size()==1)
		            	{
		            		
		            		int pos=ind.get(0);
		            		if((length-1-pos>1)&&(pos-0>1))
		            		{
		            			for(int k=length-1;k>pos+2;k--)
			            		{
			            			for(Node n : current.nodes.get(k).becla)
			            			{
			            				for(Node m : current.nodes.get(k-1).becla)
			            				{
			            					writer.write(n.getNodename()+" "+net.getInteraction(current.nodes.get(k), current.nodes.get(k-1)).type+" "+m.getNodename()+" .");
	        						        writer.write("\r\n");
	        						        line++;
			            				}
			            			}
			            		}
		            			for(Node n : current.nodes.get(pos+2).becla)
		            			{
		            				writer.write(n.getNodename()+" "+net.getInteraction(current.nodes.get(pos+2), current.nodes.get(pos+1)).type+" "+current.nodes.get(pos).getNodename()+" .");
	        						writer.write("\r\n");
	        						line++;
		            			}
		            			for(Node n : current.nodes.get(pos-2).becla)
		            			{
		            				writer.write(current.nodes.get(pos).getNodename()+" "+net.getInteraction(current.nodes.get(pos-1), current.nodes.get(pos-2)).type+" "+n.getNodename()+" .");
	        						writer.write("\r\n");
	        						line++;
		            			}
		            			for(int k=pos-2;k>0;k--)
			            		{
			            			for(Node n : current.nodes.get(k).becla)
			            			{
			            				for(Node m : current.nodes.get(k-1).becla)
			            				{
			            					writer.write(n.getNodename()+" "+net.getInteraction(current.nodes.get(k), current.nodes.get(k-1)).type+" "+m.getNodename()+" .");
	        						        writer.write("\r\n");
	        						        line++;
			            				}
			            			}
			            		}
		            			
		            		}
		            		else if(length-1-pos>1)
		            		{
		            			for(int k=length-1;k>pos+2;k--)
			            		{
			            			for(Node n : current.nodes.get(k).becla)
			            			{
			            				for(Node m : current.nodes.get(k-1).becla)
			            				{
			            					writer.write(n.getNodename()+" "+net.getInteraction(current.nodes.get(k), current.nodes.get(k-1)).type+" "+m.getNodename()+" .");
	        						        writer.write("\r\n");
	        						        line++;
			            				}
			            			}
			            		}
		            			for(Node n : current.nodes.get(pos+2).becla)
		            			{
		            				writer.write(n.getNodename()+" "+net.getInteraction(current.nodes.get(pos+2), current.nodes.get(pos+1)).type+" "+current.nodes.get(pos).getNodename()+" .");
	        						writer.write("\r\n");
	        						line++;
		            			}
		            		}
		            		else if(pos-0>1)
		            		{
		            			for(Node n : current.nodes.get(pos-2).becla)
		            			{
		            				writer.write(current.nodes.get(pos).getNodename()+" "+net.getInteraction(current.nodes.get(pos-1), current.nodes.get(pos-2)).type+" "+n.getNodename()+" .");
	        						writer.write("\r\n");
	        						line++;
		            			}
		            			for(int k=pos-2;k>0;k--)
			            		{
			            			for(Node n : current.nodes.get(k).becla)
			            			{
			            				for(Node m : current.nodes.get(k-1).becla)
			            				{
			            					writer.write(n.getNodename()+" "+net.getInteraction(current.nodes.get(k), current.nodes.get(k-1)).type+" "+m.getNodename()+" .");
	        						        writer.write("\r\n");
	        						        line++;
			            				}
			            			}
			            		}
		            		}
		            		else
		            		{
		            			writer.write(current.nodes.get(pos).getNodename()+" "+"<cc>"+" "+current.nodes.get(pos).getNodename()+" .");
        						writer.write("\r\n");
        						line++;
		            		}
		            		
		            		
		            	}
		            	else
		            	{
		            		int num=ind.size();
		            		for(int s=0;s<=num-1;s++)
		            		{
		            			int pos=ind.get(s);
		            			if(s==0)
		            			{
								    int nextpos=ind.get(s+1);
		            				if((length-1-pos>1)&&(pos-nextpos>2))
		            		        {
		            			        for(int k=length-1;k>pos+2;k--)
			            		        {
			            			        for(Node n : current.nodes.get(k).becla)
			            			        {
			            				        for(Node m : current.nodes.get(k-1).becla)
			            				        {
			            					        writer.write(n.getNodename()+" "+net.getInteraction(current.nodes.get(k), current.nodes.get(k-1)).type+" "+m.getNodename()+" .");
	        						                writer.write("\r\n");
	        						                line++;
			            				        }
			            			        }
			            		        }
		            			        for(Node n : current.nodes.get(pos+2).becla)
		            			        {
		            				        writer.write(n.getNodename()+" "+net.getInteraction(current.nodes.get(pos+2), current.nodes.get(pos+1)).type+" "+current.nodes.get(pos).getNodename()+" .");
	        						        writer.write("\r\n");
	        						        line++;
		            			        }
										int diff=pos-nextpos;
										if(diff==3)
										{
										    writer.write(current.nodes.get(pos).getNodename()+" "+net.getInteraction(current.nodes.get(pos-1), current.nodes.get(pos-2)).type+" "+current.nodes.get(nextpos).getNodename()+" .");
	        						        writer.write("\r\n");
	        						        line++;
										}
										else
										{
										    for(Node n : current.nodes.get(pos-2).becla)
		            			            {
		            				            writer.write(current.nodes.get(pos).getNodename()+" "+net.getInteraction(current.nodes.get(pos-1), current.nodes.get(pos-2)).type+" "+n.getNodename()+" .");
	        						            writer.write("\r\n");
	        						            line++;
		            			            }
											List<Node> sub=current.nodes.subList(nextpos+2,pos-1);
											if(sub.size()>=2)
											{
											    for(int o=sub.size()-1;o>0;o--)
											   {
											      int j=o-1;
												  for(Node n : sub.get(o).becla)
		            			                  {
												     for(Node m : sub.get(j).becla)
													 {
													    writer.write(n.getNodename()+" "+net.getInteraction(sub.get(o), sub.get(j)).type+" "+m.getNodename()+" .");
	        						                    writer.write("\r\n");
	        						                    line++;
													 }
		            				                 
		            			                  }
											   }
											}
											
											for(Node n : current.nodes.get(nextpos+2).becla)
		            			            {
		            				            writer.write(n.getNodename()+" "+net.getInteraction(current.nodes.get(nextpos+2), current.nodes.get(nextpos+1)).type+" "+current.nodes.get(nextpos).getNodename()+" .");
	        						            writer.write("\r\n");
	        						            line++;
		            			            }
							
										}
		            		        }
									else if(length-1-pos>1)
									{
									   for(int k=length-1;k>pos+2;k--)
			            		        {
			            			        for(Node n : current.nodes.get(k).becla)
			            			        {
			            				        for(Node m : current.nodes.get(k-1).becla)
			            				        {
			            					        writer.write(n.getNodename()+" "+net.getInteraction(current.nodes.get(k), current.nodes.get(k-1)).type+" "+m.getNodename()+" .");
	        						                writer.write("\r\n");
	        						                line++;
			            				        }
			            			        }
			            		        }
		            			        for(Node n : current.nodes.get(pos+2).becla)
		            			        {
		            				        writer.write(n.getNodename()+" "+net.getInteraction(current.nodes.get(pos+2), current.nodes.get(pos+1)).type+" "+current.nodes.get(pos).getNodename()+" .");
	        						        writer.write("\r\n");
	        						        line++;
		            			        }
										writer.write(current.nodes.get(pos).getNodename()+" "+"<cc>"+" "+current.nodes.get(nextpos).getNodename()+" .");
	        						    writer.write("\r\n");
	        						    line++;
									}
									else if(pos-nextpos>2)
									{
									    int diff=pos-nextpos;
										if(diff==3)
										{
										    writer.write(current.nodes.get(pos).getNodename()+" "+net.getInteraction(current.nodes.get(pos-1), current.nodes.get(pos-2)).type+" "+current.nodes.get(nextpos).getNodename()+" .");
	        						        writer.write("\r\n");
	        						        line++;
										}
										else
										{
										    for(Node n : current.nodes.get(pos-2).becla)
		            			            {
		            				            writer.write(current.nodes.get(pos).getNodename()+" "+net.getInteraction(current.nodes.get(pos-1), current.nodes.get(pos-2)).type+" "+n.getNodename()+" .");
	        						            writer.write("\r\n");
	        						            line++;
		            			            }
											List<Node> sub=current.nodes.subList(nextpos+2,pos-1);
											if(sub.size()>=2)
											{
											    for(int o=sub.size()-1;o>0;o--)
											   {
											      int j=o-1;
												  for(Node n : sub.get(o).becla)
		            			                  {
												     for(Node m : sub.get(j).becla)
													 {
													    writer.write(n.getNodename()+" "+net.getInteraction(sub.get(o), sub.get(j)).type+" "+m.getNodename()+" .");
	        						                    writer.write("\r\n");
	        						                    line++;
													 }
		            				                 
		            			                  }
											   }
											}
											
											for(Node n : current.nodes.get(nextpos+2).becla)
		            			            {
		            				            writer.write(n.getNodename()+" "+net.getInteraction(current.nodes.get(nextpos+2), current.nodes.get(nextpos+1)).type+" "+current.nodes.get(nextpos).getNodename()+" .");
	        						            writer.write("\r\n");
	        						            line++;
		            			            }
							
										}
									}
									else
									{
									    writer.write(current.nodes.get(pos).getNodename()+" "+"<cc>"+" "+current.nodes.get(nextpos).getNodename()+" .");
	        						    writer.write("\r\n");
	        						    line++;
									}
		            			}
		            			else if(s==num-1)
		            			{
		            				if(pos-0>1)
									{
									    for(Node n : current.nodes.get(pos-2).becla)
		            			        {
		            				        writer.write(current.nodes.get(pos).getNodename()+" "+net.getInteraction(current.nodes.get(pos-1), current.nodes.get(pos-2)).type+" "+n.getNodename()+" .");
	        						        writer.write("\r\n");
	        						        line++;
		            			        }
									    for(int k=pos-2;k>0;k--)
			            		        {
			            			        for(Node n : current.nodes.get(k).becla)
			            			        {
			            				        for(Node m : current.nodes.get(k-1).becla)
			            				        {
			            					        writer.write(n.getNodename()+" "+net.getInteraction(current.nodes.get(k), current.nodes.get(k-1)).type+" "+m.getNodename()+" .");
	        						                writer.write("\r\n");
	        						                line++;
			            				        }
			            			        }
			            		        }         			        
									}
									else
									{
									    
									}
		            			}
		            			else
		            			{
								    int nextpos=ind.get(s+1);									
		            				if(pos-nextpos>2)
									{
									    int diff=pos-nextpos;
										if(diff==3)
										{
										    writer.write(current.nodes.get(pos).getNodename()+" "+net.getInteraction(current.nodes.get(pos-1), current.nodes.get(pos-2)).type+" "+current.nodes.get(nextpos).getNodename()+" .");
	        						        writer.write("\r\n");
	        						        line++;
										}
										else
										{
										    for(Node n : current.nodes.get(pos-2).becla)
		            			            {
		            				            writer.write(current.nodes.get(pos).getNodename()+" "+net.getInteraction(current.nodes.get(pos-1), current.nodes.get(pos-2)).type+" "+n.getNodename()+" .");
	        						            writer.write("\r\n");
	        						            line++;
		            			            }
											List<Node> sub=current.nodes.subList(nextpos+2,pos-1);
											if(sub.size()>=2)
											{
											    for(int o=sub.size()-1;o>0;o--)
											   {
											      int j=o-1;
												  for(Node n : sub.get(o).becla)
		            			                  {
												     for(Node m : sub.get(j).becla)
													 {
													    writer.write(n.getNodename()+" "+net.getInteraction(sub.get(o), sub.get(j)).type+" "+m.getNodename()+" .");
	        						                    writer.write("\r\n");
	        						                    line++;
													 }
		            				                 
		            			                  }
											   }
											}
											
											for(Node n : current.nodes.get(nextpos+2).becla)
		            			            {
		            				            writer.write(n.getNodename()+" "+net.getInteraction(current.nodes.get(nextpos+2), current.nodes.get(nextpos+1)).type+" "+current.nodes.get(nextpos).getNodename()+" .");
	        						            writer.write("\r\n");
	        						            line++;
		            			            }
							
										}
									}
									else
									{
									    writer.write(current.nodes.get(pos).getNodename()+" "+"<cc>"+" "+current.nodes.get(nextpos).getNodename()+" .");
	        						    writer.write("\r\n");
	        						    line++;
									}
		            			}
		            		}
		            	}
	            		
			            }
		            
		            writer3.write(line+" "+String.valueOf(current.value));
		            writer3.write("\r\n");
			    }
			}
				
		    
		    writer2.close();
		    writer3.close();
		    writer.close();
		} catch (IOException e) {
            e.printStackTrace();
        }
		
		//TODO
	}
	public void showMulti(Vector<Path> paths,String dir, String file, Network net, boolean collapse,double[] multi)
	{
		for(int i=0;i<multi.length;i++)
		{
			int t=(int)(multi[i]*100);
			String tem=String.valueOf(t);
			String name=dir+"top"+tem+"percent_"+file;
			showPath(paths,name,net,collapse,multi[i]);
		}
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
