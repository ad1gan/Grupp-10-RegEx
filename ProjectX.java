public class ProjectX{
	public static void main(String[] args) {
		Tree oak = new Tree(args[0]);
		Automaton entron = new Automaton(oak);
		Pair<Integer, String> bag = new Pair<Integer, String>(1, "");
		Pair<Boolean, Pair<Integer, String>> douche =
			new Pair<Boolean, Pair<Integer, String>>(false, bag);
		
		douche = simulate(args[1], entron);

		System.out.println(douche.first());
		System.out.println(douche.second().first());
		System.out.println(douche.second().second());

		System.out.println();

		System.out.println(simulate(args[1], entron).first());
		System.out.println(simulate(args[1], entron).second().first());
		System.out.println(simulate(args[1], entron).second().second());
	}

	/** Tests if a given string testText matches on an Automaton or actually
	 *   if it matches a regular expression returns true if it's a yes
	 *   @param testText the String which is tested
	 *   @param entron the Automaton that represents the given regular expression
	 *   @return true if testText matches with entron otherwise false
	 */
	public static Pair<Boolean, Pair<Integer, String>> simulate(String testText, Automaton entron){
		Set<Integer> actives = new Set<Integer>();
		Set<Integer> comparison = new Set<Integer>();
		Pair<Integer, String> helper = new Pair<Integer, String>(1, "");
		actives.addElement(entron.start());
		
		for(int i = 0; i < testText.length(); i++){
			for(int j = 0; j < actives.size(); j++)
				actives.union(cheapConnect(actives.getElement(j),entron));
			
			Set<Integer> actives2 = new Set<Integer>();
			
			for(int j = 0; j < actives.size(); j++)
				for(int k = 0; k < entron.getSize(); k++)
					if ( entron.getEdge(actives.getElement(j),k) == testText.charAt(i) )
						actives2.addElement(k);

			if ( actives2 == comparison ){		//If there couldn't be added any new nodes to
				helper.setSecond("");			//actives2 the latest part of testText was not
				helper.setFirst(i+1);						//part of match for entron
			}else{
				helper.setSecond(helper.second() + testText.charAt(i));
			}

			actives = actives2;
		}
		for(int j = 0; j < actives.size(); j++)
			actives.union(cheapConnect(actives.getElement(j),entron));

		if(actives.contains(entron.end())){
			return new Pair<Boolean, Pair<Integer, String>>(true, helper);
		}
		else{		
			helper.setFirst(2147483647); helper.setSecond("{}");
			return new Pair<Boolean, Pair<Integer, String>>(false, helper);
		}
	}

	/** Finds the subgraph of nodes that is ocnnected to node numb only via "3"-connections
	*   @param numb the index in the Automaton-graph entron
	*	@entron the Automaton the subgraph is searched in
	*	@return Set<Integer> which holds the indices of the nodes
	*/
	public static Set<Integer> cheapConnect(int numb, Automaton entron){
		Set<Integer> helper = new Set<Integer>();
		for(int i = 0; i < entron.getNode(numb).getEdges().length; i++){
			if (entron.getNode(numb).getEdge(i)=='3')
				helper.addElement(i);
		}
		return helper;
	}
}