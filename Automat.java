public class Automat{
	private Node[] nodes;
	static int counter;

	private Pair parseChar(char a){
		int start, end;
		nodes[counter] = new Node(nodes.length);
		start = end = counter;
		counter++;
		return new Pair(start, end);
	}
	private void parseAnd (Syntaxbaum left, Syntaxbaum right){
		int start, end;

	}
	private void parseOr  (Syntaxbaum left, Syntaxbaum right){
		
	}
	private void parseStar(Syntaxbaum down, int start, int end){
		
	}

	public Automat(Syntaxbaum tree){
		new Automat(tree.size()); //Vorrausgesetzte implementierung size()!
		
	}



	public Automat(int size){
		nodes = new Node[size];
		for(int i=0;i<size;i++)
			nodes[i] = new Node(size);
	}

	public int getSize(){
		return nodes.length;
	}
	public char getEdge(int s, int e){
		return nodes[s].getEdge(e);
	}
	public boolean containsEdge(int s, int e){
		return (getEdge(s,e)!='0');
	}
	public void setEdge(int s, int e, char c){
		nodes[s].setEdge(e,c);
	}
	public Node[] getAutomat(){
		return this.nodes;
	}
}