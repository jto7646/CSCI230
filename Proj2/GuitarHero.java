import java.lang.Math;
import java.lang.reflect.Array;
import java.lang.Thread;
//import java.util.Arrays;

/**
 * GuitarHero
 * @author John OLeary
 * @version 1.0
 * @since March/25/2020
 */
public class GuitarHero {

    public static void main(String[] args) {
        int index = 0;
        int indCheck = -1;
        Double frequencyFiller = 0.00;
        Double sample = 0.00;
        GuitarString[] keyValues = new GuitarString[37];  // Array holding 37 GuitarStrings for the keyboard

        
       // Bellow for graph drawing
        StdDraw.setPenRadius(.0035);
        StdDraw.setScale(0.0, 100.0);
        StdDraw.setPenColor(StdDraw.RED);
        double drawIndex = 0.0;
        double temp = 0.00;
        StdDraw.enableDoubleBuffering();

       
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        char key = 'f';

        for(int i = 0; i < 37; i++){
            frequencyFiller = 440.0 * Math.pow(1.05956, (i - 24.0) ); 
            keyValues[i] = new GuitarString(frequencyFiller);
        }
        

        while (true) {
           
            if(StdDraw.hasNextKeyTyped()){
                key = StdDraw.nextKeyTyped();
                indCheck = indexOf(keyboard, key);
                if(indCheck != -1){
                    index = indCheck;
                    keyValues[index].pluck();
                }
            }
            sample = keyValues[index].sample();
            
            // SuperPosition
            for(int i = 0; i < 37; i++){
                sample +=keyValues[i].sample();
            }


            StdAudio.play(sample);

             // New Stuff
             StdDraw.line((drawIndex), (50.0 + (temp * 10)), (drawIndex + 1), (50.0 + (sample * 10)));
             if(drawIndex == 99){
                StdDraw.show();
                StdDraw.clear();}
             drawIndex = (drawIndex + 1) % 100;
             temp = sample;
             // ^^^^^^^^


           for(int i = 0; i < 37; i++){
                keyValues[i].tic();
             }
        }//END WHILE  
    } // END MAIN

    public static int indexOf(String string, char find){

        for(int i = 0; i < string.length(); i++){
            if(string.charAt(i) == find){return i;}
        }
        return -1;
    }// END INDEXOF
}