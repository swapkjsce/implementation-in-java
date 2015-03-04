import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

class dequeue<T>
{
	Node<T> head=null;
	public static void main(String args[])
	{
		
/*driver program with Integer*/
			
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
		dequeue<Integer> dq= new dequeue<>();										
		String operation = "";
		int operand;
		while (!((operation = sc.next()).equals("End"))) {
		
		
			
			switch (operation) {
			
			case "AddF": {
			operand = sc.nextInt();
			dq.addF(operand);
			break;
			}
			
			case "AddL": {
				operand = sc.nextInt();
				dq.addL(operand);
				break;
				}
			
			case "GetF": {
				int x=dq.getF();
				System.out.println("First Element:"+x);
				break;
				}
			
			case "GetL": {
				
				int x=dq.getL();
				System.out.println("Last Element:"+x);
				break;
				}
			case "RemoveF": {
				dq.removeF();;
				break;
				}
			case "RemoveL": {
				dq.removeL();;
				break;
				}
	
			}
		}
	}
	
	public void addF(T x)
	{
		Node<T> a= new Node<T>(x);
		if(head==null)
		{
			head=a;
			return;
		}
		
		
		
		a.next=head;
		a.prev=null;
		head.prev=a;
		head=a;
		
		
	}
	
	public void addL(T x)
	{
		Node<T> current=head;
		Node<T> a= new Node<T>(x);
		if(current==null)
		{
			head=a;
			return;
		}
		while(current.next!=null)
		{
			current=current.next;
		}
		current.next=a;
		a.prev=current;
	}
	
	public void removeF()
	{
		head=head.next;
		head.prev.next=null;
		head.prev=null;
	}
	
	public void removeL()
	{
		Node<T> current=head;
		
		while(current.next!=null)
		{
			current=current.next;
		}
		current=current.prev;
		current.next.prev=null;
		current.next=null;
		
	}
	
	public T getF()
	{
		return head.data;
	}
	public T getL()
	{
		Node<T> current=head;
		
		while(current.next!=null)
		{
			current=current.next;
		}
		
		return current.data;
	}
	
    
}



class Node<T>													//Node class for dequeue
{
	Node<T> prev;
	Node<T> next;
	T data;
	
	Node(T x)
	{
		prev=null;
		next=null;
		this.data=x;
	}
	
}