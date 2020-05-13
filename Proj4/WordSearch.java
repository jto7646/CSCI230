import java.io.InputStream;
import java.util.ArrayList;
import java.util.TreeMap;

public class WordSearch implements Runnable{
    private Thread red;
    private String threadName;
    private InputStream input;
    private ArrayList<String> compare;
    boolean isRunning;
    int matchCount;

    public WordSearch(String name, InputStream in, ArrayList<String> comp){
        threadName = name;
        input = in;
        compare = comp;
    }

    public void wordList(ArrayList<String> comp){compare = comp;}

    public void inputFile(InputStream in){input = in;}

    @Override
    public void run(){
        isRunning = true;
        ArrayList<String> incoming = new ArrayList<>();
        StringBuilder build = new StringBuilder(0);
        int temp = 0;
        int same = 0;
        matchCount = 0;
        

        if(compare.size() != 6){return;}

        try {

            // Build out initial list of six words to compare
            while(true){
                // Build word
                while(true){
                    // reads in one character
                    temp = input.read();
                    // Space or end of file hit
                    if(temp == 32 || temp == -1){break;}
                    // Append character to string
                    build.append((char) temp);
                }
                // Add word to arrayList
                incoming.add(build.toString());
                build = new StringBuilder(0); // clear stringbuilder

                // Six words to compare to
                if(incoming.size() == 6){break;}
                // End of file, break
                if(temp == -1){break;}
            }

            // The process of comparing words
            while (true){
                

                // Compare each word in the lists. If all six match, add one to match count
                for (int i = 0; i < incoming.size(); i++) {
                    if(compare.get(i).equals(incoming.get(i))){
                        same++;
                    }
                }
                if(same == 6){matchCount++;}

                // Read in another word from the document
                while(true){
                    temp = input.read();

                    // Space of end of document
                    if(temp == 32 || temp == -1){break;}
                    // Build new word
                    build.append((char) temp);
                }
                if(temp == -1){break;}

                // Shift compare list over one word
                incoming.remove(0);
                incoming.add(build.toString());
                build = new StringBuilder(0); 

                /*
                // for testing
                for (int i = 0; i < incoming.size(); i++) {
                    System.out.print(incoming.get(i) + " ");
                }*/
                System.out.print("\n Size: " + incoming.size() + " compsize: " + compare.size() + "\n");
            }
        } catch (Exception e) {
            System.out.println("Thread " + threadName + " error " + e);
            isRunning = false;
        } 

        isRunning = false;
    }// RUN

    public void start(){
        if(red == null){
            red = new Thread(this, threadName);
            red.start();
        }
    }
    
}