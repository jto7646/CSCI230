import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.management.ThreadInfo;
import java.util.ArrayList;
import java.util.TreeMap;

import jdk.nashorn.api.tree.Tree;

public class MultiTest {
    public static void main(String[] args) {
        Thread main = Thread.currentThread();
        System.out.println("**** TEST START *****");
        TreeMap compTree = new TreeMap<Integer, Integer>();
        InputStream inward, input;

        try {
            inward = new FileInputStream("C:\\cygwin64\\home\\CSCI230\\Proj4\\suspicious-document01501.txt");
            input = new FileInputStream("C:\\cygwin64\\home\\CSCI230\\Proj4\\suspicious-document01502.txt");
            TreeBuilder TB1 = new TreeBuilder("Tree1", inward, compTree);
            TreeBuilder TB2 = new TreeBuilder("Tree2", input, compTree);
            TB1.start();
            main.wait();
            

            compTree = new TreeMap<Integer, Integer>();
            TB2.start();

            System.out.println("MT Tree Size: " + compTree.size());
        } catch (Exception e) {
           System.out.println("File read error: " + e);
        }

        /*
        InputStream testIn, compareIn;
        StringBuilder build = new StringBuilder(0);
        int temp = 0;
        ArrayList<String> compare = new ArrayList<>();
        // Bellow is old way of searching
        try {


            while(true){// Ends when source is empty
                testIn = new FileInputStream("C:\\cygwin64\\home\\CSCI230\\Proj4\\suspicious-document01502.txt");
                // Build out initial list of six words to compare
                while(true){
                    // Build word
                    while(true){
                        // reads in one character
                        temp = testIn.read();
                        // Space or end of file hit
                        if(temp == 32 || temp == 0){break;}
                        // Append character to string
                        build.append((char) temp);
                    }
                    // Add word to arrayList
                    compare.add(build.toString());
                    build = new StringBuilder(0); // clear stringbuilder

                    // Six words to compare to
                    if(compare.size() == 6){break;}
                    // End of file, break
                    if(temp == -1){break;}
                }
                if(temp == -1){break;}// EOF
                //System.out.print("Temp = " + temp + "\n" + "compsize: " + compare.size() + "\n");

                // Run search thread
                try {
                    compareIn = new FileInputStream("C:\\cygwin64\\home\\CSCI230\\Proj4\\suspicious-document01501.txt");
                    WordSearch WS1 = new WordSearch("Search1", compareIn, compare);
                    WS1.start();
                    
                    // Pause Main Thread
                    while(WS1.isRunning){
                        Thread.sleep(100);
                    }
                    System.out.println("Looped");
                } catch (Exception e) {
                    System.out.println("File read error...");
                }
                // Redies array for six more words
                compare.clear();

            }//Body Loop
        } catch (Exception e) {
            System.out.println("Initial array build error ..." + e);
        }*/

                
       
        System.out.println("**** TEST END ****");
    }// MAIN
}