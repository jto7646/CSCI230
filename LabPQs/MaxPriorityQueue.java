import java.util.Comparator;

//package LabPriorityQueue;

public class MaxPriorityQueue<K,V> extends UnsortedPriorityQueue<K,V> {

	

	public MaxPriorityQueue(){ super(); }

	public Entry<K,V> insert(K key, V value) { return super.insert(key, value);} // Built in Parent

	public Position<Entry<K,V>> findMax() { return super.findMax();} // Built in parent

	public Entry<K,V> removeMax() { return super.removeMax();} // Built in parent 

	public Entry<K,V> max(){ return super.max(); } // Built in Parent 


	
	public static void main(String[] args) {
		
		MaxPriorityQueue<Integer,String> pq = new MaxPriorityQueue<>();
		
		pq.insert(1, "Apple");
		pq.insert(2, "Banana");
		
		/*
		 * This should print "Banana" "Apple"
		 */
		while(!pq.isEmpty())
			System.out.println(pq.removeMax().getValue());
		
	}
}
