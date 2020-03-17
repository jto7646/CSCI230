import java.lang.Math;

/**
 * Driver
 */
public class Driver {

    public static void main(String[] args) {
        RingBuffer testing = new RingBuffer(5);

        try {
            testing.enqueue(12.5);
        } catch (Exception e) {
            System.out.println(e);
        }
        //testing.enqueue(12.5);
        
       // testing.enqueue(12.5);
        
       for(int i = 0; i < 25; i++){
           System.out.println(Math.random()-.5);
       }

    }
}