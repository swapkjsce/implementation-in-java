/*  author : SWAPNIL SHAH   - sjs140230  */


import java.util.*;
import java.io.*;

public class twomaxsubarray{
	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		
		System.out.println("Enter the number of values in array");
		
		int n= s.nextInt();
		
		System.out.println("Enter the values of array:");
		
		int [] arr= new int[n];
		
		for(int i=0;i<n;i++)
		{
			arr[i]=s.nextInt();
		}
		

	

		int a[] = new int[6];
		a = maxsub(0, arr.length - 1, arr);
		
		//output in form start index, end index and sum
		System.out.println("start index:"+a[0] + " 		end index:" + a[1] + " 		subarraysum:" + a[2] );
		System.out.println("start index:"+a[3] + " 		end index:" + a[4] + " 		subarraysum:" + a[5]);

	}

	private static int[] maxsub(int p,int r,int[] arr) {
	int[] ans=new int[6];int q;int[] al;int[] ar;int []ac;
	//base case of one element
	if (p==r)
	{
		ans[0]=p;
		ans[1]=r;
		ans[2]=arr[p];
		ans[3]=p;
		ans[4]=r;
		ans[5]=arr[r];
		
		return ans;
	}
	else
	{
		q=(p+r)/2;
		al=maxsub(p,q,arr);			//recursive call for left half
		ar=maxsub(q+1,r,arr);		//recursive call for right half
		ac=maxCross(p, r, q, arr);	//Crossing subarray sum
		
		
		int[] finans= new int[6];
		
		int[] temp=new int[6];
		
		temp=max(al,ar);		//find max between left and right.. returns two max subarray from left and right subarray
		finans=max(temp,ac);	//finding max between left right and centre.. returns the max and second mac
		
	return finans;
	}
}

	public static int[] maxCross(int p, int r, int q, int arr[]) 
	{
		int l1 = 0;
		int l2=0;
		int rh1=0;
		int rh2 = 0;
		int[] ans = new int[6];
		int sum = 0;
		int left_sum1 = -99999;
		int left_sum2 = -99999;
		
		//finding max and second max on left
		for (int i = q; i >= p; i--) {
			sum = sum + arr[i];
			if (sum > left_sum1)
			{
				left_sum2 = left_sum1;
				left_sum1=sum;
				l2 = l1;
				l1=i;
			}
		}
		
		sum = 0;
		int right_sum1 = -99999;
		int right_sum2 = -99999;
		
		//finding max and second max on right
		for (int i = q + 1; i <= r; i++) {
			sum = sum + arr[i];
			if (sum > right_sum1) {
				right_sum2 = right_sum1;
				right_sum1=sum;
				rh2=rh1;
				rh1= i;
			}
		}
		
		ans[0] = l1;
		ans[1] = rh1;
		ans[2] = (left_sum1 + right_sum1);
		ans[3] = l2;
		ans[4] = rh2;
		ans[5] = (left_sum2 + right_sum2);

		return ans;
	}
	
	public static int[] max(int a[],int b[])
	{
		
		//function to compare 4 input values and return the max two values.. 
		int ans[]=new int[6];
		if(a[2]>b[2])
		{
			if(a[5]>b[2])
			{
				ans=a;
			}
			else
			{
				ans[0]=a[0];
				ans[1]=a[1];
				ans[2]=a[2];
				ans[3]=b[0];
				ans[4]=b[1];
				ans[5]=b[2];
			}
		}
		else
		{
			if(b[5]>a[2])
			{
				ans=b;
			}
			else
			{
				ans[0]=b[0];
				ans[1]=b[1];
				ans[2]=b[2];
				ans[3]=a[0];
				ans[4]=a[1];
				ans[5]=a[2];
			}
		}
		return ans;
	}

}



/* INPUT
 * {4, -1, 2, -1, 3, -9, 4 }  
 * OUTPUT :
 * start index:0 		end index:4 		subarraysum:7
 * start index:0 		end index:2 		subarraysum:5

 */
  
