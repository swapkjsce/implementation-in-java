// Ver 0.1:  Sat, Feb 28.  Initial description.
// Ver 1.0:  Tue, Mar 03.  Added more comments.  Modified decreaseKey().
/*author : sjs140230 */
import java.lang.Comparable;
import java.lang.reflect.Array;

public class PriorityQueueIndexed<T extends Comparable<? super T> & PQIndex> {
    T[] queue;																//prioroty queue
    static int size;														//size of queue
    /** Build a priority queue with a given array q */
    PriorityQueueIndexed(T[] q) {											//constructor of queue when an array is passed as input
    	
    	queue=q;
    	size=q.length-1;
    	
    }

    /** Create an empty priority queue of given maximum size */
    PriorityQueueIndexed(int n) 								
    {
    	queue= (T[])Array.newInstance(queue.getClass().getComponentType(), n);
    	size=n;
    }
    /*Insert a value in priority queue*/
    void insert(T x) {
    	
	add(x);
    }

    void add(T x) 
    {
    	if(size==queue.length-1)
			resize();
    	
    	queue[0]=x;													//to stop percolate up
    	int hole=size+1;
    	queue[hole]=x;
    	percolateUp(hole);											//percolate up the value to its actual position for it to not violate heap property
    	size++;
    	
    }
    /*Delete min value of priority queue*/
    T remove() {
       return deleteMin();
    }

    T deleteMin() 
    {
    	if(isEmpty())
			return null;
    	
    	T x=queue[1];
    	queue[1]=queue[size];										//remove first element and put the last element in its place
    	size--;
    	percolateDown(1);											//percolate down the last element to its position
	return x;	
    }

    /** restore heap order property after the priority of x has decreased */
    void decreaseKey(T x) {
    	queue[0]=x;
	percolateUp(x.getIndex());										//used to restore heap property
	//buildHeap();													//restore heap 
    }

    T min() { 														//return min value without deleting
	return queue[1];
    }

    /** Priority of element at index i of queue has decreased.  It may violate heap order.
     *  Restore heap property */
    void percolateUp(int i) 										//percolate up the value to its actual position
    {
      	T x= queue[i];
    	int hole=i;
    	while(queue[hole/2].compareTo(x)>0)							//if val<queue[hole/2]
    	{
    		queue[hole]=queue[hole/2];								//move hole up
    		queue[hole].putIndex(hole);								
    		hole=hole/2;
    	}
    	queue[hole]=x;												//put value in hole
    	queue[hole].putIndex(hole);									
    }

    /** Create heap order for sub-heap rooted at node i.  Precondition: Heap order may be violated 
     *  at node i, but its children are roots of heaps that are fine.  Restore heap property */
    void percolateDown(int i) 										//percolate down the value to its actual position
    {
    	
    	if (2*i >size)										//case 1- no children									
    		return;
    	if(2*i ==size)										//case 2 - only on child
    	{
    		if(queue[i].compareTo(queue[2*i])>0)
    		{
    			T temp=queue[i];
    			queue[i]=queue[2*i];
    			queue[2*i]=temp;
    			queue[i].putIndex(i);
    			queue[2*i].putIndex(2*i);
    			
    		}
    		return;
    	}
    	if((2*i)<size)											//case 3- two children
    	{
    		T x1=queue[2*i];
        	T x2=queue[2*i+1];
        	int a=x1.compareTo(x2);
        	T x;
        	if(a>0)
        		x=x2;
        	else
        		x=x1;
        	int child=x.getIndex();
        	
    		if(queue[i].compareTo(queue[child])>0)
    		{
    			T temp=queue[i];
    			queue[i]=queue[child];
    			queue[child]=temp;
    			queue[i].putIndex(i);
    			queue[child].putIndex(child);
    			percolateDown(child);
    		}
    	}
    			
    	
    }

    /** Create a heap.  Precondition: none.  Array may violate heap order in many places. */
    void buildHeap() 
    {
    	for(int i=(size+1);i>=1;i--)
    		percolateDown(i);
    	
    }
    
    public boolean isEmpty()									//to check if the PQ is empty
    {
    	return (size==0);
    }
    
	private void resize()
	{
		T[] tempQ = (T[]) new Comparable[(queue.length-1)* 2 + 1];
		for (int i = 1; i <= queue.length-1; i++)
		{
			tempQ[i] = queue[i];
		}
		queue = tempQ;
	}
    
}
