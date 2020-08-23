package mooc.vandy.java4android.gate.logic;

import java.util.Random;

import mooc.vandy.java4android.gate.ui.OutputInterface;

/**
 * This class uses your Gate class to fill the corral with snails.  We
 * have supplied you will the code necessary to execute as an app.
 * You must fill in the missing logic below.
 */
public class FillTheCorral {
    /**
     * Reference to the OutputInterface.
     */
    private OutputInterface mOut;

    /**
     * Constructor initializes the field.
     */
    FillTheCorral(OutputInterface out) {
        mOut = out;
    }

    // TODO -- Fill your code in here

    /**
     * Randomly set the gate positions for each gate in the passed in array
     * @param gate array of gate objects
     * @param slectDirection 
     */
    public void setCorralGates(Gate[] gate, Random slectDirection){
        //int direction = 0; // Direction of the gate 

        // Loop through the array of gates
        for (Gate current : gate) {
            // Randomly select a gate position, then set the current gate to that position
            switch (selectDirection.nextInt(2) - 1) {
                case 0: // Close Gate
                    current.setSwing(0);
                    break;
                case 1: // Gate opens inward
                    current.setSwing(1);
                    break;
                case -1: // Gate opens outward
                    current.setSwing(-1); 
                    break;
                default:
                    mOut.println("INVALID INPUT");
                    break;
            }
        }
    }

    /**
     * Check the array of gate object for a gate set to open inward
     * @param corral
     * @return true if an inward opening gate found, false if not
     */
    public boolean anyCorralAvailable(Gate[] corral){

        for (Gate gate : corral) {
            if(gate.getSwingDirection() == Gate.IN){ return true; }
        }

        return false;
    }

    

}
