package util;
import entry.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
	public void showPath(Vector<Path> paths, String file, Network net, boolean collapse, double percent, boolean pathflag){
		try {
	//		Collections.sort(paths);		
			List<Path> rename_paths=paths.subList(0, paths.size());
			System.out.println(paths.size());
			if(!pathflag)
			{		
				Collections.sort(rename_paths,new Comparator<Path>(){
		            public int compare(Path arg0, Path arg1) {
		//                return arg1.value.compareTo(arg0.value);
		            	double tem=(arg1.getValue() - arg0.getValue())*10000000;
		              return (int)tem;
		            }
		        });
			}
			
			
			//Use pathflag, if true(v4), we sort Increment, or decrement 
			if(pathflag){
	//			Collections.reverse(paths);
				Collections.sort(rename_paths,new Comparator<Path>(){
		            public int compare(Path arg0, Path arg1) {
		//                return arg1.value.compareTo(arg0.value);
		            	double tem=(arg0.value - arg1.value)*10000000;
		              return (int)tem;
		            }
		        });
			}
			
			int per=(int)(paths.size()*percent);
			List<Path> subpaths=rename_paths.subList(0, per);
			
			
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
		            writer3.write(line+" "+String.valueOf(current.value)+" "+current.confinum);
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
	
	/**
	 * 
	 * @param paths
	 * @param dir
	 * @param file
	 * @param net
	 * @param collapse
	 * @param multi
	 * @param pathflag v4 is true collect smallest path; or collect largest
	 */
	public void showMulti(Vector<Path> paths,String dir, String file, Network net, boolean collapse,double[] multi,boolean pathflag)
	{
		for(int i=0;i<multi.length;i++)
		{
			int t=(int)(multi[i]*100);
			String tem=String.valueOf(t);
			String name=dir+"top"+tem+"percent_"+file;
			showPath(paths,name,net,collapse,multi[i],pathflag);
		}
	}
	
	public void showPersonalPath(Vector<Path> paths, String indir, String outdir, Hashtable<Node, Integer> confi, Network net)
	{
		System.out.println(confi.size());
		try{
	//	  String path="d:/downloads/";
		  File file=new File(indir);
		  File[] tempList = file.listFiles();
		  System.out.println("该目录下对象个数："+tempList.length);
		  for (int i = 0; i < tempList.length; i++) 
		  {
			  if (tempList[i].isFile()) 
			  {
				  System.out.println("文     件："+tempList[i]);
				  String[] tem=tempList[i].toString().split("\\\\");
				  String name=tem[tem.length-1];
				  String[] t=name.split(".csv");
				  String netfile=outdir+t[0]+".txt";
				  String infofile=outdir+t[0]+"_info.txt";
				  FileWriter writer = new FileWriter(netfile);
				  FileWriter writer3 = new FileWriter(infofile);
				  
				  File rfile=new File(tempList[i].toString());
				  BufferedReader reader = new BufferedReader(new FileReader(rfile));
				  String tempString = null;
				  
				  Vector<String> vec=new Vector();
				  String pattern = ",(hsa:\\d*),";
		    	  Pattern r=Pattern.compile(pattern);
		    	  Hashtable phsa=new Hashtable();
				  while ((tempString = reader.readLine()) != null) 
		    	  {
					  Matcher m = r.matcher(tempString);
					  if(m.find())
		    			{
		    				tempString=m.group(1);
		    				String pattern2 =":";
		    				String replace="_";
		    				Pattern r2=Pattern.compile(pattern2);
		    				Matcher m2=r2.matcher(tempString);
		    				tempString=m2.replaceAll(replace);
		    				phsa.put(tempString, 1);
		    				System.out.println(tempString);
		    			}
		    			else
		    			{
		    				continue;
		    			}
	//				  if((net.nodes.containsKey(tempString))&&(confi.contains(net.getByName(tempString))))
	//				  {
	//					  vec.add(tempString);
	//				  }
		    	    	
		    	  }
				 
				  Pattern p=Pattern.compile("Gene_(hsa_[^_>]*?)[_>]");			        
				  for(Map.Entry<Node, Integer> x : confi.entrySet())
				  {
	//				  System.out.println(x.getKey().getNodename());
					  Matcher pm=p.matcher(x.getKey().getNodename());
					  while(pm.find())
					  {
		//				  System.out.println(1);
						  if(phsa.containsKey(pm.group(1)))
						  {
							  System.out.println(x.getKey().getNodename());
							  vec.add(x.getKey().getNodename());
						  }
					  }
					  
				  }//personal confidence set
				  System.out.println("personal confidence");
				  
				  List<Path> subpaths=new ArrayList();
				  for(int j=0;j<paths.size();j++)
				  {
					  double personconfi=0;
					  for(int w=0;w<vec.size();w++)
					  {
						  if(paths.get(j).nodes.contains(net.getByName(vec.get(w))))
						  {
							  if(!subpaths.contains(paths.get(j)))
							  {
								  subpaths.add(paths.get(j));
							  }
							  paths.get(j).percent=(++personconfi)/(paths.get(j).confinum);
						  }
					  }
					  
				  }//personal paths
				  System.out.println("personal paths");
				  
				  Collections.sort(subpaths,new Comparator<Path>(){
			            public int compare(Path arg0, Path arg1) {
			            	double tem=(arg1.percent - arg0.percent)*10000000;
			              return (int)tem;
			            }
			        });//sort personal paths, from big to small(arg1 first...)
				  
				  
				  for(int j=0;j<subpaths.size();j++)
				    {
					    Path current=subpaths.get(j);
					    int line=0;
			            int length=current.nodes.size();
			            if (length==1)
			            {
			            	writer.write(current.nodes.get(0).getNodename()+" "+"<self>"+" "+current.nodes.get(0).getNodename()+" ."+" "+t[0]);
			            	writer.write("\r\n");
			            	line++;
			            	
			            } 
			            else
			            {
			            	for(int w=length-1;w>=1;w--)
				            {
			            		if(current.nodes.get(w-1).cla.isEmpty())
			            		{		            		
				            	  writer.write(current.nodes.get(w).getNodename()+" "+net.getInteraction(current.nodes.get(w), current.nodes.get(w-1)).type+" "+current.nodes.get(w-1).getNodename()+" ."+" "+t[0]);
				            	  writer.write("\r\n");
				            	  line++;
			            		}
			            		else
			            		{
			            			int u=w-2;
			            			writer.write(current.nodes.get(w).getNodename()+" "+"<same_class>"+" "+current.nodes.get(u).getNodename()+" ."+" "+t[0]);
					            	writer.write("\r\n");
					            	line++;				            	
					            	w--;
			            		}
				            }
			            }
			    //        writer3.write(line+" "+String.valueOf(current.value)+" "+current.confinum);
			            writer3.write(line+" "+String.valueOf(current.percent));
			            writer3.write("\r\n");
				    }
				writer.close();
				writer3.close();
				  
			  }
			  else
			  {
				  System.out.println(333);
			  }
			  
		   
		  }//for each personal information
		 
		}catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	/**
	 * output paths in jason file type, directly call from main function
	 * @param paths
	 * @param file
	 * @param net
	 * @param collapse
	 * @param percent
	 * @param pathflag
	 */
	public void showPath2Jason(Vector<Path> paths, String file, Network net, boolean collapse, double percent, boolean pathflag){
		try {
//			Collections.sort(paths);		
				List<Path> rename_paths=paths.subList(0, paths.size());
				Collections.sort(rename_paths,new Comparator<Path>(){
		            public int compare(Path arg0, Path arg1) {
		//                return arg1.value.compareTo(arg0.value);
		            	double tem=(arg1.value - arg0.value)*10000000;
		              return (int)tem;
		            }
		        });
				
				//Use pathflag, if true(v4), we sort Increment, or decrement 
				if(pathflag){
		//			Collections.reverse(paths);
					Collections.sort(rename_paths,new Comparator<Path>(){
			            public int compare(Path arg0, Path arg1) {
			//                return arg1.value.compareTo(arg0.value);
			            	double tem=(arg0.value - arg1.value)*10000000;
			              return (int)tem;
			            }
			        });
				}
				
				int per=(int)(paths.size()*percent);
				List<Path> subpaths=rename_paths.subList(0, per);
			
			
			
			HashMap hm = new HashMap();
			HashMap hmm = new HashMap();
			FileWriter writer = new FileWriter(file);			
			
			//no collapse
			int nodeNumber=0;
			StringBuilder pathString = new StringBuilder();
			pathString.append("\n],\"edges\":[\n");
			if(collapse==false)
			{
				for(int i=0;i<subpaths.size();i++)
			    {
				    Path current=subpaths.get(i);
				    int line=0;
		            int length=current.nodes.size();
		            if (length==1)
		            {
		            	String tmpstr = current.nodes.get(0).getNodename();
		            	tmpstr=tmpstr.split("#")[1].split(">")[0];
		            	if(!hm.containsKey(tmpstr)){
		            		hm.put(tmpstr, nodeNumber);
		            		hmm.put(nodeNumber,tmpstr);
		            		nodeNumber++;
		            	}
		            	pathString.append("{\"source\":"+hm.get(tmpstr)+",\"target\":"+hm.get(tmpstr)+",\"relation\":\"self\"},\n");
		            	line++;
		            	
		            } 
		            else
		            {
		            	for(int j=length-1;j>=1;j--)
			            {
		            		if(current.nodes.get(j-1).cla.isEmpty())
		            		{	
		            			String tmpstr = current.nodes.get(j).getNodename();
		            			tmpstr=tmpstr.split("#")[1].split(">")[0];
		            			String tmpstr1 =current.nodes.get(j-1).getNodename();
		            			tmpstr1=tmpstr1.split("#")[1].split(">")[0];
		            			String tmpstr2 =net.getInteraction(current.nodes.get(j), current.nodes.get(j-1)).type;
		            			tmpstr2=tmpstr2.split("#")[1].split(">")[0];
		            			if(!hm.containsKey(tmpstr)){
				            		hm.put(tmpstr, nodeNumber);
				            		hmm.put(nodeNumber,tmpstr);
				            		nodeNumber++;
				            	}
		            			if(!hm.containsKey(tmpstr1)){
				            		hm.put(tmpstr1, nodeNumber);
				            		hmm.put(nodeNumber,tmpstr1);
				            		nodeNumber++;
				            	}
		            			pathString.append("{\"source\":"+hm.get(tmpstr)+",\"target\":"+hm.get(tmpstr1)+",\"relation\":\""+tmpstr2+"\"},\n");
			            	  line++;
		            		}
		            		else
		            		{
		            			int u=j-2;
		            			String tmpstr = current.nodes.get(j).getNodename();
		            			tmpstr=tmpstr.split("#")[1].split(">")[0];
		            			String tmpstr1 =current.nodes.get(u).getNodename();
		            			tmpstr1=tmpstr1.split("#")[1].split(">")[0];
		            			if(!hm.containsKey(tmpstr)){
				            		hm.put(tmpstr, nodeNumber);
				            		hmm.put(nodeNumber,tmpstr);
				            		nodeNumber++;
				            	}
		            			if(!hm.containsKey(tmpstr1)){
				            		hm.put(tmpstr1, nodeNumber);
				            		hmm.put(nodeNumber,tmpstr1);
				            		nodeNumber++;
				            	}
		            			pathString.append("{\"source\":"+hm.get(tmpstr)+",\"target\":"+hm.get(tmpstr1)+",\"relation\":\"same_class\"},\n");
				            	line++;				            	
				            	j--;
		            		}
			            }
		            }
		            
			    }
			}
			//collapse
			else
			{
				//TODO
				//comes from the original function showpath
			    
			}
			pathString.append("]\n}\n");
			
			
			StringBuilder nodeString=new StringBuilder();
			nodeString.append("{\n\"nodes\":[\n");

			for(int i=0;i<nodeNumber;i++){
				 nodeString.append("{ \"name\":\""+hmm.get(i)+"\",\"type\":\""+"\"},\n");
			}			
			
			writer.write(nodeString.toString());
			writer.write(pathString.toString());
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
