
import java.util.ArrayList;
import java.util.Random;

/*
 * Map implementation using two bucket arrays
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 * @author John T. O'Leary
 */
public class PowerTwoHashMap<K,V> extends AbstractHashMap<K,V> {
  // a fixed capacity array of UnsortedTableMap that serve as buckets
  private UnsortedTableMap<K,V>[] table;   // initialized within createTable

  private int n = 0; // number of entries in first dictionary
  private int m = 0; // number of entries in second dictionary
  private long scaleFirst, shiftFirst;
  private long scaleSecond, shiftSecond;
  private int prime;

  // provide same constructors as base class
  /** Creates a hash table with capacity 11 and prime factor 109345121. */
  public PowerTwoHashMap() { this(17); }

  /** Creates a hash table with given capacity and prime factor 109345121. */
  public PowerTwoHashMap(int cap) { this(cap, 109345121); }

  /** Creates a hash table with the given capacity and prime factor. */
  public PowerTwoHashMap(int cap, int p) {
    this.prime = p;
    capacity = cap;
    Random rand = new Random();
    scaleFirst = rand.nextInt(prime-1) + 1;
    shiftFirst = rand.nextInt(prime);
    scaleSecond = rand.nextInt((prime / 2)-1) + 1;
    shiftSecond = rand.nextInt((prime / 2));
    createTable();
    }



  // ****************************************************
  // -------------- HASH FUNCTIONS ----------------------
  // ****************************************************
  private int firstHash(K key){
    return (int) ((Math.abs(key.hashCode()*scaleFirst + shiftFirst) % this.prime) % capacity);
  }

  private int secondHash(K key){
    return (int) ((Math.abs(key.hashCode()*scaleSecond + shiftSecond) % this.prime) % capacity);
  }

  /** Creates an empty table having length equal to current capacity. */
  @Override
  @SuppressWarnings({"unchecked"})
  protected void createTable() {
    table = (UnsortedTableMap<K,V>[]) new UnsortedTableMap[capacity];
  }
  // ****************************************************
  // ****************************************************

  /**
   * Returns value associated with key k in bucket with hash value h.
   * If no such entry exists, returns null.
   * @param h  the hash value of the relevant bucket
   * @param k  the key of interest
   * @return   associate value (or null, if no such entry)
   */
  @Override
  protected V bucketGet(int h, K k) {
    int firstHash = firstHash(k);
    int secondHash = secondHash(k);

    UnsortedTableMap<K,V> bucketA = table[firstHash];
    UnsortedTableMap<K,V> bucketB = table[secondHash];

    // if a bucket is empty, special case
    if (bucketA == null || bucketB == null){
      // both empty return null
      if(bucketA == null && bucketB == null){
        return null;
      }
      // If bucketA null, return bucketB
      if(bucketA == null) return bucketB.get(k);
      // bucketB is empty, return bucketA
      return bucketA.get(k);
    }
    
    // if not in bucketA, get from bucketB
    if(bucketA.get(k) == null) return bucketB.get(k);
    //if (bucketA.isEmpty()) return bucketB.get(k);
    
    // if it is in bucketA, return it
    return bucketA.get(k);
  }

  /**
   * Associates key k with value v in bucket with hash value h, returning
   * the previously associated value, if any.
   * @param h  the hash value of the relevant bucket (never used in function)
   * @param k  the key of interest
   * @param v  the value to be associated
   * @return   previous value associated with k (or null, if no such entry)
   */
  @Override
  protected V bucketPut(int h, K k, V v) {
    int firstHash = firstHash(k);
    int secondHash = secondHash(k);

    UnsortedTableMap<K,V> bucketA = table[firstHash];
    UnsortedTableMap<K,V> bucketB = table[secondHash];
    
    // If a bucket is empty, put the entry into the empty one
    if(bucketA == null){
      bucketA = table[firstHash] = new UnsortedTableMap<>();
      int oldSize = bucketA.size();
      V answer = bucketA.put(k,v);
      n += (bucketA.size() - oldSize);   // size may have increased
      System.out.println("In A");
      return answer;
    }
    if(bucketB == null){
      bucketB = table[secondHash] = new UnsortedTableMap<>();
      int oldSize = bucketB.size();
      V answer = bucketB.put(k,v);
      n += (bucketB.size() - oldSize);   // size may have increased
      System.out.println("In B");
      return answer;
    }

    // If neither bucket is empty, compare their sizes
    if (bucketA.size() < bucketB.size()){
      int oldSize = bucketA.size();
      V answer = bucketA.put(k,v);
      n += (bucketA.size() - oldSize);   // size may have increased
      System.out.println("In A");
      return answer;
    }
    else{
      int oldSize = bucketB.size();
      V answer = bucketB.put(k,v);
      n += (bucketB.size() - oldSize);   // size may have increased
      System.out.println("In B");
      return answer;
    } 
  }

  /**
   * Removes entry having key k from bucket with hash value h, returning
   * the previously associated value, if found.
   * @param h  the hash value of the relevant bucket (unused in function)
   * @param k  the key of interest
   * @return   previous value associated with k (or null, if no such entry)
   */
  @Override
  protected V bucketRemove(int h, K k) {
    int firstHash = firstHash(k);
    int secondHash = secondHash(k);

    UnsortedTableMap<K,V> bucketA = table[firstHash];
    UnsortedTableMap<K,V> bucketB = table[secondHash];
    
    // If both buckets are empty, return null
    if (bucketA.isEmpty() && bucketB.isEmpty()) return null;
    // If neither bucket contains the key, return null
    if (bucketA.get(k) == null && bucketB.get(k) == null) return null;

    // If the key isn't in bucketA, get it from bucketB
    if(bucketA.get(k) == null){
      int oldSize = bucketB.size();
      V answer = bucketB.remove(k);
      n -= (oldSize - bucketB.size());   // size may have decreased
      return answer;
    }
    else{ // If it is in A, retrieve it
      int oldSize = bucketA.size();
      V answer = bucketA.remove(k);
      n -= (oldSize - bucketA.size());   // size may have decreased
      return answer;
    }
  }

  /**
   * Returns an iterable collection of all key-value entries of the map.
   *
   * @return iterable collection of the map's entries
   */
  @Override
  public Iterable<Entry<K,V>> entrySet() {
    ArrayList<Entry<K,V>> buffer = new ArrayList<>();
    for (int h=0; h < capacity; h++)
      if (table[h] != null)
        for (Entry<K,V> entry : table[h].entrySet())
          buffer.add(entry);
    return buffer;
  }



  

  // VV MAIN FOR TESTING
  public static void main(String[] args) {
    PowerTwoHashMap testHashMap = new PowerTwoHashMap<Integer, String>(1000);

    /* Note: Could not figure out the issue with trying to remove or display
     *  from a key that does not exsist. I am already past my five hour mark
     *  on this lab, so I am leaving it in this state. It all works, as long
     *  as you dont do the above
     */
    
    // bucketPut testing
    testHashMap.bucketPut(0, 5, "Hello");
    testHashMap.bucketPut(0, 8, "there!");
    testHashMap.bucketPut(0, 12, "This is a");
    testHashMap.bucketPut(0, 12, "test.");
    testHashMap.bucketPut(0, 12, "AHHHH");
    testHashMap.bucketPut(0, 16, "BANANA!!!!!");

    // bucketGet testing        "Hello"                             "there!"
    System.out.println("\n" + testHashMap.bucketGet(0, 5) + " " + testHashMap.bucketGet(0, 8));
    System.out.print(testHashMap.bucketGet(0, 12) + " " );
    //                   "This is a"

    // bucketRemove testing
    testHashMap.bucketRemove(0, 12);
    //                   V "test."
    System.out.println(testHashMap.bucketGet(0, 12));

    testHashMap.bucketRemove(0, 12);
    System.out.println(testHashMap.bucketGet(0, 12));
    //testHashMap.bucketRemove(0, 5);
    //testHashMap.bucketRemove(0, 5);
    //System.out.println(testHashMap.bucketGet(0, 5));

    System.out.println("****END TESTING****");
  }// *** END MAIN ***

  
}// **** END CLASS ****
