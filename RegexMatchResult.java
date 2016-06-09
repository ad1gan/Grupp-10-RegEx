/**
 * Represents the result of a matching String in RegEx
 * @author 
 *
 */
public class RegexMatchResult {

    private int startingPosition;
    private String matchedString;

    /**
     * Constructor
     * @param i starting Position of the String to be tested
     * @param s matched String of the String to be tested
     */
    public RegexMatchResult(int i, String s) {
        startingPosition = i;
        matchedString = s;
    }
    /**
     * set starting Position
     * @param i
     */
    public void setStartingPosition(int i){
        startingPosition = i;
    }
    
    /**
     * set matched String
     * @param s
     */
    public void setMatchedString(String s){
        matchedString = s;
    }
    
    public int getStartingPosition() {
        return startingPosition;
    }

    public String getMatchedString() {
        return matchedString;
    }

    /**
     * prints the result of Regex, whereby -1 is a symbol for no match
     */
    public void print() {
        if (startingPosition == -1) {
            System.out.println("There is no match!");
        } else {
            System.out.println("Matching position: " + startingPosition);
            System.out.println("Matched substring: " + matchedString);
        }
    }

}
