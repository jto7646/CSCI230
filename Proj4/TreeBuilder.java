import java.io.InputStream;
import java.util.ArrayList;
import java.util.TreeMap;

public class TreeBuilder implements Runnable{
    private Thread red;
    private String threadName;
    private InputStream input;
    TreeMap hashTree;
    boolean TBRunning = false;
    int matchCount;

    public TreeBuilder(String name, InputStream in, TreeMap tree){
        threadName = name;
        input = in;
        hashTree = tree;
    }

    public void inputFile(InputStream in){input = in;}

    @Override
    public void run(){                              //TODO Variable word chunk sizes, passed in 
        TBRunning = true;
        boolean prevAlid = false;
        int temp = 0;
        ArrayList<String> incoming = new ArrayList<>();
        StringBuilder sBuilder = new StringBuilder(0);
        
        try { 
            // Build initial list of six words
            while (true) {
                // Read in a character
                temp = input.read();
                if(temp == -1){break;}// EOF break
                // Checks for characters/numbers only
                if(isValid(temp)){ // Valid add to the string
                    sBuilder.append((char) temp);
                    prevAlid = true;
                }
                // Word ends if last was valid and current is not 
                else if(!isValid(temp) && prevAlid){
                    incoming.add(sBuilder.toString());
                    // Clears the string builder
                    sBuilder = new StringBuilder(0);
                    prevAlid = false;
                }
                else{prevAlid = false;}
                // When six words collected, break loop
                if(incoming.size() == 6){break;}
            }//END WHILE
            if(temp == -1){System.out.print("File less than six words..."); return;}

            // ---------------------------------------------------
            // Now read through, build the tree with 6-word chunks
            while (true) {

                // *** Hash string, add to tree ***
                for (int i = 0; i < incoming.size(); i++) {
                    // Build a string with the six words
                    sBuilder.append(incoming.get(i));
                }
                // Get the hash code for that string
                temp = sBuilder.toString().hashCode();
                // Add the hash code to the binary search tree
                hashTree.put(temp, temp);
                sBuilder = new StringBuilder(0);
                // ********************************
                System.out.println("Tree size: " + hashTree.size());

                // Rotate words in the array
                while (true) {
                     // Read in a character
                    temp = input.read();
                    if(temp == -1){break;}// EOF break
                    // Checks for characters/numbers only
                    if(isValid(temp)){ // Valid add to the string
                        sBuilder.append((char) temp);
                        prevAlid = true;
                    }
                    // Word ends if last was valid and current is not 
                    else if(!isValid(temp) && prevAlid){
                        // rotate the new word into the arraylist
                        incoming.add(sBuilder.toString());
                        incoming.remove(0);
                        // Clears the string builder
                        sBuilder = new StringBuilder(0);
                        prevAlid = false;
                        break;
                    }
                    else{prevAlid = false;}
                }
                if(temp == -1){break;}
            }
            // --------------------------------------------------------
            // ********************************************************

        } catch (Exception e) {
            System.out.println("Error in thread " + threadName + ": " + e);
            TBRunning = false;
        }
        System.out.println("Tree Building Done!");
        TBRunning = false;
    }// RUN

    public void start(){
        if(red == null){
            red = new Thread(this, threadName);
            red.start();
        }
    }


    /**
     * Checks for a valid character input (', -, 0-9, A-Z, a-z)
     * @param check input needinf validation
     * @return true if valid, false if not
     */
    private boolean isValid(int check){
        int tempC = check;
        if(tempC == 39 /*'*/ || tempC == 45 /*-*/ || (tempC >= 48 && tempC <= 57)/*0-9*/ || (tempC >= 65 && tempC <= 90) /*A-Z*/ || (tempC >= 97 && tempC <122)/*a-z*/){
            return true;
        }
        return false;
    }


    
}