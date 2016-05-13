public class Syntaxbaum{
	private int size;
	public int size(){
		return this.size;
	}
	public Syntaxbaum(){
		size = 1;
	}
	public Syntaxbaum(String expression){
		size = 2;
	}
	public char getValue(){
		return 'A';
	}
	public Syntaxbaum getLeft(){
		return this;
	}
	public Syntaxbaum getRight(){
		return this;
	}
}

/*
Anforderungen:
size
hatKinder
getValue

*/