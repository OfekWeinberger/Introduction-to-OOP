package oop.ex4.data_structures;

/**
 * this class represents an Avl node
 */
public class AvlNode extends BSNode {


	/**
	 * the difference between the height of the right son of the node and the
	 * height of the left son
	 */
	private int balance;
	/**
	 * the height of the node
	 */
	private int height;

	/**
	 * the father of the node
	 */
	private AvlNode father;

	/**
	 * creates a new Avl node
	 *
	 * @param data   the data of the node
	 * @param father the father of the node
	 */
	AvlNode(int data, AvlNode father) {
		super(data);
		this.father = father;
	}

	/**
	 * @return the balance factor of the node
	 */
	public int getBalance() {
		return balance;
	}

	/**
	 * set the balance factor of the node
	 *
	 * @param balance the new balance factor
	 */
	public void setBalance(int balance) {
		this.balance = balance;
	}

	/**
	 * sets the balance of the node to be the height of the right son minus the height of the left son
	 */
	void setBalance() {
		balance = getRightHeight() - getLeftHeight();
	}

	/**
	 * @return the height of the node
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * sets the height of the node
	 *
	 * @param height the new height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * sets the height of the node to be the maximum height between its sons plus one
	 */
	void setHeight() {
		height = 1 + Math.max(getLeftHeight(), getRightHeight());
	}

	/**
	 * @return the father of the node
	 */
	public AvlNode getFather() {
		return father;
	}

	/**
	 * sets the father of the node
	 *
	 * @param father the new father
	 */
	public void setFather(AvlNode father) {
		this.father = father;
	}

	/**
	 * @return the node with the smallest value in the tree whose root is the node
	 */
	public AvlNode smallestInTree() {
		AvlNode node = this;
		while (node.getLeft() != null) {
			node = node.getLeft();
		}
		return node;
	}

}
