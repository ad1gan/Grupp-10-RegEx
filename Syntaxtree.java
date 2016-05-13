public class Syntaxtree{
	private int size;
	public int size(){
		return this.size;
	}
	public Syntaxtree(){
		size = 1;
	}
	public Syntaxtree(String expression){
		size = 2;
	}
	public char getValue(){
		return 'A';
	}
	public Syntaxtree getLeft(){
		return this;
	}
	public Syntaxtree getRight(){
		return this;
	}
}

/*
Anforderungen:
size
hatKinder
getValue

*/