package oop.ex4.data_structures;

import java.util.Iterator;

/**
 * this class represents an Avl tree
 */
public class AvlTree implements Iterable<Integer>{

	/**
	 * the root node of the tree
	 */
	private AvlNode root;
	private int size;

	/**
	 * The default constructor.
	 */
	public AvlTree() {
		size = 0;
	}

	/**
	 * A copy constructor that creates a deep copy of the given AvlTree. The new tree contains all the values
	 * of the given tree, but not necessarily in the same structure.	 *
	 *
	 * @param tree The AVL tree to be copied.
	 */
	public AvlTree(AvlTree tree) {
		size = 0;
		if (tree != null && tree.root != null) {
			Iterator<Integer> iterator = tree.iterator();
			while (iterator.hasNext())
				add(iterator.next());
			add(iterator.next());
		} else
			root = null;
	}

	/**
	 * A constructor that builds a new AVL tree containing all unique values in the input array.
	 *
	 * @param data the values to add to tree.
	 */
	public AvlTree(int[] data) {
		size = 0;
		if (data != null) {
			for (int num : data)
				this.add(num);
		} else
			root = null;
	}

	/**
	 * Calculates the minimum number of nodes in an AVL tree of height h.
	 *
	 * @param h the height of the tree (a non-negative number) in question.
	 * @return the minimum number of nodes in an AVL tree of the given height.
	 */
	public static int findMinNodes(int h) {
		int height = 0;
		int minHeightMinusOne = 0;
		int minHeight = 1;
		while (height < h) {
			int temp = minHeight + minHeightMinusOne + 1;
			minHeightMinusOne = minHeight;
			minHeight = temp;
			height++;
		}
		return minHeight;
	}

	/**
	 * Calculates the maximum number of nodes in an AVL tree of height h.
	 *
	 * @param h the height of the tree (a non-negative number) in question.
	 * @return the maximum number of nodes in an AVL tree of the given height.
	 */
	public static int findMaxNodes(int h) {
		return (int) Math.pow(2, h + 1) - 1;
	}

	/**
	 * Add a new node with the given key to the tree.
	 *
	 * @param newValue the value of the new node to add.
	 * @return true if the value to add is not already in the tree and it was successfully
	 * added, false otherwise.
	 */
	public boolean add(int newValue) {
		if (contains(newValue) != -1) {
			return false;
		}
		size++;
		if (root == null) {
			root = new AvlNode(newValue, null);
			return true;
		}
		add(root, newValue);
		return true;
	}

	// This method is the recursive part of the add method, it finds the place to add the new value and
	// then makes sure the tree is kept a correct AvlTree.
	private void add(AvlNode node, int newValue) {
		AvlNode parent = node;
		boolean wentLeft = node.getData() > newValue;
		node = wentLeft ? node.getLeft() : node.getRight();
		if (node == null) {
			if (wentLeft)
				parent.setLeft(new AvlNode(newValue, parent));
			else
				parent.setRight(new AvlNode(newValue, parent));

			reheightTree(root);
			rebalance(parent);
			return;
		}
		add(node, newValue);
	}

	/**
	 * Check whether the tree contains the given input value.
	 *
	 * @param searchVal value to search for
	 * @return if val is found in the tree, return the depth of the node (0 for the root) with the given
	 * value if it was found in the tree, -1 otherwise.
	 */
	public int contains(int searchVal) {
		if (root == null)
			return -1;
		return contains(root, searchVal, 0);
	}

	// This method is the recursive part of the contains method, searches value recursively on the tree.
	private int contains(AvlNode node, int searchVal, int depth) {
		if (node == null)
			return -1;
		if (node.getData() == searchVal)
			return depth;
		return (node.getData() > searchVal) ? contains(node.getLeft(), searchVal, depth + 1) : contains
				(node.getRight(), searchVal, depth + 1);
	}

	/**
	 * Removes the node with the given value from the tree, if it exists.
	 *
	 * @param toDelete the value to remove from the tree.
	 * @return true if the given value was found and deleted, false otherwise.
	 */
	public boolean delete(int toDelete) {
		if (contains(toDelete) == -1) {
			return false;
		}
		size--;
		AvlNode n = root;
		return delete(root, toDelete);
	}

	// This method is the recursive part of the delete method, searches the node recursively on the tree.
	private boolean delete(AvlNode node, int toDelete) {
		if (node == null)
			return false;
		if (node.getData() == toDelete)
			return delete(node);
		return (node.getData() > toDelete) ? delete(node.getLeft(), toDelete) : delete(node.getRight(),
				toDelete);
	}

	// This method is here to make sure the father-son relations are kept correct after delete, and
	// re-balances the tree from the place it was changed (where node was deleted). returns true always
	// because it is the case the item we want to delete was found in the tree.
	private boolean delete(AvlNode node) {
		if (node.getLeft() == null && node.getRight() == null) {
			//case of no children
			if (node.getFather() == null) {
				root = null;
			} else {
				AvlNode parent = node.getFather();
				if (parent.getLeft() == node) {
					parent.setLeft(null);
				} else {
					parent.setRight(null);
				}
				reheightTree(root);
				rebalance(parent);
			}
		} else {
			if (node.getRight() != null) {
				// case of right child or two children
				AvlNode child = node.getRight();
				while (child.getLeft() != null) child = child.getLeft();
				node.setData(child.getData());
				delete(child);
			} else {
				// case of left child
				node.getLeft().setFather(node.getFather());
				if (node.getFather() != null){
					if (node.getFather().getLeft() == node){
						node.getFather().setLeft(node.getLeft());
					}
					else{
						node.getFather().setRight(node.getLeft());
					}
				}
			}
		}
		return true;
	}

	/**
	 * @return the number of nodes in the tree.
	 */
	public int size() {
		return size;
	}

	/**
	 * Returns an iterator over elements of type {@code T}.
	 *
	 * @return an iterator for the Avl Tree. The returned iterator iterates over the tree nodes in an
	 * ascending order, and does NOT implement the remove() method.
	 */
	public Iterator<Integer> iterator() {
		if (root == null) {
			return new AvlTreeIntegerIterator(null);
		}
		return new AvlTreeIntegerIterator(root.smallestInTree());
	}

	/**
	 * rebalances the tree after insertion / delete
	 *
	 * @param node the node which is unbalanced
	 */
	private void rebalance(AvlNode node) {

		if (node.getBalance() == -2) {
			if (height(node.getLeft().getLeft()) >= height(node.getLeft().getRight()))
				node = rotateRight(node);
			else
				node = rotateLeftThenRight(node);

		} else if (node.getBalance() == 2) {
			if (height(node.getRight().getRight()) >= height(node.getRight().getLeft()))
				node = rotateLeft(node);
			else
				node = rotateRightThenLeft(node);
		}

		if (node.getFather() != null) {
			rebalance(node.getFather());
		} else {
			root = node;
		}
	}

	// This method performs left rotation in the tree given, used internally in re-balance, to perform the
	// rotate it changes, one-by-one the left, right, parent references to change the graph of the tree
	// according to the way we learned to perform the rotateLeft in DaSt.
	private AvlNode rotateLeft(AvlNode node) {
		if(node.getData() == 2)
			System.out.print("");
		AvlNode newFather = node.getRight();
		newFather.setFather(node.getFather());
		node.setRight(newFather.getLeft());

		if (node.getRight() != null)
			node.getRight().setFather(node);

		newFather.setLeft(node);
		node.setFather(newFather);

		if (newFather.getFather() != null) {
			if (newFather.getFather().getRight() == node)
				newFather.getFather().setRight(newFather);
			else
				newFather.getFather().setLeft(newFather);
		}

		while(node != null){
			node.setHeight();
			node.setBalance();
			node = node.getFather();
		}

		return newFather;
	}

	// This method performs right rotation in the tree given, used internally in re-balance, to perform the
	// rotate it changes, one-by-one the left, right, parent references to change the graph of the tree
	// according to the way we learned to perform the rotateRight in DaSt.
	private AvlNode rotateRight(AvlNode node) {
		AvlNode newFather = node.getLeft();
		newFather.setFather(node.getFather());
		node.setLeft(newFather.getRight());

		if (node.getLeft() != null)
			node.getLeft().setFather(node);

		newFather.setRight(node);
		node.setFather(newFather);

		if (newFather.getFather() != null) {
			if (newFather.getFather().getRight() == node)
				newFather.getFather().setRight(newFather);
			else
				newFather.getFather().setLeft(newFather);
		}

		while(node != null){
			node.setHeight();
			node.setBalance();
			node = node.getFather();
		}

		return newFather;
	}

	// This method performs left rotation then right rotation in the tree given, used internally in re-balance
	private AvlNode rotateLeftThenRight(AvlNode node) {
		node.setLeft(rotateLeft(node.getLeft()));
		return rotateRight(node);
	}

	// This method performs right rotation then left rotation in the tree given, used internally in re-balance
	private AvlNode rotateRightThenLeft(AvlNode node) {
		node.setRight(rotateRight(node.getRight()));
		return rotateLeft(node);
	}

	/**
	 * @param node a certian node in the tree
	 * @return returns the height of a node, -1 if the node is null
	 */
	private int height(AvlNode node) {
		if (node == null)
			return -1;
		return node.getHeight();
	}

	/**
	 * goes over the tree and fixes the heights of the nodes
	 *
	 * @param node the root of the tree
	 */
	private void reheightTree(AvlNode node) {
		if (node != null) {
			if (isLeaf(node)) {
				node.setHeight(0);
				node.setBalance(0);
			}
			reheightTree(node.getLeft());
			reheightTree(node.getRight());
			node.setHeight();
			node.setBalance();
		}
	}

	/**
	 * @param node a node to check
	 * @return true iff node has no sons.
	 */
	private boolean isLeaf(AvlNode node) {
		return node.getLeft() == null && node.getRight() == null;
	}

	@Override
	public String toString() {
		return "AvlTree{" + root + '}';
	}
}
