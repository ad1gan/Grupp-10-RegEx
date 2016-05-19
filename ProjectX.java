public class ProjectX{
	public static void main(String[] args) {
		Tree oak = new Tree(args[0]);
		Automaton entron = new Automaton(oak);
		RegexMatchResult douche = simulate(args[1], entron);
		douche.print();
	}

	/** Tests if a given string testText matches on an Automaton or actually
	 *   if it matches a regular expression returns true if it's a yes
	 *   @param testText the String which is tested
	 *   @param entron the Automaton that represents the given regular expression
	 *   @return true if testText matches with entron otherwise false
	 */
	public static RegexMatchResult simulate(String testText, Automaton entron){
		Set<Integer> actives   = new Set<Integer>();
		Set<Integer> actives2  = new Set<Integer>();
		RegexMatchResult curr  = new RegexMatchResult(1, "");
		RegexMatchResult valid = new RegexMatchResult(-1,"");
		actives.addElement(entron.start());
		
		for(int i = 0; i < testText.length(); i++){
			freeSteps(actives,entron);
			
			if(actives.contains(entron.end())){ //We already found a valid string, just need to see if it's of maximum length
				valid.setStartingPosition(curr.getStartingPosition());
				valid.setMatchedString(curr.getMatchedString());
			}

			actives2 = new Set<Integer>(); //Why doesn't clear work here?
			for(int j=0; j<actives.size(); j++) //"expensive" connect
				for(int k=0; k<entron.getSize(); k++)
					if( entron.getEdge(actives.getElement(j),k)==testText.charAt(i) ) 
						actives2.addElement(k);

			if(actives2.size()==0){ //Cannot go further with the current string. Return or scrap?
				if(valid.getStartingPosition()!=-1) //We already found something valid, so it's of maximum length. Return it.
					return valid;
				else{ //We haven't found anything valid. Scrap.
					curr.setStartingPosition(i+1);
					curr.setMatchedString("");
				}
			} else{
				curr.setMatchedString(curr.getMatchedString() + testText.charAt(i));
				actives = actives2; //Why does this have to be in the else loop and not after it?
			}
		}
		freeSteps(actives,entron);

		if(!actives.contains(entron.end())) //The last string was not valid, so we haven't found anything
			curr.setStartingPosition(-1);
		return curr;
	}
	/** Extends an existing subgraph with all it's epsilon-connected nodes.
	*   @param actives The indices of the subgraph that is connected
	*	@param entron The Automaton the subgraph is searched in
	*/
	public static void freeSteps(Set<Integer> actives, Automaton entron){
		for(int i=0; i<actives.size(); i++)
			for(int j=0;j<entron.getSize();j++)
				if(entron.getEdge(actives.getElement(i),j)=='3')
					actives.addElement(j);
	}
}