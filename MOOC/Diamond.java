public class Diamond {
    
    public static void main(String args[]){
        int sizeInput = 7;
        
        // Top of frame printout 
        frameTB(sizeInput);

        // Top of diamond printout
        diamondTop(sizeInput);


        // Center of diamond printout
        System.out.print("|<");
        for(int i = 0; i < sizeInput * 2 - 2; i++){
            System.out.print("-");
        }
        System.out.println(">|");

        // Bottom of diamond printout
        diamondBottom(sizeInput);

        // Bottom of frame printout
        frameTB(sizeInput);

    }
    
    /**
     * Prints the top or bottom of the frame whos lenght is passed in.
     * Note: only prints one line, needs to be called for both top and bottom.
     *  *** ENDS WITH NEW LINE *** 
     * @param length
     */
    public static void frameTB(int length){

        System.out.print("+"); // initial plus sign print

        // Prints out the line between the plus signs, in the amount of double "length"
        for(int i = 0; i < length * 2; i++){
            System.out.print("-");
        }

        System.out.println("+"); // Final plus sign print, followed by new line
    }

    /**
     * Prints the top partion of the diamond using the passed in length to determine the number of levels
     * *** ENDS WITH NEW LINE ***
     * @param length
     */
    public static void diamondTop(int length){
        // Number of levels to be drawn for the diamond
        int level = length - 1;
        // Used to determine the level of the diamond being printed 
        int symbols = 0;

        // Loop used to build the levels of the diamond (TOP).
        // The lines variable counts down to zero. It is used to indicate when 
        // the center of the diamond is hit. There are length - 1 levels to each 
        // half of the diamond.
        while (level > 0){
            
            System.out.print("|");
            // Prints the spaces before the "filler"
            for (int i = 0; i < level; i++){
                System.out.print(" ");
            }
            
            
            System.out.print("/");
            // Prints the diamond filler.
            // Checks for odd/even levels. Odds are "-", evens are "=".
            if (level % 2 == 0){ // Even level
                for(int i = symbols * 2; i > 0; i--){
                    System.out.print("-");
                }
            }
            else{ // Odd level
                for(int i = symbols * 2; i > 0; i--){
                    System.out.print("=");
                }
            }

            // Prints spaces after the "filler"
            System.out.print("\\");
            for(int i = 0; i < level; i++){
                System.out.print(" ");
            }
            System.out.println("|");

            level--;
            symbols++; // Used to count the number of symbols in each level of the diamond
        }
    }//diamondTop()


    /**
     * Prints the bottom portiion of the diamond using the passed in length to determine the number of levels
     *  *** ENDS WITH NEW LINE ***
     * @param length
     */
    public static void diamondBottom(int length){
        // Used to count the number of levels on of the diamond, counts up to length-1 in while loop
        int level = 1;
        // Used to determine the level of the diamond being printed 
        int symbols = length - 2;

        // Loop used to build the levels of the diamond (TOP).
        // The lines variable counts down to zero. It is used to indicate when 
        // the center of the diamond is hit. There are length - 1 levels to each 
        // half of the diamond.
        while (level <= length - 1){
            
            System.out.print("|");
            // Prints the spaces before the "filler"
            for (int i = 0; i < level; i++){
                System.out.print(" ");
            }
            
            
            System.out.print("\\");
            // Prints the diamond filler.
            // Checks for odd/even levels. Odds are "-", evens are "=".
            if (level % 2 == 0){ // Even level
                for(int i = symbols * 2; i > 0; i--){
                    System.out.print("-");
                }
            }
            else{ // Odd level
                for(int i = symbols * 2; i > 0; i--){
                    System.out.print("=");
                }
            }

            // Prints spaces after the "filler"
            System.out.print("/");
            for(int i = 0; i < level; i++){
                System.out.print(" ");
            }
            System.out.println("|");

            level++;
            symbols--; // Used to count the number of symbols in each level of the diamond
        }
    }//diamondBottom()

}//Class