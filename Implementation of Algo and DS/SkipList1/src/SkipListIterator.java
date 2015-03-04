import java.util.Iterator;

 class SkipListIterator<T> implements Iterator<T>
   {
       SkipNode<T> nextNode;
      public SkipListIterator(SkipNode<T> header)
      {
    	  nextNode = header;
      }

	public boolean hasNext()
      {
         return nextNode.next[0]!=null;
      }

      public T next()
      {
         if (!hasNext()) 
         {
        	 return null;
         }
         T res = nextNode.data;
         nextNode = nextNode.next[0];
         return res;
      }

      
   }
