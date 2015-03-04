public class SkipNode<T>
{
		
		T data;
		SkipNode<T>[] next;
		
		SkipNode(T x,int len) 
		{
			this.data=x;
			next=new SkipNode[len];
		}
		
		public void updatemaxsize(int newmax)
		{
			SkipNode<T>[] newarr= new SkipNode[newmax];
			for(int i=0;i<next.length;i++)
				{
					newarr[i]=next[i];
				}
			
			next=newarr;
			
			
		}
}
