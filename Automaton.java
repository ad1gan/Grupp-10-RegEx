/** Data Type for Automatons, realized as a Graph
 * @author Lukas
 */
public class Automaton{
	private Node[] nodes;
	private static int counter;
	private Pair<Integer,Integer> start_end;

	private Pair<Integer,Integer> parseTree(Tree tree){
		if(tree.getValue()=='.'){
			Pair<Integer,Integer> l = parseTree(tree.getLeft());
			Pair<Integer,Integer> r = parseTree(tree.getRight());
			//Nicht exakt gleiche Realisierung wie in Notes, aber nah genug
			nodes[counter] = new Node(nodes.length);
			if(l.first()!=l.second())
				nodes[l.second()].setEdge(counter,'3');
			else
				nodes[l.second()].setEdge(counter,tree.getLeft().getValue());
			if(r.first()!=r.second())
				nodes[counter].setEdge(r.first(),'3');
			else
				nodes[counter].setEdge(r.first(),tree.getRight().getValue());
			counter++;
			return new Pair<Integer,Integer>(l.first(),r.second());
		} else if(tree.getValue()=='|'){
			//Oberer und unterer grauer Block; Pair jeweils erstes und letzes Element
			Pair<Integer,Integer> l = parseTree(tree.getLeft());
			Pair<Integer,Integer> r = parseTree(tree.getRight());
			//Legt Anfangs und Endnode an
			nodes[counter]   = new Node(nodes.length);
			nodes[counter+1] = new Node(nodes.length);
			//Legt Edges von erster Node aus an
			if(l.first()!=l.second())
				nodes[counter].setEdge(l.first(),'3');
			else
				nodes[counter].setEdge(l.first(),tree.getLeft().getValue());
			if(r.first()!=r.second())
				nodes[counter].setEdge(r.first(),'3');
			else
				nodes[counter].setEdge(r.first(),tree.getRight().getValue());
			//Legt Edges zu letzer Node an
			nodes[l.second()].setEdge(counter+1,'3');
			nodes[r.second()].setEdge(counter+1,'3');
			//Berichtigt Counter
			counter+=2;
			return new Pair<Integer,Integer>(counter-2, counter-1);
		} else if(tree.getValue()=='*'){
			Pair<Integer,Integer> l = parseTree(tree.getLeft());

			nodes[counter]   = new Node(nodes.length);
			nodes[counter+1] = new Node(nodes.length);

			nodes[counter].setEdge(l.first(),'3');
			nodes[counter].setEdge(counter+1,'3');
			nodes[l.second()].setEdge(counter+1,'3');

			if(l.first()!=l.second())
				nodes[l.second()].setEdge(l.first(),'3');
			else
				nodes[l.second()].setEdge(l.first(),tree.getLeft().getValue());

			counter+=2;
			return new Pair<Integer,Integer>(counter-2, counter-1);
		} else if(tree.getValue()=='+'){
			Pair<Integer,Integer> l = parseTree(tree.getLeft());

			nodes[counter]   = new Node(nodes.length);
			nodes[counter+1] = new Node(nodes.length);

			nodes[counter].setEdge(l.first(),'3');
			nodes[l.second()].setEdge(counter+1,'3');

			if(l.first()!=l.second())
				nodes[l.second()].setEdge(l.first(),'3');
			else
				nodes[l.second()].setEdge(l.first(),tree.getLeft().getValue());

			counter+=2;
			return new Pair<Integer,Integer>(counter-2, counter-1);
		} else{
			nodes[counter] = new Node(nodes.length);
			counter++;
			return new Pair<Integer,Integer>(counter-1,counter-1);
		}
	}
	/** Creates the Automaton
	 * @param tree The Tree that is to be parsed
	 */
	public Automaton(Tree tree){
		int size = tree.getVerts();
		nodes = new Node[size];
		for(int i=0;i<size;i++)
			nodes[i] = new Node(size);
		start_end = parseTree(tree);
	}
	/** Returns the start index of the Graph
	 * @return The start index
	 */
	public int start(){
		return start_end.first();
	}
	/** Returns the end index of the Graph
	 * @return The end index
	 */
	public int end(){
		return start_end.second();
	}
	/** Returns the number of nodes of the Graph
	 * @return The number of nodes
	 */
	public int getSize(){
		return nodes.length;
	}
	/** Returns a connecting edge's character. '3' means eps, '0'  no connection
	 * @param s The edge's starting point index
	 * @param e The edge's end point index
	 * @return The connecting character
	 */
	public char getEdge(int s, int e){
		return nodes[s].getEdge(e);
	}
	/** Changes the edge between two Nodes. '3' means eps, '0' no connection
	 * @param s The index of the Node at which the edge starts
	 * @param e The index of the Node at which the edge ends
	 * @param c The character the edge will be set to
	 */
	public Node getNode(int i){
		return nodes[i];
	}
}