
import java.lang.Math;

/**
 * GuitarString
 * @author John O'Leary
 * @version 1.0
 * @since March/16/2020
 */
public class GuitarString {

    private RingBuffer sampleRate;  // RingBuffer holding the samples 
    private Double  temp, tempFirst, tempSecond, tempAverage; // Variables for the tic() function
    private static int ticNumber = 0;   // Keeps track of the number of times tic() is called

    /**
     * Creates a guitar string with a sample rate of to given frequency
     * @param frequency of string
     */
    public GuitarString(Double frequency){
        temp = Math.ceil(44100/frequency);
        sampleRate = new RingBuffer(temp.intValue());   // Creates new RingBUffer of given size 

        for( int i = 0; i < temp; i++){     // Fills the new RingBuffer with zeros
            try {
                sampleRate.enqueue(0.00);
            } catch (Exception e) {
                System.out.println("GuitarString: " + e);
            }
        }
    }

    /**
     * Creates a guitar string of values given in a double array
     * @param init, array of initial values
     */
    public GuitarString(Double[] init){
        sampleRate = new RingBuffer(init.length); // Creates a new RingBuffer with the same length as given array

        for(int i = 0; i < init.length; i++){ // Fills the ner RingBuffer with the values in the given array
            try {
                sampleRate.enqueue(init[i]);
            } catch (Exception e) {
                System.out.println("GuitarString: " + e);
            }   
        }
    }

    /**
     * Plucks a guitar string by filling it with random values between 0.5 and -0.5
     */
    void pluck(){
       while (!sampleRate.isEmpty()) { // Empties the RingBuffer of values
            try {
                sampleRate.dequeue();
            } catch (Exception e) {
                System.out.println("GuitarString: " + e);
            }
        }

        while (!sampleRate.isFull()) { // Fills the RingBuffer with new values, between 0.5 and -0.5
            try {
                sampleRate.enqueue(Math.random() - .5);
            } catch (Exception e) {
                System.out.println("GuitarString: " + e);
            }
        }
    }

    /**
     * Advances the guitar string simulation by one step
     */
    void tic(){
        // Delete first sample and store its value
        try {
            tempFirst = sampleRate.dequeue();
        } catch (Exception e) {
            System.out.println("GuitarString: " + e);
        }
        // Store the value of second sample, which is now the first
        try {
            tempSecond = sampleRate.peek();
        } catch (Exception e) {
            System.out .println("guitarString: " + e);
        }

        tempAverage = ((tempFirst + tempSecond) / 2) * 0.994; // Calculation of Karplus-Strong update

        // Adds new value to the end of the RingBuffer
        try {
            sampleRate.enqueue(tempAverage);
        } catch (Exception e) {
            System.out.println("GuitarString: " + e);
        }

        ticNumber++; // Keeps track of the number of times tic() has been called
    }

    /**
     * Returns the first sample in the buffer
     * @return double sample
     */
    double sample(){
        temp = 0.00;
        try {
            temp = sampleRate.peek();
        } catch (Exception e) {
            System.out.print("GuitarString: " + e);
        }
        return temp;
    }

    /**
     * Returns the number of simulation steps taken
     * @return int time
     */
    int time(){
        return ticNumber;
    }

    
}// End Class