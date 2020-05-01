
import java.util.ArrayList;

/**
 * This class will be used to encode characters to binary using the tree built from TreeBuilder
 */
public class HuffEncoder {
    
    private static ArrayList<String> codeString = new ArrayList<>();
    private static String[] encodedBinary = new String[256];
    private static StringBuilder sb;

    public static String[] encoding(){return encodedBinary;}

    /**
     * Creates a table of huffman encoded binary values using a huffman tree built in TreeBuilder.
     * @param nxt - Pass in the root treenode of the huffman tree.
     */
    public static void huffEncode(TreeNode nxt){
        
        if(nxt.myLeft != null){
            // Append a 0 to string
            codeString.add("0");
            huffEncode(nxt.myLeft);
            // Print Current Code
            //System.out.println(temp.myValue + "  " + codeString.toString());
            // remove a 0 from string
            codeString.remove(codeString.size()-1);
        }
       
        if(nxt.myRight != null){
            //append a 1 to string
            codeString.add("1");
            huffEncode(nxt.myRight);
            // Print Current code
            //System.out.println(temp.myValue + "  " + codeString.toString());
            //remove a 1 from string
            codeString.remove(codeString.size()-1);
        }

        sb = new StringBuilder();

        for (int i = 0; i < codeString.size(); i++) {
            sb.append(codeString.get(i));
        }

        if(nxt.myValue == 300){return;}
        encodedBinary[nxt.myValue] = sb.toString();
        //System.out.println("W: " + nxt.myWeight + " V:  " + nxt.myValue + " " + codeString.toString());
    }
}// **** END CLASS ****