/** Data Type for binary Trees of characters
 * @author Adrian and Lukas
 */
public class Tree{
	private char value;
	private Tree left;
	private Tree right;
	/** Creates the finished Tree from a String
	 * @param p The String that is being parsed
	 */
	public Tree(String p){
		Pair<Tree,Integer> a = parseExpression(p,0);
		value = a.first().value;
		left  = a.first().left;
		right = a.first().right;
	}
	/** Creates a single Node of the Tree with two children
	 * @param c The char that is used for the Trees Node
	 * @param l The left child
	 * @param r the right child
	 */
	public Tree(char c, Tree l, Tree r){
		value = c;
		left  = l;
		right = r;
	}
	/** Returns the left child
	 * @return The left child of the tree
	 */
	public Tree getLeft(){
		return left;
	}
	/** Returns the right child
	 * @return The right child of the tree
	 */
	public Tree getRight(){
		return right;
	}
	/** Returns the Nodes character
	 * @return The Nodes character
	 */
	public char getValue(){
		return value;
	}
	/** Changes the Trees left child
	 * @param t The new left child
	 */
	public void setLeft(Tree t){
		left = t;
	}
	/** Changes the Trees right child
	 * @param t The new right child
	 */
	public void setRight(Tree t){
		left = t;
	}
	/** Changes the Trees nodes char
	 * @param c The new char
	 */
	public void setValue(char c){
		value = c;
	}
	/** Returns the weighted sum of the Nodes necessary for Automaton's parsing
	 * @return The weighted sum
	 */
	public int getVerts(){
		int res;
		if(value=='|' || value=='*')
			res = 2;
		else
			res = 1;
		if (this.left!=null)
			res+=this.left.getVerts();
		if (this.right!=null)
			res+=this.right.getVerts();
		return res;
	}
	private Pair<Tree,Integer> parseExpression(String p, int j){
		Tree L = null;
		while(j<p.length()){
			if(p.charAt(j)=='('){
				Pair<Tree,Integer> a = parseExpression(p,j+1);
				j = a.second();
				j++;
				if (L!=null)
					L = new Tree('.',L,a.first());
				else
					L = a.first();

			} else if(p.charAt(j)=='|'){
				Pair<Tree,Integer> a = parseExpression(p,j+1);
				j = a.second();
				L = new Tree('|',L,a.first());

			} else if(p.charAt(j)=='*'){
				j++;
				L = new Tree('*',L,null);

			} else if(p.charAt(j)==')'){
				return new Pair<Tree,Integer>(L,j);

			} else{
				Tree R = new Tree(p.charAt(j),null,null);
				j++;
				if (L!=null)
					L = new Tree('.',L,R);
				else
					L = R;
			}
		}
	return new Pair<Tree,Integer>(L,j);
	}
}