
public class MyLinkedList<AnyType> implements Iterable<AnyType> {
	/**
	 * Construct an empty LinkedList.
	 */
	public MyLinkedList() {
		doClear();
	}

	@SuppressWarnings("unused")
	private void clear() {
		doClear();
	}

	/**
	 * Change the size of this collection to zero.
	 */
	public void doClear() {
		beginMarker = new Node<AnyType>(null, null, null);
		endMarker = new Node<AnyType>(null, beginMarker, null);
		beginMarker.next = endMarker;

		theSize = 0;
		modCount++;
	}

	/**
	 * Returns the number of items in this collection.
	 * 
	 * @return the number of items in this collection.
	 */
	public int size() {

		return theSize;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Adds an item to this collection, at the end.
	 * 
	 * @param x
	 *            any object.
	 * @return true.
	 */
	public boolean add(AnyType x) {
		add(size(), x);
		return true;
	}

	/**
	 * Adds an item to this collection, at specified position. Items at or after
	 * that position are slid one position higher.
	 * 
	 * @param x
	 *            any object.
	 * @param idx
	 *            position to add at.
	 * @throws IndexOutOfBoundsException
	 *             if idx is not between 0 and size(), inclusive.
	 */
	public void add(int idx, AnyType x) {
		addBefore(getNode(idx, 0, size()), x);
	}

	/**
	 * Adds an item to this collection, at specified position p. Items at or
	 * after that position are slid one position higher.
	 * 
	 * @param p
	 *            Node to add before.
	 * @param x
	 *            any object.
	 * @throws IndexOutOfBoundsException
	 *             if idx is not between 0 and size(), inclusive.
	 */
	private void addBefore(Node<AnyType> p, AnyType x) {

		Node<AnyType> newNode = new Node<AnyType>(x, p.prev, p);
		newNode.prev.next = newNode;
		p.prev = newNode;
		theSize++;
		modCount++;
	}

	/**
	 * Returns the item at position idx.
	 * 
	 * @param idx
	 *            the index to search in.
	 * @throws IndexOutOfBoundsException
	 *             if index is out of range.
	 */
	public AnyType get(int idx) {
		return getNode(idx).data;
	}

	/**
	 * Changes the item at position idx.
	 * 
	 * @param idx
	 *            the index to change.
	 * @param newVal
	 *            the new value.
	 * @return the old value.
	 * @throws IndexOutOfBoundsException
	 *             if index is out of range.
	 */
	public AnyType set(int idx, AnyType newVal) {
		Node<AnyType> p = getNode(idx);
		AnyType oldVal = p.data;

		p.data = newVal;
		return oldVal;
	}

	/**
	 * Gets the Node at position idx, which must range from 0 to size( ) - 1.
	 * 
	 * @param idx
	 *            index to search at.
	 * @return internal node corresponding to idx.
	 * @throws IndexOutOfBoundsException
	 *             if idx is not between 0 and size( ) - 1, inclusive.
	 */
	private Node<AnyType> getNode(int idx) {
		return getNode(idx, 0, size() - 1);
	}

	/**
	 * Gets the Node at position idx, which must range from lower to upper.
	 * 
	 * @param idx
	 *            index to search at.
	 * @param lower
	 *            lowest valid index.
	 * @param upper
	 *            highest valid index.
	 * @return internal node corresponding to idx.
	 * @throws IndexOutOfBoundsException
	 *             if idx is not between lower and upper, inclusive.
	 */
	private Node<AnyType> getNode(int idx, int lower, int upper) {
		Node<AnyType> p;

		if (idx < lower || idx > upper)
			throw new IndexOutOfBoundsException("getNode index: " + idx
					+ "; size: " + size());

		if (idx < size() / 2) {
			p = beginMarker.next;
			for (int i = 0; i < idx; i++)
				p = p.next;

		} else {
			p = endMarker;
			for (int i = size(); i > idx; i--)
				p = p.prev;

		}

		return p;
	}

	/**
	 * Removes an item from this collection.
	 * 
	 * @param idx
	 *            the index of the object.
	 * @return the item was removed from the collection.
	 */
	public AnyType remove(int idx) {
		return remove(getNode(idx));
	}

	public void Swap(int idx1, int idx2) {
		data1 = get(idx1 - 1);
		data2 = get(idx2 - 1);
		dummy = data1;
		data1 = data2;
		data2 = dummy;
		set(idx1 - 1, data1);
		set(idx2 - 1, data2);
	}

	public void shift(int value)

	{
		if (value > 0) {
			for (int i = 0; i < value; i++) {

				Node<AnyType> lastNode = endMarker.prev;
				Node<AnyType> firstNode = beginMarker.next;

				endMarker.prev.prev.next = endMarker;
				endMarker.prev = endMarker.prev.prev;

				beginMarker.next = lastNode;
				lastNode.prev = beginMarker;

				lastNode.next = firstNode;
				firstNode.prev = lastNode;

			}
		} else {
			value = -(value);
			for (int i = 0; i < value; i++) {
				Node<AnyType> lastNode = endMarker.prev;
				Node<AnyType> firstNode = beginMarker.next;

				beginMarker.next = beginMarker.next.next;
				beginMarker.next.next.prev = beginMarker;

				endMarker.prev = firstNode;
				firstNode.next = endMarker;

				lastNode.next = firstNode;
				firstNode.prev = lastNode;
			}
		}
	}

	public void erase(int idx, int N) {

		Node<AnyType> firstNode = getNode(idx).prev;
		Node<AnyType> lastNode = getNode(idx + N - 1).next; // to avoid index out of bound. can get endMarker instead
		firstNode.next = lastNode;
		lastNode.prev = firstNode;
		theSize -= N;

	}

	public void insert(MyLinkedList<AnyType> sec, int idx) {
		Node<AnyType> newNode = getNode(idx).prev;
		Node<AnyType> newNodeNext = newNode.next;

		newNode.next = sec.beginMarker.next;
		newNode.next.prev = newNode;
		newNodeNext.prev = sec.endMarker.prev;
		sec.endMarker.prev.next = newNodeNext;
		theSize += sec.size();
		sec.doClear();
	}

	/**
	 * Removes the object contained in Node p.
	 * 
	 * @param p
	 *            the Node containing the object.
	 * @return the item was removed from the collection.
	 */
	private AnyType remove(Node<AnyType> p) {
		p.next.prev = p.prev;
		p.prev.next = p.next;
		theSize--;
		modCount++;

		return p.data;
	}

	/**
	 * Returns a String representation of this collection.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder("[ ");

		for (AnyType x : this)
			sb.append(x + " ");
		sb.append("]");

		return new String(sb);
	}

	/**
	 * Obtains an Iterator object used to traverse the collection.
	 * 
	 * @return an iterator positioned prior to the first element.
	 */
	public java.util.Iterator<AnyType> iterator() {
		return new LinkedListIterator();
	}

	/**
	 * This is the implementation of the LinkedListIterator. It maintains a
	 * notion of a current position and of course the implicit reference to the
	 * MyLinkedList.
	 */
	private class LinkedListIterator implements java.util.Iterator<AnyType> {
		private Node<AnyType> current = beginMarker.next;

		private int expectedModCount = modCount;

		private boolean okToRemove = false;

		public boolean hasNext() {
			return current != endMarker;
		}

		public AnyType next() {
			if (modCount != expectedModCount)
				throw new java.util.ConcurrentModificationException();
			if (!hasNext())
				throw new java.util.NoSuchElementException();

			AnyType nextItem = current.data;
			current = current.next;
			okToRemove = true;
			return nextItem;
		}

		public void remove() {
			if (modCount != expectedModCount)
				throw new java.util.ConcurrentModificationException();
			if (!okToRemove)
				throw new IllegalStateException();

			MyLinkedList.this.remove(current.prev);
			expectedModCount++;
			okToRemove = false;
		}
	}

	/**
	 * This is the doubly-linked list node.
	 */
	private static class Node<AnyType> {
		public Node(AnyType d, Node<AnyType> p, Node<AnyType> n) {
			data = d;
			prev = p;
			next = n;
		}

		public AnyType data;

		public Node<AnyType> prev;

		public Node<AnyType> next;
	}

	public AnyType data1;

	public AnyType data2;

	public AnyType dummy;

	private int theSize;

	private int modCount = 0;

	private Node<AnyType> beginMarker;

	private Node<AnyType> endMarker;

	public static void main(String[] args) 
	{
		MyLinkedList<Integer> lst = new MyLinkedList<Integer>();
		MyLinkedList<Integer> sec = new MyLinkedList<Integer>();

		for (int i = 0; i < 10; i++)
			lst.add(i);

		for (int i = 30; i < 35; i++)
			sec.add(i);

		System.out.println("Original LinkedList 1:" + lst); // print first Linked List
															 

		lst.Swap(5, 8); // Swap positions of two nodes
		System.out.println("Swapp 5th and 8th element:" + lst);// print first LinkedList
																	
																	 

		lst.shift(5); // shifts list to 5 positions forward
		System.out.println("Shift five positions forward:" + lst);// Print
																	// first
																	// LinkedList

		lst.shift(-5); // shifts list to 5 positions forward
		System.out.println("Shift five positions backward:" + lst);// Print
																		// first
																		// LinkedList

		lst.erase(4, 5); // Erase five elements after position four of LinkedList
		System.out.println("Erase five elements after 4th position:" + lst); // Print first LinkedList

		System.out.println("Second LinkedList Original:"+sec); // print second Linked List

		lst.insert(sec, 3); // Insert second list after 3rd index position of first List
							
		System.out.println("Insert second linkedlist after 3rd position of 1st:"+lst); // print first Linked List
		System.out.println("Empty LinkedList"+sec); // print second Linked List

	}
}

class TestLinkedList {
	public static void main(String[] args) {
		MyLinkedList<Integer> lst = new MyLinkedList<Integer>();

		for (int i = 0; i < 10; i++)
			lst.add(i);
		for (int i = 20; i < 30; i++)
			lst.add(0, i);

		java.util.Iterator<Integer> itr = lst.iterator();
		while (itr.hasNext()) {
			itr.next();
			itr.remove();
			System.out.println(lst);
		}
	}
}
