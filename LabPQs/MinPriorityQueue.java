//package LabPriorityQueue;


// Implementation of a minimum priority queue which allows
// you to determine the minimum by using the DefaultComparator
public class MinPriorityQueue<K,V> extends UnsortedPriorityQueue<K,V> {
	
	public MinPriorityQueue() { super(); }
	
	public static void main(String[] args) {
		
		MinPriorityQueue<Integer,String> pq = new MinPriorityQueue<>();
		
		pq.insert(1, "Apple");
		pq.insert(2, "Banana");
		
		while(!pq.isEmpty())
			System.out.println(pq.removeMin().getValue());
		
	}

}
