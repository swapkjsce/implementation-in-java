import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;


//Driver program and the skeleton for the skip list implementation.


public  class SkipListImpl<T extends Comparable<? super T>> implements SkipList<T>{
	static int maxsize=10;										//variable to store maxlevel of the header and tail dummy node
	static int listsize = 0;									//no of elements in list

	SkipNode<T> header= new SkipNode<T>(null, maxsize);			//header dummy node
	SkipNode<T> tail= new SkipNode<T>(null, maxsize);			//tail dummy node





	public static void main(String[] args) {

		Scanner sc = null;
		/*TO READ INPUT */
		if (args.length > 0) {
			File file = new File(args[0]);
			try {
				sc = new Scanner(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			sc = new Scanner(System.in);
		}
		String operation = "";
		long operand = 0;
		int modValue = 997;
		long result = 0;
		Long returnValue = null;
		SkipListImpl<Long> skipList = new SkipListImpl<Long>();
		if(listsize==0)											//point all head arrows to tail
		{
			for(int i=0;i<skipList.header.next.length;i++)
			{
				skipList.header.next[i]=skipList.tail;							// all heads pointing to tail
			}
		}
		// Initialize the timer
		long startTime = System.currentTimeMillis();



		while (!((operation = sc.next()).equals("End"))) {
		
		
			switch (operation) {
			case "Add": {
			operand = sc.nextLong();
			skipList.add(operand);
			result = (result + 1) % modValue;
			break;
			}
			case "Ceiling": {
			operand = sc.nextLong();
			returnValue = skipList.ceiling(operand);
			if (returnValue != null) {
			result = (result + returnValue) % modValue;
			}
			break;
			}
			case "FindIndex": {
			operand = sc.nextLong();
			returnValue = skipList.ceiling(operand);
			if (returnValue != null) {
			result = (result + returnValue) % modValue;
			}
			break;
			}
			case "First": {
			returnValue = skipList.first();
			if (returnValue != null) {
			result = (result + returnValue) % modValue;
			}
			break;
			}
			case "Last": {
			returnValue = skipList.last();
			if (returnValue != null) {
			result = (result + returnValue) % modValue;
			}
			break;
			}
			case "Floor": {
			operand = sc.nextLong();
			returnValue = skipList.floor(operand);
			if (returnValue != null) {
			result = (result + returnValue) % modValue;
			}
			break;
			}
			case "Remove": {
			operand = sc.nextLong();
			if (skipList.remove(operand)) {
			result = (result + 1) % modValue;
			}
			break;
			}
			case "Display":
			{
			Iterator<Long> l=skipList.iterator();
			while(l.hasNext())
			{
				System.out.println(l.next());
			}
			
			break;
			}

			case "Contains":
			{
				operand = sc.nextLong();
				if (skipList.contains(operand)) {
				result = (result + 1) % modValue;
				}
				break;
				

			}

		}
		}
		// End Time
		long endTime = System.currentTimeMillis();
		long elapsedTime = endTime - startTime;
		System.out.println(result + " " + elapsedTime);

	}

	public int choice(int maxlevel) 							//randomised method to decide no of levels in a node
	{
		int l = 1;
		while (l < maxlevel)
		{
			Random choiceLevel= new Random();
			int c=choiceLevel.nextInt(1000);
			int b = (int) (c % 2);
			if (b ==1)
				return l;
			else
				l++;
		}
		return l;


	}

	public void add(T x)										//method to add element in list
	{

		SearchNode<T> result=search(x);							//Search for x and return the prev array and p node (refer search methods)

		if(result.p!=null)
		{
			return;
		}

		int l=choice(maxsize);									//get randomised level number

		SkipNode<T> n = new SkipNode<T>(x, l);					//create the node
		for(int i=0;i<l;i++)
		{
			n.next[i]=result.prev[i].next[i];
			result.prev[i].next[i]=n;
		}
		listsize++;
		int oldsize=maxsize;
		updatemaxlevel(header);									//update max level of header(refer updatemaxlevel method)
		int size=header.next.length-1;
		for(int i=oldsize;i<=size;i++)
		{
			header.next[i]=tail;
		}

	}


	public T ceiling(T x) 										//Least element that is >= x, or null if no such element
	{
		
		if(isEmpty())
		{
			return null;
		}
			
		
		SkipNode<T> p = header;
		for(int i=maxsize-1;i>=0;i--)
		{
			if(p.next[i].data!=null)
			while((p.next[i].data).compareTo(x)<0)
				{
					p=p.next[i];
					if(p.next[i].data==null)
						break;
				}
		}


				return p.next[0].data;

	}

	public boolean contains(T x) 									//find if elements is in list
	{
		if(isEmpty())
		{
			return false;
		}
	
		SearchNode<T>result=search(x);
		if(result.p==null)
		return false;
		else
			return true;
	}


	public T findIndex(int n) {		
		SkipNode<T> current=header;
		
		if(n>listsize)
			return null;
		int i=0;
		while(i<n)
		{
			current=current.next[0];
			i++;
		}
		
		return current.data;
			
	}

	public T first() {												//return first element of list
		if(listsize==0)
		{
			return null;
		}
		else
			return header.next[0].data;

	}

	public T floor(T x) {											//Greatest element that is <= x, or null if no such element
		if(isEmpty())
		{
			return null;
		}
		if(header.next[0].data==x)
			return null;
		SkipNode<T> p = header;
		
		for(int i=maxsize-1;i>=0;i--)
		{
			if(p.next[i].data!=null)
			while((p.next[i].data).compareTo(x)<0)
				{
					p=p.next[i];
					if(p.next[i].data==null)
						break;
				}
		}
				return p.data;
	}

	public boolean isEmpty() 										//finds if list is empty
	{
		if(listsize==0)
		return true;
		else
			return false;
	}

	public Iterator<T> iterator() {
		
		 return new SkipListIterator(header.next[0]);				//creates object of class SkipListIterator and returns it
	}

	public T last() 												//returns last element of list
	{
		if(isEmpty())
		{
			return null;
		}
		else
		{
			SkipNode<T> p = header;
			for(int i=maxsize-1;i>=0;i--)
			{

				while(p.next[i].data!=null)
					p=p.next[i];
						if(p.next[i].data==null)
							continue;

			}
			
				return p.data;
			}

		}


	public void rebuild() {												//not implemented

	}

	public boolean remove(T x) 											//removes element from list
	{
		SearchNode<T> result=search(x);
		if(result.p==null)
			{
				return false;
			}
		else
			{

				for(int i=0;i<maxsize;i++)
				{
					if(result.prev[i].next[i]==result.p)
						result.prev[i].next[i]=result.p.next[i];

				}
				listsize--;

			}
		return true;
	}

	public int size() {													//returns size of list
		return listsize;
	}

	

	public void updatemaxlevel(SkipNode<T> object)					//method that updates maximum level of dummy nodes
	{
		int newmax= (int) Math.ceil((Math.log(listsize) / Math.log(2)));		//newmax is calculated as log(listsize)
		if(newmax<=maxsize)														//if newmax> maxsize list level is increased
			return;
		else
		{
			object.updatemaxsize(maxsize*2);
			if(object == header)
			{
				SkipNode<T> object1=tail;
				object1.updatemaxsize(maxsize*2);
			}

		}
		maxsize=maxsize*2;
	}

	public SearchNode<T> search(T x)
	{
		SearchNode<T> result= new SearchNode<T>(maxsize);
		SkipNode<T> p = header;
		for(int i=maxsize-1;i>=0;i--)
		{
			if(p.next[i].data!=null)
			while((p.next[i].data).compareTo(x)<0)
				{
					p=p.next[i];
					if(p.next[i].data==null)
						break;
				}
			result.prev[i]=p;
		}

		if((p.next[0].data)!=null && (p.next[0].data).compareTo(x)==0)
			{
				result.p=p.next[0];
				return result;
			}
		else
			return result;

	}

}
