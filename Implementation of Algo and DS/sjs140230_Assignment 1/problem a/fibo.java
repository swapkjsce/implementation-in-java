import java.util.Scanner;


public class fibo {
	private static int phase = 0;
	private static long startTime, endTime, elapsedTime;
	static int p;
	
	public static void main(String args[])
	{
		Scanner s= new Scanner(System.in);
		System.out.println("Enter the number");
		int a= s.nextInt();
		System.out.println("Enter the modulo number");
		p= s.nextInt();
		int x=1;
		int y=1;
		int z=0;
		timer();
		if(a>2)
		for (int i=2;i<a;i++)
		{
			z=(x+y)%p;
			x=y;
			y=z;
		}
		System.out.println("Output Using Iteration");		
		System.out.println(z);
		timer();
		
		System.out.println();
		
		
		System.out.println("Output for O(logn)");
		timer();
		int mulfact[][]={{1,1},{1,0}};
		int second[][]={{1},{0}};
		int result[][]=matmul(power2(mulfact,a-1),second);
		System.out.println(result[0][0]);
		
		timer();
	}
	
	public static int[][] power2(int a[][],int n)
	{
		int[][] s;
		if (n==1)
				return a;
		else
		{
			s=power2(matmul(a,a),n/2);
			if(n%2==0)
				return s;
			else
				return matmul(s,a);
		}
	}
	
	public static int[][] matmul(int a[][],int b[][])
	{
		int[][] c = new int[a.length][b[0].length];
		for(int i=0;i<a.length;i++)
			for(int j=0;j<b[0].length;j++)
				for(int k=0;k<a.length;k++)
					c[i][j]+=a[i][k]*b[k][j]%p;
		
		return c;
	}
	
	public static void timer() {
		if (phase == 0) {
			startTime = System.currentTimeMillis();
			phase = 1;
		} else {
			endTime = System.currentTimeMillis();
			elapsedTime = endTime - startTime;
			System.out.println("Time: " + elapsedTime + " msec.");
			memory();
			phase = 0;
		}
	}

	public static void memory()
	{
		long memAvailable = Runtime.getRuntime().totalMemory();
		long memUsed = memAvailable - Runtime.getRuntime().freeMemory();
		System.out.println("Memory: " + memUsed / 1000000 + " MB / "
				+ memAvailable / 1000000 + " MB.");
	}
}



