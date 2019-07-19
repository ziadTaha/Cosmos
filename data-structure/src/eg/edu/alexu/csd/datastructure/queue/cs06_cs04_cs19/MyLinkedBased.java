package eg.edu.alexu.csd.datastructure.queue.cs06_cs04_cs19;

import eg.edu.alexu.csd.datastructure.queue.ILinkedBased;
import eg.edu.alexu.csd.datastructure.queue.IQueue;

/**
 *
 * @author zeny ziad 3abdllah
 *
 */
public class MyLinkedBased implements IQueue, ILinkedBased {
	/**
	 * @serialField head
	 */
	Node head;
	/**
	 * @serialField tail
	 */
	Node tail;
	/**
	 * @serialField size
	 */
	int size;

	/**
	 * @serialData constructor
	 */
	public MyLinkedBased() {
		head = new Node(null, tail);
		tail = new Node(null, null);
		size = 0;
	}

	/**
	 * @serialData enqueue
	 * @param object
	 *            item
	 */
	@Override
	public void enqueue(final Object item) {
		// TODO Auto-generated method stub
		Node temp = new Node(item, null);
		if (size == 0) {
			head = temp;
		} else {
			tail.setNext(temp);
		}
		tail = temp;
		size++;
	}

	/**
	 * @return the first element
	 */
	@Override
	public Object dequeue() {
		// TODO Auto-generated method stub
		if (isEmpty()) {
			throw new RuntimeException();
		}
		Node temp = new Node(null, null);
		temp = head;
		head = head.getNext();
		size--;
		return temp.getElement();

	}

	/**
	 * @serialData isEmpty
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
