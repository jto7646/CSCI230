import java.lang.Math;
import java.lang.reflect.Array;
//import java.util.Arrays;

/**
 * GuitarHero
 */
public class GuitarHero {

    public static void main(String[] args) {
        int index = -1;
        Double frequencyFiller = 0.00;
        Double sample = 0.00;
        GuitarString[] keyValues = new GuitarString[37];  // Array holding 37 GuitarStrings for the keyboard
       
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        char key = 'f';

        for(int i = 0; i < 37; i++){
            frequencyFiller = 440.0 * Math.pow(1.05956, (i - 24.0) ); 
            keyValues[i] = new GuitarString(frequencyFiller);
        }
        
        while (true) {
           
            if(StdDraw.hasNextKeyTyped()){
                key = StdDraw.nextKeyTyped();
                index = indexOf(keyboard, key);
                if(index != -1){keyValues[index].pluck();}
                //index = -1;
                     // find index of key in string keyboard  
            }

                sample = 0.00;
                for(int i = 0; i < 37; i++){
                    sample += keyValues[i].sample();
                } 

                StdAudio.play(sample);
                System.out.println(key + " " + index);

            

            for(int i = 0; i < 37; i++){
                keyValues[i].tic();
            }
            //index = -1;
        }//END WHILE  
    } // END MAIN

    public static int indexOf(String string, char find){

        for(int i = 0; i < string.length(); i++){
            if(string.charAt(i) == find){return i;}
        }
        return -1;
    }// END INDEXOF
}