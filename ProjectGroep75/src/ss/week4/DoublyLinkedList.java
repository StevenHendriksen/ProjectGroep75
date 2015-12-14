package ss.week4;

/**
 * DoublylinkedList, a double linked list
 * @author Stan Peters en Steven Hendriksen
 * @version $version: 1.0 $
 */

public class DoublyLinkedList<Element> {

	 // ------------------ Instance variables ----------------
	
	private /* @ spec_public @ */ int size;
	private Node head;

	 // ------------------ Constructor ------------------------
    /**
     * Constructs an empty double linked list.
     */
	// @ ensures this.size == 0;
	public DoublyLinkedList() {
		size = 0;
		head = new Node(null);
		head.next = head;
		head.previous = head;
	}

    // ------------------ Commands --------------------------
    /**
     * Adds a new node with an index and an element.
     */
	// @ requires element != null;
	// @ requires 0 <= index && index <= this.size;
	// @ ensures this.size == \old(size) + 1;
	// @ ensures this.getNode(index).equals(element);
	public void add(int index, Element element) {
		Node newNode = new Node(element);
		if (head.element == null) {
			this.head = newNode;
			size++;
		} else if (index == 0) {
			newNode.next = head;
			this.head.previous = newNode;
			this.head = newNode;
			size++;
		} else if (index == size()) {
			Node lastNode = getNode(size() - 1);
			lastNode.next = newNode;
			newNode.previous = lastNode;
			size++;
		} else {
			Node currentNode = getNode(index);
			newNode.previous = currentNode.previous;
			newNode.next = currentNode;
			currentNode.previous.next = newNode;
			currentNode.previous = newNode;
			size++;
		}
	}
    /**
     * Removes a new node with an index and an element.
     */
	// @ requires 0 <= index && index < this.size;
	// @ ensures this.size == \old(size) - 1;
	public void remove(int index) {	
		Node currentNode = getNode(index);
		Node prevNode = currentNode.previous;
		Node nextNode = currentNode.next;
		if (nextNode != null)
			nextNode.previous = prevNode;
		if (prevNode != null)
			prevNode.next = nextNode;
		if(index == 0){
			head = head.next;
		}
		size--;
	}
	
    // ------------------ Queries --------------------------
    /**
     * Returns the element at given index.
     */
	// @ requires 0 <= index && index < this.size;
	/* @ pure */
	public Element get(int index) {
		Node p = getNode(index);
		return p.element;
	}

	/**
	 * The node containing the element with the specified index. The head node
	 * if the specified index is -1.
	 */
	// @ requires -1 <= i && i < this.size;
	// @ pure
	public Node getNode(int i) {
		Node p = head;
		int pos = 0;
		while (pos < i) {
			p = p.next;
			pos = pos + 1;
		}
		return p;
	}
	/**
	 * Returns the size of the doublylinkedlist
	 */
	public int size() {
		return this.size;
	}
	/**
	 * Assigns a new element
	 */
	public class Node {
		public Node(Element element) {
			this.element = element;
			this.next = null;
			this.previous = null;
		}

		private Element element;
		public Node next;
		public Node previous;

		public Element getElement() {
			return element;
		}
	}
}