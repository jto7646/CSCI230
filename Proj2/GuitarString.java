import java.lang.Math;

/**
 * GuitarString
 */
public class GuitarString {

    private RingBuffer sampleRate; // = new RingBuffer();

    public GuitarString(Double frequency){
        // create RingBuffer of (44'100 / frequency) rounded to nearest integer
        Double temp = Math.ceil(44100/frequency);
        sampleRate = new RingBuffer(temp.intValue());
        for( int i = 0; i < temp; i++){
            try {
                sampleRate.enqueue(0.00);
            } catch (Exception e) {
                System.out.println("GuitarString: " + e);
            }
        }
    }

    public GuitarString(Double[] init){
        sampleRate = new RingBuffer(init.length);

        for(int i = 0; i < init.length; i++){
            try {
                sampleRate.enqueue(init[i]);
            } catch (Exception e) {
                System.out.println("GuitarString: " + e);
            }   
        }
    }

    void pluck(){
        while (!sampleRate.isEmpty()) { //empty
            try {
                sampleRate.dequeue();
            } catch (Exception e) {
                System.out.println("GuitarString: " + e);
            }
        }

        while (!sampleRate.isFull()) { //fill
            try {
                sampleRate.enqueue(Math.random() - .5);
            } catch (Exception e) {
                System.out.println("GuitarString: " + e);
            }
        }
    }

    
}// End Class