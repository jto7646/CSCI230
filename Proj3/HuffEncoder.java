
import java.util.ArrayList;

/**
 * This class encodes character to binary using the tree built in the TreeBuilder
 * @author John O'Leary
 * @version 1.0
 * @since April/29/2020
 */
public class HuffEncoder {
    
    // VV Map of huff codes
    private static String[] encodedBinary = new String[256];
    // VV for building the huff code string
    private static ArrayList<String> codeString = new ArrayList<>();
    private static StringBuilder sb;

    /**
     * Returns a string array of encoded character values
     * @return String array of character codes
     */
    public static String[] encoding(){return encodedBinary;}

    /**
     * Creates a table of huffman encoded binary values using a huffman tree built in TreeBuilder.
     * @param nxt - Pass in the root treenode of the huffman tree.
     */
    public static void huffEncode(TreeNode nxt){
    // Iteratively runs through the tree building character codes

        // If there is a left child, go there
        if(nxt.myLeft != null){
            // Append a 0 to string
            codeString.add("0");
            huffEncode(nxt.myLeft);
            // remove a 0 from string
            codeString.remove(codeString.size()-1);
        }

        // If there is a right child, go there
        if(nxt.myRight != null){
            //append a 1 to string
            codeString.add("1");
            huffEncode(nxt.myRight);
            //remove a 1 from string
            codeString.remove(codeString.size()-1);
        }

        // If there are no children, a chracter node has been reached.
        // We now build the code string from the array that has been collecting left(0)
        // and right(1) branches along the way.
        sb = new StringBuilder();
        for (int i = 0; i < codeString.size(); i++) {
            sb.append(codeString.get(i));
        }

        // If the value is 300, there is no character at this node
        //      This is just for safety
        if(nxt.myValue == 300){return;}
        // Huffman code string now added to the map of codes
        encodedBinary[nxt.myValue] = sb.toString();
    }
}// **** END CLASS ****