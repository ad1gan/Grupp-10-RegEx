public class Node{
	private char[] edges;

	public Node(int size){
		edges = new char[size];
		for(int i=0;i<size;i++){
			edges[i]='0';
		}
	}
	public void setEdge(int i, char c){
		edges[i]=c;
	}
	public char getEdge(int i){
		return edges[i];
	}
	public char[] getEdges(){
		char[] res = new char[edges.length];
		for(int i=0;i<edges.length;i++){
			res[i] = edges[i];
		}
		return res;
	}
}