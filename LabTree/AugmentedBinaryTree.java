
/**
 * A class that augments the LinkedBinaryTree from the course primary textbook. Instead of <i>directly</i> modifying the LinkedBinaryTree class, we can simply extend the class and
 * exploit the "is-a" relationship.
 * 
 * @author John OLeary
 * @version 1.0.5
 * @since April-4th-2020
 *  
 */
public class AugmentedBinaryTree<E> extends LinkedBinaryTree<E> {

	/**
	 * Removes t he entire subtree rooted at position p, making sure to maintain an accurate count of the size of the tree. <b>Hint</b>: Use recursion. Reference to the remove method
	 * to see how to remove a node but do not use the remove method.
	 * @param p
	 */
	public void pruneSubtree(Position<E> p) {

		// If the position is empty, return
		if(p == null){return;} 

		// If there is a left child, go to its position 
		if(left(p) != null){pruneSubtree(left(p));}

		// If there is a right child, go to its position
		if(right(p) != null){pruneSubtree(right(p));}

		// There are no children, set this position to null
		remove(p);	
		
	}
	
	/**
	 * Restructures the tree so that the node referenced by p takes the place of the node referenced by q, and vice versa. Make sure to properly handle the case when the nodes are
	 * adjacent
	 * @param p
	 * @param q
	 */
	public void swap(Position<E> p, Position<E> q) {
		
		Node<E> tempNode = validate(p); // P node temp storage		

		// Makes p's parent point to q, checks if it was pointing left or right
		if(validate(parent(p)).getLeft() == validate(p)){
			validate(parent(p)).setLeft(validate(q));
		}
		else{ 
			validate(parent(p)).setRight(validate(q));
		}

		// Makes q's parent point to p, checks if it was pointin left or right
		if(validate(parent(q)).getLeft() == validate(q)){
			validate(parent(q)).setLeft(tempNode);
		}
		else{
			validate(parent(q)).setRight(tempNode);
		}
		
		// Sets p's parent to q's parent
		validate(p).setParent(validate(q).getParent());
		// Sets q's parent to p's original parent
		validate(q).setParent(tempNode.getParent());
	}
	
}
