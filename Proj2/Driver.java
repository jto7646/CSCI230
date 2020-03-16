/**
 * Driver
 */
public class Driver {

    public static void main(String[] args) {
        RingBuffer testing = new RingBuffer(5);

        testing.enqueue(12.5);
        testing.enqueue(45.6);

        System.out.println(testing.size());
        
        testing.enqueue(32.50);
        //System.out.println(testing.dequeue());
        //System.out.println(testing.dequeue());
        testing.enqueue(45.6);
        testing.enqueue(63.2);
        testing.enqueue(68.23);
    }
}