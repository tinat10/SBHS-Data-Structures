public class BinarySearchTree<E extends Comparable<E>> {

	private TreeNode<E> root;
	private int size;
	private String st= ""; //traversal

	public BinarySearchTree() {
		root = null;
		size = 0;
	}

	public void add(E val) {
		TreeNode<E> newNode = new TreeNode(val);
		if (root == null) {
			root = newNode;
			size++;
		}
		else
			add(root, val);
	}

	public void add(TreeNode<E> curr, E value) {
		int comp = value.compareTo(curr.value);
		if (comp == 0) {}
		else if (comp < 0) {
			if (curr.left == null) { //curr.left.value == null
				TreeNode<E> newNode = new TreeNode(value);
				curr.left = newNode;
				size++;
			}
			else {
				add(curr.left, value);
			}
		}
		else {
			if (curr.right == null) { //curr.right.value == null
				TreeNode<E> newNode = new TreeNode(value);
				curr.right = newNode;
				size++;
			}
			else {
				add(curr.right, value);
			}
		}
		//if equal, do nothing and return
		// if newnode < current Go left
			// if left null add node
			//if not null recurse left
		// if newnode > current (do above except with right)

	}

	public String inOrder() {
		if (size==0)
			return "[]";
		else {
			st = "[";
			inOrder(root);
			st = st.substring(0, st.length()-2);
			return st + "]";
		}
		/*handle empty BST OR
		recursively call private inOrde rmethod for nonEmpty
		use recursive alls to assemble the String st (declared in header) */
	}

	private String inOrder(TreeNode<E> curr) {
		// recursively traverse Tree in order and add to String
		if (curr != null)
		{
			inOrder(curr.left);
			st+=String.valueOf(curr.value) + ", ";
			inOrder(curr.right);
		}

		return st;
	}

	public String preOrder() {
		if (size==0)
			return "[]";
		else {
			st = "[";
			preOrder(root);
			st = st.substring(0, st.length()-2);
			return st + "]";
		}
	}

	private String preOrder(TreeNode<E> curr) {

		if (curr != null)
		{
			st+=curr.value + ", ";
			preOrder(curr.left);
			preOrder(curr.right);
		}

		return st;
	}

	public String postOrder() {
		if (size==0)
			return "[]";
		else {
			st = "[";
			postOrder(root);
			st = st.substring(0, st.length()-2);
			return st + "]";
		}
	}

	private String postOrder(TreeNode<E> curr) {

		if (curr != null)
		{
			postOrder(curr.left);
			postOrder(curr.right);
			st+=curr.value + ", ";
		}
		return st;
	}

	public boolean contains(E val) {
		if (size==0)
			return false;
		return contains(root, val);
	}

	private boolean contains(TreeNode<E> node, E val) {

		if (node == null)
			return false;

		int comp = val.compareTo(node.value);
		if (comp==0)
			return true;
		else if (comp<1) {
			return contains(node.left, val);
		}
		else if (comp>1) {
			return contains(node.right, val);
		}

		return false;

	}

	public void remove(E val) {
		if (root == null)
			return;
		if (contains(root, val)) {
			size--;
			root = remove(root, val);
		}
	}

	public TreeNode<E> remove(TreeNode<E> node, E value) {

		if (node == null)
			return null;

		int comp = value.compareTo(node.value);
		if (comp < 0)
			node.left = remove(node.left, value);
		else if (comp > 0)
			node.right = remove(node.right, value);
		else { //if comp == 0
			if (node.left == null && node.right == null) // a leaf
				return null;
			else if (node.left == null && node.right != null) { // one child on right side
				return node.right;
			}
			else if (node.right == null && node.left != null) {  // one child on left side
				return node.left;
			}
			else {// (node.left != null && node.right!= null) // 2 children
				return nextInOrder(root, node);
			}
		}

		return node;

	}

	public TreeNode<E> nextInOrder(TreeNode<E> root, TreeNode<E> node) {

		if (node.right != null) {
			return null;
		}

		TreeNode<E> curr = node;
		curr = node.right;
		while (curr.left != null) {
			curr = curr.left;
		}
		return curr; //START HERE TO FIX

	}

	public void rotateRight() {
		TreeNode formerRoot = root;
		root = root.left;
		formerRoot.left = root.right;
		root.right = formerRoot;
	}

	public void rotateLeft() {
		TreeNode formerRoot = root;
		root = root.right; //root = newRoot now
		formerRoot.right = root.left;
		root.left = formerRoot;
	}

	public int size() {
		return size;
	}

	/**** INNER TreeNode ****/
	private class TreeNode<E extends Comparable<E>> {

		private E value;
		private TreeNode<E> left, right;

		private TreeNode (E val) {
			value = val;
			left = right = null;
		}

		public String toString() {
			return value.toString();
		}

	}
	/**** INNER TreeNode ****/

	/*************   PRINT  **************/
	public void print()
	{
		if (root == null)
			System.out.print("Empty Tree");
		print(root, 0, "");
		System.out.println();
	}

	private void print(TreeNode<E> curr, int depth, String s)
	{
		if (curr == null)
			return;
		for (int i = 1; i <= depth - 1; i++)
			System.out.print("|\t");
		if (depth == 0)
			System.out.println("[" + curr.value + "] <- root (L___ left, R___ right)");
		if (depth > 0)
		{
			System.out.print(s);
			System.out.println(curr.value);
		}
		print(curr.left, depth + 1, "L___"); // indicates left or "less than" side
		print(curr.right, depth + 1, "R___");  // indicate right or "greater than" side
	  }
    /********** END PRINT **********/

	public static void main(String []args) {

		// TEST CODE
		System.out.println("Beginner Tests: ");
		String word = "kaleidoscope";
		BinarySearchTree<Character> bst = new BinarySearchTree<>();
		for (int i = 0; i < word.length(); i++)
			bst.add(word.charAt(i));
		System.out.println("Size => "+bst.size());
		System.out.println("In Order => "+bst.inOrder());
		System.out.println("Pre Order => "+bst.preOrder());
		System.out.println("Post Order => "+bst.postOrder());
		System.out.println("Contains 'i' => "+bst.contains('i'));
		System.out.println("Contains 't' => "+bst.contains('t'));

		///////////////////////////////////////////////////////////////
		System.out.println();
		///////////////////////////////////////////////////////////////

		System.out.println("Removal Tests: ");
		BinarySearchTree<Integer> bTree = new BinarySearchTree<>();
		int[] nums = {45,13,6,77,23,5,54,24,19,99,24,72,17,18};
		for (int i: nums)
		  bTree.add(i);

		bTree.print();
		bTree.remove(99); // Delete a leaf
		bTree.print();
		bTree.remove(17); // Delete a 1-Child Node
		bTree.print();
		bTree.remove(23); // Delete a 2-Child Node
		bTree.print();
		bTree.remove(45); // Delete the root
  		bTree.print();

		///////////////////////////////////////////////////////////////
		System.out.println();
		////////////////////////////////////////////////////////////////////////

		System.out.println("Rotation Tests: ");
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		int[] nums2 = {45,13,6,77,23,5,54,24,19,99,24,72,17,18};
		for (int i: nums2)
		  tree.add(i);

		tree.print();
		tree.rotateRight();
   		tree.print();
   		tree.rotateLeft();
   		tree.print();


	}

}