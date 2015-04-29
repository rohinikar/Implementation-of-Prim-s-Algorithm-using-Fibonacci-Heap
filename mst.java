package ads_project_package;

/*importing libraries*/
import java.io.IOException;  
import java.util.Random;
import java.util.*;
import java.io.*;
import ads_project_package.*;

/**
 * @author ROHINI
 * @UFID 67308301
 * @Class mst.java
 * @Descrition: This is the main method from which other classes are called
 */
public class mst {
	public static void main(String args[]) throws IOException {
		
		/*declaration of variable*/
		String a = null;
        String b = null;
        String c = null;
        int numberOfNodes = 0;
        int density = 0;
        double no_of_edges = 0.0;
        long start_time_simple;
        long end_time_simple;
        long time_taken_simple;
        long start_time_fibonacci;
        long end_time_fibonacci;
        long time_taken_fibonacci;
        long total_time_taken_simple = 0;
        long total_time_taken_fibonacci = 0;
        String type = null;
               

        a=args[0];
        
        if(a.equals("-r"))
        {
        b=args[1];
        c=args[2];
        numberOfNodes = Integer.parseInt(args[1]);
        density = Integer.parseInt(args[2]);
        RandomGeneration rng=new RandomGeneration(numberOfNodes,density);
        
        
            
            		  
			//System.out.println("Enter the scheme:");
			//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			//type = br.readLine();
			
			//if(type.equals("fibonacci"))
			//{
			/*calling the Fibonacci Scheme for Random mode and calculating time taken*/
			
			FibonacciScheme fs=new FibonacciScheme();
			
            //no_of_edges=rng.getEdges();
              for(int i=0;i<5;i++)
            {
			start_time_fibonacci = System.currentTimeMillis();
			fs.heapify(rng.getMatrix(),numberOfNodes,(int)rng.getEdges());
            end_time_fibonacci = System.currentTimeMillis();
            time_taken_fibonacci = end_time_fibonacci - start_time_fibonacci;
            total_time_taken_fibonacci = total_time_taken_fibonacci + time_taken_fibonacci;
            //System.out.println("Total time taken by Fibonacci Scheme in Random mode:"+time_taken_fibonacci);*/
            }
            System.out.println("Average time taken for Fibonacci Scheme in Random mode:"+(total_time_taken_fibonacci/5));
            		  
			//}
			//else if(type.equals("simple"))
			//{
            /*calling the Simple Scheme for Random mode and calculating time taken*/
			 SimpleScheme ss=new SimpleScheme();
            
            for(int i=0;i<5;i++)
            {
            start_time_simple = System.currentTimeMillis();
            ss.primFunction(rng.getMatrix(),numberOfNodes, (int)rng.getEdges());
            end_time_simple = System.currentTimeMillis();
	        time_taken_simple = end_time_simple - start_time_simple;
	        total_time_taken_simple = total_time_taken_simple + time_taken_simple;
	        //System.out.println("Total time taken by Simple Scheme in Random mode:"+time_taken_simple);
            }
            System.out.println("Average time taken for Simple Scheme in Random mode:"+(total_time_taken_simple/5));
        }
        //}
        
        else if(a.equals("-s"))
        {
        	/*calling the Simple Scheme for User Input mode and calculating time taken*/
            b=args[1];
        	SimpleScheme ss=new SimpleScheme();
            
            ss.primFunction(b);;
                    
        }
        
        else if(a.equals("-f"))
        {
        	/*calling the Fibonacci Scheme for User Input mode and calculating time taken*/
        	b=args[1];
        	FibonacciScheme fs=new FibonacciScheme();
			
			fs.heapify(b);
            			
		}
	}
}


