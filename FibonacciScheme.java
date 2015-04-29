
package ads_project_package;

import java.io.*;
import java.util.*;


/**
 * @author ROHINI
 * @UFID 67308301
 * @CLASS FibonacciScheme
 * @DESCRIPTION This generates the Prim's minimum cost spanning tree using Fibonacci Heap 
 */
public class FibonacciScheme{
	public HeapNode minHeap=new HeapNode();
	public HeapNode startNode=new HeapNode();
	public HeapNode lastNode=new HeapNode();
	public int noEdges;
	public int noVerts;
	HashMap<String, Integer> treeData=new HashMap<String, Integer>();
	HashMap<String, Integer> verV=new HashMap<String, Integer>();
	int[][] edgeList;
	
	
	public FibonacciScheme() {
		minHeap=null;
		startNode=null;
	}
	
	
	public boolean heapify(int[][] matrix, int noV,int noE) throws IOException{
		
		edgeList=getData(matrix,noV,noE);
		Random rand=new Random();
		
		
		addNodeMatrix(rand.nextInt(noV-1)+1, edgeList);
		int totalCost=0;
		int vertVisted=1;
		HeapNode temp=removeMin();
		String path="";
		
		
		while(vertVisted<noVerts){
			
			if(treeData.containsKey(temp.from+"-"+temp.to)||treeData.containsKey(treeData.get(temp.to+"-"+temp.from))){
				System.out.println("Already Visited: "+temp.from+"-->"+temp.to);
				temp=removeMin();
			}else{
				
				if(treeData.containsKey(Integer.toString(temp.from))&&treeData.containsKey(Integer.toString(temp.to))){
					System.out.println("Loop Nodes"+temp.from+"-->"+temp.to +" count: "+vertVisted);
					temp=removeMin();
				}else{
					
					treeData.put(temp.from+"-"+temp.to, temp.data);	
					treeData.put(Integer.toString(temp.from), 0);
					treeData.put(Integer.toString(temp.to), 0);
					totalCost=totalCost+temp.data;
					vertVisted++;
					
				
					path=path+"--"+temp.from+"->"+temp.to;
					addNodeMatrix(temp.to, edgeList);
					
					if(vertVisted==noVerts){
						break;
					}else{
						temp=removeMin();
					}
					
				}
				
			}
		}
		
		System.out.println("\n**********************");
		System.out.println("Final fibonacci Output");
		System.out.println("Cost: "+totalCost);
		
		String[] p=path.split("--");
		for(int i=0;i<p.length;i++){
			System.out.println(p[i]);
		}
		System.out.println("");
		return true;
	}
	
	
	public boolean heapify(String filePath) throws IOException{
		
		edgeList=getData(filePath);
		Random rand=new Random();
		
		addNode(1, edgeList[1]);
		int totalCost=0;
		int vertVisted=1;
		HeapNode temp=removeMin();
		String path="";
		while(vertVisted<noVerts-1){
			
			if(treeData.containsKey(temp.from+"-"+temp.to)||treeData.containsKey(treeData.get(temp.to+"-"+temp.from))){
				System.out.println("Already Visited: "+temp.from+"-"+temp.to);
				temp=removeMin();
			}else{
				
				if(treeData.containsKey(Integer.toString(temp.from))&&treeData.containsKey(Integer.toString(temp.to))){
					System.out.println("Loop return");
					temp=removeMin();
				}else{
					
					treeData.put(temp.from+"-"+temp.to, temp.data);	
					treeData.put(Integer.toString(temp.from), 0);
					treeData.put(Integer.toString(temp.to), 0);
					totalCost=totalCost+temp.data;
					vertVisted++;
					System.out.println("Path: "+temp.from+"->"+temp.to+" data: "+temp.data+" no. of vertVisted: "+vertVisted+" cost: "+totalCost);
					
					path=path+"--"+temp.from+"->"+temp.to;
					addNode(temp.to, edgeList[temp.to]);
					
					temp=removeMin();

				}
				
			}
		}
		
		System.out.println("\n**********************");
		System.out.println("Final fibonacci Output");
		System.out.println("Cost: "+totalCost);
		String[] p=path.split("--");
		for(int i=0;i<p.length;i++){
			System.out.println(p[i]);
		}
		System.out.println("");
		return true;
	}
	
	
	public boolean ifPresent(int i,int j){
		if(verV.containsKey(i+"-"+j)||verV.containsKey(j+"-"+i)){
				
			return true;
		}else{
			return false;
		}
		
	}
	
	
	public int[][] getData(String filePath) throws IOException{
		BufferedReader input = new BufferedReader(new FileReader(filePath));
		int i=0;
		String stringInput = input.readLine();
		String[] stringSplit=stringInput.split(" ");
		noVerts=Integer.parseInt(stringSplit[0]);
		noEdges=Integer.parseInt(stringSplit[1]);
		int[][] vertEdge=new int[noVerts+1][noVerts+1];
		String key;
		
		System.out.println("No. of Vertices: "+noVerts+"\n No. Edges: "+noEdges);
		System.out.println("Reading Edges from file: "+filePath);
		
		
		for(int jj=0;jj<vertEdge.length;jj++){
			Arrays.fill(vertEdge[jj],-100);
			System.out.println("VertEdge Size"+vertEdge.length);
		}
		int vert1,vert2;
		
		for(i=0;i<noEdges;i++){
			stringSplit=input.readLine().split(" ");
			vert1=Integer.parseInt(stringSplit[0]);
			vert2=Integer.parseInt(stringSplit[1]);
			vertEdge[vert1][vert2]=Integer.parseInt(stringSplit[2]);
			vertEdge[vert2][vert1]=Integer.parseInt(stringSplit[2]);
			key=vert1+"-"+vert2;
			
		}
		edgeList=vertEdge;
		input.close();
		return vertEdge;
	}
	
	
	public int[][] getData(int[][] matrix, int v,int e) {
		int i=0,j=0;
		
		noVerts=v;
		noEdges=e;
		int[][] vertEdge=new int[matrix.length][matrix.length];
		String key;
		
		System.out.println("No. of Vertices: "+noVerts+"\n No. Edges: "+noEdges);
		System.out.println("Reading Edges from Random Matrix ");
		
		for(i=0;i<noVerts;i++){
			for(j=0;j<noVerts;j++){
				if(matrix[i][j]!=-100){
					int data=matrix[i][j];
					vertEdge[i][j]=data;
					vertEdge[j][i]=data;
					key=i+"-"+j;
					
				}else{
					vertEdge[i][j]=-100;
					vertEdge[j][i]=-100;
				}
			}
		}
		edgeList=vertEdge;
		return matrix;
	}
	
	
	public void addNode(int randNode,int[] data){
		int val=0;
		for(int i=1;i<data.length;i++){
			val=data[i];
			
			if(!ifPresent(i, randNode)){
				
				if(val!=-100){
					String key=randNode+"-"+i;
					
					verV.put(key,val);	
					
					HeapNode temp=new HeapNode(val, randNode, i);
					System.out.println("Creating Node"+temp.data);
					
					
					if(startNode==null){
						temp.loopNode=temp;
						startNode=temp;
						startNode.loopNode=temp;
						startNode.prevNode=temp;
						minHeap=temp;
						lastNode=temp;
						
					}else{
						if(startNode.loopNode==lastNode){
							if(minHeap.data>val){
								minHeap=temp;
								temp.prevNode=lastNode;
								lastNode.loopNode=temp;
								temp.loopNode=startNode;
								lastNode=temp;
								startNode.prevNode=temp;
							}else{
								temp.prevNode=lastNode;
								lastNode.loopNode=temp;
								temp.loopNode=startNode;
								lastNode=temp;
								startNode.prevNode=temp;
							}
						}else{
							if(minHeap.data>val){
								minHeap=temp;
								temp.prevNode=lastNode;
								lastNode.loopNode=temp;
								temp.loopNode=startNode;
								lastNode=temp;
								startNode.prevNode=temp;
							}else{
								temp.prevNode=lastNode;
								lastNode.loopNode=temp;
								temp.loopNode=startNode;
								lastNode=temp;
								startNode.prevNode=temp;
							}
						}
					}
				}
			}else{
				System.out.println("Path already added:"+randNode+"-"+i);
			}
			
		}
		
		
	}
	
	public void addNodeMatrix(int randNode,int[][] data){
		int val=0,val2=0;
		int ii=0;
		for(ii=0;ii<data.length;ii++){
			val=data[randNode][ii];
			val2=data[ii][randNode];
				
			
			if(val==-100){
				if(val2!=-100){
 					val=val2;
				}
			}
			
			if(!ifPresent(ii, randNode)){
				if(val!=-100){
					String key=randNode+"-"+ii;
					verV.put(key,val);	
					
					HeapNode temp=new HeapNode(val, randNode, ii);
					
					if(startNode==null){
						temp.loopNode=temp;
						startNode=temp;
						startNode.loopNode=temp;
						startNode.prevNode=temp;
						minHeap=temp;
						lastNode=temp;
						
					}else{
						if(startNode.loopNode==lastNode){
							if(minHeap.data>val){
								minHeap=temp;
								temp.prevNode=lastNode;
								lastNode.loopNode=temp;
								temp.loopNode=startNode;
								lastNode=temp;
								startNode.prevNode=temp;
							}else{
								temp.prevNode=lastNode;
								lastNode.loopNode=temp;
								temp.loopNode=startNode;
								lastNode=temp;
								startNode.prevNode=temp;
							}
						}else{
							if(minHeap.data>val){
								minHeap=temp;
								temp.prevNode=lastNode;
								lastNode.loopNode=temp;
								temp.loopNode=startNode;
								lastNode=temp;
								startNode.prevNode=temp;
							}else{
								temp.prevNode=lastNode;
								lastNode.loopNode=temp;
								temp.loopNode=startNode;
								lastNode=temp;
								startNode.prevNode=temp;
							}
						}
					}
				}
			}else{
				
			}
			
		}
		ii=0;
		
		
	}
	
	
	public HeapNode removeMin(){
		HeapNode returnMin= minHeap;
		
		if(minHeap==null){
			System.out.println("Matrix problem min is NULL");
			return null;
		}
		
		if(minHeap.degree==0){
			HeapNode tempMin=minHeap;
			if(minHeap==startNode){
						
				if(minHeap==lastNode){
					lastNode=null;
					startNode=null;
					minHeap=null;
				}else{
					startNode=minHeap.loopNode;
					startNode=minHeap.loopNode;
					lastNode.loopNode=startNode;
					minHeap=null;
				}
			}else{
				if(minHeap==lastNode){
					
					lastNode=minHeap.prevNode;
					lastNode.loopNode=startNode;		
					minHeap=null;
				}else{
					if(minHeap.prevNode==startNode){
						if(minHeap.loopNode==lastNode){
							startNode.nextNode=lastNode;
							lastNode.prevNode=startNode;
							minHeap=null;
						}else{
							startNode.loopNode=minHeap.loopNode;
							minHeap.loopNode.prevNode=startNode;
							minHeap=null;
						}
						
					}else{
						minHeap.prevNode.loopNode=minHeap.loopNode;
						minHeap.loopNode.prevNode=minHeap.prevNode;
						
						minHeap=null;
					}					
				}
			}
			ifMin();
			
			meld();
		}else{
			HeapNode prev=minHeap.prevNode;
			HeapNode next=minHeap.loopNode;
			HeapNode childLoopStart=minHeap.child;
			HeapNode childLoopEnd=minHeap.child.prevNode;
			if(minHeap==startNode){
				if(childLoopStart==childLoopEnd){
					if(minHeap==lastNode){
						childLoopStart.prevNode=childLoopStart;
						childLoopEnd.loopNode=childLoopEnd;
						startNode=childLoopStart;
						lastNode=childLoopStart;
						minHeap=null;
					}else{
						lastNode.loopNode=childLoopStart;
						childLoopStart.prevNode=lastNode;
						childLoopStart.loopNode=startNode.loopNode;
						startNode.loopNode.prevNode=childLoopStart;
						startNode=childLoopStart;
						minHeap=null;
					}
					
				}else{
					if(minHeap==lastNode){
						startNode=childLoopStart;
						lastNode=childLoopEnd;
						minHeap=null;
					}else{
						childLoopStart.prevNode=lastNode;
						lastNode.loopNode=childLoopStart;
						childLoopEnd.loopNode=startNode.loopNode;
						startNode.loopNode.prevNode=childLoopEnd;
						startNode=childLoopStart;
						minHeap=null;
					}
					
				}
			}else{
				if(minHeap==lastNode){
					lastNode.prevNode.loopNode=childLoopStart;
					childLoopStart.prevNode=lastNode.prevNode;
					childLoopEnd.loopNode=startNode;
					lastNode=childLoopEnd;
					startNode.prevNode=childLoopEnd;
					minHeap=null;
				}else{
					if(minHeap.prevNode==startNode){
						if(minHeap.loopNode==lastNode){
							startNode.loopNode=childLoopStart;
							childLoopStart.prevNode=startNode;
							childLoopEnd.loopNode=lastNode;
							lastNode.prevNode=childLoopEnd;
							minHeap=null;									
						}else{
							startNode.loopNode=childLoopStart;
							childLoopStart.prevNode=startNode;
							childLoopEnd.loopNode=next;
							next.prevNode=childLoopEnd;
							minHeap=null;
						}
					}else{
						minHeap.prevNode.loopNode=childLoopStart;
						childLoopStart.prevNode=minHeap.prevNode;
						childLoopEnd.loopNode=next;
						next.prevNode=childLoopEnd;
						minHeap=null;
					}
					
				}
				
			}
			ifMin();
			
			meld();
		}
		return returnMin;
	}
	
	public boolean meld(){
		HashMap<Integer,HeapNode> degreeTable=new HashMap<Integer, HeapNode>();
		HeapNode temp=startNode;
		minHeap=startNode;
		if((startNode==null||lastNode==null)||(startNode==lastNode)){
			System.out.println("Empty");
		}else{
			
			boolean firstEntry=false;
			while(temp!=startNode||firstEntry==false){
				firstEntry=true;
				if(degreeTable.containsKey(temp.degree)){
					int tempDeg=temp.degree;
					
					HeapNode matchNode=(HeapNode)degreeTable.get(temp.degree);
					removeNode(matchNode);
					
					
					removeNode(temp);
					
					HeapNode mergeHeap=merge(matchNode, temp);		
					
					if(startNode==lastNode && lastNode==temp){
						
						HeapNode temp2=startNode;
						startNode=mergeHeap;
						mergeHeap.loopNode=temp2;
						mergeHeap.prevNode=temp2.prevNode;
						lastNode=mergeHeap;
						lastNode.loopNode=startNode;
						lastNode.prevNode=mergeHeap;
						temp=mergeHeap;
						firstEntry=false;
						ifMin();
						
					}else{
						
						mergeHeap.loopNode=startNode;
						startNode.prevNode=mergeHeap;
						lastNode.loopNode=mergeHeap;
						mergeHeap.prevNode=lastNode;
						startNode=mergeHeap;
						temp=startNode;
						firstEntry=false;
						
					}
					
					degreeTable=new HashMap<Integer, HeapNode>();
					ifMin();
					
				}else{
					
					degreeTable.put(temp.degree, temp);
					temp=temp.loopNode;
					ifMin();
					
				}
			}
		}
		return true;
	}
	
	public HeapNode merge(HeapNode first, HeapNode second){
		
		HeapNode temp=new HeapNode();
		if(first.data<second.data||first.data==second.data){
			first.updateChild(second);
			temp=first;
			
		}else{
			second.updateChild(first);
			temp=second;
			
		}
		return temp;
	}
	
	public boolean ifMin(){
		HeapNode temp=startNode;
		minHeap=startNode;
		if(startNode==null || lastNode==null){
			System.out.println("Cannont find min: Empty Heap");
			return false;
		}else{
			temp=temp.loopNode;
			if(temp.data<minHeap.data){
				minHeap=temp;
			}
			while(temp.loopNode!=startNode){
				if(temp.data<minHeap.data){
					minHeap=temp;
					break;
				}else{
					temp=temp.loopNode;
				}
			}
		}
		
		return true;
	}
	
	public void removeNode(HeapNode rm){
		HeapNode temp=startNode;
		
			if(rm==startNode){
				if(rm.loopNode==lastNode){
					startNode=lastNode;
					lastNode.prevNode=startNode;
					lastNode.loopNode=startNode;
					
				}else{
					HeapNode temp2=startNode;
					startNode=startNode.loopNode;
					startNode.prevNode=lastNode;
					lastNode.loopNode=startNode;
					
				}
				
			}else{
				if(rm==lastNode){
					lastNode=lastNode.prevNode;
					lastNode.loopNode=startNode;
					startNode.prevNode=lastNode;
					
				}else{
					{
						if(rm.prevNode==startNode){
							if(rm.loopNode==lastNode){
								lastNode.prevNode=startNode;
								startNode.loopNode=lastNode;
							
							}else{
								startNode.loopNode=rm.loopNode;
								rm.loopNode.prevNode=startNode;
							
							}
							
						}else{

							rm.prevNode.loopNode=rm.loopNode;
							rm.loopNode.prevNode=rm.prevNode;
							
						}
				}
				}
			}
		
	}
	
	public void show(String msg){
		System.out.println(msg);
		if(startNode==null||lastNode==null){
			System.out.println("Empty Heap");
		}else{
			HeapNode temp=startNode;
			
			temp=temp.loopNode;
			while(temp!=startNode){
				System.out.print("--"+temp.data+"("+temp.from+","+temp.to+","+temp.degree+")");
				temp=temp.loopNode;
			}
			System.out.println("\nMin Node: "+minHeap.data+" degree: "+minHeap.degree);
			System.out.println("StartNode: "+startNode.data+" LastNode : "+lastNode.data);
		}
		
		
	}
}

class HeapNode{
	HeapNode parentNode=null;
	boolean root=false;
	int data=-100, from=-100, to=-100,degree=0;
	
	int noOfChild=0;
	HeapNode child;
	HeapNode loopNode;
	HeapNode nextNode;
	HeapNode prevNode;
	HeapNode(){
		child=null;
		loopNode=null;
		nextNode=null;
		prevNode=null;
	}
	HeapNode(int val, int f, int t){
		this.data=val;
		this.from=f;
		this.to=t;
		loopNode=null;
		root=true;
	}
	
	
	public boolean updateChild(HeapNode data){
		if(child!=null){
			HeapNode temp=child;
			if(temp.loopNode!=child){
				data.parentNode=this;
				child.loopNode=data;
				child.prevNode=data;
				data.loopNode=child;
				data.prevNode=child;
				this.degree++;
				noOfChild++;
			}else{
				data.loopNode=child.loopNode;
				data.prevNode=child;
				child.loopNode.prevNode=data;
				child.loopNode=data;
				this.degree++;
				noOfChild++;
			}
		}else{
			this.child=data;
			this.child.loopNode=data;
			this.child.prevNode=data;
			this.child.parentNode=this;
			this.degree++;
			noOfChild++;
		}
		
		return true;
	}
	
	
	public boolean updateData(HeapNode data){
		loopNode=data;
		return true;
	}
	
	
	public boolean updateDegree(HeapNode current){
		while(current.parentNode!=null){
			current.degree++;
			current=current.parentNode;
		}
		current.degree++;
		return true;
	}
	
	
	
}

