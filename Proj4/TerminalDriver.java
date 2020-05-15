import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.ArrayList;

public class TerminalDriver {




    public static void main(String[] args) {
        boolean running = true;
        boolean dirGood = false;
        
        TreeMap<Integer, Integer> hashTree = new TreeMap<>();
        ArrayList<File> files = new ArrayList<>();
        Scanner userInput = new Scanner(System.in);
        String userString = new String();
        InputStream comparing, compareTo;
        int userInt = 0, fileSelect = 0;
        File folder;
        TreeBuilder hashBuilder = new TreeBuilder();
        WordSearch compareFiles = new WordSearch();
        
        // Program main loop, Needed?
        while(running){

            // ************* Directory selection ****************
            // --------------------------------------------------
            while(!dirGood){
                System.out.print("Input the path to the file directory: ");
                userString = userInput.nextLine();

                try {
                    folder = new File(userString); 

                    // Adds all of the compatable files to the list to compare
                    for (File file : folder.listFiles()) {
                        userString = file.getName();
                        // Checks for compatability
                        if(userString.contains(".txt")){
                            files.add(file);
                        }
                    }

                    if(files.size() < 2){System.out.println("Not enough compatable files in directory");}
                    else{dirGood = true;}

                } catch (Exception e) {
                    System.out.println("Error with path..." + e);
                }
            }//Directory Selection Loop
            // ----------------------------------------------------
            // ****************************************************
            System.out.println("Directory selected....");

            // For testing purposes
            for (int i = 0; i < files.size(); i++) {
                System.out.println(files.get(i).getName());
            }
            
            // ----------------------------------------------------
            // ************* Word Sequence Selection **************
            while (true) {
                System.out.print("Enter the word sequence size: ");
                userInt = userInput.nextInt();
                if(userInt > 1){
                    System.out.println("Word sequence size " + userInt + " selected....");
                    break;
                }
            }

            // ---------------------------------------------------
            // ************** Comparison loop ********************
            while(true){

                // Make an array of hashMaps, one from each file, then itterate through them for comparison

                try {
                    // Path to the first file
                    comparing = new FileInputStream(files.get(fileSelect).getPath());
                    // Use bellow
                    for (int key : hashTree.keySet()) {
                        
                    }
                    // Compare first file with the rest of the files
                    while(true){
                    
                    }




                } catch (Exception e) {
                    System.out.println("File read error: " + e);
                }

            }
            // ---------------------------------------------------
            // ***************************************************


        }//Program Loop

        System.out.println("End Program");
    }

    public class MatchSet {
        private int match;
        private String fileNames;

        public MatchSet(int match, String fileNames){
            this.match = match;
            this.fileNames = fileNames; 
        }
    }



    
}