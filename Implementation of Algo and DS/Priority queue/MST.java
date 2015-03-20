/*author : sjs140230 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MST {
    static final int Infinity = Integer.MAX_VALUE;

    static int PrimMST(Graph g)										
    {
    
    PriorityQueueIndexed pq= new PriorityQueueIndexed(g.V);					//create priority queue for the graph
    	
	MST mst = new MST();													//object of class MST
	int wmst = 0;															// min weight of spanning tree
	Graph.Vertex src = g.V[1];												// setting the source vertex
	
	src.weight=0;															//setting weight of source to zero
	

	
	while(!pq.isEmpty())													
	{
		Graph.Vertex u= (Graph.Vertex)pq.deleteMin();						//extract min of prims algo
		wmst+=u.weight;														// add min weight
		u.seen=true;														// visited == true
		for(Graph.Edge e: u.Adj)											//for every edge in adj of u
		{
			if(e.otherEnd(u).seen!=true && e.Weight<e.otherEnd(u).weight)	//e.otherEnd(u) is v
			{
				e.otherEnd(u).parent=u;										//v.parent==u;
				e.otherEnd(u).weight=e.Weight;
				pq.decreaseKey(e.otherEnd(u));								//decreasekey will change index and change the pq as weight changes
			}
		}
	}
	

       

	return wmst;
    }

    public static void main(String[] args) throws FileNotFoundException 
    {
	Scanner in = new Scanner(new File(args[0]));
	Graph g = Graph.readGraph(in);
	g.initialize();															//initialize the graph. function found in graph.java
	long startTime = System.currentTimeMillis();
	System.out.println("Result:"+PrimMST(g));
	long endTime = System.currentTimeMillis();
	long elapsedTime = endTime - startTime;
	System.out.println("TIME: "+elapsedTime+"msec");
    }
}
