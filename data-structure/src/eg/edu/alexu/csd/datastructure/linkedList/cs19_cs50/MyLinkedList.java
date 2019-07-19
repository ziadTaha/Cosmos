package eg.edu.alexu.csd.datastructure.linkedList.cs19_cs50;

import eg.edu.alexu.csd.datastructure.linkedList.ILinkedList;

/**
 *
 * @author ziad_mostafa
 *
 */
public class MyLinkedList implements ILinkedList {
	/**
	 * @serialField head
	 */
	private Node head;
	/**
	 * @serialField size
	 */
	private long size;

	/**
	 * @serialData contsructor
	 */
	public MyLinkedList() {
		// TODO Auto-generated constructor stub
		head = new Node(null, null);
		setSize(0);
	}

	/**
	 * @serialData adding elements in certain index
	 * @param index
	 *            index index in list
	 * @param element
	 *            object add
	 */
	@Override
	public void add(final int index, final Object element) {

		// TODO Auto-generated method stub
		Node temp = head;
		Node added = new Node(element, null);
		int index1 = index;
		if (index1 > size || index1 < 0) {
			throw new RuntimeException();
		}
		if (index1 == 0) {
			if (head.getElement() == null) {
				head.setElement(element);
				head.setNext(null);
			} else {
				added.setNext(head);
				head = added;

			}
			setSize(getSize() + 1);
		} else if (index1 < size) {
			while (index1 > 1) {
				temp = temp.getNext();
				index1--;
			}
			added.setNext(temp.getNext());
			temp.setNext(added);
			setSize(getSize() + 1);

		} else if (index1 == size) {
			while (temp.getNext() != null) {
				temp = temp.getNext();
			}
			temp.setNext(added);
			setSize(getSize() + 1);
		}

	}

	/**
	 *
	 * @param object
	 *            element
	 */
	@Override
	public void add(final Object element) {
		// TODO Auto-generated method stub
		Node temp;
		Node added = new Node(element, null);
		temp = head;
		if (head.getElement() == null) {
			head.setElement(element);
			setSize(getSize() + 1);
		} else {
			while (temp.getNext() != null) {
				temp = temp.getNext();
			}
			temp.setNext(added);
			setSize(getSize() + 1);
		}
	}

	@Override
	public Object get(final int index) {
		// TODO Auto-generated method stub
		Node temp;
		int check = 0;
		temp = head;
		if (index > size || index < 0) {
			throw new RuntimeException();
		}
		for (int i = 0; i < index; i++) {
			if (temp.getNext() == null) {
				check = 1;
				break;
			}
			temp = temp.getNext();
		}
		if (check == 0) {
			return temp.getElement();
		} else {
			return null;
		}
	}

	@Override
	public void set(final int index, final Object element) {
		// TODO Auto-generated method stub
		Node temp;
		temp = head;
		if (index > size || index < 0) {
			throw new RuntimeException();
		}
		if (index < size) {
			for (int i = 0; i < index; i++) {
				temp = temp.getNext();
			}
		}
		temp.setElement(element);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		head.setNext(null);
		head.setElement(null);
		setSize(0);
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if (head.getElement() == null) {
			return true;
		}
		return false;
	}

	@Override
	public void remove(final int index) {
		// TODO Auto-generated method stub
		Node temp;
		Node temp2;
		temp = head;
		if (index >= size || index < 0) {
			throw new RuntimeException();
		}
		if (index == 0) {
			head = temp.getNext();
			temp.setNext(null);
		} else {
			for (int i = 0; i < index - 1; i++) {
				temp = temp.getNext();
			}
			temp2 = temp.getNext();
			temp.setNext(temp2.getNext());
			temp2.setNext(null);
		}
		setSize(getSize() - 1);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return (int) size;
	}

	@Override
	public ILinkedList sublist(final int fromIndex,
			final int toIndex) {
		// TODO Auto-generated method stub
		MyLinkedList list = new MyLinkedList();
		Node temp;
		if (fromIndex >= size || toIndex >= size
				|| fromIndex < 0 || toIndex < 0) {
			throw new RuntimeException();
		}
		temp = head;
		for (int i = 0; i < fromIndex; i++) {
			temp = temp.getNext();
		}
		for (int i = fromIndex; i <= toIndex; i++) {
			list.add(temp.getElement());
			temp = temp.getNext();

		}
		return list;
	}

	@Override
	public boolean contains(final Object o) {
		// TODO Auto-generated method stub
		Node temp;
		int check = 0;
		temp = head;
		if (temp.getElement().equals(o)) {
			check = 1;
		}
		while (temp.getNext() != null) {
			if (temp.getElement().equals(o)) {
				check = 1;
				break;
			}
			temp = temp.getNext();
		}
		if (check == 0) {
			if (temp.getElement().equals(o)) {
				return true;
			}
			return false;

		}
		return true;

	}
/**
 *
 * @return size
 */
	public long getSize() {
		return size;
	}
/**
 *
 * @param size1
 * size after set
 */
	public void setSize(final long size1) {
		this.size = size1;
	}

}
