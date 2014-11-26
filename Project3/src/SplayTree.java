import java.util.LinkedList;

public class SplayTree {

	private Node rootnode = null;

	private static class Node {

		private Node left;
		private Node right;
		private Node parent;
		int value;

		public Node(Node left, int value, Node right, Node parent) {
			this.left = left;
			this.value = value;
			this.right = right;
			this.parent = parent;
		}
	}

	/*
	 * Adds a node to the tree containing the passed integer value. Rotates the
	 * node to the top.
	 */
	public void insert(int val) {
		if (rootnode == null)
			rootnode = new Node(null, val, null, null);
		else
			insert(rootnode, val);
	}

	private void insert(Node node, int value) {

		if (value < node.value) 
		{
			if (node.left != null)
				insert(node.left, value);
			else
				node.left = new Node(null, value, null, node);
		}
		else if (value > node.value)
		{
			if (node.right != null)
				insert(node.right, value);
			else
				node.right = new Node(null, value, null, node);
		}
		find(value); // Finds the node and rotates it to the top
	}

	/*
	 * Returns true if the value passed is in the tree. Rotates the node to the
	 * top if found or the last node accessed if not found.
	 */
	public boolean find(int val) 
	{
		Node y = searchNode(rootnode, val);
		splay(y); 							// Rotates the node to the top
		boolean flag = y.value == val;
		return flag;
	}

	private Node searchNode(Node node, int val) 
	{
		Node z = node;

		
		if (z.value < val)
		{
			if (z.right == null)
				return z;
			else
				return searchNode(z.right, val);
		}
		else if (z.value > val)
		{
			if (z.left == null)
				return z;
			else
				return searchNode(z.left, val);
		}
		else
			return z;

	}

	/*
	 * Returns the count of all of the leaves in the tree. This should be
	 * performed as a recursive function that traverses the tree and counts the
	 * leaves.
	 */
	public int countLeaves() 
	{
		int count = 0;
		return count(rootnode, count);

	}

	private int count(Node node, int count) 
	{
		if (node == null)
			return count;
		if (node.left == null && node.right == null)
			return 1 + count;
		else 
		{
			int leftCount = count(node.left, count);
			int totalCount = count(node.right, leftCount);
			return totalCount;
		}

	}

	/*
	 * Returns the sum of all of the values in the tree. This should be
	 * performed as a recursive function that traverses the tree and sums the
	 * values.
	 */
	public int findSum() 
	{
		int sum = 0;
		return sum(rootnode, sum);
	}

	private int sum(Node node, int sum) 
	{
		if (node == null)
			return sum;

		sum = sum + node.value;

		int leftSum = sum(node.left, sum);
		int totalSum = sum(node.right, leftSum);

		return totalSum;
	}

	/* Returns a string of the values of an In order Traversal of the nodes. */

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Inorder Traversal : ");
		return inorder(rootnode, buffer).toString();
		 
	}

	private StringBuffer inorder(Node node, StringBuffer buffer) {
		
		if (node != null) 
		{
			inorder(node.left, buffer);
			buffer.append(" ");
			buffer.append(node.value);
			inorder(node.right, buffer);

		}

		return buffer;
	}

	/*
	 * Prints the values level-by-level, root first, then its children, then
	 * their children, etc.
	 */
	public void levelOrder() {
		System.out.print("Level-by-Level    : ");
		level(rootnode);
	}

	private void level(Node root) {

		Node z = root;
		LinkedList<Node> q = new LinkedList<Node>();
		q.add(root);

		while (!q.isEmpty()) {
			z = q.remove();
			System.out.print(" " + z.value);
			if (z.left != null) {
				q.add(z.left);
			}
			if (z.right != null) {
				q.add(z.right);
			}

		}
	}
/* Function rotates the node to the top*/	
	private void splay(Node x) {
		while (x.parent != null) {
			Node parent = x.parent;
			Node grandParent = parent.parent;
			if (grandParent == null) {
				if (x == parent.left)
					makeLeftChildParent(x, parent);
				else
					makeRightChildParent(x, parent);
			} else {
				if (x == parent.left) {
					if (parent == grandParent.left) {
						makeLeftChildParent(parent, grandParent);
						makeLeftChildParent(x, parent);
					} else {
						makeLeftChildParent(x, x.parent);
						makeRightChildParent(x, x.parent);
					}
				} else {
					if (parent == grandParent.left) {
						makeRightChildParent(x, x.parent);
						makeLeftChildParent(x, x.parent);
					} else {
						makeRightChildParent(parent, grandParent);
						makeRightChildParent(x, parent);
					}
				}
			}
		}
		rootnode = x;
	}
	/*Function to rotate */
	public void makeLeftChildParent(Node c, Node p) {
		if ((c == null) || (p == null) || (p.left != c) || (c.parent != p))
			throw new RuntimeException("WRONG");

		if (p.parent != null) {
			if (p == p.parent.left)
				p.parent.left = c;
			else
				p.parent.right = c;
		}
		if (c.right != null)
			c.right.parent = p;

		c.parent = p.parent;
		p.parent = c;
		p.left = c.right;
		c.right = p;
	}

	/* Function to rotate */
	public void makeRightChildParent(Node c, Node p) {
		if ((c == null) || (p == null) || (p.right != c) || (c.parent != p))
			throw new RuntimeException("WRONG");
		if (p.parent != null) {
			if (p == p.parent.left)
				p.parent.left = c;
			else
				p.parent.right = c;
		}
		if (c.left != null)
			c.left.parent = p;
		c.parent = p.parent;
		p.parent = c;
		p.right = c.left;
		c.left = p;
	}

/* Main Function*/	
public static void main(String[] args) {

		SplayTree m1 = new SplayTree();
		m1.insert(25);
		m1.insert(15);
	    m1.insert(30);
	    m1.insert(45);
	    m1.insert(28);
	    m1.insert(26);
		m1.insert(50);
	    m1.insert(12);
		m1.insert(17);
		
		System.out.println("Search Result	  : " + m1.find(15));    
		System.out.println("Total Count is	  : " + m1.countLeaves());
		System.out.println("Total Sum is	  : " + m1.findSum());
		System.out.println(m1);
		m1.levelOrder();

	}

}
