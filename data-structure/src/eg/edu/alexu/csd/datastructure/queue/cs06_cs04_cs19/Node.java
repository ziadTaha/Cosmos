package eg.edu.alexu.csd.datastructure.queue.cs06_cs04_cs19;

/**
 *
 * @author ziad_mostafa
 *
 */
public class Node {
	/**
	 * @serialField element
	 */
	private Object element;
	/**
	 * @serialField next
	 */
	private Node next;

	/**
	 *
	 * @param o
	 *            object
	 * @param n
	 *            node
	 */
	public Node(final Object o, final Node n) {
		element = o;
		next = n;
	}

	/**
	 *
	 * @return element
	 */
	public Object getElement() {
		return element;
	}

	/**
	 *
	 * @return node
	 */
	public Node getNext() {
		return next;
	}

	/**
	 *
	 * @param newEle
	 *            the new set element
	 */
	public void setElement(final Object newEle) {
		element = newEle;
	}

	/**
	 *
	 * @param newNode
	 *            the new set node
	 */
	public void setNext(final Node newNode) {
		next = newNode;
	}
}
