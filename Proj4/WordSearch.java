import java.io.InputStream;
import java.util.ArrayList;
import java.util.TreeMap;

public class WordSearch {
    private InputStream input;
    private TreeMap<Integer, Integer> searchTree;
    private int sSize;
    private int matchCount = 0;
    

    /**
     * Used to compare word sequences from one file to those in another
     * @param in InputStream for the first file
     * @param sequenceSize size of word sequences
     * @param searching tree of hash values
     */
    public WordSearch(InputStream in, int sequenceSize, TreeMap<Integer, Integer> searching){
        input = in;
        sSize = sequenceSize;
        searchTree = searching;
    }

    /**
     * Used to compare word sequences from one file to those in another; InputStream null, sequence 6, TreeMap null
     */
    public WordSearch(){this(null, 6, null);}

    /**
     * Changes the input file used to build the binary tree
     * @param in input stream of the text document
     */
    public void changeStream(InputStream in){ input = in; }

    /**
     * Returns the number of matches found between the two compared files
     * @return word sequence matches found
     */
    public int getMatches(){return matchCount;}

    /**
     * Changes the hash tree used for comparing the files
     * @param searching hash tree of second file
     */
    public void setCompareTree(TreeMap<Integer, Integer> searching){searchTree = searching;}

    /**
     * Builds a red-black binary tree of hash values of specified word chunks from the text document
     * @return a binary tree of hash values
     */
    public int compareFiles(){
        boolean prevAlid = false;
        int temp = 0;
        ArrayList<String> incoming = new ArrayList<>();
        StringBuilder sBuilder = new StringBuilder(0);

        // Safety First!!
        if(sSize <= 0){ System.out.println("Sequence size must be larger than zero..."); return -1;}
    
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
            if(temp == -1){System.out.print("File less than " + sSize + " words..."); return -1;}

            // ---------------------------------------------------
            // Now read through, compare the currrent chunk with the tree, then update the chunk
            while (true) {

                // *** Hash string, compare to tree ***
                for (int i = 0; i < incoming.size(); i++) {
                    // Build a string with the six words
                    sBuilder.append(incoming.get(i));
                }
                // Get the hash code for that string
                temp = sBuilder.toString().hashCode();
                // Search the tree for the word chunk, note if found
                if(searchTree.get(temp) != null){matchCount++;}
                sBuilder.setLength(0);
                // ********************************

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
            // --------------------------------------------------------
            // ********************************************************

        } catch (Exception e) {
            System.out.println("Error comparing tree: " + e);
        }
        System.out.println("File comparing done!");
        return matchCount;
    }//ENDCOMPARE


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