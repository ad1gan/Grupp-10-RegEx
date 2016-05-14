public class ProjectX{
	public static void main(String[] args) {
		Tree oak = new Tree(args[0]);
		Automaton entron = new Automaton(oak);
		//System.out.println(simulate(args[1], entron));
		Set<Integer> cheaper = new Set<Integer>();
		cheaper = expensiveConnect(1, 'A', entron);
		//Set<Integer> active = new Set<Integer>();
		System.out.println(simulate(args[1], entron));
		//for(int i = 0; i < active.size(); i++)
		//	System.out.print("" + active.getElement(i) + " ");		
		//Settester();
	}

	public static void Settester(){
		Set<Integer> A = new Set<Integer>();
		A.addElement(1); A.addElement(3); A.addElement(4); A.addElement(4);
		Set<Integer> B = new Set<Integer>();
		B.addElement(2); B.addElement(3);
		Set<Integer> C = new Set<Integer>();
		C.addElement(4);
		C.intersect(A);
		B.union(A);
		for(int i = 0; i < A.size(); i++)
			System.out.print("" + A.getElement(i) + " ");
		System.out.println();
		for(int i = 0; i < B.size(); i++)
			System.out.print("" + B.getElement(i) + " ");
		System.out.println();
		for(int i = 0; i < C.size(); i++)
			System.out.print("" + C.getElement(i) + " ");
	}


	public static boolean simulate(String expression, Automaton entron){
		Set<Integer> active = new Set<Integer>();
		Set<Integer> helper = new Set<Integer>();
		active.addElement(entron.start());
		for(int i = 0; i < expression.length(); i++){
			active.clear();
			active.addElement(entron.start());
			do{
				helper = active;
				for(int j = 0; j < helper.size(); j++)
					active.union( cheapConnect(helper.getElement(j), entron) );
			}while(active != helper);

			for(int k = 0; k < active.size(); k++)
				active.union( expensiveConnect(k, expression.charAt(i), entron) );

			do{
				helper = active;
				for(int j = 0; j < helper.size(); j++)
					active.union( cheapConnect(helper.getElement(j), entron) );
			}while(active != helper);

			System.out.println();
			for(int l = 0; l < active.size(); l++)
				System.out.print("" + active.getElement(l) + " ");
		}
		return( active.contains(entron.end()) );
	}

	//prueft ob expression entron positiv abschliesst
	/*public static boolean simulate(String expression, Automaton entron){
		Set<Node> active = new Set<Node>();
		active.addElement(entron.getNode(entron.start()));
		Set<Node> helper = new Set<Node>();
		Set<Node> cheapHelp = new Set<Node>();
		for(int i = 0; i < expression.length(); i++){
			while( cheapHelp != helper ){
				System.out.println("Starte while");
				helper.addElement(entron.getNode(entron.start()));
				cheapHelp = helper;
				for(int j = 0; j < active.size(); j++)
					helper.union(cheapConnect(j, entron));
			}

			for(int k = 0; k < active.size(); k++){
				System.out.println("Starte for");
				helper.union( expensiveConnect(k, expression.charAt(i), entron) );
			}
			active = helper;
			helper.clear();
			
			System.out.println();
		}
		return ( active.contains(entron.getNode(entron.end())) );
	}*/

	public static Set<Integer> cheapConnect(int numb, Automaton entron){
		Set<Integer> helper = new Set<Integer>();
		for(int i = 0; i < entron.getNode(numb).getEdges().length; i++){
			if (entron.getNode(numb).getEdge(i)=='3')
				helper.addElement(i);
		}
		return helper;
	}

	//gibt Netz der mit epsilon verbundenen Knoten zurueck
	/*public static Set<Node> cheapConnect(int numb, Automaton entron){
		Set<Node> helper = new Set<Node>();
		for(int i = 0; i < entron.getNode(numb).getEdges().length; i++){
			if (entron.getNode(numb).getEdge(i)=='3'){
				helper.addElement(entron.getNode(i));
				System.out.print(" " + i + " ");
			}
		}
		helper.addElement(entron.getNode(numb));
		return helper;
	}*/

	public static Set<Integer> expensiveConnect(int numb, char c, Automaton entron){
		Set<Integer> helper = new Set<Integer>();
		for(int i = 0; i < entron.getNode(numb).getEdges().length; i++){
			if(entron.getNode(numb).getEdge(i) == c)
				helper.addElement(i);
		}
		return helper;
	}

	/*public static Set<Node> expensiveConnect(int numb, char c, Automaton entron){
		Set<Node> helper = new Set<Node>();
		for(int i = 0; i < entron.getNode(numb).getEdges().length; i++){
			if(entron.getNode(numb).getEdge(i) == c){
				helper.addElement(entron.getNode(i));
				System.out.print(" " + i + " ");
			}
		}
		return helper;
	}*/

}