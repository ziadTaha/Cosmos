package eg.edu.alexu.csd.datastructure.mailServer.cs04_cs06_cs19;

/**
 *
 * @author Z.A.Z
 *
 */
public class PNode {
	/**
	 * @serialField element
	 */
	private Object element;
	/**
	 * @serialField key
	 */
	private int key;
	/**
	 * @serialField next
	 */
	private PNode next;

	/**
	 *
	 * @param ele
	 *            element to add
	 * @param n
	 *            next PNode
	 * @param k
	 *            key
	 */
	public PNode(final Object ele, final  PNode n, final int k) {
		element = ele;
		next = n;
		key = k;
	}

	/**
	 *
	 * @param newEle
	 *            the new element
	 */
	void setEle(final Object newEle) {
		element = newEle;
	}

	/**
	 *
	 * @param newNxt
	 *            new next
	 */
	void setNext(final PNode newNxt) {
		next = newNxt;
	}

	/**
	 *
	 * @return element
	 */
	Object getELe() {
		return element;
	}

	/**
	 *
	 * @return next node
	 */
	PNode getNxt() {
		return next;
	}

	/**
	 *
	 * @return key
	 */
	int getKey() {
		return key;
	}

	/**
	 *
	 * @param k
	 *            key
	 */
	void setKey(final int k) {
		key = k;
	}

}
