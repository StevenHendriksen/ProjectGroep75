package ss.week4;

/**
 * linkedList, a linked list
 * @author Stan Peters en Steven Hendriksen
 * @version $version: 1.0 $
 */

import ss.week4.DoublyLinkedList.Node;

public class LinkedList<Element> {

	 // ------------------ Instance variables ----------------
	
	private /* @ spec_public @ */ int size;
	private Node first;

	 // ------------------ Constructor ------------------------
	
    /**
     * Constructs an empty linked list.
     */	
	
	// @ ensures \result.size == 0;
	public LinkedList() {
		size = 0;
		first = null;
	}
	
    // ------------------ Commands --------------------------
    /**
     * Adds a new node with an index and an element.
     */
	public void add(int index, Element element) {
		Node newNode = new Node(element);
		if (index == 0) {
			newNode.next = first;
			first = newNode;
		} else {
			Node p = getNode(index - 1);
			newNode.next = p.next;
			p.next = newNode;
		}
		size = size + 1;
	}
	
    /**
     * Removes the first node with element.
     */

	// @ ensures this.size == \old(size) - 1;
	public void remove(Element element) {
		Node nextNode = null;
		Node currentNode = findBefore(element).next;
		Node prevNode = findBefore(element);
		if(currentNode != null && currentNode.next != null && currentNode.next.next != null){
			nextNode = currentNode.next;
		}
		if (nextNode != null)
			findBefore(element).next = prevNode;
			first = first.next;
		if (prevNode != null)
			prevNode.next = nextNode;
		size--;

	}
	
    // ------------------ Queries --------------------------
	
    /**
     * Returns the node before the node with the element.
     */

	public Node findBefore(Element element) {
		Node currentNode = first;
		if (first.getElement() == element) {
			return null;
		}
		while (currentNode.next != null) {
			if (currentNode.next.getElement() == element) {
				return currentNode;
			}
			currentNode = currentNode.next;

		}
		return null;
	}

	/**
     * Returns the element at given index.
     */
	
	// @ requires 0 <= index && index < this.size();
	public Element get(int index) {
		Node p = getNode(index);
		return p.element;
	}

	/**
     * Returns the Node at given index.
     */
	
	// @ requires 0 <= i && i < this.size();
	private /* @ pure @ */ Node getNode(int i) {
		Node p = first;
		int pos = 0;
		while (pos != i) {
			p = p.next;
			pos = pos + 1;
		}
		return p;
	}

	/**
     * Returns size of the Node;
     */
	
	// @ ensures \result >= 0;
	public /* @ pure @ */ int size() {
		return size;
	}

	public class Node {
		private Element element;
		public Node next;

		public Node(Element element) {
			this.element = element;
			this.next = null;
		}

		public Element getElement() {
			return element;
		}
	}
}
