import java.io.InputStream;

public class CharCounter /*implements ICharCounter*/{

    private int numChar;
    private static int[] arr = new int[256];

    /**
     * Returns the count associated with specified character.
     * @param ch is the chunk/character for which count is requested
     * @return count of specified chunk, -1 if pased in int is invalid
     */
    public static int getCount(int ch){
        // Make sure this conditional Works!!
        return ((ch >= 0) && (ch <= 255)) ? arr[ch] : -1;  
    }
    
    /**
     * Initialize state by counting bits/chunks in a stream
     * @param stream is source of data
     * @return count of all chunks/read
     * @throws IOException if reading fails
     */
    public static int countAll(InputStream stream) /*throws IOException*/{
        // iterate through the stream, get ascii value of character, add one to that location in the array
        int walk = 0;
        int walkCount = 0;
        //boolean endLoop = false;
        try {
            while(true){ // -1 means the stream has reached the end
                walk = stream.read(); // returns in value 0-255
                if(walk == -1) break;
                arr[walk]++;
                walkCount++;   
            }
            return walkCount;
        } catch (Exception e) {
            System.out.println(e);
        }
        return -1;
    }

    /**
     * Returns a deep copy of the array of character counts
     * @return int array of character counts
     */
    public static int[] countValues(){
        return arr.clone();
    }
    
    
    /**
     * Update state to record one occurrence of specified chunk/character.
     * @param i is the chunk being recorded
     */
    public static void add(int i){
        if((i >= 0) && (i <= 255)) arr[i]++;
    }
    
    /**
     * Set the value/count associated with a specific character/chunk.
     * @param i is the chunk/character whose count is specified
     * @param value is # occurrences of specified chunk
     */
    public static void set(int i, int value){
        if((i >= 0) && (i <= 255) && (value >= 0)) arr[i] = value;
    }
    
    /**
     * All counts cleared to zero.
     */
    public static void clear(){
        for(int i = 0; i <= 255; i++) arr[i] = 0;
    }

    @Override
    public int[] clone(){
        int[] arrClone = new int[255];
        int temp = 0;

        for(int i =  0; i <= 255; i++){
            temp = arr[i];
            arrClone[i] = temp;
        }

        return arrClone;
    }



}// **** END OF CLASS ****