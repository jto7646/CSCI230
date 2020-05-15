
 /**
 * Used to store file name/match pairs for later printing 
 */
public class MatchSet {
    private int match;
    private String fileNames;

    /**
     *  Creates a new file name/match pair 
     * @param match Count of matches between the two files
     * @param fileNames The name of the two files compared
     */
    public MatchSet(int match, String fileNames){
        this.match = match;
        this.fileNames = fileNames; 
    }

    /**
     * Returns the names of the two files that were compared
     * @return names of compared files 
     */
    public String getFileNames(){return fileNames;}

    /**
     * Returns the number of matches between the two files 
     * @return number of sequence matches between the two files
     */
    public int getMatchCount(){return match;}

}