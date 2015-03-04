/* author : Swapnil Shah - sjs140230 */

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;


public class Arithmetic  {

	public static void main(String args[])
	{
		
		
		Scanner s=new Scanner(System.in);
		System.out.println("Enter first number");					
		String s1=s.next();											//takes in first number
		System.out.println("Enter second number");	
		String s2=s.next();											//takes in second number
		
		
		
		System.out.println("Enter add or sub");
		String operator=s.next();									//takes the operator
		if(operator.equals("add"))
		{
			/*Addittion of first and second number*/
			if(s1.contains("-") && s2.contains("-"))				//case where both numbers are negative
			{
				System.out.println("-"+listToString(add(stringToList(s1.substring(1)),stringToList(s2.substring(1)))));
			}
			else if(s1.contains("-"))								//case where first number is negative
			{
				System.out.println(listToString(sub(stringToList(s2),stringToList(s1.substring(1)))));
			}
			else if(s2.contains("-"))								//case where second number is negative
			{
				System.out.println(listToString(sub(stringToList(s1),stringToList(s2.substring(1)))));
			}
			else													//case where both numbers are positive
			{
				System.out.println(listToString(add(stringToList(s1),stringToList(s2))));
			}
		}
		else if(operator.equals("sub"))
		{
			
			/*Subtraction of first and second number*/
			if(s1.contains("-") && s2.contains("-"))				//case where both numbers are negative
			{
				System.out.println(listToString(sub(stringToList(s2.substring(1)),stringToList(s1.substring(1)))));
			}
			else if(s1.contains("-"))								//case where first number is negative
			{
				System.out.println("-"+listToString(add(stringToList(s1.substring(1)),stringToList(s2))));
			}
			else if(s2.contains("-"))								//case where second number is negative
			{
				System.out.println(listToString(add(stringToList(s1),stringToList(s2.substring(1)))));
			}
			else													//case where both numbers are positive
			{
				System.out.println(listToString(sub(stringToList(s1),stringToList(s2))));
			}
		}
		else
		{
			System.out.println("Wrong Operation");
		}
		s.close();															// close scanner
		
		
		
		
	}
	
	public static LinkedList<Integer> add(LinkedList<Integer> a, LinkedList<Integer> b) 
	{  
		Iterator<Integer> i1= a.iterator();						//iterate list a
		Iterator<Integer> i2= b.iterator();						//iterate list b
		
		int carry=0;
		LinkedList<Integer> c=new LinkedList<>();				//result list c
		
		while(i1.hasNext() && i2.hasNext())	
		{
			int s=(int)i1.next()+(int)i2.next();
			
			if(carry==1)
			{
				c.add((s+1)%10);
				carry=(s+1)/10;
			}
			else
			{
				c.add(s%10);
				carry=(s)/10;
			}
		}
				
		if(i1.hasNext())												//addition of rest over digits to the list
			while(i1.hasNext())
				{c.add(i1.next()+carry);carry=0;}
		else if(i2.hasNext())
			while(i2.hasNext())
				{c.add(i2.next()+carry);carry=0;}
		
		return c;
	}
	public static LinkedList<Integer> sub(LinkedList<Integer> a, LinkedList<Integer> b) 
	{  
		Iterator<Integer> i1= a.iterator();
		Iterator<Integer> i2= b.iterator();
		
		int carry=0;
		
		
		LinkedList<Integer> c=new LinkedList<>();
		if(b.size()<a.size())												//case where size of first number is bigger than first
		{
		while(i1.hasNext() && i2.hasNext())
		{
			int s=(int)i1.next()-(int)i2.next()+carry;
			
			if(s<0)
			{
				c.add((s+10));
				carry=-1;
			}
			else
			{
				c.add(s);
			}
		}
		
		if(i1.hasNext())
			while(i1.hasNext())
			{
				int s=i1.next();
				if((s+carry)<0)
					{c.add(s+carry+10);carry=-1;}
				else
					{c.add(s+carry);carry=0;}
			}
			
		}
		
		else if(a.size()<b.size())										//case where size of second number is bigger than first
		{
			c=sub(b,a);													//calls to previous case of first nuber bigger
				
				
				while(c.peekLast()==0)									//remove trailing zeros
					c.pollLast();
					int last=c.pollLast();
					c.add(last*-1);										//add - sign to last digit
		}
		else																	//case where size of nummbers is same
		{
			while(i1.hasNext() && i2.hasNext())
				{
					int s=(int)i1.next()-(int)i2.next()+carry;
					
					if(s<0)
					{
						c.add((s+10));
						carry=-1;
					}
					else
					{
						c.add(s);
						carry=0;
					}
				}
				
				if(carry==-1)
				{
					c.add(1);
					
					LinkedList<Integer> d= new LinkedList<>();
					for(int i=1;i<c.size();i++)
						d.add(0);
					d.add(2);
					
					LinkedList<Integer> e= new LinkedList<>();
					e=sub(d,c);
					
					while(e.peekLast()==0)
						e.pollLast();
					int last=e.pollLast();
					e.add(last*-1);
					
					return e;
				}
				
				
				
				
		}
			
		
		return c;
	}
	
	/*converts string to list in reverse order*/
	public static LinkedList<Integer> stringToList(String s) 
	{  
		
		LinkedList<Integer> l = new LinkedList<>();
		
		char c[]=s.toCharArray();
		int zerooffset=0;
		for(int i=0;i<c.length;i++)														//remove starting zeros
		{
			if(c[i]!='0')
				break;
				zerooffset++;
		}
		
		for(int i=c.length-1;i>=zerooffset;i--)											//adding to string
		{
			l.add(Character.getNumericValue(c[i]));
		}
		
		return l;
	}
	
	/*Convers list to string by reversing the order*/
	public static String listToString(LinkedList<Integer> a) 
	{  
		String s = "";
		int flag=0;
		Iterator<Integer> iter= a.iterator();
		while(iter.hasNext())												//extract string in reverse order
		{
			int curr=iter.next();
			if(curr<0)
			{
				flag=1;
				curr=curr*-1;
			}
			s=s+(curr);
		}
		String reverse = "";														//reverse string
		int length = s.length();

		if(flag==1)
			reverse=reverse+"-";
		for ( int i = length - 1 ; i >= 0 ; i-- )
			reverse = reverse + s.charAt(i);
		
		return reverse;
	}
}
