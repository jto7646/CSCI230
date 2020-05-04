import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * "Compresses" and "decompresses" passed in files
 * @author John O'Leary
 * @version 1.2
 * @since May/03/2020
 */
public class SimpleHuffProcessor implements IHuffProcessor {
    
    private HuffViewer myViewer;
    // vv Hold the map of codes read from the top of the file being decompressed
    private String[] decodeArr = new String[256];
    
    /**
     * "Compresses" files using the huffman encoding algorithm
     * @param in The input stream of the file being compressed
     * @param out The output stream to the newly compressed file 
     * @return The number of bytes read and compressed 
     * @throws IOException If the file is unable to compress for any reason
     */
    public int compress(InputStream in, OutputStream out, boolean force) throws IOException {
        int walk = 0;   // Temp storage for incoming byte from read file
        int walkCount = 0;  // Number of total bits read
        char codeCheck = ' ';   // Used as a key to find the code in the code map
        String[] codeMap = HuffEncoder.encoding(); // Stores the character codes, copied from HuffEncode
        String s2b = "";    // Temp string storage 
        int codeLen = 0;    // Temp storage for code length (array storage)
        BitInputStream  bitIn = new BitInputStream(in); 
        BitOutputStream bitOut = new BitOutputStream(out);

        // Writes the code array at the top of the file. It first writes the number of bits in the code, then 
        // the code itself. In the decode proccess, the code length is first read, then the code is read using the length
        // as a guide.
        // A space at top of file ensures the following is the array, and the file will correctly decompress
        out.write(32);
        for (int i = 0; i < codeMap.length; i++) {
            // Value of the character
            s2b = codeMap[i];
            // If null, make it zero
            if(s2b == null){
                s2b = "0";
            }
            // Record length of code
            codeLen = s2b.length();
            // Write the length of the code, to use for decoding
            bitOut.writeBits(8, codeLen);
            // Write the value of the character
            for (int j = 0; j < s2b.length(); j++) {
                bitOut.writeBits(1, Integer.valueOf(s2b.charAt(j)));
            }
            System.out.println(" ");
        }

        // Reads in a byte from the file, converts it to a character, then uses that 
        // caracter to look up the huffman code. The huffman code is writen to the new 
        // file.
        try {
            while(true){
                // Read first eight bits
                walk = bitIn.read();
                // -1 means the stream has reached the end
                if(walk == -1){break;}
                // convert the binary to a character
                codeCheck = (char) walk;
                // find the huff code for the character
                s2b = codeMap[codeCheck];
                // write the code to new file
                for (int i = 0; i < s2b.length(); i++) {
                    bitOut.writeBits(1, Integer.valueOf(s2b.charAt(i)));
                }
                walkCount += 8; // Number of bits read
            }
            bitIn.close();
            bitOut.close();
            return walkCount;       
        } catch (IOException e) {
            bitIn.close();
            bitOut.close();
            throw e;
        }
    }// ** END COMPRESS **

    /**
     * Creates huffman character codes for a passed in file
     * @param in The input stream that generates huffman character codes.
     */
    public int preprocessCompress(InputStream in) throws IOException {
        try {
            // Count the characters
            int counted = CharCounter.countAll(in); 
            int[] countedArray = CharCounter.countValues();
            // Build the huffman tree
            TreeBuilder countedTree = new TreeBuilder();
            countedTree.buildTree(countedArray);
            // Create the huffman character codes
            HuffEncoder.huffEncode(countedTree.getRoot());
            return counted;
        } catch (IOException e) {
            throw e;
        }
    }

    public void setViewer(HuffViewer viewer) {
        myViewer = viewer;
    }

     /**
      * "Decompresses" files that were previously "compressed" by this program
      * @param in the stream with the compressed file to be decompressed
      * @param out the stream to the file writing the uncompressed file
      */
    public int uncompress(InputStream in, OutputStream out) throws IOException { 
        int codeCount = 0;
        int code = 0;
        BitInputStream bitIn = new BitInputStream(in);
        StringBuilder codeString = new StringBuilder(0);


        // *** Bellow for building array of encoded values ****
        // ****************************************************
        // If the first character in the file is not a space, cannot decompress file
        codeCount = in.read();
        if(codeCount != 32){
            bitIn.close();
            return -1;
        }
        // Read in length of the code, then read the codes number of bits into the code array
        for (int i = 0; i < decodeArr.length; i++) {
            //read in number of bits (length of character code) 
            codeCount = bitIn.read();
        
            // Create the code string (reads the number of bits found above)
            for (int j = 0; j < codeCount ; j++) {
                codeString.append(bitIn.readBits(1));
            }
            // Add the code string to the code array
            decodeArr[i] = codeString.toString();
            codeString = new StringBuilder(0);
        }
        // If the character has no code, make it null in array
        for (int i = 0; i < decodeArr.length; i++) {
            if(decodeArr[i].equals("0")){decodeArr[i] = null;}
        }
        // ****************************************************
        // ****************************************************


        // ********** Bellow is the decoding process **********
        // ****************************************************
        // Slowly build a sting of binary comparing it to the codes in the map
        // Once a match is found, output the character associated with it.
        try{
        codeString = new StringBuilder(0);
        while (true) { 
            // Read in one bit
            code = bitIn.readBits(1);
            // If -1 is returned, reached the end of file
            if(code == -1){break;}
            // Search the code array, writing the character of the code when found
            codeString.append(String.valueOf(code));
            for (int i = 0; i < decodeArr.length; i++) {
                if(decodeArr[i] != null){ // null means 0 count for a character
                    // If the building code string matches a code in the map, write the associated character
                    if(decodeArr[i].equals(codeString.toString())){
                        out.write(i);
                        // vv make ready for next code input
                        codeString = new StringBuilder(0);
                    }
                }
            }
        }
        }catch (IOException e){
            bitIn.close();
            throw e;
        }
        // ****************************************************
        // ****************************************************
        bitIn.close();
        return 0;
    }// ** END UNCOMPRESS **
    
    private void showString(String s){
        myViewer.update(s);
    }

}
