
import java.util.Arrays;
import java.lang.*;



public class RingBuffer
{
	private int c, first = 0, last = 0;
	private double buffer[];
	
	/*
	class RingBufferException extends Exception 
	{ 
		public RingBufferException(String s) 
	    { 
	        // Call constructor of parent Exception 
	        super(s); 
	    } 
	} 
	*/
	
	public RingBuffer(int capacity)
	{
	    c = capacity;
	    buffer = new double[c];
	    Arrays.fill(buffer, 1);
	}
	
	public RingBuffer()
	{
	    c = 100;
	    buffer = new double[c];
	    Arrays.fill(buffer, 1);
	}
	
	public int getFirst()
	{
		return first;
	}
	
	public int size()
	{
		int count = 0;
	    for (int i = 0; i < buffer.length; i++)
	    {
	        if(buffer[i] == 1)
	        {
	            count++;
	        }
	    }
	    return Math.abs(buffer.length - count);
	}
	
	public boolean isEmpty()
	{
	    if (buffer[first] == buffer[last])
	    {
	        return true;
	    }
	    return false;
	}
	
	public boolean isFull()
	{
	    if (buffer[0] == buffer[first] && buffer[buffer.length - 1] == buffer[last])
	    {
	        return true;
	    }
	    if (first - 1 >= 0)
	    {
	    	if (buffer[first - 1] == buffer[last])
	    	{
	    		return  true;
	    	}
	    }
	    return false;
	}
	
	public void enqueue(double x) //throws RingBufferException
	{
		if (buffer[last] == 1)
		{
			buffer[last] = x;
		    last = (last + 1) % buffer.length;
		}
	    else 
	    {
	    	//throw new RingBufferException("Enqueue error");
	    	System.out.println("Enqueue Error");
	    }
	}
	
	public Double dequeue() //throws RingBufferException
	{
	    if (buffer[first] != 1)
	    {
	        double temp = buffer[first];
	        buffer[first] = 1;
	        first = (first + 1) % buffer.length;
	        return temp;
	    }
	    else 
	    {
	    	//throw new RingBufferException("Dequeue error");
	    	System.out.println("Dequeue Error");
	    }
	    return 0.0;
	}
	
	public Double peek()
	{
	    return buffer[first];
	}
	
	public Double peek(int i)
	{
	    return buffer[i];
	}
	
public static void main(String[] args)
{
    RingBuffer test = new RingBuffer(200);
    test.enqueue(0.3);
    System.out.println(test.isEmpty());
}

}


