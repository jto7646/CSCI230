import java.io.FileInputStream;
import java.io.InputStream;

public class TerminalDriver{

    public static void main(String[] args) {

       HuffViewer gui = new HuffViewer("Test Window");
       IHuffProcessor proc = new SimpleHuffProcessor();
       gui.setModel(proc);  

       String test = "10111001";
       int i = Integer.valueOf(test);
       System.out.println(i);


        System.out.println("\n****END TESTING****");
    }

}