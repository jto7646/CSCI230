
import javax.swing.*;
import java.awt.*;

public class GuiDriver {

    public static void main(String[] args) {
        
        // Creating the Frame
        JFrame frame = new JFrame("Huffing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        //Creating the MenuBar and adding components 
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("File");
        JMenu m2 = new JMenu("Help");
        mb.add(m1);
        mb.add(m2);
        JMenuItem m11 = new JMenuItem("Open");
        JMenuItem m22 = new JMenuItem("Save as");
        m1.add(m11);
        m1.add(m22);

        //Creating the panel at bottom and adding components 
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel label = new JLabel("Enter Text");
        JTextField tf = new JTextField(10); // accepts up to 10 characters 
        JButton send = new JButton("Send");
        JButton reset = new JButton("Reset");
        panel.add(label); // Components added using flow layout
        panel.add(tf);
        panel.add(send);
        panel.add(reset);
        
        // Text area at the center 
        JTextArea ta = new JTextArea();

        // Adding components to the frame
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.CENTER, ta);
        frame.setVisible(true);
    }

}