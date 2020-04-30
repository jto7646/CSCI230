//import bookClasses.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class will be used to build huffman trees
 */
public class TreeBuilder {

    public static ArrayList<TreeNode> tree;
    private static ArrayList<String> codeString = new ArrayList<>();
    //private static String codeString;

    public TreeBuilder() {tree = new ArrayList<>();}

    /**
     * Builds a huffman tree using passed in int array full of character counts
     * @param arr int array 
     */
    public void buildTree(int[] arr) {
        // Temp array holding character counts from the count in array
        //ArrayList<TreeNode> countArray = new ArrayList<>();
        ArrayList<TreeNode> sortingArray = new ArrayList<>();
        boolean endLoop = false;
        int tempWeight = 0;
        TreeNode sortTemp;

        // Don't want to include any character counts of 0
        for(int i = 0; i <= 255; i++){
            if(arr[i] != 0){
                TreeNode tempNode = new TreeNode(i, arr[i], null, null);
                tree.add(tempNode);
            }
        }

        // Sorts the ArrayList from smallest to largest weight.
        Collections.sort(tree);

        // Making second Arraylist for sorting purposes
        for(int i = 0; i < tree.size(); i++){
            sortTemp = new TreeNode(tree.get(i).myValue, tree.get(i).myWeight, null, null);
            sortingArray.add(sortTemp);
        }
        Collections.sort(sortingArray); 

        
        
        
        while(!endLoop){
            //check the two smallest weights
            // Array is being sorted, so first two non-null values encountered are the ones being combined
            
            if( 1 < sortingArray.size()){
                //combine get(0) with get(1) in new parent node 
                //new combined weight
                tempWeight = sortingArray.get(0).myWeight + sortingArray.get(1).myWeight;
                // New parent node of combined weight pointing to left and right children in tree
                sortTemp = new TreeNode(0, tempWeight, tree.get(getIndex(sortingArray.get(0))), tree.get(getIndex(sortingArray.get(1))));
                System.out.println("NewNode- w:" + sortTemp.myWeight + " lftwt: " + tree.get(getIndex(sortingArray.get(0))).myWeight + " rtwt: " + tree.get(getIndex(sortingArray.get(1))).myWeight );
                // Update tree with new parent node
                tree.add(sortTemp);
                // Update sorting array to keep sorting
                sortingArray.add(sortTemp);
                sortingArray.remove(0);
                sortingArray.remove(0);
                // Re-sort array to ready for next combining
                Collections.sort(sortingArray);
                System.out.println( "SortArray: ");
                for (int j = 0; j < sortingArray.size(); j++) {
                    System.out.print(" " + sortingArray.get(j).myWeight);
                }
                System.out.println( " ");
            }
            else{endLoop = true;}
            
        }
        // The tree should now be built!
    }// * END buildTree *


    /**
     * Returns the index location of the node within tree
     * @param ind
     * @return index location of the node in tree, -1 if it dosen't exsist
     */
    private static int getIndex(TreeNode ind){
        TreeNode tempNode1, tempNode2;
        tempNode1 = new TreeNode(ind.myValue, ind.myWeight, ind.myLeft, ind.myRight);

        for(int i = 0; i < tree.size(); i++){
            tempNode2 = new TreeNode(tree.get(i).myValue, tree.get(i).myWeight, tree.get(i).myLeft, tree.get(i).myRight);
            if((tempNode1.myWeight == tempNode2.myWeight) && (tempNode1.myValue == tempNode2.myValue)
                && (tempNode1.myLeft == tempNode2.myLeft) && (tempNode1.myRight == tempNode2.myRight)){return i;} 
        }
        return -1;
    }


    /**
     * Returns the root node for the tree, needed for traversal
     * @return TreeNode root node
     */
    public static TreeNode getRoot(){
        TreeNode root = new TreeNode(0,0,null,null);
        for (int i = 0; i < tree.size(); i++) {
            if(tree.get(i).myWeight > root.myWeight){root = tree.get(i);}
        }
        return root;
    } 



    public static String huffEncode(TreeNode nxt){

        // Need to log every left(0) and right(1) branch on the way to a 
        //      character, then print out whole string

        //TreeNode temp = nxt;
        
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
        
        System.out.println("W: " + nxt.myWeight + " V:  " + nxt.myValue + " " + codeString.toString());
        

        return "turds";
    }



    public static void print(TreeNode nxt){
        TreeNode temp = nxt;
        
        if(temp.myLeft != null){print(temp.myLeft);}
        if(temp.myRight != null){print(temp.myRight);}

        System.out.println("w: " + temp.myWeight + " v: " + temp.myValue);
        //return 1;
        
    }

}// **** END CLASS ****