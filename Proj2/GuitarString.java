import java.lang.Math;

/**
 * GuitarString
 */
public class GuitarString {

    private RingBuffer sampleRate; // = new RingBuffer();

    public GuitarString(Double frequency){
        // create RingBuffer of (44'100 / frequency) rounded to nearest integer
        double temp = Math.ceil(44100/frequency);
        sampleRate = new RingBuffer(temp);
    
        for( int i = 0; i < temp; i++){sampleRate.enqueue(0.00);}
    }

    public GuitarString(Double[] init){
        sampleRate = new RingBuffer(init.length);

        for(int i = 0; i < intit.length; i++){
            sampleRate.enqueue(init[i]);
        }
    }

    void pluck(){
        
    }

    
}