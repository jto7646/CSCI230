import java.util.Scanner;
import java.util.ArrayList;


public class SimpleCalc {

    public static void main(String[] args) {

        /* Instatiations */
        Scanner input = new Scanner(System.in); // Used for user input
        ArrayList list = new ArrayList<>();     // Used to store user input
        Boolean endProg = false;                // Used as exit perameter
        Boolean struct = true;                  // Determines if first time instructions are displayed
        Boolean compute = false;                // Used to exit the user input loop
        Boolean testing = true;
        int test = 0;//TESTING

        while(!endProg){ // Loop holding the program
            if(struct){ // First time instructions
                System.out.println("Simple clculator program:\nPress enter after each opperator or opperand\nCan use parenthesis\n");
                System.out.println("Special opperators:\nSquare root: sqrt Exponent: pow");
                System.out.println("*****************************************************");
                struct = false;
            }

            while(!compute){    // Loop for user input
                list.add(input.next()); // adds an element to the arrayList
                if( (list.get(list.size()-1)) == "e "){compute = true;}; // Checks if last entered is "e", if so breaks loop
            }
            compute = false;
            list.remove(list.size()-1); // removes the "e" from the end of the arraylist

            // Make loop for inner test loops!!
            while(testing){     // Loop used for error checking

                for(int i = 0; i <list.size(); i++){ // Loop used for checking for parenthisis errors
                    
                }

            }
            testing = true;
            //if(test == 3){endProg = true;}//TESTING
           // test++; //TESTING
        }
        
        System.out.println("Size " + list.size());//TESTING
        System.out.println("Contents " + list.get(0) );//TESTING
        input.close();
    }
    
}



// Create Scanner for input !DONE!
// Create Array list to store problem   !DONE!
// Create Program loop with exit code   !DONE!
// Print out instructions

    //Loop for input, While loop with exit code?   !DONE! 
        // - Add opperator/opperand to list after every enter

// Error Checking Sequence
    // Run throught and check for proper parenthisis - loop
        // - They have pairs, have stuff between them
        // - If error, throw perenthisis error
    // Run through and check for propper opperators - loop
        // - have numbers between them
        // - Throw error if wrong
    // Run through and check for proper opperands - loop
        // - have oppereators between, no double decimals, ect...
        // - Throw error if wrong
    // Run through and check for complex opperands (sqrt, pow, ect) - loop
        // - Correct spelling, have stuff in them
        // - throw error if wrong.

// Do Problem - follow PMDAS
    // Parenthisis first - loop
        // find parenthisis pair, then do the opperation in the middle
        // Delete the opperands/opperators, replace with answer
    // Do mult/div - loop
        // including sqrt, pow
    // Do addition and subtraction - loop

// Output the answer! -Array list will be size 1 by the end
    // prompt for another problem, or go straight into it