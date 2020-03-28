
import java.lang.*;
import java.text.DecimalFormat;
import java.util.Random;

public class GuitarString
{
	private RingBuffer sampleRate;
	private Random random = new Random(System.currentTimeMillis());
	int time = 0;
	DecimalFormat df = new DecimalFormat("###.###");
	
	public GuitarString (double frequency)
	{
    	int size = (int) Math.ceil(44100 / frequency);
    	sampleRate = new RingBuffer(size);
    	
    	for (int i = 0; i < size; i++)
    	{
        	sampleRate.enqueue(0.00);
    	}
	}
	
	public GuitarString (double[] arr)
	{
    	sampleRate = new RingBuffer(arr.length);
    	for (int i = 0; i < arr.length; i++)
    	{
        	sampleRate.enqueue(arr[i]);
    	}
	}
	
	public void pluck()
	{
		for (int i = 0; i < sampleRate.size(); i++)
		{
			sampleRate.dequeue();
			sampleRate.enqueue(-0.5 + (0.5 - -0.5) * random.nextDouble());
		}
	}
	
	public void tic()
	{
		double advance = sampleRate.peek();
		sampleRate.dequeue();
		advance = advance + sampleRate.peek();
		advance = 0.994 * 0.5 *(advance);
		sampleRate.enqueue(advance);
		
		time++;
	}
	
	public double sample()
	{
		return sampleRate.peek();
	}
	
	public int time()
	{
		return time;
	}
	
	private void print()
	{
		System.out.print("[");
		for (int i = 0; i < sampleRate.size(); i++)
		{
			System.out.print(df.format(sampleRate.peek(i)) + ", ");
		}
		System.out.print("end]");
	}
	
 public static void main(String[] args)
 { 
	 GuitarString test = new GuitarString(830);
	 test.pluck();
	 test.tic();
	 test.tic();
	 test.print();
	 System.out.println("\nFirst Value: " + test.sample() + ", time: " + test.time());
 }
}

