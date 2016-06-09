/** Data Type for Automatons, realized as a Graph
 * @author Lukas Juschka
 */
import java.util.ArrayList;

public class Automaton{
	private Node[] nodes;
	private int counter;
	private Pair<Integer,Integer> start_end;
	private ArrayList<Pair<Integer,Integer>> qms;

	private Pair<Integer,Integer> parseTree(Tree tree){
		if(tree.getValue()=='.'){
			Pair<Integer,Integer> l = parseTree(tree.getLeft());
			Pair<Integer,Integer> r = parseTree(tree.getRight());
			nodes[counter] = new Node(nodes.length);
			nodes[l.second()].setEdge(counter,'3');
			nodes[counter].setEdge(r.first(),'3');
			counter++;
			return new Pair<Integer,Integer>(l.first(),r.second());
		} else if(tree.getValue()=='|'){
			Pair<Integer,Integer> l = parseTree(tree.getLeft());
			Pair<Integer,Integer> r = parseTree(tree.getRight());

			nodes[counter]   = new Node(nodes.length);
			nodes[counter+1] = new Node(nodes.length);

			nodes[counter].setEdge(l.first(),'3');
			nodes[counter].setEdge(r.first(),'3');

			nodes[l.second()].setEdge(counter+1,'3');
			nodes[r.second()].setEdge(counter+1,'3');
			
			counter+=2;
			return new Pair<Integer,Integer>(counter-2, counter-1);
		} else if(tree.getValue()=='*'){
			Pair<Integer,Integer> l = parseTree(tree.getLeft());
			nodes[counter]   = new Node(nodes.length);
			nodes[counter+1] = new Node(nodes.length);

			nodes[counter].setEdge(l.first(),'3');
			nodes[counter].setEdge(counter+1,'3');
			nodes[l.second()].setEdge(counter+1,'3');

			nodes[l.second()].setEdge(l.first(),'3');

			counter+=2;
			return new Pair<Integer,Integer>(counter-2, counter-1);
		} else if(tree.getValue()=='+'){
			Pair<Integer,Integer> l = parseTree(tree.getLeft());
			nodes[counter]   = new Node(nodes.length);
			nodes[counter+1] = new Node(nodes.length);

			nodes[counter].setEdge(l.first(),'3');
			nodes[l.second()].setEdge(counter+1,'3');
			nodes[l.second()].setEdge(l.first(),'3');

			counter+=2;
			return new Pair<Integer,Integer>(counter-2, counter-1);
		} else if(tree.getValue()=='?'){
			Pair<Integer,Integer> l = parseTree(tree.getLeft());
			qms.add(new Pair<Integer,Integer>(counter,l.first()));
			nodes[counter]   = new Node(nodes.length);
			nodes[counter+1] = new Node(nodes.length);

			nodes[counter].setEdge(counter+1,'3');
			nodes[counter].setEdge(l.first(),'3');
			nodes[l.second()].setEdge(counter+1,'3');
			counter+=2;
			return new Pair<Integer,Integer>(counter-2,counter-1);
		} else{
			nodes[counter] = new Node(nodes.length);
			nodes[counter+1] = new Node(nodes.length);
			nodes[counter].setEdge(counter+1,tree.getValue());
			counter+=2;
			return new Pair<Integer,Integer>(counter-2,counter-1);
		}
	}
	/**
	 * Moves loops around '?' operator into the operator to avoid '3'-loops
	 */
	private void fixdat(){
		for(int i=0;i<qms.size();i++){
			int a = qms.get(i).first();
			int b = qms.get(i).second();
			if (getEdge(a+1,a)=='3'){
				nodes[a+1].setEdge(a,'0');
				if(a-b>2)
					nodes[a-2].setEdge(b,'3');
				else
					nodes[a-1].setEdge(b,'3');
			}
		}
	}
	/** Creates the Automaton
	 * @param tree The Tree that is to be parsed
	 */
	public Automaton(Tree tree){
		qms = new ArrayList<Pair<Integer,Integer>>();
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
	/** Returns the node with Index i
	 * @param i The index of the Node
	 * @return The requested node
	 */
	public Node getNode(int i){
		return nodes[i];
	}
	/** Extends an existing subgraph with all it's epsilon-connected nodes.
	*   @param actives The indices of the subgraph that is connected
	*/
	public void freeSteps(Set<Integer> actives){
		for(int i=0; i<actives.size(); i++)
			for(int j=0;j<getSize();j++)
				if(getEdge(actives.getElement(i),j)=='3')
					actives.addElement(j);
	}
	/** Checks whether you can the BFS with that character
	 * @param c The character you want to start with
	 * @return The Node you end up with that character, -1 if you cannot
	 */
	public int startsright(char c){
		Set<Integer> s = new Set<Integer>();
		s.addElement(start());
		freeSteps(s);
		for(int i=0; i<s.size(); i++)
			for(int j=0;j<getSize();j++)
				if(getEdge(s.getElement(i),j)==c)
					return j;
		return -1;
	}
}