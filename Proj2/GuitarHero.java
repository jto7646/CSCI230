import java.lang.Math;

//import java.util.Arrays;

/**
 * GuitarHero: Hit the keys to make beautiful rock music!
 * @author John OLeary
 * @version 2.0
 * @since April/8th/2020
 */
public class GuitarHero {

    public static void main(String[] args) {
        int index = 0; // Stores the index of key in keyboard
        int indCheck = -1; // Used to check if valid key index found
        Double frequencyFiller = 0.00; // Used for filling the array 
        Double sample = 0.00;   // Stores the current audio sample
        GuitarString[] keyValues = new GuitarString[37];  // Array holding 37 GuitarStrings for the keyboard

        
       // ***** Bellow for graph drawing ****
        StdDraw.setPenRadius(.0035);
        // VV Makes draw area 100X100 pixels
        StdDraw.setScale(0.0, 100.0);   
        StdDraw.setPenColor(StdDraw.RED);
        // VV Used as a timer to hide the graph as it draws
        double drawIndex = 0.0; 
        // VV Temp storage for the sample used as starting point for next line draw       
        double temp = 0.00;             
        StdDraw.enableDoubleBuffering();
        // VV Used as a delay time for the image to start drawing,
        // VV needed for the program to run smoothly
        double drawDelay = 0.00;
        // ***^^^**********************^^^***

        // VV This string holds the valid key presses. The users input is checked 
        // VV against this string to validate the key press
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        char key = 'f'; // Stores user input

        // VV The GuitarString array is filled with GuitarStrings of different frequencies
        for(int i = 0; i < 37; i++){
            frequencyFiller = 440.0 * Math.pow(1.05956, (i - 24.0) ); 
            keyValues[i] = new GuitarString(frequencyFiller);
        }
        

    
        while (true) { // Program body loop 
           
            // VV Checks for key presses, initiates if a key is pressed
            if(StdDraw.hasNextKeyTyped()){
                // VV stores the pressed key as character
                key = StdDraw.nextKeyTyped();
                // VV Tries to find the key in the keyboard string, if found will return
                // VV the index, if not found will return -1 
                indCheck = indexOf(keyboard, key); 
                // VV Plucks the tring at the location within the array using the index from the 
                // VV keyboard string as the array index, if key was found in the string 
                if(indCheck != -1){
                    index = indCheck;
                    keyValues[index].pluck();
                }
            }
            sample = keyValues[index].sample();
            
            // VV Calculates the superposition of the plucked strings sample
            for(int i = 0; i < 37; i++){
                sample +=keyValues[i].sample();
            }

            // VV Plays the audio sample of the plucked string
            StdAudio.play(sample);

            

            // ***** Bellow for graph drawing **** 
            // VV This bit of code draws 100 little line segments using coordinates derived from the currently
            // VV playing audio sample. Each little line is drawn from the end of the first one to the value stored 
            // VV in the sample (times ten to make it a little bigger). The Y coordinates have 50 added to them to 
            // VV make the lines draw close to the center of the 100 X 100 pixel box.
            if(drawDelay % 5 == 0){ //Delay for smooth running
                // VV The little line being drawn
                StdDraw.line((drawIndex), (50.0 + (temp * 10)), (drawIndex + 1), (50.0 + (sample * 10)));
                // VV Only displays the graph after all of the lines are drawn
                if(drawIndex == 99){
                    StdDraw.show();// Shows hidden display buffer
                    StdDraw.clear();} // Hides display buffer again
                drawIndex = (drawIndex + 1) % 100;
                temp = sample; // Stores end of previos line
            }
            // ***^^^**********************^^^***

            // VV Advances the guitar string simulation by one step for all strings
           for(int i = 0; i < 37; i++){
                keyValues[i].tic();
             }

             drawDelay++; // Draw delay incrementation

        }//END OF BODY LOOP 
    } // **** END OF MAIN ****

    /**
     * Returns the index of a given character as an int value within a given String. Returns -1 if the character is not found
     * @param string
     * @param find
     * @return index if found, -1 if not 
     */
    public static int indexOf(String string, char find){

        // VV Iterates through the string, returning the index where the character is found
        for(int i = 0; i < string.length(); i++){
            if(string.charAt(i) == find){return i;}
        }
        return -1;
    }// END INDEXOF
}