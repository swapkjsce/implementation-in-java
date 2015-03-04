
public class SearchNode<T>
{
		SkipNode<T> p;
		SkipNode<T>[] prev;
		
		public SearchNode(int maxsize) 
		{
			this.p=null;
			this.prev= new SkipNode[maxsize];
		}
	
}
