package mooc.vandy.java4android.birthdayprob.logic;

import mooc.vandy.java4android.birthdayprob.ui.OutputInterface;
import java.util.Arrays;
import java.util.Random;

/**
 * This is where the logic of this App is centralized for this assignment.
 * <p>
 * The assignments are designed this way to simplify your early Android interactions.
 * Designing the assignments this way allows you to first learn key 'Java' features without
 * having to beforehand learn the complexities of Android.
 *
 */
public class Logic 
       implements LogicInterface {
    /**
     * This is a String to be used in Logging (if/when you decide you
     * need it for debugging).
     */
    public static final String TAG =
        Logic.class.getName();

    /**
     * This is the variable that stores our OutputInterface instance.
     * <p>
     * This is how we will interact with the User Interface
     * [MainActivity.java].
     * <p>
     * It is called 'mOut' because it is where we 'out-put' our
     * results. (It is also the 'in-put' from where we get values
     * from, but it only needs 1 name, and 'mOut' is good enough).
    */
    OutputInterface mOut;

    /**
     * This is the constructor of this class.
     * <p>
     * It assigns the passed in [MainActivity] instance
     * (which implements [OutputInterface]) to 'out'
     */
    public Logic(OutputInterface out){
        mOut = out;
    }

    /**
     * This is the method that will (eventually) get called when the
     * on-screen button labelled 'Process...' is pressed.
     */
    public void process() {
        int groupSize = mOut.getSize();
        int simulationCount = mOut.getCount();

        if (groupSize < 2 || groupSize > 365) {
            mOut.makeAlertToast("Group Size must be in the range 2-365.");
            return;
        }
        if (simulationCount <= 0) {
            mOut.makeAlertToast("Simulation Count must be positive.");
            return;
        }

        double percent = calculate(groupSize, simulationCount);

        // report results
        mOut.println("For a group of " + groupSize + " people, the percentage");
        mOut.println("of times that two people share the same birthday is");
        mOut.println(String.format("%.2f%% of the time.", percent));

    }

    /**
     * This is the method that actually does the calculations.
     * <p>
     * We provide you this method that way we can test it with unit testing.
     */
    public double calculate(int size, int count) {
        int tempInt = 0;
        double tempDouble = 0.0;
        int doupCount = 0;

        // TODO -- add your code here
        // Array holding the birth dates
        int day[] = new int[364];
        // Array holding the percentage of same birthdays per simulation
        double percentArr[] = new double[count];

        // Loop that runs the simulations
        for(int i = 1; i <= count; i++){
            
            // Random object used to randomly select each persons birthday
            Random randDay = new Random(i);

            // Make the random set of birthdays, one per person in the group
            for(int j = 0; j < size; j++){
                temp = randDay.nextInt(364); // get random day
                day[temp]++; // Add one to that day in the array 
            }

            // calculate the percentage of douplicate days, add to the array for final calculation
            for (int d : day) { // Finding number of douplicate dates 
                if (i >= 2){ doupcount++; }
            }
            tempDouble = doupCount / 3.65; // Calculating percentage
            percentArr[i - 1] = tempDouble; // Adding percentage to the array
            day = new int[364]; // Clears the dat array, ready for next simulation
        }

        // Calculate and return the average percentage of all runs
        tempDouble = 0.0;
        for (double d : percentArr) {
            tempDouble += d;   
        }
        return tempDouble / count;

    }

    // TODO - add your code here
}
