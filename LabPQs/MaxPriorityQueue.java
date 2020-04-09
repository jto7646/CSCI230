//package LabPriorityQueue;

public class MaxPriorityQueue {

	/*
	 * Finish me
	 */
	public static void main(String[] args) {
		
		MinPriorityQueue<Integer,String> pq = new MinPriorityQueue<>();
		
		pq.insert(1, "Apple");
		pq.insert(2, "Banana");
		
		/*
		 * This should print "Banana" "Apple"
		 */
		while(!pq.isEmpty())
			System.out.println(pq.removeMin().getValue());
		
	}
}
