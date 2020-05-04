import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.nio.ByteBuffer;

public class SimpleHuffProcessor implements IHuffProcessor {
    
    private HuffViewer myViewer;
    private String[] decodeArr = new String[256];
    
    /**
     * "Compresses" files using the huffman encoding algorithm
     * @param in The input stream of the file being compressed
     * @param out The output stream to the newly compressed file 
     * @return The number of bytes read and compressed 
     * @throws IOException If the file is unable to compress for any reason
     */
    public int compress(InputStream in, OutputStream out, boolean force) throws IOException {
        int walk = 0;
        int walkCount = 0;
        char codeCheck = ' ';
        String[] codeMap = HuffEncoder.encoding();
        String s2b = "";
        int codeLen = 0;
        BitInputStream  bitIn = new BitInputStream(in);
        BitOutputStream bitOut = new BitOutputStream(out);

        String test = " ";

        // Writes the code array at the top of the file (Decode, read length of code from 8-bit chunk, use that length to read the code)
        // A space at top of file ensures the following is the array, and the file will correctly decompress
        out.write(32);
        for (int i = 0; i < codeMap.length; i++) {

            // Value of the character
            s2b = codeMap[i];
            // If null, make it zero
            if(s2b == null){
                s2b = "00000000";
            }

            // Record length of code
            codeLen = s2b.length();
            // Write the length of the code, to use for decoding
            bitOut.writeBits(8, codeLen);;
            System.out.print("Pass: " + i + " Code length: " + codeLen + " Written: ");
        
            // Write the value of the character
            for (int j = 0; j < s2b.length(); j++) {
                System.out.print(s2b.charAt(j));
                bitOut.writeBits(1, Integer.valueOf(s2b.charAt(j)));
            }
            System.out.println(" ");
        }
        //out.write(10);

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
                System.out.print("RDB: " + codeCheck);
                // write the code to new file
                System.out.println(" HCS: " + s2b);
                for (int i = 0; i < s2b.length(); i++) {
                    bitOut.writeBits(1, Integer.valueOf(s2b.charAt(i)));
                }
                walkCount += 8;
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


    public int preprocessCompress(InputStream in) throws IOException {
        try {
            int counted = CharCounter.countAll(in);
            int[] countedArray = CharCounter.countValues();
            TreeBuilder countedTree = new TreeBuilder();
            countedTree.buildTree(countedArray);
            HuffEncoder.huffEncode(countedTree.getRoot());
            return counted;
        } catch (IOException e) {
            throw e;
        }
    }

    public void setViewer(HuffViewer viewer) {
        myViewer = viewer;
    }

    public int uncompress(InputStream in, OutputStream out) throws IOException { 
        int codeCount = 0;
        int code = 0;
        BitInputStream bitIn = new BitInputStream(in);
        StringBuilder codeString = new StringBuilder(0);
        boolean uncompress = true;
        //1. read in the code array
        //2. used the code array to compare the incoming bits for decompression
        //      - Make a copy of the code array
        //      - read in a bit, compare to the array
        //      - remove the locations in the array that do not match the incoming bits
        //      - repeat until only one location is left in the array
        
        // *** Bellow for building array of encoded values ****
        // ****************************************************
        // If the first character in the file is not a space, cannot decompress file
        codeCount = in.read();
        if(codeCount != 32){
            bitIn.close();
            return -1;
        }

        // Read in length of the code, then read the length number of bits into the code array
        for (int i = 0; i < decodeArr.length; i++) {
            //read in number of bits 
            codeCount = bitIn.read();
        
            // Create the code string
            for (int j = 0; j < codeCount ; j++) {
                codeString.append(bitIn.readBits(1));
            }
            // Add the code string to the code array
            decodeArr[i] = codeString.toString();
            codeString = new StringBuilder(0);
        }

        
        // If the character has no code, make it null in array
        for (int i = 0; i < decodeArr.length; i++) {
            if(decodeArr[i].equals("00000000")){decodeArr[i] = null;}
            System.out.println("Value: " + i + " Code: " + decodeArr[i]);
        }
        // ****************************************************
        // ****************************************************

        // ********** Bellow is the decoding process **********
        // ****************************************************
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
                if(decodeArr[i] != null){
                    if(decodeArr[i].equals(codeString.toString())){
                        out.write(i);
                        System.out.println(codeString.toString());
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
