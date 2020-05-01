import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.nio.ByteBuffer;

public class SimpleHuffProcessor implements IHuffProcessor {
    
    private HuffViewer myViewer;
    
    public int compress(InputStream in, OutputStream out, boolean force) throws IOException {
        int walk = 0;
        int walkCount = 0;
        char codeCheck = ' ';
        String[] codeMap = HuffEncoder.encoding();
        String s2b = "";
        String codeLen = "";
        BitInputStream  bitIn = new BitInputStream(in);
        BitOutputStream bitOut = new BitOutputStream(out);

        
        // Writes the code array at the top of the file (Decode, read length of code from 8-bit chunk, use that length to read the code)
        // A space at top of file ensures the following is the array, and the file will correctly decompress
        out.write(32);
        for (int i = 0; i < codeMap.length; i++) {
            //bitOut.writeBits(8, i);
            // Value of the character
            s2b = codeMap[i];
            // If null, make it zero
            if(s2b == null){s2b = "0";}
            // Record length of code
            codeLen += s2b.length();
            // Write the length of the code, to use for decoding
            bitOut.writeBits(8, Integer.valueOf(codeLen));
            codeLen = "";
            // Write the value of the character
            bitOut.writeBits(s2b.length(), Integer.valueOf(s2b));
            // Write a space
            out.write(32);
        }
        out.write(10);


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
                System.out.println("RDB:\n" + walk);
                // write the code to new file
                System.out.println("HCS:\n" + s2b);
                bitOut.writeBits(s2b.length(), Integer.valueOf(s2b));
                bitOut.flush();
                walkCount += 8;
            }
            return walkCount;       
        } catch (IOException e) {
            throw e;
        }
    }

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
        //return 0;
    }

    public void setViewer(HuffViewer viewer) {
        myViewer = viewer;
    }

    public int uncompress(InputStream in, OutputStream out) throws IOException {       

        throw new IOException("uncompress not implemented");
        //return 0;
    }
    
    private void showString(String s){
        myViewer.update(s);
    }

}
