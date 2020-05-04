import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Simple driver program, initialises the gui via HuffViewer
 * @author John O'Leary
 * @version 2.0
 * @since  April/30/2020
 */
public class TerminalDriver{

    public static void main(String[] args) {

        HuffViewer gui = new HuffViewer("Test Window");
        IHuffProcessor proc = new SimpleHuffProcessor();
        gui.setModel(proc); 
        System.out.println("Program now running.");
    }

}