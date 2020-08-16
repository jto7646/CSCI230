import java.io.InputStream;
import java.util.ArrayList;
import java.util.TreeMap;

public class TreeBuilder {
    private InputStream input;
    private int sSize;
    

    /**
     * Creates a tree building object, used to build a red-black binary tree of hash values from sequenceSize
     *  word chunks from text document
     * @param in
     * @param sequenceSize
     */
    public TreeBuilder(InputStream in, int sequenceSize){
        input = in;
        sSize = sequenceSize;
    }

    /**
     * Creates a tree building object, used to build a red-black binary tree of hash values from sequenceSize
     *  word chunks from text document: input stream null, sequence size 6 
     */
    public TreeBuilder(){ this(null, 6);}

    /**
     * Changes the input file used to build the binary tree
     * @param in input stream of the text document
     */
    public void changeStream(InputStream in){input = in;}

    /**
     * Changes the sequence size used to build the binary tree
     * @param sequenceSize The size of the word sequence read form the document
     */
    public void changeSequenceSize(int sequenceSize){ sSize = sequenceSize; }


    /**
     * Builds a red-black binary tree of hash values of specified word chunks from the text document
     * @return a binary tree of hash values
     */
    public TreeMap<Integer, Integer> build(){
        TreeMap<Integer, Integer> hashTree = new TreeMap<>();
        boolean prevAlid = false;
        int temp = 0;
        ArrayList<String> incoming = new ArrayList<>();
        StringBuilder sBuilder = new StringBuilder(0);

        // Safety First!!
        if(sSize <= 0){ System.out.println("Sequence size must be larger than zero..."); return null;}
    
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
                    sBuilder.setLength(0);
                    prevAlid = false;
                }
                else{prevAlid = false;}
                // When six words collected, break loop
                if(incoming.size() == sSize){break;}
            }//END WHILE
            if(temp == -1){System.out.print("File less than " + sSize + " words..."); return null;}

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
                sBuilder.setLength(0);
                // ********************************
                //System.out.println("Tree size: " + hashTree.size());

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
                        sBuilder.setLength(0);
                        prevAlid = false;
                        break;
                    }
                    else{prevAlid = false;}
                }
                if(temp == -1){break;}
            }
            input.close();
            // --------------------------------------------------------
            // ********************************************************

        } catch (Exception e) {
            System.out.println("Error building tree: " + e);
        }
        return hashTree;
    }//ENDBUILD


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