
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class builds huffman trees using an array of character values passed in from CharCounter
 * @author John O'Leary
 * @version 1.0
 * @since April/29/2020
 */
public class TreeBuilder {

    // The ArrayList holding the huff tree
    public static ArrayList<TreeNode> tree;

    /**
     * Creates an empty tree ready for building
     */
    public TreeBuilder() {tree = new ArrayList<>();}

    /**
     * Builds a huffman tree using passed in int array full of character counts
     * @param arr int array 
     */
    public void buildTree(int[] arr) {
        // Temp array holding character counts from the count in array
        ArrayList<TreeNode> sortingArray = new ArrayList<>();
        boolean endLoop = false;
        int tempWeight = 0;
        TreeNode sortTemp;

        // Fills the tree with nodes holding character count values
        for(int i = 0; i <= 255; i++){
            if(arr[i] != 0){//Exclude caracters with counts of 0
                TreeNode tempNode = new TreeNode(i, arr[i], null, null);
                tree.add(tempNode);
            }
        }

        // Making second Arraylist for sorting purposes
        for(int i = 0; i < tree.size(); i++){
            sortTemp = new TreeNode(tree.get(i).myValue, tree.get(i).myWeight, null, null);
            sortingArray.add(sortTemp);
        }
        // Sorts the second array by lowest weight first
        Collections.sort(sortingArray); 

        // This loop builds the tree using the following steps:
        //      1. Combine the weight of the first two nodes in the array.
        //          Since the array is sorted each loop iteration, the first two nodes
        //          always have the smallest weights
        //      2. Make a new parent node with the combined weight from part 1, it left and right pointers
        //          pointing to the children in the original tree array
        //      3. Add this new parent node to both arrays, then remove the children from the sorting array
        //      4. Re-sort the sorting array, then repeat the proccess. Size of one means the tree is now complete
        while(!endLoop){
            if( 1 < sortingArray.size()){
                //combine get(0) with get(1) in new parent node 
                //new combined weight
                tempWeight = sortingArray.get(0).myWeight + sortingArray.get(1).myWeight;
                // New parent node of combined weight pointing to left and right children in tree
                sortTemp = new TreeNode(300, tempWeight, tree.get(getIndex(sortingArray.get(0))), tree.get(getIndex(sortingArray.get(1))));
                // Update tree with new parent node
                tree.add(sortTemp);
                // Update sorting array to keep sorting
                sortingArray.add(sortTemp);
                sortingArray.remove(0);
                sortingArray.remove(0);
                // Re-sort array to ready for next combining
                Collections.sort(sortingArray);
            }
            else{endLoop = true;}   
        }
    }// * END buildTree *
   


    /**
     * Returns the index location of the node within tree
     * @param ind
     * @return index location of the node in tree, -1 if it dosen't exsist
     */
    private static int getIndex(TreeNode ind){
        TreeNode tempNode1, tempNode2;
        // Make a new node with the values of the node passed in
        tempNode1 = new TreeNode(ind.myValue, ind.myWeight, ind.myLeft, ind.myRight);

        // Loop through tree array, returning the index storing the node that matches the one passed in
        for(int i = 0; i < tree.size(); i++){
            tempNode2 = new TreeNode(tree.get(i).myValue, tree.get(i).myWeight, tree.get(i).myLeft, tree.get(i).myRight);
            if((tempNode1.myWeight == tempNode2.myWeight) && (tempNode1.myValue == tempNode2.myValue)
                && (tempNode1.myLeft == tempNode2.myLeft) && (tempNode1.myRight == tempNode2.myRight)){return i;} 
        }
        return -1; // -1 means the node was not found int the tree array
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

}// **** END CLASS ****