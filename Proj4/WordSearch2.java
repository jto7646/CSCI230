import java.io.InputStream;
import java.util.ArrayList;
import java.util.TreeMap;
import java.io.File;
import java.io.FileInputStream;

public class WordSearch2 {
    //private ArrayList<MatchSet> matchList = new ArrayList<>();
    private ArrayList<File> fileList;
    private int sequenceSize;
    private int matchCieling;
    private TreeBuilder builder = new TreeBuilder();


    public WordSearch2(ArrayList<File> files, int sequenceSize, int matchCieling){
        fileList = files;
        this.sequenceSize = sequenceSize;
        this.matchCieling = matchCieling;
    }

    public WordSearch2(){this(null, 6, 25);}




    public ArrayList<MatchSet> compareFiles(){
        TreeMap<Integer, Integer> mainTree;
        TreeMap<Integer, Integer> secondTree;
        ArrayList<MatchSet> matchList = new ArrayList<>();
        builder.changeSequenceSize(sequenceSize);
        InputStream comparing;
        String fileNames;
        int matches = 0;

        int pass1 = 0, pass2 = 0;

        try {

            // Compares all of the files
            for (int i = 0; i < fileList.size(); i++) {
                
                // The current file being compared to the directory is used 
                //  to build a binary tree for easier searching
                comparing = new FileInputStream(fileList.get(i)); 
                builder.changeStream(comparing);
                mainTree = builder.build();
                System.out.println("On File number: " + pass1);

                // Compare current file to all of the others
                for (int j = i+1; j < fileList.size(); j++) { 
                    // Build the hash tree for the next file 
                    comparing = new FileInputStream(fileList.get(j));
                    builder.changeStream(comparing);
                    secondTree = builder.build();
                    System.out.println("Comparing File " + pass1 + " to file " + pass2);

                    // Compares all of the hash values in the current files tree to those in the second files    
                    for (Object key : mainTree.keySet()) {
                        // If a match is found, add one to the counter (Check this)
                        if(secondTree.get(key) != null){ matches++;}
                    }
                    // Create the name pair of the files
                    fileNames = fileList.get(i).getName() + " " + fileList.get(j).getName() + ":";
                    // Record the pair of files compared along with their match count
                    MatchSet pair = new MatchSet(matches, fileNames);
                    matchList.add(pair);
                    // Clear match counter for next file loop
                    matches = 0;
                    pass2++;
                }
                comparing.close();
                pass2 = 0;
                pass1++;
            }
            // Returns an array of match counted file pairs
            return matchList;
            
        } catch (Exception e) {
            System.out.println("File read error - WordSearch2:\n" + e);
        }
        return matchList;
    }

}