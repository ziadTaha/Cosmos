package eg.edu.alexu.csd.datastructure.linkedList.cs19_cs50;

/**
 *
 * @author ziad_mostafa
 *
 */
public class DNode {
	/**
	 * @serialField next
	 */
	DNode next, pre;
	/**
	 * @serialField ele
	 */
	public Object element;

	/**
	 *
	 * @param value
	 * element value
	 * @param p
	 * previous node
	 * @param n
	 * next node
	 */
	public DNode(final Object value, final DNode p, final DNode n) {
		element = value;
		pre = p;
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
	 * @return next
	 */
	public DNode getnext() {
		return next;
	}

	/**
	 *
	 * @return next
	 */
	public DNode getpre() {
		return pre;
	}

	/**
	 *
	 * @param n
	 *            next node
	 */
	public void setnext(final DNode n) {
		// TODO Auto-generated method stub
		next = n;

	}

	/**
	 *
	 * @param p
	 *            previous node
	 */
	public void setpre(final DNode p) {
		// TODO Auto-generated method stub
		pre = p;

	}

	/**
	 *
	 * @param e
	 *            element
	 */
	public void setElement(final Object e) {
		element = e;
	}

}
