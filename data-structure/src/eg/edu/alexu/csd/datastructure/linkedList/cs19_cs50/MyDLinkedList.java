package eg.edu.alexu.csd.datastructure.linkedList.cs19_cs50;

import eg.edu.alexu.csd.datastructure.linkedList.ILinkedList;

/**
 *
 * @author ziad_mostafa
 *
 */
public class MyDLinkedList implements ILinkedList {
	/**
	 * @serialField head ;
	 */
	private DNode head;
	/**
	 * @serialField tail
	 */
	private DNode  tail;
	/**
	 * @serialField size
	 */
	private int size;
/**
 * @serialData initialization
 */
	public MyDLinkedList() {
		head = new DNode(null, null, null);
		tail = new DNode(null, head, null);
		head.setnext(tail);
		setSize(0);
	}

	@Override
	public void add(final int index, final Object element) {
		// TODO Auto-generated method stub
		if (index > size || index < 0) {
			throw new RuntimeException();
		} else if (index == 0) {
			DNode add = new DNode(element, null, head.getnext());
			add.setpre(head);
			head.getnext().setpre(add);
			head.setnext(add);
			setSize(getSize() + 1);
		} else {
			DNode temp = head.getnext();
			DNode add = new DNode(element, null, null);
			for (int i = 0; i < index; i++) {
				temp = temp.getnext();
			}
			add.setnext(temp);
			temp.getpre().setnext(add);
			add.setpre(temp.getpre());
			temp.setpre(add);
			setSize(getSize() + 1);
		}
	}

	@Override
	public void add(final Object element) {
		// TODO Auto-generated method stub
		DNode temp = tail.getpre();
		DNode add = new DNode(element, null, null);
		add.setnext(tail);
		temp.setnext(add);
		add.setpre(temp);
		tail.setpre(add);
		setSize(getSize() + 1);
	}

	@Override
	public Object get(final int index) {
		// TODO Auto-generated method stub
		if (index >= size || index < 0) {
			throw new RuntimeException();
		}
		DNode temp = head.getnext();
		for (int i = 0; i < index; i++) {
			temp = temp.getnext();
		}
		return temp.getElement();
	}

	@Override
	public void set(final int index, final Object element) {
		// TODO Auto-generated method stub
		if (index >= size || index < 0) {
			throw new RuntimeException();
		}
		DNode temp = head.getnext();
		for (int i = 0; i < index; i++) {
			temp = temp.getnext();
		}
		temp.setElement(element);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		head.setnext(tail);
		tail.setpre(head);
		setSize(0);
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if (head.getnext() == tail) {
			return true;
		}
		return false;
	}

	@Override
	public void remove(final int index) {
		if (index >= size || index < 0) {
			throw new RuntimeException();
		} else {
			DNode temp = head.getnext();
			for (int i = 0; i < index; i++) {
				temp = temp.getnext();
			}
			DNode p = temp.getpre();
			DNode n = temp.getnext();
			n.setpre(p);
			p.setnext(n);
			temp.setpre(null);
			temp.setnext(null);
			setSize(getSize() - 1);
		}
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public ILinkedList sublist(final int fromIndex
			, final int toIndex) {
		if (fromIndex >= size || toIndex >= size
				|| fromIndex < 0 || toIndex < 0) {
			throw new RuntimeException();
		} else {
			MyDLinkedList list = new MyDLinkedList();
			DNode temp;
			temp = head.getnext();
			for (int i = 0; i < fromIndex; i++) {
				temp = temp.getnext();
			}
			for (int i = fromIndex; i <= toIndex; i++) {
				list.add(temp.getElement());
				temp = temp.getnext();

			}
			return list;
		}
		// TODO Auto-generated method stub

	}

	@Override
	public boolean contains(final Object o) {
		DNode temp = head.getnext();
		boolean x = false;
		for (int i = 0; i < size; i++) {
			if (temp.getElement().equals(o)) {
				x = true;
				break;
			}
			temp = temp.getnext();
		}
		return x;
	}
/**
 *
 * @return size of list
 */
	public int getSize() {
		return size;
	}
/**
 *
 * @param size1
 *  new set size
 */
	public void setSize(final int size1) {
		this.size = size1;
	}

}
