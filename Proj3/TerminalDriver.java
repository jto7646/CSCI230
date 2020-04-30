import java.io.FileInputStream;
import java.io.InputStream;

public class TerminalDriver{

    public static void main(String[] args) {

        TreeBuilder test = new TreeBuilder();
        
        
        // Bellow testing the CharCounter.countAll function: WORKS
        try {
            InputStream testInput = new FileInputStream("C:\\cygwin64\\home\\CSCI230\\Proj3\\testText.txt");
            CharCounter.countAll(testInput);  
        } catch (Exception e) {
            System.out.println(e);
        }


        test.buildTree(CharCounter.countValues());

        test.huffEncode(test.getRoot());

        /*

        TreeNode motest =  new TreeNode(test.getRoot().myValue, test.getRoot().myWeight);
        System.out.println(" ");
        */
        /*
        for (int i = 0; i < test.tree.size(); i++) {
            System.out.print(" " + test.tree.get(i).myWeight);
        }
        */
        System.out.println(" ");
        System.out.println("Values ");
        for (int i = 0; i < test.tree.size(); i++) {
            System.out.print(" " + test.tree.get(i).myValue);
        }

        System.out.println(" ");
        System.out.println("Weights ");
        for (int i = 0; i < test.tree.size(); i++) {
            System.out.print(" " + test.tree.get(i).myWeight);
        }

       //System.out.println("\n" + motest.myValue + " " + motest.myWeight);

        System.out.println("\n****END TESTING****");
    }

}