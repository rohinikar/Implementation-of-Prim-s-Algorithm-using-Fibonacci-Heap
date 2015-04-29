package ads_project_package;

import java.text.DateFormatSymbols;
import java.util.*;
import java.io.*;
import java.math.*;

/**
 * @author ROHINI
 * @UFID 67308301
 * @Class RandomGeneration.java
 * @Descrition: This generates a matrix randomly containing vertices and edges of a graph
 */
public class RandomGeneration {
	
	public int no_of_edges;
	public double d;
	public int n;
	
	/*constructor for the RandomGeneration class*/
	public RandomGeneration(int numberOfNodes, int density)
	{
		n=numberOfNodes;
		d=density;
	}
	
	/**
	 * @return integer matrix
	 */
	public int[][] getMatrix()
	{
		
		int randomMatrix[][]= new int[n][n];
		double counter=0;
		no_of_edges=(int) Math.ceil((d/100)*n*n);;
		System.out.println("Density:"+d);
		
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				randomMatrix[i][j]=-100;	
			}
		}
		Random abc = new Random();
		boolean flag1=false;
		while(!flag1){
			counter=0;
			for(int i=0;i<n;i++){
				for(int j=0;j<n;j++){
					randomMatrix[i][j]=-100;	
				}
			}
			
			
			while(counter!=no_of_edges){
				int r = abc.nextInt(n);
		        int s = abc.nextInt(n);
		        int t = abc.nextInt(1000)+1;
		        if (randomMatrix[r][s]== -100)
		        	{
		        	randomMatrix[r][s]=t;
			        	counter++;
		        	}
		    				}
			
		/* The constructor of DFS class is called here in order to check if the graph is connected or not*/
			
			DFS dfs = new DFS(randomMatrix,n);
			if(dfs.depthFirstSearch(0, n)){
				System.out.println("Final result after DFS");
				System.out.println("*****************************");
				System.out.println("        DENSITY = " + d+"%");
				System.out.println("Counter value:"+counter);
				System.out.println("*****************************");
				for(int i=0;i<n;i++){
					for(int j=0;j<n;j++){
						System.out.print(randomMatrix[i][j]+ "  ");
					}
					System.out.println();
				}
				flag1=true;
				
			}else{
				flag1=false;
			}
		
		}	
		return randomMatrix;
	}
	
		/**
		 * @return number of edges of the graph
		 */
		public double getEdges(){
		return no_of_edges;
	}
}


