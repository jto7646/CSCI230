
public final class AugmentedBinaryTreeDriver 
{
    private static <E> void printPreorder(AugmentedBinaryTree<E> tree)
    {
        printPreorderSubtree(tree, tree.root(), 0);
    }

    private static <E> void printPreorderSubtree(AugmentedBinaryTree<E> tree, Position<E> subroot, int depth)
    {
        for (int i = 1; i <= depth; ++i) System.out.print("\t");
        System.out.println(subroot.getElement());

        if (tree.left(subroot) != null) printPreorderSubtree(tree, tree.left(subroot), depth + 1);
        if (tree.right(subroot) != null) printPreorderSubtree(tree, tree.right(subroot), depth + 1);
    }

    private static AugmentedBinaryTree<Integer> tree;

    public static void main(String[] args) 
    {
        //Bellow adds values to the tree
        tree = new AugmentedBinaryTree<Integer>();
        tree.addRoot(1);
        Position<Integer> p = tree.root();
        tree.addLeft(p, 2);
        tree.addRight(p, 3);
        tree.addLeft(tree.left(p), 4);
        tree.addRight(tree.left(p), 5);

        //Printing out the original tree
        System.out.println("Original Tree: ");
        printPreorder(tree);
        

        //Positions used for the swap method
        Position<Integer> q = tree.left(p);
        Position<Integer> a = tree.right(p);

        // Swapping, then printing the tree
        System.out.println("Swapped tree, 2 with 3: ");
        tree.swap(a, q); // calling the swap method 
        printPreorder(tree);

        // Pruning, then printing the tree
        System.out.println("Pruned tree, removing 2 subtree: ");
        tree.pruneSubtree(tree.right(p));
        printPreorder(tree);

        System.out.println("\n\n\n ********* END OF PROGRAM ********* ");
    }

}// *********  END CLASS **********