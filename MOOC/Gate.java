package mooc.vandy.java4android.gate.logic;

/**
 * This file defines the Gate class.
 */
public class Gate {
    // TODO -- Fill in your code here

    // ----- Variables -----
    public static int IN = 1;
    public static int OUT = -1;
    public static int CLOSED = 0;
    private int mSwing;

    // ----- Constructor -----
    public Gate(){ mSwing = CLOSED;}


    // ----- Class Methods -----
    /**
     * Set the gate swing direction
     * @param direction IN = 1, OUT = -1, CLOSED = 0
     * @return true if direction successfully changed, false if otherwise
     */
    public boolean setSwing(int direction){
        // Check if passed in value is within range
        if (direction >= -1 && direction <= 1){
            mSwing = direction;
            return true;
        }
        
        return false;
    }

    /**
     * Set the gate to open
     * @param direction IN = 1, OUT = -1
     * @return true if the gate was successfully opened, false if otherwise
     */
    public boolean open(int direction){
        // Check to make sure passed in value isn't closing gate 
        if (direction == 0){ return false; }

        return setSwing(direction);
    }

    /**
     * Set the gate to closed
     */
    public void close(){ mSwing = CLOSED; }

    /**
     * Determine the number of object passed through the gate, depends on the gate position
     * @param count number of initial objects
     * @return
     */
    public int thru(int count){

        // Determine the position of the gate, then return the appropriate value
        switch (mSwing) {
            case 1: // IN
                return -count;
            case -1: // OUT
                return count;
            default: // CLOSED
                return 0;
        }
    }

    /**
     * Returns the swing direction of the gate as an int
     * @return
     */
    public int getSwingDirection(){ return mSwing;}

    /**
     * Returns the swign direction of the gate as a String
     */
    public String toString(){

        // Determine the position of the gate, then return the appropriate caption 
        switch (mSwing) {
            case 1: // IN
                return "This gate is open and swings to enter the pen only";
            case -1: // OUT
                return "This gate is open and swings to exit the pen only";
            case 0: // CLOSED
                return "This gate is closed";
            default: // INVALID 
                return "This gate has an invalid swing direction";
        }
    }


}
