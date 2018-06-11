package oop.ex4.data_structures;

/**
 * this class implements the general binary tree node, to be extended by the avl node
 */
public class BSNode {
	/**
	 * the value of the node
	 */
	private int data;

	/**
	 * the left son of the node
	 */
	private AvlNode left;

	/**
	 * the right son of the node
	 */
	private AvlNode right;

	public BSNode(int data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}

	/**
	 * @return the data of the node
	 */
	public int getData() {
		return data;
	}

	/**
	 * set the data of the node
	 *
	 * @param data the new data
	 */
	public void setData(int data) {
		this.data = data;
	}

	/**
	 * @return the left son of the node
	 */
	public AvlNode getLeft() {
		return left;
	}

	/**
	 * sets the left son of the node
	 *
	 * @param left the new left son
	 */
	public void setLeft(AvlNode left) {
		this.left = left;
	}

	/**
	 * @return the right son of the node
	 */
	public AvlNode getRight() {
		return right;
	}

	/**
	 * sets the right son of the node
	 *
	 * @param right the new right son
	 */
	public void setRight(AvlNode right) {
		this.right = right;
	}


	/**
	 * @return the height of the left son if it is not null, otherwise -1
	 */
	public int getLeftHeight() {
		if (left != null) {
			return left.getHeight();
		}
		return -1;
	}

	/**
	 * @return the height of the right son if it is not null, otherwise -1
	 */
	public int getRightHeight() {
		if (right != null) {
			return right.getHeight();
		}
		return -1;
	}

	@Override
	public String toString() {
		return data + "[" + ((left != null) ? left.toString() : "") + "," + ((right != null) ?
				right.toString() : "") +
				"]";
	}


}
