import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.ArrayList;

public class TerminalDriver {




    public static void main(String[] args) {
        boolean running = true; // Main Loop bool
        boolean dirGood = false; // Directory select bool
        ArrayList<TreeMap> treeArray = new ArrayList<>(); // holds hash trees for all files 
        TreeMap<Integer, Integer> hashTree = new TreeMap<>(); // creates the hash trees
        ArrayList<File> files = new ArrayList<>();  // Holds the files for comparison
        ArrayList<MatchSet> matchSets = new ArrayList<>(); // Holds the file matches 
        Scanner userInput = new Scanner(System.in); // Used for user input
        String userString = new String();   // Used for user input
        int userInt = 0;    // Used for user input 
        InputStream comparing; // Reads the files 
        int sequenceSize = 0; // Dictates the size of the word sequences being compared 
        File folder; // Directory path 
        TreeBuilder hashBuilder = new TreeBuilder(); // creates the hash trees
        WordSearch compareFiles = new WordSearch(); // compares the two files, needed?
        
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

                // Make Array of hash trees
                for (int i = 0; i < files.size(); i++) {
                    try {
                        comparing = new FileInputStream(files.get(i).getPath());
                        // Build a hash tree for the file
                        hashBuilder = new TreeBuilder(comparing, sequenceSize);
                        hashTree = hashBuilder.build();
                        // Add the hashTree to the array
                        treeArray.add(hashTree);

                    } catch (Exception e) {
                        System.out.println("File read error - Tree Building: " + e);
                    }

                }

                // Compare the hash trees
                for (int i = 0; i < files.size(); i++) {
                    // Current file being compared 
                    hashTree = treeArray.get(i);

                    // Compare current file to all of the others
                    for (int j = i+1; j < files.size(); j++) {  
                        
                        for (Object key : treeArray.get(j).keySet()) {
                            // TODO: if current key is found in hashTree, iterate 
                        }
                        // TODO: add count from above to arrayList with MatchSets
                    }


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

        public String getFileNames(){return fileNames;}

        public int getMatchCount(){return match;}

    }



    
}