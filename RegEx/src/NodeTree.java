/**
 * This is the class Node, which represents a Node in 
 * a binary tree. 
 * @author Adrian Kolb 
 *
 */
public class NodeTree {
	private NodeTree left;
	private NodeTree right;
	private NodeTree parent;
	
	private String key;
	
	/**
	 * 
	 * @param left left child node
	 * @param key the value of the node
	 * @param right right child node
	 */
	public NodeTree(NodeTree left, String key, NodeTree right) {
		this.left = left;
		this.right = right;
		this.key = key;
	}

	/**
	 * 
	 * @return left child node
	 */
	public NodeTree getLeft() {
		return left;
	}

	/**
	 * 
	 * @return right child node
	 */
	public NodeTree getRight() {
		return right;
	}

	/**
	 * 
	 * @return parent node
	 */
	public NodeTree getParent() {
		return parent;
	}
	
	/**
	 * 
	 * @return the value of the node
	 */
	public String getKey() {
		return key;
	}
	
	/**
	 * The size of the left and right child node
	 * @return The size
	 */
	public int getSize() {
		int sizeLeft = 0;
		int sizeRight = 0;
		if(left != null)
			sizeLeft = left.getSize();
		if(right != null)
			sizeRight = right.getSize();
		
		return 1 + sizeLeft + sizeRight;
	}
	
}
