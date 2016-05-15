/** Data Type for Nodes of Automatons
 * @author Lukas
 */
public class Node{
	private char[] edges;
	/** Creates a Node without any edges.
	 * @param size The number of Nodes in the Automaton
	 */
	public Node(int size){
		edges = new char[size];
		for(int i=0;i<size;i++){
			edges[i]='0';
		}
	}
	/** Sets the Edge to another Node to a specified char. '3' stats for eps, '0' for no connection
	 * @param i The end Node of the new edge.
	 * @param c The char assigned to the new edge.
	 */
	public void setEdge(int i, char c){
		edges[i]=c;
	}
	/** Returns the char associated with a specified edge. '3' stats for eps, '0' for no connection
	 * @param i The end Node of the edge.
	 * @return The char of the edge
	 */
	public char getEdge(int i){
		return edges[i];
	}
	/** Returns the size of the Automaton this Node is in.
	 * @return The size of the Automaton
	 */
	public int getSize(){
		return edges.length;
	}
}