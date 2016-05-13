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
}
