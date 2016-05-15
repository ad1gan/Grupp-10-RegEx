public class RegexMatchResult {

    private int startingPosition;
    private String matchedString;

    public RegexMatchResult(int i, String s) {
        startingPosition = i;
        matchedString = s;
    }

    public void setStartingPosition(int i){
        startingPosition = i;
    }
    public void setMatchedString(String s){
        matchedString = s;
    }
    public int getStartingPosition() {
        return startingPosition;
    }

    public String getMatchedString() {
        return matchedString;
    }

    public void print() {
        if (startingPosition == -1) {
            System.out.println("There is no match!");
        } else {
            System.out.println("Matching position: " + startingPosition);
            System.out.println("Matched substring: " + matchedString);
        }
    }

}