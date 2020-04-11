//import jdk.internal.jimage.ImageReader.Node;

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
//package net.datastructures;

/**
 * Realization of a FIFO queue as an adaptation of a SinglyLinkedList.
 * All operations are performed in constant time.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 * @see SinglyLinkedList
 */
public class LinkedQueue<E> implements Queue<E> {

  /** The primary storage for elements of the queue */
  private SinglyLinkedList<E> list = new SinglyLinkedList<>();   // an empty  list

  /** Constructs an initially empty queue. */
  public LinkedQueue() { }                  // new queue relies on the initially empty list

  /**
   * Returns the number of elements in the queue.
   * @return number of elements in the queue
   */
  @Override
  public int size() { return list.size(); }

  /**
   * Tests whether the queue is empty.
   * @return true if the queue is empty, false otherwise
   */
  @Override
  public boolean isEmpty() { return list.isEmpty(); }

  /**
   * Inserts an element at the rear of the queue.
   * @param element  the element to be inserted
   */
  @Override
  public void enqueue(E element) { list.addLast(element); }

  /**
   * Returns, but does not remove, the first element of the queue.
   * @return the first element of the queue (or null if empty)
   */
  @Override
  public E first() { return list.first(); }


  /**
   * Removes and returns the first element of the queue.
   * @return element removed (or null if empty)
   */
  @Override
  public E dequeue() { return list.removeFirst(); }

  /** Produces a string representation of the contents of the queue.
   *  (from front to back). This exists for debugging purposes only.
   */
  public String toString() {
    return list.toString();
  }

  /**
   * Appends Q2 to Q1 in constant time
   * @param Q1 to append to 
   * @param Q2 being appended
   */
  public void concatenate(LinkedQueue<E> Q1, LinkedQueue<E> Q2) {
    // Appends Q2 to Q1 in constant-time
    list.concantinate(Q1.list, Q2.list);

    assert Q2.isEmpty() : "Error: Q2 should be empty!";
    }

    // Bellow main function used for testing
    
    /*
    public static void main(String[] args) {
      LinkedQueue test = new LinkedQueue<Integer>();
      LinkedQueue test2 = new LinkedQueue<Integer>();

      test.enqueue(13);
      test.enqueue(15);
      test.enqueue(24);

      test2.enqueue(1);
      test2.enqueue(2);
      test2.enqueue(3);
      test2.enqueue(4);
      test2.enqueue(5);


      test.concatenate(test, test2);

      System.out.println(test.toString());

      System.out.println(test2.toString());

      for(int i = 0; i < 7; i++){ test.dequeue();}

      System.out.println(test.toString());


    }
    */


}// ************ END OF CLASS *************
