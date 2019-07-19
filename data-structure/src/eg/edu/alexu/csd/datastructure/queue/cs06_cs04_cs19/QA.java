package eg.edu.alexu.csd.datastructure.queue.cs06_cs04_cs19;

import eg.edu.alexu.csd.datastructure.queue.IArrayBased;
import eg.edu.alexu.csd.datastructure.queue.IQueue;

/**
 *
 * @author zeny abdalah zol
 *
 */
public class QA implements IQueue, IArrayBased {
	/**
	 * @serialField s
	 */
	private int s;
	/**
	 * @serialField q[]
	 */
	private Object[] q;
	/**
	 * @serialField num
	 */
	private int num = 0;
	/**
	 * @serialField r,
	 *                  f
	 */
	private int r = 0, f = 0;

	/**
	 * @serialData enqueue
	 */
	@Override
	public void enqueue(final Object item) {
		// TODO Auto-generated method stub
		if (num == s) {
			throw new RuntimeException("Queue is full");
		} else {
			q[r] = item;
			if (r == s - 1) {
				r = 0;
			} else {
				r++;
			}
			num++;
		}
	}

	/**
	 * @serialData dequeue
	 * @return returned
	 */
	@Override
	public Object dequeue() {
		// TODO Auto-generated method stub
		if (num == 0) {
			throw new RuntimeException("Queue is Empty");
		} else {
			Object returned = q[f];
			if (f == s - 1) {
				f = 0;
			} else {
				f++;
			}
			num--;
			return returned;
		}
	}

	/**
	 * @return is empty
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return num == 0;
	}

	/**
	 * @return size
	 */
	@Override
	public int size() {
		// TODO Auto-generated methossssd stub
		return num;
	}
/**
 *
 * @param size
 * size of array
 */
	public QA(final int size) {
		s = size;
		q = new Object[s];
	}

}
