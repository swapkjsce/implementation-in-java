
// author : Swapnil Shah - sjs140230

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;


public class LLUNION {
	
	public static void main(String args[]) throws IOException
	{
		LinkedList<Integer> l1= new LinkedList<Integer>();		//List1
		LinkedList<Integer> l2= new LinkedList<Integer>();		//List2
		
		
		Scanner fileScanner1 = new Scanner(new File("c:\\l1.txt"));		//To read input - enter path for list one
		Scanner fileScanner2 = new Scanner(new File("c:\\l2.txt"));		//To read input - enter path for list two
		while (fileScanner1.hasNextInt()){
		   l1.add(fileScanner1.nextInt());
		}
		while (fileScanner2.hasNextInt()){
			   l2.add(fileScanner2.nextInt());
		}
		fileScanner1.close();
		fileScanner2.close();
		
		
		Iterator<Integer> i1= l1.iterator();				//iterator for list1
		System.out.print("List 1: ");
		while(i1.hasNext())
		{
			System.out.print(i1.next()+" ");
		}
		
		System.out.println();
				
		Iterator<Integer> i2= l2.iterator();				//iterator for list2
		System.out.print("List 2: ");
		while(i2.hasNext())
		{
			System.out.print(i2.next()+" ");
		}
		
		System.out.println();
		intersect(l1,l2);									//function finds intersection of two list
		union(l1,l2);										//function finds union of two list
	}
	
	public static<T extends Comparable<? super T>>LinkedList<T> intersect(LinkedList<T> l1, LinkedList<T> l2) 
	{  
		LinkedList<T> l3= new LinkedList<T>();
		Iterator<T> i1= l1.iterator();					//iterator for list1
		Iterator<T> i2= l2.iterator();					//iterator for list2
		
		T x1;
		T x2;
		
		x1=nextob(i1);									//helper function
		x2=nextob(i2);
		
		int cmp;
	
		
		while(x1!=null && x2!=null)
		{
			cmp=x1.compareTo(x2);
			if(cmp<0)									//x1<x2
				x1=nextob(i1);
			else if(cmp>0)								//x2<x1
				x2=nextob(i2);
			else										//x1=x2
			{
				l3.add(x1);
				x1=nextob(i1);
				x2=nextob(i2);
			}
		}
		
		
		Iterator<T> i3= l3.iterator();					//Print Intersection
		if(l3.isEmpty())
			System.out.println("NO ELEMENT IN INTERSECTION");
		else
		{
		while(i3.hasNext())
		{
			System.out.print(i3.next()+" ");
		}
		
		System.out.println(": INTERSECTION");
		}
		return l3;
		
	}
	
	public static<T extends Comparable<? super T>>LinkedList<T> union(LinkedList<T> l1, LinkedList<T> l2) 
	{  
		LinkedList<T> l3= new LinkedList<T>();
		Iterator<T> i1= l1.iterator();						//iterator for list1
		Iterator<T> i2= l2.iterator();						//iterator for list2
			
		T x1;
		T x2;
		
		x1=nextob(i1);										//helper function
		x2=nextob(i2);
		
		int cmp;
	
		
		while(x1!=null && x2!=null)
		{
			cmp=x1.compareTo(x2);
			if(cmp<0)										//x1<x2
			{
				l3.add(x1);
				x1=nextob(i1);
			}
			else if(cmp>0)									//x1>x2	
			{
				l3.add(x2);
				x2=nextob(i2);
			}
			else											//x1==x2
			{
				l3.add(x1);
				x1=nextob(i1);
				x2=nextob(i2);
			}
		}
		
		while(x1!=null)										//add remaining elements of x1
		{
			l3.add(x1);
			x1=nextob(i1);
		}
		
		while(x2!=null)										//add remaining elements of x2
		{					
			l3.add(x2);
			x2=nextob(i2);
		}
		
		Iterator<T> i3= l3.iterator();						//print union
		if(l3.isEmpty())
			System.out.println("NO ELEMENT IN UNION");
		else
		{
		while(i3.hasNext())
		{
			System.out.print(i3.next()+" ");
		}
		
		System.out.println(": UNION");
		}
		return l3;
		
	}
	
	public static<T extends Comparable<? super T>>T nextob(Iterator<T> i)		//helper function returns the next 
	{												  							//element if exists or returns null
		if(i.hasNext())
			return i.next();
		else
			return null;
	}

}
