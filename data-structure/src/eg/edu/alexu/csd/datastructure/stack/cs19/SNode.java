package eg.edu.alexu.csd.datastructure.stack.cs19;

/**
 *
 * @author ziad
 *
 */
public class SNode {
	/**
	 * @serialField element
	 */
	private Object element;
	/**
	 * @serialField nextnode
	 */
	private SNode next;

	/**
	 * @serialData empty constructor
	 */
	public SNode() {

	}

	/**
	 *
	 * @param obj
	 *            element
	 * @param n
	 *            next node
	 */
	public SNode(final Object obj, final SNode n) {
		element = obj;
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
	public SNode getNext() {
		return next;
	}

	/**
	 *
	 * @param newEle
	 *            the new elemnt set
	 */
	public void setElement(final Object newEle) {
		this.element = newEle;
	}

	/**
	 *
	 * @param newNxt
	 *            the new node set
	 */
	public void setNext(final SNode newNxt) {
		this.next = newNxt;
	}
}
