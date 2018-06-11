// there is a problem here!!!!!!!
//package oop.ex4.data_structures;
package oop.ex4.data_structures;

public class main {
	public static void main(String[] args) {
		AvlTree tree = new AvlTree();
		for (int i = 1; i <= 10; i++) {
			tree.add(i);
		}
		System.out.println(tree);
		System.out.println("size: " + tree.size());
		for (int i = 1; i < 15/2; i++) {
			tree.delete(i*2);
			System.out.println("del=" + i*2);
			System.out.println(tree);
		}
		System.out.println(tree);
		System.out.println("size: " + tree.size());

	}
}
