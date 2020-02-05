import net.datastructures.SinglyLinkedList;


public class LinkedListOperations{
    
    public static <E> SinglyLinkedList<E> reverse(SinglyLinkedList<E> list){


        SinglyLinkedList<E> ret = new SinglyLinkedList<>();
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> oldList = new SinglyLinkedList<>();
        oldList.addFirst(1); oldList.addFirst(21); oldList.addFirst(3); oldList.addFirst(5);

        SinglyLinkedList<Integer> newList = LinkedListOperations(reverse);
    }
        //including jar files javac -cp sourcecode.jar LinkedListOperations.java
}