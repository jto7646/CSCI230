import java.util.Scanner;
import java.lang.*;


public class PS1_Test{



    /* Created 02/02/2020 by John O'Leary */

    public static void main(String[] args) {
        
        /* -- Variables -- */
        int[] intArray = new int[10];   // Array stores ints from problem 
        String test = "523*+183/+";     // Test problem in string form
        int arCount = 0;                // Keeps track of loop itteration
        int ans = 0;                    // Temporary math storage for loop
        char tempChar = ' ';            // Temporary character starage for loop

        /* -- Function Body -- */

        /* Note!!! only works with single digit operands */
        // Loop iterates through the problem string
        for( int i = 0; i <= test.length()-1; i++ ){

            tempChar = test.charAt(i);
            if (Character.getNumericValue(tempChar) <= 9 && Character.getNumericValue(tempChar) != -1){ // Test for a number in the string by getting character value (0-9 for number, -1 for anything else)
                intArray[arCount] = Character.getNumericValue(tempChar);
                arCount++;
            }
            else {
                switch (test.charAt(i)) { // Swith use for opperation, each opperation has its own case
                    case '+':
                        ans = (intArray[arCount-1] + intArray[arCount-2]);
                        arCount -= 1;
                        intArray[arCount-1] = ans;
                        break;
                    case '-': 
                        ans = (intArray[arCount-1] - intArray[arCount-2]);
                        arCount -= 1;
                        intArray[arCount-1] = ans;
                        break; 
                    case '*': 
                        ans = (intArray[arCount-1] * intArray[arCount-2]);
                        arCount -= 1;
                        intArray[arCount-1] = ans;
                        break; 
                    case '/': 
                        ans = (intArray[arCount-1] / intArray[arCount-2]);
                        arCount -= 1;
                        intArray[arCount-1] = ans;
                        break; 
                    default:
                        break;
                }
            }
        }

        System.out.println(intArray[arCount-1]);
    }

}

