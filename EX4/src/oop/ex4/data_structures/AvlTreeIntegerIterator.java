package oop.ex4.data_structures;


import java.util.Iterator;

/**
 * this class represents an iterator which goes over an AvlTree.
 */
public class AvlTreeIntegerIterator implements Iterator<Integer> {

	/**
	 * the current item of the iterator
	 */
	private AvlNode current;

	/**
	 * constracts a new iterator
	 *
	 * @param node the current item of the iterator
	 */
	public AvlTreeIntegerIterator(AvlNode node) {
		current = node;
	}

	/**
	 * @return true iff the iterator has another value to return
	 */
	public boolean hasNext() {
		if (current == null)
			return false;
		if (current.getRight() != null)
			return true;
		AvlNode father = current.getFather();
		AvlNode son = current;
		while (father != null) {
			if (father.getRight() != son)
				return true;
			son = father;
			father = father.getFather();
		}
		return false;
	}

	/**
	 * @return the next value of the iterator
	 */
	public Integer next() throws java.util.NoSuchElementException {
		if (current == null) {
			throw new java.util.NoSuchElementException();
		}
		if (hasNext()) {
			int data = current.getData();
			if (current.getRight() == null) {
				AvlNode father = current.getFather();
				AvlNode son = current;
				while (father.getRight() == son) {
					son = father;
					father = father.getFather();
				}
				current = father;
			} else {
				current = current.getRight().smallestInTree();
			}
			return data;
		}
		int data = current.getData();
		current = null;
		return data;
	}

	/**
	 * this method has to be implemented, because it is part of the interface Iterator.
	 * the method only throws an exception.
	 *
	 * @throws UnsupportedOperationException This 'remove' is not supported in this specific iterator
	 *                                       implementation for a Binary Tree
	 */
	public void remove() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

}
