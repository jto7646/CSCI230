package mooc.vandy.java4android.diamonds.logic;

import mooc.vandy.java4android.diamonds.ui.OutputInterface;

/**
 * This is where the logic of this App is centralized for this assignment.
 * <p>
 * The assignments are designed this way to simplify your early
 * Android interactions.  Designing the assignments this way allows
 * you to first learn key 'Java' features without having to beforehand
 * learn the complexities of Android.
 */
public class Logic
       implements LogicInterface {
    /**
     * This is a String to be used in Logging (if/when you decide you
     * need it for debugging).
     */
    public static final String TAG = Logic.class.getName();

    /**
     * This is the variable that stores our OutputInterface instance.
     * <p>
     * This is how we will interact with the User Interface [MainActivity.java].
     * <p>
     * It is called 'out' because it is where we 'out-put' our
     * results. (It is also the 'in-put' from where we get values
     * from, but it only needs 1 name, and 'out' is good enough).
     */
    private OutputInterface mOut;

    /**
     * This is the constructor of this class.
     * <p>
     * It assigns the passed in [MainActivity] instance (which
     * implements [OutputInterface]) to 'out'.
     */
    public Logic(OutputInterface out){
        mOut = out;
    }

    /**
     * This is the method that will (eventually) get called when the
     * on-screen button labeled 'Process...' is pressed.
     */
    public void process(int size) {
        String midSymbol = "-";
        // Top of frame printout
        frameTB(size);

        // Top of diamond printout
        diamondTop(size);

        if(size % 2 != 0){midSymbol = "=";}

        // Center of diamond printout
        mOut.print("|<");
        for(int i = 0; i < size * 2 - 2; i++){
            mOut.print(midSymbol);
        }
        mOut.println(">|");

        // Bottom of diamond printout
        diamondBottom(size);

        // Bottom of frame printout
        frameTB(size);

    }

    /**
     * Prints the top or bottom of the frame whos lenght is passed in.
     * Note: only prints one line, needs to be called for both top and bottom.
     *  *** ENDS WITH NEW LINE ***
     * @param length
     */
    public void frameTB(int length){

        mOut.print("+"); // initial plus sign print

        // Prints out the line between the plus signs, in the amount of double "length"
        for(int i = 0; i < length * 2; i++){
            mOut.print("-");
        }

        mOut.println("+"); // Final plus sign print, followed by new line
    }

    /**
     * Prints the top partion of the diamond using the passed in length to determine the number of levels
     * *** ENDS WITH NEW LINE ***
     * @param length
     */
    public void diamondTop(int length){
        // Number of levels to be drawn for the diamond
        int level = length - 1;
        // Used to determine the level of the diamond being printed
        int symbols = 0;
        // Used to determine the symbol printed on each level
        int levelSymbol = 0;

        // Loop used to build the levels of the diamond (TOP).
        // The lines variable counts down to zero. It is used to indicate when
        // the center of the diamond is hit. There are length - 1 levels to each
        // half of the diamond.
        while (level > 0){

            mOut.print("|");
            // Prints the spaces before the "filler"
            for (int i = 0; i < level; i++){
                mOut.print(" ");
            }


            mOut.print("/");
            // Prints the diamond filler.
            // Checks for odd/even levels. Odds are "-", evens are "=".
            if (levelSymbol % 2 == 0){ // Even level
                for(int i = symbols * 2; i > 0; i--){
                    mOut.print("=");
                }
            }
            else{ // Odd level
                for(int i = symbols * 2; i > 0; i--){
                    mOut.print("-");
                }
            }

            // Prints spaces after the "filler"
            mOut.print("\\");
            for(int i = 0; i < level; i++){
                mOut.print(" ");
            }
            mOut.println("|");

            level--;
            symbols++; // Used to count the number of symbols in each level of the diamond
            levelSymbol++;
        }
    }

    /**
     * Prints the bottom portion of the diamond using the passed in length to determine the number of levels
     *  *** ENDS WITH NEW LINE ***
     * @param length
     */
    public void diamondBottom(int length){
        // Used to count the number of levels on of the diamond, counts up to length-1 in while loop
        int level = 1;
        // Used to determine the level of the diamond being printed
        int symbols = length - 2;
        // Used to determine the symbol printed on each level
        int levelSymbol = length - 1;


        // Loop used to build the levels of the diamond (TOP).
        // The lines variable counts down to zero. It is used to indicate when
        // the center of the diamond is hit. There are length - 1 levels to each
        // half of the diamond.
        while (level <= length - 1){

            mOut.print("|");
            // Prints the spaces before the "filler"
            for (int i = 0; i < level; i++){
                mOut.print(" ");
            }


            mOut.print("\\");
            // Prints the diamond filler.
            // Checks for odd/even levels. Odds are "-", evens are "=".
            if (levelSymbol % 2 == 0){ // Even level
                for(int i = symbols * 2; i > 0; i--){
                    mOut.print("-");
                }
            }
            else{ // Odd level
                for(int i = symbols * 2; i > 0; i--){
                    mOut.print("=");
                }
            }

            // Prints spaces after the "filler"
            mOut.print("/");
            for(int i = 0; i < level; i++){
                mOut.print(" ");
            }
            mOut.println("|");

            level++;
            symbols--; // Used to count the number of symbols in each level of the diamond
            levelSymbol++;
        }
    }

}
