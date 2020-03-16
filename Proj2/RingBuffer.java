import java.util.Arrays;
import java.lang.Exception;

/**
 * RingBuffer
 */
public class RingBuffer {

    
private Double buffer[];
private int firstIndex, lastIndex;
private RingBufferException enqueueError = new RingBufferException();//TODO FIX THIS!!!

public RingBuffer(){
    buffer = new Double[100];
    Arrays.fill(buffer, null);
}


public RingBuffer(int capacity){
    buffer = new Double[capacity];
    Arrays.fill(buffer, null);
}

public int size(){
    int counter = 0;
    try {
        for(int i = 0; i < buffer.length; i++){
            if(buffer[i] == null){counter++;}
        }
        return (buffer.length - counter);        
    } catch (Exception e) {
        System.out.println("sizeError");
    }
    return 0;  
}

public void enqueue(Double x){
   if(buffer[lastIndex] == null){
       buffer[lastIndex] = x;
       lastIndex = (lastIndex + 1) % buffer.length;
   }
   else{
        throw new ArrayIndexOutOfBoundsException("Enqueue error...");
        //System.out.println("RingBufferException"); // TODO: THROW ACTUAL EXCEPTION
   }
}

public Double dequeue(){
    if(isEmpty()){
        throw new ArrayIndexOutOfBoundsException("Dequeue error...");
    }

    Double temp = buffer[firstIndex];
    buffer[firstIndex] = null;
    firstIndex = (firstIndex + 1) % buffer.length;
    return temp;
}

public Double peek(){
    if(isEmpty()){
        throw new ArrayIndexOutOfBoundsException("Peek error...");
        //System.out.print("Array Empty Exception"); // TODO: THROW ERROR TO TERMINATE
    }

    return buffer[firstIndex];
}


public Boolean isEmpty(){
    return(size() == 0);
}

public Boolean isFull(){
    return(size() == buffer.length);
}

public class RingBufferException extends Exception{

    public RingBufferException(){
       super();
    }

    public RingBufferException(String message){
        super(message);
    }
}

}// End of class