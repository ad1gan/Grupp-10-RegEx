public class Tree{
	private char value;
	private Tree left;
	private Tree right;

	public Tree(String p){
		TreePair a = parseExpression(p,0);
		value = a.first().value;
		left  = a.first().left;
		right = a.first().right;
	}
	public Tree(){}
	public Tree(char c, Tree l, Tree r){
		value = c;
		left  = l;
		right = r;
	}
	public Tree getLeft(){
		return left;
	}
	public Tree getRight(){
		return right;
	}
	public char getValue(){
		return value;
	}
	public void setLeft(Tree t){
		left = t;
	}
	public void setRight(Tree t){
		left = t;
	}
	public void setValue(char c){
		value = c;
	}
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
	private TreePair parseExpression(String p, int j){
		Tree L = null;
		while(j<p.length()){

			if(p.charAt(j)=='('){
				TreePair a = parseExpression(p,j+1);
				j = a.second();
				j++;
				if (L!=null)
					L = new Tree('.',L,a.first());
				else
					L = a.first();

			} else if(p.charAt(j)=='|'){
				TreePair a = parseExpression(p,j+1);
				j = a.second();
				L = new Tree('|',L,a.first());

			} else if(p.charAt(j)=='*'){
				j++;
				L = new Tree('*',L,null);

			} else if(p.charAt(j)==')'){
				return new TreePair(L,j);

			} else{
				Tree R = new Tree(p.charAt(j),null,null);
				j++;
				if (L!=null)
					L = new Tree('.',L,R);
				else
					L = R;
			}

		}
	return new TreePair(L,j);
	}

	public String toString(){

		if( (this.left != null) && (this.right != null) ){
			return this.left.toString() + "," + this.value +
			", " + this.right.toString();
		}
		else if( (this.left != null) && (this.right == null) ){
			return this.left.toString() + "," + this.value;
		}
		else if( (this.left == null) && (this.right != null) ){
			return "" + this.value + "," + this.right.toString();
		}
		else{
			return "" + this.value;
		}	
	}

	public void cout(){
		System.out.println(this.toString());
	}
}