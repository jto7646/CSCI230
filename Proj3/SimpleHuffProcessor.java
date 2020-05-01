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
        String s2b = " ";
        BitInputStream  bitIn = new BitInputStream(in);
        BitOutputStream bitOut = new BitOutputStream(out);

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
                System.out.println(s2b);
                // write the code to new file
                System.out.println(walk);
                bitOut.write(s2b.getBytes());
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
