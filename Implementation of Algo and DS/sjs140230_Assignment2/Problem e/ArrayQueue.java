
/**
 *  Array based queue that stores index of front and rear elements.
 *  If queue gets full, it raises an exception.  It could have been
 *  designed to resize array to accommodate additional elements.  
 *  If queue is empty, a call to Dequeue raise an exception.
*/

import java.lang.Exception;

public class ArrayQueue<T> {
    private int front, rear;  // index of front and rear elements of the queue
    private int size, maxSize;  // size is the actual number of elements in the queue;  maxSize is size of array
    private T[] Q;

    // Constructor  
    ArrayQueue(int s) {
	maxSize = s;
	front = 0;
	rear = -1;  // when array has no elements, rear index is to the left of front;  could also be maxSize-1
	size = 0;
	Q = (T[]) new Object[maxSize];  // generates compile time warning
    }
  
    /** Add a new element x to the queue to the end of the queue 
     *  @param: x   item of type T to be added to the queue
     */
    void add(T x) throws Exception { enqueue(x); }

    void enqueue(T x) throws Exception {
	if ( !isFull() ) {
	    // If rear goes past the end of the queue array, it wraps back to 0
	    rear = (rear + 1) % maxSize;
	    Q[rear] = x;
	    size++;
	} else {  // Queue is full.  Raise exception or resize queue
	   resize();
	   enqueue(x);
	}
    }
  

    /** Remove the head element from the queue and return it */
    T remove() throws Exception { return dequeue(); }

    T dequeue() throws Exception {
	if ( !isEmpty() ) {
	    T tmp = Q[front];
	    size--;
	    // If front goes past the end of the queue array, it wraps back to 0
	    front = (front+1) % maxSize;
	    return tmp;
	} else {  
	    throw new Exception("Queue is empty");
	}
    }
  
    boolean isEmpty()  { return size == 0; }
  
    boolean isFull()  { return size == maxSize; }
  
    void resize() { 										//resizes the array to twice the size
    	if(isFull())
    	{
    		int newMax=maxSize*2;
    		T nq[]=(T[]) new Object[newMax];
    		if(front>rear)
    		{
    			for(int i=front;i<maxSize;i++)				//copy front to end
    			{
    				nq[i-front]=Q[i];
    			}
    			for(int i=0;i<rear;i++)						//copy start to rear
    			{
    				nq[maxSize-front+i]=Q[i];
    			}
    		}
    		else if(front<rear)								//this case happens when front is 0
    		{
    			for(int i=front;i<=rear;i++)				
    			{
    				nq[i]=Q[i];
    			}
    		}
    		Q=nq;
    		front=0;
    		rear=maxSize-1;
    		maxSize=newMax;
    			
    	}

    }

    /** Sample driver program.  Output: 1 2 3 4 5 6 7 8 9 10 11 12 */
    public static void main (String [] args) throws Exception {
	ArrayQueue<Integer> A = new ArrayQueue<>(5);

	for(int i=1; i<=5; i++) {
	    Integer x = new Integer(i);
	    A.add(x);
	}

	for(int i=6; i<=12; i++) {
	    Integer x = A.remove();
	    System.out.print(x + " ");
	    x = new Integer(i);
	    A.add(x);
	}

	while(!A.isEmpty()) {
	    Integer x = A.remove();
	    System.out.print(x + " ");
	}
	System.out.println();
    }
}
