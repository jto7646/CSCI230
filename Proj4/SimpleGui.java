//Usually you will require both swing and awt packages
// even if you are working with just swings.
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
class SimpleGui {

    //private JFileChooser choose = new JFileChooser(System.getProperties().getProperty("user.dir"));
    public int secSize = 6;

    public static void main(String args[]) {
        JFileChooser choose = new JFileChooser(System.getProperties().getProperty("user.dir"));
        choose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        //Creating the Frame
        JFrame frame = new JFrame("Chat Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("FILE");
        //JMenu m2 = new JMenu("Help");
        mb.add(m1);
        //mb.add(m2);
        //JMenuItem m22 = new JMenuItem("Save as");
        m1.add(new AbstractAction("Sequence Size"){
            @Override
            public void actionPerformed(ActionEvent e) {
                int sec = selectNumbers();
            }
        });
        
        m1.add(new AbstractAction("Open Directory"){
            @Override
            public void actionPerformed(ActionEvent e) {
                int ret = choose.showOpenDialog(null);
            }
        });
        //m1.add(m22);

        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel label = new JLabel("Enter Text");
        JTextField tf = new JTextField(10); // accepts upto 10 characters
        JButton send = new JButton("Send");
        JButton reset = new JButton("Reset");
        panel.add(label); // Components Added using Flow Layout
        panel.add(tf);
        panel.add(send);
        panel.add(reset);

        // Text Area at the Center
        JTextArea ta = new JTextArea();

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.CENTER, ta);
        frame.setVisible(true);
    }


    private static int selectNumbers(){
        JFrame frame = new JFrame("Sequence Size");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setAlwaysOnTop(true);
        frame.setSize(400, 200);

        JLabel lbl = new JLabel("Enter word sequence size");
        JTextField tf = new JTextField(2);
        JTextPane tp = new JTextPane();
        JButton set = new JButton("Set");

        frame.getContentPane().add(BorderLayout.NORTH, lbl);
        frame.getContentPane().add(BorderLayout.CENTER, tf);
        frame.getContentPane().add(BorderLayout.SOUTH, set);
        frame.setVisible(true);

        return -1;
    }
}