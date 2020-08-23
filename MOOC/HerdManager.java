package mooc.vandy.java4android.gate.logic;

import java.util.Random;

import mooc.vandy.java4android.gate.logic.Gate;
import mooc.vandy.java4android.gate.ui.OutputInterface;

/**
 * This class uses your Gate class to manage a herd of snails.  We
 * have supplied you will the code necessary to execute as an app.
 * You must fill in the missing logic below.
 */
public class HerdManager {
    /**
     * Reference to the output.
     */
    private OutputInterface mOut;

    /**
     * The input Gate object.
     */
    private Gate mEastGate;

    /**
     * The output Gate object.
     */
    private Gate mWestGate;

    /**
     * Maximum number of iterations to run the simulation.
     */
    private static final int MAX_ITERATIONS = 10;

    /**
     * Constructor initializes the fields.
     */
    public HerdManager(OutputInterface out,
                       Gate westGate,
                       Gate eastGate) {
        mOut = out;

        mWestGate = westGate;
        mWestGate.open(Gate.IN);

        mEastGate = eastGate;
        mEastGate.open(Gate.OUT);
    }

    // TODO -- Fill your code in here
    /**
     * Size of the snail herd in the corral
     */
    public static final int HERD = 24; // Size of the snail corral 


    /**
     * Runs a set number of simulations, randomly moving a set number of smnails in and out of a corral.
     * Prints the number of snails on the inside and outside of the corral each simulation.
     * @param rand
     */
    public void simulateHerd(Random rand){
        int herdSize = HERD;    // Total number of snails
        int simRun = 0;         // Total number of simulation runs
        int moving = 0;         // Number of snails moving through a gate  

        // Print out the status of the gates 
        mOut.println("East Gate: This gate is Closed");
        mout.println("West Gate: This gate is Closed");

        // Loop to run the herd simulations
        while(simRun <= MAX_ITERATIONS){

            switch (herdSize) {
                case HERD: // All snails in pen
                    // Select random nuber of snails moving out of pen
                    moving = rand.nextInt(HERD - 1) + 1;
                    // Move the snails out of the pen, account for the movement in the herd size
                    herdSize += mEastGate.thru(moving);
                    break;
                case 0:  // All snails out of pen 
                    // Select random number of snails moving into pen 
                    moving = rand.nextInt(HERD - 1) + 1;
                    // Move the snails into the pen, account for the move in the herd size
                    herdSize += mWestGate.thru(moving);
                    break;
                default:   // Some snails both in and out of pen
                    // Randomly select the gate
                    if( moving = rand.nextInt(1) == 0){ // Exiting pen 
                        // Randomly select number of snails to exit the pen
                        moving = rand.nextInt(herdSize - 1) + 1;
                        // Move the snails out of the pen, account for the move in the herd size 
                        herdSize += mEastGate.thru(moving);
                    }
                    else{       // Entering pen 
                        // Randomly select number of snails to enter pen
                        moving = rand.nextInt(HERD - herdSize - 1) + 1;
                        // Move the snails into pen, account for the move in herd size 
                    }
                    break;
            }

            // Print the number of snails in and out of the corral
            mOut.println("There are currently " + herdSize + " snails in the pen and " + (HERD - herdSize) + " snails in the pasture.");

            simRun++; // Increase simulation count

            // 1. Randomly select a gate
            //      *Direction of movement depends on the gate
            //      *If there are no snails in the pen, pull a ramdom number in. Vice-versa
            // 2. Ramdomly select a number of snails to move through that gate
            //      * Cannot go negative in or out of pen.
            // 3. Print out the number of snails both in and out of the pen
        }
        
    }

}
