public class ProjectX{
	public static void main(String[] args) {
		Tree oak = new Tree(args[0]);
		oak.cout();
		Automaton entron = new Automaton(oak);
		//simulate(args[1], entron);
	}


	//prueft ob expression entron positiv abschliesst
	public static boolean simulate(String expression, Automaton entron){
		Set<Node> active = new Set<Node>();
		active.addElement(entron.getNode(entron.start()));
		Set<Node> helper = new Set<Node>();
		for(int i = 0; i < expression.length(); i++){
			helper.addElement(entron.getNode(entron.start()));
			for(int j = 0; j < active.size(); j++)
				helper.union(cheapConnect(active.getElement(j), entron));
			for(int k = 0; k < active.size(); k ++){
				helper.union( expensiveConnect(active.getElement(k),
					expression.charAt(i), entron) );
			}
			active = helper;
			helper.clear();
		}
		return ( active.contains(entron.getNode(entron.end())) );
	}

	//gibt Netz der mit epsilon verbundenen Knoten zurueck
	public static Set<Node> cheapConnect(Node rooter, Automaton entron){
		Set<Node> helper = new Set<Node>();
		helper.addElement(rooter);
		for(int i = 0; i < rooter.getSize(); i++){
			if(rooter.getEdge(i) == '3')
				helper.addElement(entron.getNode(i));
		}
		for(int j = 1; j < helper.size(); j++)
			helper.union( cheapConnect(helper.getElement(j), entron) );
		
		return helper;
	}

	public static Set<Node> expensiveConnect(Node rooter, char c, Automaton entron){
		Set<Node> helper = new Set<Node>();
		for(int i = 0; i < rooter.getSize(); i++){
			if(rooter.getEdge(i) == c)
				helper.addElement(entron.getNode(i));
		}
		return helper;
	}

}