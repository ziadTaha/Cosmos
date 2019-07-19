package eg.edu.alexu.csd.datastructure.stack.cs19;

import eg.edu.alexu.csd.datastructure.stack.IStack;

/**
 *
 * @author ziad
 *
 */
public class MyStack implements IStack {
	/**
	 * @serialField top
	 *                  element
	 */
	private SNode top;
	/**
	 * @serialField size
	 *                  of stack
	 */
	private int size;

	/**
	 * @serialData constructor
	 */
	public MyStack() {
		// TODO Auto-generated constructor stub
		top = null;
		size = 0;
	}

	/**
	 * @throws exception
	 *             when empty
	 * @return the top element and pop it
	 */
	@Override
	public Object pop() {
		// TODO Auto-generated method stub
		if (isEmpty()) {
			throw new RuntimeException();
		}
		Object temp = top.getElement();
		top = top.getNext();
		size--;
		return temp;
	}

	/**
	 * @throws exception
	 *             when empty
	 * @return the top element
	 */
	@Override
	public Object peek() {
		// TODO Auto-generated method stub
		if (isEmpty()) {
			throw new RuntimeException();
		}
		return top.getElement();
	}

	/**
	 * @serialData add elemnt at top of stack
	 * @param element
	 */
	@Override
	public void push(final Object element) {
		// TODO Auto-generated method stub
		SNode temp = new SNode(element, top);
		top = temp;
		size++;
	}

	/**
	 * @return true or false
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if (size == 0) {
			return true;
		}
		return false;
	}

	/**
	 * @return size
	 */

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

}
