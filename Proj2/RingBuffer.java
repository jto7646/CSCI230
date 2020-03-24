

import java.util.Arrays;
import java.lang.Exception;

/**
 * Ringbuffer
 * @author John T OLeary
 * @version 1.0
 * @since March/16/2020
 */
public class RingBuffer {

    
private Double buffer[];            // Double array used as the buffer
private int firstIndex, lastIndex;  // Variables that store the location of the first and last items in the buffer


/**
 * Creates a RingBuffer of default size 100. Acts like a Que.
 */
public RingBuffer(){
    buffer = new Double[100];
    Arrays.fill(buffer, null); //Fills the buffer with null pointers to indicate empty locations
}

/**
 * Creates a RingBuffer of size given by int parameter. Acts like a Que.
 * @param capacity
 */
public RingBuffer(int capacity){
    buffer = new Double[capacity];
    Arrays.fill(buffer, null); //See above constructor 
}

/**
 * Returns the number of items stored in the RingBuffer.
 * @return int
 */
public int size(){
    int counter = 0;
    /* This function counts the nubber of empty spaces in the buffer, indicated by null pointers.
     The number of empty spaces is subtracted from the lenght of the buffer, giving the number of
     items in the buffer. This is returned as the size*/ 
    
    for(int i = 0; i < buffer.length; i++){
        if(buffer[i] == null){counter++;}
    }
    return (buffer.length - counter);        
}

/**
 * Adds a given double to the end of the RingBuffer, will throw exception if the RingBuffer is full.
 * @param x
 * @throws RingBufferException
 */
public void enqueue(Double x) throws RingBufferException{
   if(buffer[lastIndex] == null){
       buffer[lastIndex] = x;
       lastIndex = (lastIndex + 1) % buffer.length;
   }
   else{
        throw new RingBufferException("Enque attempt with full buffer...");
   }
}

/**
 * Returns the first item in the RingBuffer, then removes it from the Buffer
 * @return Double 
 * @throws RingBufferException
 */
public Double dequeue() throws RingBufferException{ 
    if(isEmpty()){ //If the buffer is empty, throws an exception
        throw new RingBufferException("Dequeue attempt with empty buffer...");
    }

    Double temp = buffer[firstIndex];   //Store first item
    buffer[firstIndex] = null;          //Makes first location empty
    firstIndex = (firstIndex + 1) % buffer.length; //Increments firstIndex pointer
    return temp;                        //Returns the original first item
}

/**
 * Returns the first item in the RingBuffer, does not remove it from the buffer
 * @return Double 
 * @throws RingBufferException
 */
public Double peek() throws RingBufferException{
    if(isEmpty()){ //If the buffer is empty, throws an exception
       throw new RingBufferException("Peek attempt with empty buffer...");
    }

    return buffer[firstIndex];
}

/**
 * Returns true if RingBuffer is empty
 * @return Boolean
 */
public Boolean isEmpty(){
    return(size() == 0);
}

/**
 * Returns true if RingBuffer is full
 * @return Boolean
 */
public Boolean isFull(){
    return(size() == buffer.length);
}


// The small class below is used to make the throw exceptions in RingBuffer
public class RingBufferException extends Exception{
    
    public RingBufferException() {
       super();
    }

    public RingBufferException(String message){
        super(message);
    }
}

}// End of class