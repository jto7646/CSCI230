import java.util.Arrays;
import java.util.Random;

import jdk.jfr.Percentage;

public class test {

    public static void main(String args[]){

    int size = 20;
    int count = 10000;

    double tempDub = calculate(size, count);

    System.out.println("Percentage found: " + tempDub);


    }

    public static double calculate(int size, int count) {
        int temp = 0;
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
                if (d >= 2){ doupCount++; }
            }
            tempDouble = doupCount / 3.65; // Calculating percentage
            percentArr[i - 1] = tempDouble; // Adding percentage to the array
            day = new int[364]; // Clears the date array, ready for next simulation
            doupCount = 0;
        }
        
        
        // Calculate and return the average percentage of all runs
        tempDouble = 0.0;
        for (double p: percentArr) {
            tempDouble += p;   
        }

        System.out.println("TD = " + tempDouble);

        return tempDouble / count;

    }

    
}
