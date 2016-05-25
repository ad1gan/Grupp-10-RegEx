import java.util.ArrayList;

public class RegexMatcher{

	public static void main(String[] args) {
		Tree oak = new Tree(args[0]);
		Automaton entron = new Automaton(oak);
		RegexMatchResult douche = simulateBFS(args[1], entron);
		douche.print();
		RegexMatchResult douche2 = simulateDFS(args[1], entron);
		douche2.print();
		
	}

	public static RegexMatchResult matchSetBased(String regex, String testText){
		Tree tree = new Tree(regex);
		Automaton auto = new Automaton(tree);
		RegexMatchResult res = simulateBFS(testText, auto);
		return res;
	}

	private static RegexMatchResult simulateDFS(String testText, Automaton entron){
		ArrayList<Pair<Integer,Integer>> stack = new ArrayList<Pair<Integer,Integer>>();
		Pair<Integer,Integer> cur = new Pair<Integer,Integer>(0,0);
		Pair<Integer,Integer> valid = new Pair<Integer,Integer>(-1,-1);
		for(int i=0; i<testText.length(); i++){
			System.out.println("i ist: " + i);
			stack.clear();
			stack.add(new Pair<Integer,Integer>(entron.start(),i-1));
			while(stack.size()!=0){
				cur = stack.get(stack.size()-1);
				//System.out.println("\t\tString:" + i + " " + cur.second());
				stack.remove(stack.size()-1);
				if (cur.first()==entron.end())
					valid = new Pair<Integer,Integer>(i,cur.second());
				else if(valid.first()!=-1)
					return new RegexMatchResult(valid.first(),testText.substring(valid.first(),valid.second()+1));

				entron.pathSteps(stack,cur,testText);

			}
		}
		return new RegexMatchResult(-1,"");
	}

	/** Tests if a given string testText matches on an Automaton or actually
	 *   if it matches a regular expression returns true if it's a yes
	 *   @param testText the String which is tested
	 *   @param entron the Automaton that represents the given regular expression
	 *   @return true if testText matches with entron otherwise false
	 */
	private static RegexMatchResult simulateBFS(String testText, Automaton entron){
		Set<Integer> actives   = new Set<Integer>();
		Set<Integer> actives2  = new Set<Integer>();
		RegexMatchResult curr  = new RegexMatchResult(0, "");
		RegexMatchResult valid = new RegexMatchResult(-1,"");
		actives.addElement(entron.start());
		
		for(int i=0; i<testText.length(); i++){
			entron.freeSteps(actives);
			
			if(actives.contains(entron.end())){ //We already found a valid string, just need to see if it's of maximum length
				valid.setStartingPosition(curr.getStartingPosition());
				valid.setMatchedString(curr.getMatchedString());
			}

			for(int j=0; j<actives.size(); j++) //"expensive" connect
				for(int k=0; k<entron.getSize(); k++)
					if( entron.getEdge(actives.getElement(j),k)==testText.charAt(i) ) 
						actives2.addElement(k);

			if(actives2.size()==0){ //Cannot go further with the current string. Return or scrap?
				if(valid.getStartingPosition()!=-1 && valid.getMatchedString()!="") //We already found something valid, so it's of maximum length. Return it.
					return valid;
				else{ //We haven't found anything valid. Scrap.
					curr.setStartingPosition(i+1);
					curr.setMatchedString("");
				}
			} else{
				curr.setMatchedString(curr.getMatchedString() + testText.charAt(i));
				actives.copy(actives2);
			}
			actives2.clear();
		}
		entron.freeSteps(actives);

		if(!actives.contains(entron.end())) //The last string was not valid, so we haven't found anything
			return valid;
		return curr;
	}

}