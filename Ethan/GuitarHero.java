import java.lang.Math;
import java.util.ArrayList;


public class GuitarHero 
{
	public static void main(String[] args)
	{
		
			// create an array of GuitarString object corresponding with the keyboard frequencies
			GuitarString[] Notes = new GuitarString[37];
			//A ringbuffer object to store sound samples
			RingBuffer samples = new RingBuffer(1000);
			//Initializing the first sample in the Ringbuffer
			
			samples.enqueue(0.0);
			
				
			
			
			//These variables are for coordinates for drawing the lines
			int x1 = 0, x2 = 0;
			double y1 = 0.0, y2 = 0.0;
			
			//This for loop will initialize the guitarString array with the proper frequencies using the given equation
			for (int i = 0; i < 37; i++)
			{
				double freq = 440 * Math.pow(1.05956, i - 24);
				GuitarString note = new GuitarString(freq);
				Notes[i] = note;
			}
			
			//This string has the proper keyboard of notes, used for checking
			String Keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
			
			while (true) 
			{
				//Used for checking if the user enters in a proper character
				boolean checkLetter = false;
				// check if the user has typed a key; if so, process it
				if (StdDraw.hasNextKeyTyped()) 
				{
					char key = StdDraw.nextKeyTyped();
					//check if the key entered is one of the proper keys on the keyboard
					for (int i = 0; i < 37; i++)
					{
						if (key == Keyboard.charAt(i)) 
						{ 
							Notes[i].pluck();
							
							//Using the Ringbuffer object, determine the coordinates for the line
							x1 = samples.getFirst();
							y1 = samples.peek();
							samples.dequeue();
							x2 = samples.getFirst();
							y2 = samples.peek();
							
							//Checking the coordinates above
							System.out.println(x1 + ", " + y1 + " / " + x2 + ", " + y2);
							
							//theoretically draw the line
							StdDraw.show();
							StdDraw.point(0.5, 0.5);
					        StdDraw.setPenColor(StdDraw.BLACK);
					        StdDraw.line(x1, y1, x2, y2);
					        StdDraw.show();
					        
							checkLetter = false;
							break;
						}
						else
						{
							checkLetter = true;
						}
					}
					
					//If the user did not enter a proper character then print an error
					if (checkLetter)
					{
						System.out.println("error");
						
					}
				}
				
				// compute the superposition of samples
				double sample = Notes[0].sample();
				for (int i = 1; i < 37; i++)
				{
					sample += Notes[i].sample();
				}
				// play the sample on standard audio
				StdAudio.play(sample);
				// advance the simulation of each guitar string by one step
				for (int i = 0; i < 37; i++)
				{
					Notes[i].tic();
				}
				
			}
	}
}