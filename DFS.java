
package ads_project_package;

/*importing libraries*/
import java.io.*;
import java.util.*;


/**
 * @author ROHINI
 * @UFID 67308301
 * @Class DFS.java
 * @Descrition: This checks if a particular graph is connected or not and returns a boolean value
 */
public class DFS{
	
	/*initialising variables*/
	Stack<Integer> stack;
	int firstNode;
	int[][] adjMatrix;
	int[] isVisited;
	int counter=1;
	
	/*constructor for DFS class. Randomly generated matrix and the number of nodes is passed as arguments*/
	public DFS(int[][] Matrix, int noOfNodes){
		this.adjMatrix = Matrix;
		this.isVisited=new int[noOfNodes];
		stack = new Stack<Integer>();
		
		
	} 
	/*this function performs the DFS in order to determine if the graph is connected or not and returns a boolean value*/
	public boolean depthFirstSearch(int firstNode,int n){
		int i;
		int j;
		stack.push(firstNode);
		boolean flag2=false;
		while(!stack.isEmpty()){
			i = stack.pop();
							
			if(isVisited[i]==0){
				isVisited[i]=1;
			}		
			for ( j=0;j<n;j++){
				if((adjMatrix[i][j] != -100) && (isVisited[j] == 0)){
						stack.push(i);
						isVisited[j]=1;
						i = j;
						counter++;
				}
			}
		}
		if (counter==n){
			System.out.println("final connected Graph");
			System.out.println("Counter value:"+counter);
			flag2=true;
			return flag2;
		}
		else{
			return flag2;
		}
				
	}
}		