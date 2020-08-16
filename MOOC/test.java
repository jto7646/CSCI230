import java.text.DecimalFormat;
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
        int day[] = new int[365];

        // Loop that runs the simulations
        for(int i = 1; i <= count; i++){
            
            // Random object used to randomly select each persons birthday
            Random randDay = new Random(i);

            // Make the random set of birthdays, one per person in the group
            for(int j = 0; j < size; j++){
                temp = randDay.nextInt(365); // get random day
                day[temp] += 1; // Add one to that day in the array.
                // If birthday pair is found, take note and end simulation
                if(day[temp] == 2) {
                    doupCount++;
                    break;
                } 
            }

            Arrays.fill(day, 0); // Clears the array back to zero values
            
        }

        // Deterniming the percentage of douplicate birthday simulations
        tempDouble = doupCount / (double) count;
        tempDouble *= 100;


        return tempDouble;

    }

    
}
