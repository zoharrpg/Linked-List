import java.io.*;
import java.util.*;



public class LinkedList<T>
{
	private Node<T> head;  

	public LinkedList()
	{
		head = null; 
	}


	@SuppressWarnings("unchecked")
	public LinkedList( String fileName, boolean orderedFlag )
	{	head = null;
		try
		{
			BufferedReader infile = new BufferedReader( new FileReader( fileName ) );
			while ( infile.ready() )
			{
				if (orderedFlag)
					insertInOrder( (T)infile.readLine() );  
				else
					insertAtTail( (T)infile.readLine() );  
			}
			infile.close();
		}
		catch( Exception e )
		{
			System.out.println( "FATAL ERROR CAUGHT IN C'TOR: " + e );
			System.exit(0);
		}
	}

	//-------------------------------------------------------------

	// inserts new elem at front of list - pushing old elements back one place

	public void insertAtFront(T data)
	{
		head = new Node<T>(data,head);
	}

	

	public String toString()
	{
		String toString = "";

		for (Node curr = head; curr != null; curr = curr.next)
		{
			toString += curr.data;		        
			if (curr.next != null)
				toString += " ";
		}

		return toString;
	}

	



	@SuppressWarnings("unchecked")
	public int size() 
	{
		Node<T> curr=head;
		int count=0;
		 
		while(curr!=null)
		{
			curr=curr.next;
			count++;
		}
		
		
		
		return count; 
	}

	public boolean empty()
	{
		return size()==0;
		
		
		
	}

	public boolean contains( T key )
	{
		
		return search(key)!=null;
		
		 
	}

	@SuppressWarnings("unchecked")
	public Node<T> search( T key )
	{
		Node<T> curr = head;
		while(curr!=null)
		{
			if(curr.data.equals(key))
			{
				return curr;
			}
			else
			{
				curr=curr.next;
			}
			
		}
		
		
		
		return null;
		
		
	}
		
		
		
		
		
		
		
	

	
	@SuppressWarnings("unchecked")
	public void insertAtTail(T data)
	{
		
		Node<T> curr = head;
		if(curr==null)
		{
			insertAtFront(data);
		}
		else
		{
			while(curr.next!=null)
			{
				curr=curr.next;
			}

			curr.next=new Node<T>(data,null);
		
		
		}
		
	}

	@SuppressWarnings("unchecked")  //CAST TO COMPARABLE THROWS WARNING
	public void insertInOrder(T data)
	{
		Comparable cData = (Comparable)data;

		
		

		if(head==null || cData.compareTo(head.data)<=0)
		   {
			   insertAtFront(data);
			   return;
		   }
		   
		   Node<T> curr=head;
			
			while(curr.next!=null&&((Comparable)data).compareTo(curr.next.data)>=0)
				{
					 curr=curr.next;
				}
				
				Node<T> temp=curr.next;
				
				 curr.next=new Node<T>(data,temp);
				 
				 
	}

	@SuppressWarnings("unchecked")
	public boolean remove(T key)
	{
		if(head==null)
		return false;

		
		

		if(head.data.equals(key))
		{
			return removeAtFront();

		}

		Node<T> curr=head;
		
		
		
	
		
		
		
		while(curr.next!=null&&!curr.data.equals(key))
		{
			curr=curr.next;
		}
	    
		
		if(curr.next==null&&curr.data.equals(key))
			return removeAtTail();
		else if(!curr.data.equals(key)&&curr.next==null)
		    return false;
		else
		{
			curr.next=curr.next.next;
		return true;
		}
	
		
		
		
		

		
		
		
	}

 
	@SuppressWarnings("unchecked")	 
 public boolean removeAtTail() 
	{
		
		if(empty())
		return false;
		
		if(head.next==null)
		{
			head=null;
			return true;
		}
		
		Node<T> curr = head;
		while(curr.next.next!=null)
		{
			curr=curr.next;
		}
		curr.next=null;

		return true;
		
		
		
		
	}

	@SuppressWarnings("unchecked")
	public boolean removeAtFront() 
	{
		if(head==null)
		return false;
		else
		{
			head=head.next;
			return true;
		}
		
		
		 // YOUR CODE HERE
	}
	@SuppressWarnings("unchecked")
	public LinkedList<T> union( LinkedList<T> other )
	{
		LinkedList<T> union = new LinkedList<T>();
		Node<T> curr=head;
		while(curr!=null)
		{
			if(!union.contains((T)curr.data))
			union.insertInOrder((T)curr.data);
			
			curr=curr.next;
		}
		
		
		
		

		Node<T> curr2=other.head;
        
		while(curr2!=null)
		{
			if(!union.contains((T)curr2.data))
			union.insertInOrder((T)curr2.data);

			
			
			
			curr2=curr2.next;
		}
		

		// YOUR CODE HERE

		return union;
	}
	@SuppressWarnings("unchecked")
	public LinkedList<T> inter( LinkedList<T> other )
	{
		LinkedList<T> inter = new LinkedList<T>();
		Node<T> curr=head;

		while(curr!=null)
		{
			if(other.contains((T)curr.data))
			inter.insertInOrder((T)curr.data);

			
			
			
			curr=curr.next;
		}


		

		// YOUR CODE HERE

		return inter;
	}
	@SuppressWarnings("unchecked")
	public LinkedList<T> diff( LinkedList<T> other )
	{
		LinkedList<T> diff = new LinkedList<T>();
		Node<T> curr=head;

		while(curr!=null)
		{
			if(!other.contains((T)curr.data)&&!diff.contains((T)curr.data))
			{
				diff.insertInOrder((T)curr.data);

			}
			
			
			curr=curr.next;
		}

		// YOUR CODE HERE

		return diff;
	}
	@SuppressWarnings("unchecked")
	public LinkedList<T> xor( LinkedList<T> other )
	{
		return  (this.union(other)).diff(this.inter(other));  

	}

} //END LINKEDLIST CLASS


class Node<T>
{
   T  data;
  Node next;

  Node()
  {
    this( null, null );
  }

  Node(T data)
  {
    this( data, null );
  }

  Node(T data, Node next)
  {
    this.data=data;
    this.next=next;
  }

  
  
  public String toString()
  {
	  return ""+ data;
  } 
	 
}


