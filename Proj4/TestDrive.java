
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.TreeMap;



public class TestDrive {
    
    public static void main(String[] args) {
        
        System.out.println("**** TEST START *****");
        TreeMap<Integer, Integer> compTree = new TreeMap<Integer, Integer>();
        InputStream inward, input;

        try {
            inward = new FileInputStream("C:\\cygwin64\\home\\CSCI230\\Proj4\\suspicious-document01501.txt");
            input = new FileInputStream("C:\\cygwin64\\home\\CSCI230\\Proj4\\suspicious-document01502.txt");

            TreeBuilder building = new TreeBuilder(inward, 6);
            WordSearch searching = new WordSearch(inward, 6, null);

            compTree = building.build();

            System.out.println("MT Tree Size: " + compTree.size());
            inward = new FileInputStream("C:\\cygwin64\\home\\CSCI230\\Proj4\\suspicious-document01501.txt");

            searching.setCompareTree(compTree);
            searching.changeStream(inward);

            int matches = searching.compareFiles();

            System.out.println("Found " + matches + " matches.");

        } catch (Exception e) {
           System.out.println("File read error: " + e);
        }
    }
    
}