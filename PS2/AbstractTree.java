/*
 * Copyright 2014, Michael T. Goodrich, Roberto Tamassia, Michael H. Goldwasser
 *
 * Developed for use with the book:
 *
 *    Data Structures and Algorithms in Java, Sixth Edition
 *    Michael T. Goodrich, Roberto Tamassia, and Michael H. Goldwasser
 *    John Wiley & Sons, 2014
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import java.util.Iterator;
import java.util.List;         // for use as snapshot iterator
import java.util.Stack;

//import LinkedBinaryTree.Node;

import java.util.ArrayList;    // for use as snapshot iterator

/**
 * An abstract base class providing some functionality of the Tree interface.
 *
 * The following three methods remain abstract, and must be
 * implemented by a concrete subclass: root, parent, children. Other
 * methods implemented in this class may be overridden to provide a
 * more direct and efficient implementation.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */
public abstract class AbstractTree<E> implements Tree<E> {

  /**
   * Returns true if Position p has one or more children.
   *
   * @param p    A valid Position within the tree
   * @return true if p has at least one child, false otherwise
   * @throws IllegalArgumentException if p is not a valid Position for this tree.
   */
  @Override
  public boolean isInternal(final Position<E> p) { return numChildren(p) > 0; }

  /**
   * Returns true if Position p does not have any children.
   *
   * @param p    A valid Position within the tree
   * @return true if p has zero children, false otherwise
   * @throws IllegalArgumentException if p is not a valid Position for this tree.
   */
  @Override
  public boolean isExternal(final Position<E> p) { return numChildren(p) == 0; }

  /**
   * Returns true if Position p represents the root of the tree.
   *
   * @param p    A valid Position within the tree
   * @return true if p is the root of the tree, false otherwise
   */
  @Override
  public boolean isRoot(final Position<E> p) { return p == root(); }

  /**
   * Returns the number of children of Position p.
   *
   * @param p    A valid Position within the tree
   * @return number of children of Position p
   * @throws IllegalArgumentException if p is not a valid Position for this tree.
   */
  @Override
  public int numChildren(final Position<E> p) {
    int count=0;
    for (final Position child : children(p)) count++;
    return count;
  }

  /**
   * Returns the number of nodes in the tree.
   * @return number of nodes in the tree
   */
  @Override
  public int size() {
    int count=0;
    for (final Position p : positions()) count++;
    return count;
  }

  /**
   * Tests whether the tree is empty.
   * @return true if the tree is empty, false otherwise
   */
  @Override
  public boolean isEmpty() { return size() == 0; }

  //---------- support for computing depth of nodes and height of (sub)trees ----------

  /** Returns the number of levels separating Position p from the root.
   *
   * @param p A valid Position within the tree
   * @throws IllegalArgumentException if p is not a valid Position for this tree.
   */
  public int depth(final Position<E> p) throws IllegalArgumentException {
    if (isRoot(p))
      return 0;
    else
      return 1 + depth(parent(p));
  }

  /** Returns the height of the tree.
   *
   * Note: This implementation works, but runs in O(n^2) worst-case time.
   */
  private int heightBad() {             // works, but quadratic worst-case time
    int h = 0;
    for (final Position<E> p : positions())
      if (isExternal(p))                // only consider leaf positions
        h = Math.max(h, depth(p));
    return h;
  }

  /**
   * Returns the height of the subtree rooted at Position p.
   *
   * @param p A valid Position within the tree
   * @throws IllegalArgumentException if p is not a valid Position for this tree.
   */
  public int height(final Position<E> p) throws IllegalArgumentException {
    int h = 0;                          // base case if p is external
    for (final Position<E> c : children(p))
      h = Math.max(h, 1 + height(c));
    return h;
  }

  //---------- support for various iterations of a tree ----------

  //---------------- nested ElementIterator class ----------------
  /* This class adapts the iteration produced by positions() to return elements. */
  private class ElementIterator implements Iterator<E> {
    Iterator<Position<E>> posIterator = positions().iterator();
    public boolean hasNext() { return posIterator.hasNext(); }
    public E next() { return posIterator.next().getElement(); } // return element!
    public void remove() { posIterator.remove(); }
  }

  /**
   * Returns an iterator of the elements stored in the tree.
   * @return iterator of the tree's elements
   */
  @Override
  public Iterator<E> iterator() { return new ElementIterator(); }

  /**
   * Returns an iterable collection of the positions of the tree.
   * @return iterable collection of the tree's positions
   */
  @Override
  public Iterable<Position<E>> positions() { return preorder(); }

  /**
   * Adds positions of the subtree rooted at Position p to the given
   * snapshot using a preorder traversal
   * @param p       Position serving as the root of a subtree
   * @param snapshot  a list to which results are appended
   */
  private void preorderSubtree(final Position<E> p, final List<Position<E>> snapshot) {
    snapshot.add(p);                       // for preorder, we add position p before exploring subtrees
    for (final Position<E> c : children(p))
      preorderSubtree(c, snapshot);
  }

  /**
   * Returns an iterable collection of positions of the tree, reported in preorder.
   * @return iterable collection of the tree's positions in preorder
   */
  public Iterable<Position<E>> preorder() {
    final List<Position<E>> snapshot = new ArrayList<>();
    if (!isEmpty())
      preorderSubtree(root(), snapshot);   // fill the snapshot recursively
    return snapshot;
  }

  /**
   * Adds positions of the subtree rooted at Position p to the given
   * snapshot using a postorder traversal
   * @param p       Position serving as the root of a subtree
   * @param snapshot  a list to which results are appended
   */
  private void postorderSubtree(final Position<E> p, final List<Position<E>> snapshot) {
    for (final Position<E> c : children(p))
      postorderSubtree(c, snapshot);
    snapshot.add(p);                       // for postorder, we add position p after exploring subtrees
  }

  /**
   * Returns an iterable collection of positions of the tree, reported in postorder.
   * @return iterable collection of the tree's positions in postorder
   */
  public Iterable<Position<E>> postorder() {
    final List<Position<E>> snapshot = new ArrayList<>();
    if (!isEmpty())
      postorderSubtree(root(), snapshot);   // fill the snapshot recursively
    return snapshot;
  }

  /**
   * Returns an iterable collection of positions of the tree in breadth-first order.
   * @return iterable collection of the tree's positions in breadth-first order
   */
  public Iterable<Position<E>> breadthfirst() {
    final List<Position<E>> snapshot = new ArrayList<>();
    if (!isEmpty()) {
      final Queue<Position<E>> fringe = new LinkedQueue<>();
      fringe.enqueue(root());                 // start with the root
      while (!fringe.isEmpty()) {
        final Position<E> p = fringe.dequeue();     // remove from front of the queue
        snapshot.add(p);                      // report this position
        for (final Position<E> c : children(p))
          fringe.enqueue(c);                  // add children to back of queue
      }
    }
    return snapshot;
  }
 


  // ****************  NEW SHIZ BELLOW *****************

  private class PreorderIterator implements Iterator<E> {


    Iterator<Position<E>> posIterator = root();


    public boolean hasNext() { return posIterator.hasNext(); }
    public E next() { return helper(posIterator).getElement(); } // return element!


    // VV Franks help function bellow, will never work
    private Position<E> helper(Position<E> currentPos){

      if((parent(currentPos) == null ) //LEAVE

      Iterator<Position<E>> siblings = children(parent(currentPos));

      //Iterate through all the siblings (including currentPos)
      while(siblings.hasNext()){
        if(siblings.next() == currentPos) break;
      }

      // The next sibling is the next in line to be traversed

      // If the next sibling is null (no other siblings), so we should go to the parent
      // If the next sibling is not null, then that is going to be the next thing we return

      if(posIterator == null){
        nextValid = false;
        return null;
      }
    }


    public void remove() { posIterator.remove(); }
    private Position<E> nextItem;   // Position of next item
    boolean nextValid;      // Is next item valid?


    // What happens if you delete or add nodes to the tree if this iterator exists
    //    1) If a deletion in the "future" of the iterator happens
    //        - Nothing
    //    2) If a delete or add nodes in the "past" of the preorder traversal
    //        - Iterator is invaldi (use the boolean)
    //    3) If we delete what the iterator is currently pointing to
    //        - Iterator is invalid

    // Can maintain some data structure to keep track of the nodes you have visited, or the nodes which
    // still need to be visited (stack maybe)


    public Iterator<E> lazyIterator() { return new PreorderIterator();}

    
    public Iterable<Position<E>> lazyPreorder(){
      final List<Position<E>> snapshot = new ArrayList<>();
      if (!isEmpty())
        preorderSubtree(root(), snapshot);   // fill the snapshot recursively
      return snapshot;


      // while root hasnt been hit for final time
      // go left until end is hit 
      // check left end parent for right, the that right for children
      // then go up, checking the rights, also for rights children.
      // end when node has no parent, which is the root


      // children returns two, go to the frist one until an external is hit, store the location
      // that wasn't checked in a stack for later checking.

    }

  }


  // **************** NEW SHIZ ABOVE ********************

 
}// ***************** END OF CLASS ************************
