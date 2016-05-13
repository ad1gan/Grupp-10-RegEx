/**
 * This is the class Tree, which represents a binary Tree.
 * 
 * @author adrian
 *
 */
public class Tree{
	private NodeTree root;
	
	/**
	 * Create an empty Tree
	 */
	public Tree()
	{
		root = null;
	}
	
	/**
	 * Create a new Tree with the root's value
	 * @param key The value of the Tree
	 */
	public Tree(String key) {
		root = new NodeTree(null, key, null);
	}
	
	/**
	 * Create a new Tree with two subtrees
	 * @param left The left Subtree
	 * @param key The given value
	 * @param right The right Subtree
	 */
	public Tree(Tree left, String key, Tree right) {
		root = new NodeTree(left.root, key, right.root);
	}
	
	/**
	 * 
	 * @return The given value
	 */
	public String getKey() {
		return root.getKey();
	}
	
	/**
	 * 
	 * @return the left subtree
	 */
	public Tree getLeft() {
		Tree temp = new Tree();
		temp.root = root.getLeft();
		return temp;
	}
	
	/**
	 * 
	 * @return thr right subtree
	 */
	public Tree getRight() {
		Tree temp = new Tree();
		temp.root = root.getRight();
		return temp;
	}
	
	/**
	 * 
	 * @return the result of an empty tree
	 */
	public boolean isEmpty() {
		return root == null;
	}
	
	/**
	 * Compute the size of the tree (# Nodes)
	 * @return the size of the tree
	 */
	public int getSize() {
		if(root == null)
			return 0;
		else 
			return root.getSize();
	}
	
	
}
