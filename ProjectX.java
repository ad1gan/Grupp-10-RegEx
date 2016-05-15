public class ProjectX{
	public static void main(String[] args) {
		Tree oak = new Tree(args[0]);
		Automaton entron = new Automaton(oak);
		System.out.println(simulate(args[1], entron));
	}
	public static boolean simulate(String expression, Automaton entron){
		Set<Integer> actives = new Set<Integer>();
		actives.addElement(entron.start());
		for(int i=0;i<expression.length();i++){
			for(int j=0;j<actives.size();j++)
				actives.union(cheapConnect(actives.getElement(j),entron));

			Set<Integer> actives2 = new Set<Integer>();
			for(int j=0;j<actives.size();j++)
				for(int k=0;k<entron.getSize();k++)
					if(entron.getEdge(actives.getElement(j),k)==expression.charAt(i))
						actives2.addElement(k);

			actives = actives2;
		}
		for(int j=0;j<actives.size();j++)
			actives.union(cheapConnect(actives.getElement(j),entron));

		if(actives.contains(entron.end()))
			return true;
		return false;
	}

	public static Set<Integer> cheapConnect(int numb, Automaton entron){
		Set<Integer> helper = new Set<Integer>();
		for(int i = 0; i < entron.getNode(numb).getEdges().length; i++){
			if (entron.getNode(numb).getEdge(i)=='3')
				helper.addElement(i);
		}
		return helper;
	}
}