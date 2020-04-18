import java.util.ArrayList;
import java.util.Comparator;

//package lab.heap;

//import net.datastructures.HeapPriorityQueue;
//import net.datastructures.PositionalList;

public class MaxHeapPriorityQueue<K,V> extends HeapPriorityQueue<K,V>{

	protected ArrayList<K> mHeap = new ArrayList<>();
	private Comparator<K> comp = new DefaultComparator<>();

	int size = 0;
	
	public int size() { return size; }
	
	// ADD AND/OVERRIDE METHODS AS NEEDED

	//*************************************************** */
	// ----------- OVERRIDEN AND NEW METHODS --------------
	//*************************************************** */

	// VV Overriden Uphead function
	@Override
	protected void upheap(int j) {
		while (j > 0) {            // continue until reaching root (or break statement)
		  int p = parent(j);
		  if (compare(mHeap.get(j), mHeap.get(p)) >= 0) break; // heap property verified
		  swap(j, p);
		  j = p;                                // continue from the parent's location
		}
	}


	// VV Overriden Downheap Function
	@Override
	protected void downheap(int j) {
		while (hasLeft(j)) {               // continue to bottom (or break statement)
		  int leftIndex = left(j);
		  int smallChildIndex = leftIndex;     // although right may be smaller
		  if (hasRight(j)) {
			  int rightIndex = right(j);
			  if (compare(mHeap.get(leftIndex), mHeap.get(rightIndex)) > 0)
				smallChildIndex = rightIndex;  // right child is smaller
		  }
		  if (compare(mHeap.get(smallChildIndex), mHeap.get(j)) >= 0)
			break;                             // heap property has been restored
		  swap(j, smallChildIndex);
		  j = smallChildIndex;                 // continue at position of the child
		}
	}
	
	// Overridden Swap Function
	@Override 
	protected void swap(int i, int j) {
		K temp = mHeap.get(i);
		mHeap.set(i, mHeap.get(j));
		mHeap.set(j, temp);
	}
	

	// VV Overriden HasLeft/HasRight Functions
	protected boolean hasLeft(int j) { return left(j) < mHeap.size(); }
	protected boolean hasRight(int j) { return right(j) < mHeap.size(); }

	// New Compare Function!
	protected int compare(K a, K b){
		return comp.compare(a, b);
	}
	//*************************************************** */
	//*************************************************** */
	//*************************************************** */
  

	
	
	//*************************************************** */
	// ------------ SORT IN PLACE FUNCTION ----------------
	//*************************************************** */
	  public void sortInPlace(PositionalList<K> S) {

		

		// VV fills the arrayList in prep for sorting
		int n = S.size();
		for(int i = 0; i < n; i++){
			mHeap.add(S.remove(S.first()));
		}

		// Heapifies the array list in itself
		int listStart = 1;
		while(listStart != mHeap.size() - 1){
			upheap(listStart); //incrementally upheap
			listStart++;
		}
		  

		// Re-fills the list from heapified array list
		while((mHeap.size() - 1) != 0){
			// Insert from the top of heap, the downheap
			S.addLast(mHeap.get(0));
			mHeap.set(0, mHeap.get(mHeap.size() -1)); // set root as end
			mHeap.remove(mHeap.size() - 1); // removes the smallest that is now the root
			// need to now downheap
			downheap(0);	
		}
	  }

	//*************************************************** */
	//*************************************************** */
	//*************************************************** */
	
	
	

	  
} // ************** END OF CLASS ****************


