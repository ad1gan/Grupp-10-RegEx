public class Node{
	private char[] edges;
	private boolean endpoint=false;

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
		return edges;
	}
	public boolean getEndpoint(){
		return endpoint;
	}
	public void setEndpoint(boolean e){
		endpoint = e;
	}
}