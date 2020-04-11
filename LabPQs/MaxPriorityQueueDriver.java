
public class MaxPriorityQueueDriver {


    public static void main(String[] args) {
        MaxPriorityQueue<Integer, String> test = new MaxPriorityQueue<>();

        test.insert(1, " Test!");
        test.insert(2, " A ");
        test.insert(3, " Is ");
        test.insert(4, "This ");

        while(!test.isEmpty()){
            System.out.print(test.removeMax().getValue());
        }

    }
   

    
}