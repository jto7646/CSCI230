import java.io.FileInputStream;
import java.io.InputStream;

public class TerminalDriver{

    public static void main(String[] args) {
        
        // Bellow testing the CharCounter.countAll function: WORKS
        try {
            InputStream testInput = new FileInputStream("C:\\cygwin64\\home\\CSCI230\\Proj3\\testText.txt");
            CharCounter.countAll(testInput);  
        } catch (Exception e) {
            System.out.println(e);
        }

        // Bellow testing the CharCounter.getCount function: WORKS
        System.out.println("Should print 7: \n" + CharCounter.getCount(97));

        // Bellow testing the CharCount.set function: WORKS
        CharCounter.set(97, 200);
        System.out.println("Should print 200: \n" + CharCounter.getCount(97));

        // Bellow testing the CharCounter.add function: WORKS
        CharCounter.add(97);
        System.out.println("Should print 201: \n" + CharCounter.getCount(97));

        //Bellow testing the CharCounter.clear function: WORKS
        CharCounter.clear();
        System.out.println("Should print 0: \n" + CharCounter.getCount(97));


        System.out.println("****END TESTING****");
    }

}