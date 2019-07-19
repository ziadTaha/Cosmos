package eg.edu.alexu.csd.datastructure.mailServer.cs04_cs06_cs19;

import eg.edu.alexu.csd.datastructure.mailServer.IPriorityQueue;

/**
 *
 * @author Z.A.Z
 *
 */
public class MypriorityQueue implements IPriorityQueue {
	/**
	 * @serialField size
	 */
	int size;
	/**
	 * @serialField first in PQ
	 */
	PNode min;
	/**
	 * @serialField last in PQ
	 */
	PNode max;

	/**
	 * @serialData constructor
	 */
	public MypriorityQueue() {
		// TODO Auto-generated constructor stub
		min = null;
		max = null;
		size = 0;
	}
/**
 * @param item
 * element value
 * @param key
 * priority key
 */
	@Override
	public void insert(final Object item, final  int key) {
		// TODO Auto-generated method stub
		if (key <= 0) {
			throw new RuntimeException();
		}
		if (size == 0) {
			PNode temp = new PNode(item, max, key);

			min = temp;

		} else if (size == 1) {
			PNode temp = new PNode(item, null, key);
			if (min.getKey() <= key) {
				max = temp;
				min.setNext(max);
				if (min.getNxt() == null) {
					throw new RuntimeException();
				}
System.out.println("ssa:" + min.getNxt().getELe());
			} else {
max = new PNode(min.getELe(), null, min.getKey());
				min = temp;
				min.setNext(max);

				if (min.getNxt() == null) {
					throw new RuntimeException();
				}
			}

		} else if (max.getKey() <= key) {
			PNode temp = new PNode(item, null, key);
			max.setNext(temp);
			max = temp;
System.out.println("ssa:" + min.getNxt().getELe());
		} else {
			PNode temp = new PNode(item, null, key);

			if (min.getKey() > key) {
				PNode temp2 = new PNode(min.getELe(),
min.getNxt(), min.getKey());
				temp.setNext(temp2);
				min = temp;
			} else {
				temp = min;
				PNode temp2 = temp.getNxt();
				while (temp2.getKey() <= key) {
					if (temp2.getNxt() == null) {

						break;
					}
					temp = temp.getNxt();
					temp2 = temp2.getNxt();

				}
				if (temp2.getNxt() == null) {
					if (temp2.getKey() <= key) {
						temp = temp.getNxt();
						temp2 = temp2.getNxt();
					}
				}
				PNode temp3 = new PNode(item, temp2, key);
				temp.setNext(temp3);

			}
		}
		size++;

	}
/**
 * @return old min
 */
	@Override
	public Object removeMin() {
		// TODO Auto-generated method stub
		if (isEmpty()) {
			throw new RuntimeException();
		}
		Object temp = min.getELe();
		min = min.getNxt();
		size--;
		return temp;
	}
/**
 * @return min
 */
	@Override
	public Object min() {
		// TODO Auto-generated method stub
		if (isEmpty()) {
			throw new RuntimeException();
		}
		return min.getELe();
	}
/**
 * @serialData is empty
 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (size == 0);
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
