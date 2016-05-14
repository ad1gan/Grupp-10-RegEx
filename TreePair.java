public class TreePair{
	private Tree t;
	private int  j;

	public TreePair(Tree t, int j) {
		this.t = t;
		this.j = j;
	}

	public Tree first(){
		return t;
	}
	public int second(){
		return j;
	}
}