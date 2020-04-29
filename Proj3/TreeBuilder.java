import bookClasses.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class will be used to build huffman trees
 */
public class TreeBuilder {

    // Since tree is binary, maybe use an ArrayList; using classes from book?
    // Need to do: buildTree function; node accessors; constructor?

    public static ArrayList<TreeNode> tree;

    public TreeBuilder() {tree = new ArrayList<>();}

    /**
     * Builds a huffman tree using passed in int array full of character counts; TEST FOR ACCURACY!
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
                TreeNode tempNode = new TreeNode(i, arr[i]);
                System.out.println("v: " + i + " w: " + arr[i]);
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
        
        /* VV this is no longer needed
        // Make first two nodes child of new parent
        int tempWeight = countArray.get(0).myWeight + countArray.get(1).myWeight;
        TreeNode sortTemp = new TreeNode( 0, tempWeight, countArray.get(0), countArray.get(1));
        countArray.add(2, sortTemp);
        sortingArray.add(2, sortTemp);
        sortingArray.set(0, null);
        sortingArray.set(1, null);
        Collections.sort(sortingArray);
        */
          
        // Assuming nulls come first when sorted 
        TreeNode temp1, temp2;
        while(!endLoop){

            //check the two smallest weights
            // Array is being sorted, so first two non-null values encountered are the ones being combined
            for(int i = 0;i < sortingArray.size(); i++){
                    // If next index is out of bounds, the tree has its root
                if((i+1) < sortingArray.size()){
                    //combine get(i) with get(i+1) in new parent node 
                    temp1 = sortingArray.get(i);
                    temp2 = sortingArray.get(i + 1);
                    System.out.println("Weights: " + temp1.myWeight + " " + temp2.myWeight);
                    System.out.println(tree.get(getIndex(temp1)).myWeight + " " + tree.get(getIndex(temp2)).myWeight);
                    System.out.println("Values: " + temp1.myValue + " " + temp2.myValue);
                    System.out.println(tree.get(getIndex(temp1)).myValue + " " + tree.get(getIndex(temp2)).myValue);
                    tempWeight = tree.get(getIndex(temp1)).myWeight +  tree.get(getIndex(temp2)).myWeight;
                    sortTemp = new TreeNode(0, tempWeight, tree.get(getIndex(temp1)), tree.get(getIndex(temp2)));
                    // Update tree with new parent node
                    tree.add(sortTemp);
                    // Update sorting array to keep sorting
                    sortingArray.add(sortTemp);
                    sortingArray.remove(i);
                    sortingArray.remove(i+1);
                    // Exit for loop
                    i = sortingArray.size();
                    // Re-sort array to ready for next combining
                    Collections.sort(sortingArray);
                }
                else{endLoop = true;}
            } 
        }
        // The tree should now be built!

    }// * END buildTree *

    /**
     * Returns an integer representation of the path to a letter
     * @param ascii code of the desired character
     * @return the path to the desired character
     */
    //public int pathTo(int ascii){}


    /**
     * Returns the index location of the node within tree
     * @param ind
     * @return index location of the node in tree, -1 if it dosen't exsist
     */
    private static int getIndex(TreeNode ind){
        TreeNode tempNode1, tempNode2;
        tempNode1 = ind;

        for(int i = 0; i < tree.size(); i++){
            tempNode2 = tree.get(i);
            if((tempNode1.myWeight == tempNode2.myWeight) && (tempNode1.myValue == tempNode2.myValue)){return i;}
        }
        return -1;
    }

    public TreeNode getRoot(){
        TreeNode root = new TreeNode(0,0,null,null);
        for (int i = 0; i < tree.size(); i++) {
            if(tree.get(i).myWeight > root.myWeight){root = tree.get(i);}
        }
        return root;
    } 

    public int print(TreeNode nxt){
        TreeNode temp = nxt;
        
        if(temp.myLeft != null){print(temp.myLeft);}
        if(temp.myRight != null){print(temp.myRight);}

        System.out.print(" " + temp.myValue);
        return 1;
        
    }

}// **** END CLASS ****