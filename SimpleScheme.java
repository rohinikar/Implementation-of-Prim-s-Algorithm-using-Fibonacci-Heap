package ads_project_package;

import java.io.*;
import java.lang.Thread.State;
import java.math.BigInteger;
import java.security.acl.Permission;
import java.util.*;
import ads_project_package.*;



/**
 * @author ROHINI
 * @UFID 67308301
 * @Class SimpleScheme.java
 * @Descrition: Forms the minimum spanning tree either in User Input mode or Random mode using simple Scheme
 */
public class SimpleScheme{
	
	public int numberOfEdges;
	public int numberOfVertices;
	public Node minimumNode=new Node();
	public Node maximumNode=new Node();
	HashMap<String, Integer> value=new HashMap<String, Integer>();
	
	/*This is the first function called in case of User Input mode*/
	public boolean primFunction(String filePath) throws IOException{
		int[][] listOfEdges=getData(filePath);
		Random rand=new Random();
		mstGeneration(0, listOfEdges);
		return true;
	}
	
	/*This is the first function called in case of Random mode*/
	public boolean primFunction(int[][] matrix, int noVert, int noEdges) throws IOException{
		int[][] listOfEdges=getData(matrix,noVert, noEdges);
		Random rand=new Random();
		mstgenerationMatrix(1, listOfEdges);
		return true;
	}
	
	public int[][] getData(String filePath) throws IOException{
		BufferedReader input = new BufferedReader(new FileReader(filePath));
		int i=0;
		String stringInput = input.readLine();
		String[] stringSplit=stringInput.split(" ");
		numberOfVertices=Integer.parseInt(stringSplit[0]);
		numberOfEdges=Integer.parseInt(stringSplit[1]);
		int[][] vertexEdges=new int[numberOfVertices+1][numberOfVertices+1];
		String key;
		
					
		for(int jj=0;jj<vertexEdges.length;jj++){
			Arrays.fill(vertexEdges[jj],-100);
			
		}
		
		int vert1,vert2;
		
		for(i=0;i<numberOfEdges;i++){
			stringSplit=input.readLine().split(" ");
			vert1=Integer.parseInt(stringSplit[0]);
			vert2=Integer.parseInt(stringSplit[1]);
			vertexEdges[vert1][vert2]=Integer.parseInt(stringSplit[2]);
			vertexEdges[vert2][vert1]=Integer.parseInt(stringSplit[2]);
			key=vert1+"-"+vert2;
			value.put(key, Integer.parseInt(stringSplit[2]));
			
		}
		i=0;
		input.close();
		return vertexEdges;
	}
	
	public int[][] getData(int[][] matrix, int vert, int edges) throws IOException{
		int i=0,j=0;
		numberOfVertices=vert;
		numberOfEdges=edges;
		int[][] vertexEdges=new int[numberOfVertices+1][numberOfVertices+1];
		String key;
				
		int vert1,vert2;
		for(i=0;i<numberOfVertices;i++){
			for(j=0;j<numberOfVertices;j++){
				if(matrix[i][j]!=-100){
					int data=matrix[i][j];
					key=i+"-"+j;
					value.put(key, data);
					
				}
			}
		}
		return matrix;
	}
	
	/* This method is used to form the minimum spanning tree*/
	public void mstGeneration(int randVert,int[][] edgeList){
		HashMap visitedVertices=new HashMap();
		HashMap checkedVertices=new HashMap();
		Node startNode=null;
		queueStructure queueElement=new queueStructure();
		int totalCost=0;
		
		for(int y=1;y<numberOfVertices+1;y++){
			int data=edgeList[randVert][y];
			if(data!=-100){
				Node temp=new Node(data, randVert, y, null);
				queueElement.insert(temp);
				queueElement.printQueue();
			}
		}
		
		String path="";
		startNode=queueElement.remove();
		int checkCounter=0;
		while(startNode!=null||checkCounter==numberOfVertices){
			if(visitedVertices.containsKey(startNode.from+"-"+startNode.to)||visitedVertices.containsKey(startNode.to+"-"+startNode.from)){	
				
				startNode=queueElement.remove();
			}else{
				if(checkedVertices.containsKey(startNode.from)&&checkedVertices.containsKey(startNode.to)){
					
					startNode=queueElement.remove();					
				}else{
					int toNode=startNode.to;
					totalCost=totalCost+startNode.data;
					visitedVertices.put(startNode.from+"-"+startNode.to, startNode.data);
					checkedVertices.put(startNode.to, true);
					checkedVertices.put(startNode.from, true);
					checkCounter++;
					
					path=path+"--"+startNode.from+"->"+startNode.to;
					for(int y=1;y<numberOfVertices+1;y++){
						int data=edgeList[toNode][y];
						if(data!=-100){
							Node temp=new Node(data, toNode, y, null);
							queueElement.insert(temp);
							queueElement.printQueue();
						}
					}
					startNode=queueElement.remove();
				}
				
			}
		}
		
		/*Prints out the total cost and the edges*/
		System.out.println("Cost: "+totalCost);
		String[] p=path.split("--");
		for(int i=0;i<p.length;i++){
			System.out.println(p[i]);
		}
		System.out.println("");
	}
	public void mstgenerationMatrix(int randomNode,int[][] edgeList){
		HashMap visitedVertices=new HashMap();
		HashMap checkedVertices=new HashMap();
		Node startNode=null;
		queueStructure queueElement=new queueStructure();
		int totalCost=0;
		
		for(int y=0;y<numberOfVertices;y++){
			int data11=edgeList[randomNode][y];
			int data12=edgeList[y][randomNode];
			if(data11!=-100){
				Node temp=new Node(data11, randomNode, y, null);
				queueElement.insert(temp);
				queueElement.printQueue();
			}else{
				if(data12!=-100){
					Node temp=new Node(data12, randomNode, y, null);
					queueElement.insert(temp);
					queueElement.printQueue();
				}
			}
		}
		
		String path="";
		startNode=queueElement.remove();
		int checkCounter=0;
		while(startNode!=null||checkCounter==numberOfVertices){
			if(visitedVertices.containsKey(startNode.from+"-"+startNode.to)||visitedVertices.containsKey(startNode.to+"-"+startNode.from)){	
				
				startNode=queueElement.remove();
			}else{
				if(checkedVertices.containsKey(startNode.to)){
					
					startNode=queueElement.remove();					
				}else{
					int toNode=startNode.to;
					totalCost=totalCost+startNode.data;
					visitedVertices.put(startNode.from+"-"+startNode.to, startNode.data);
					checkedVertices.put(startNode.to, true);
					checkedVertices.put(startNode.from, true);
					checkCounter++;
					
					path=path+"--"+startNode.from+"->"+startNode.to;
					for(int y2=0;y2<numberOfVertices;y2++){
						int data21=edgeList[toNode][y2];
						int data22=edgeList[y2][toNode];
						if(data21!=-100){
							Node temp=new Node(data21, toNode, y2, null);
							queueElement.insert(temp);
							queueElement.printQueue();
						}else{
							if(data22!=-100){
								Node temp=new Node(data22, toNode, y2, null);
								queueElement.insert(temp);
								queueElement.printQueue();
							}
						}
					}
					startNode=queueElement.remove();
				}
				
			}
		}
		
		/*Prints out the total cost and the edges*/
		System.out.println("Cost: "+totalCost);
		String[] p=path.split("--");
		for(int i=0;i<p.length;i++){
			System.out.println(p[i]);
		}
		System.out.println("");
	}
	
	
}

class Node{
	int data=0,from=0, to=0;
	Node nextNode;
	
	Node(){
		this.data=0;
		this.from=0;
		this.to=0;
		this.nextNode=null;
	}
	Node(int d,int f, int t, Node next){
		this.data=d;
		this.from=f;
		this.to=t;
		this.nextNode=next;
	}
	
}

class queueStructure{
	Node firstNode=null, lastNode=null;
	
	boolean insert(Node node){
		if(firstNode==null){
			firstNode=node;
			lastNode=node;
			lastNode.nextNode=null;
		}else{
			if(firstNode.data>node.data){
				Node prevNode=firstNode;
				firstNode=node;
				node.nextNode=prevNode;
			}else{
				if(lastNode.data<node.data){
					Node prevNode=lastNode;
					lastNode=node;
					prevNode.nextNode=node;
				}else{
					Node nodeMov=firstNode;
					Node tempNode=new Node();
					while(nodeMov.nextNode!=null){
						if((nodeMov.data<node.data && nodeMov.nextNode.data >node.data)||(nodeMov.data==node.data)){
							tempNode=nodeMov.nextNode;
							nodeMov.nextNode=node;
							nodeMov.nextNode.nextNode=tempNode;
							
							break;
						}else{		
							nodeMov=nodeMov.nextNode;
						}
					}
				}
			}
		}
		return true;
	}
	
	Node remove(){
		if(firstNode==null){
			return null;
		}
		Node temp=firstNode;
		
		if(firstNode.nextNode==null){
			
			temp=firstNode;
			firstNode=firstNode.nextNode;
			return temp;
		}else{
			temp=firstNode;
			firstNode=firstNode.nextNode;
			return temp;
		}
	}
	
	void printQueue(){
		Node temp=firstNode;
		
		while(temp!=null){
			
			temp=temp.nextNode;
		}
	}
}
