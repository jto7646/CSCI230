/**
 * labMapDriver
 */
public class LabMapDriver {

    public static void main(String[] args) {
        UnsortedTableMap testMap = new UnsortedTableMap<Integer, String>();
        
        
        testMap.put(1, "This is One.");
        testMap.put(2, "This is Two.");
        testMap.put(3, "This is Three.");

        System.out.println("Original Map:");
        for(Object it : testMap.entrySet()){
            System.out.println(it);
        }

        testMap.putIfAbsent(4, "This is four if absent.");
        
        System.out.println("\nList after using putIfAbsent, not in original list:");
        for(Object it : testMap.entrySet()){
            System.out.println(it);
        }

        testMap.putIfAbsent(3, "This is three if absent.");

        System.out.println("\nAttempt to add exsisting key using putIfAbsent:");

        for(Object it : testMap.entrySet()){
            System.out.println(it);
        }

        if(testMap.containsKey(3)) System.out.println("Map contains key 3.");

        if(!testMap.containsKey(7)) System.out.println("Map doesn't contain key 7.");

        System.out.print(" **** End of Test ****");
    } // **** END OF MAIN ****  

}// ******* END OF CLASS *********