package eg.edu.alexu.csd.datastructure.linkedList.cs19_cs50;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import eg.edu.alexu.csd.datastructure.linkedList.ILinkedList;

/**
 *
 * @author mostafa
 *
 */

public class Mlink {
	/**
	 * @serialData test 1
	 */
	@Test
	public void test() {
		MyLinkedList instance = new MyLinkedList();
		final int x1 = 1;
		final int x2 = 3;
		final int x3 = 5;
		instance.add(x1);
		instance.add(x2);
		instance.add(x3);
		instance.set(x1, 'F');
		assertEquals('F', instance.get(1));
	}

	/**
	 * @serialData test 2
	 */
	@Test
	public void test2() {
		MyLinkedList instance = new MyLinkedList();
		int x0 = 0;
		int x1 = 1;
		int x2 = 2;
		instance.add('a');
		instance.add('b');
		instance.add('c');
		instance.add('d');
		ILinkedList temp = instance.sublist(x1, x2);
		assertEquals(temp.get(x0), instance.get(x1));
		assertEquals(temp.get(x1), instance.get(x2));
	}

	/**
	 * @serialData test 3
	 */
	@Test
	public void test3() {
		MyLinkedList object = new MyLinkedList();
		// MyLinkedList object = new MyLinkedList();
		final int j = 6;
		for (int i = 0; i < j; i++) {
			object.add(i, i);
		}
		final int x1 = 0;
		final int x2 = 1;
		final int x3 = 2;
		object.add(x1, x1);
		object.add(x2, x2);
		object.add(x3, x3);
		final int x4 = 1;
		final int x5 = 3;
		final int x6 = 5;
		final int x7 = 4;
		final int x8 = 2;
		object.remove(x4);
		object.remove(x5);
		object.remove(x6);
		object.remove(x4);
		object.set(x7, x8);
		MyLinkedList object2 = new MyLinkedList();
		object2 = (MyLinkedList) object.sublist(x4, x5);
		assertEquals(object.get(1), object2.get(0));
		assertEquals(object.get(2), object2.get(1));
	}

	/**
	 * @serialData test 4
	 */
	@Test
	public void test4() {
		MyLinkedList object = new MyLinkedList();
		final int j = 6;
		for (int i = 0; i < j; i++) {
			object.add(i, i);
		}
		final int x0 = 0;
		final int x1 = 1;
		final int x2 = 2;
		final int x3 = 3;
		final int x4 = 4;
		final int x5 = 5;
		object.add(x0, x0);
		object.add(x1, x1);
		object.add(x2, x2);
		object.remove(x1);
		object.remove(x3);
		object.remove(x5);
		object.remove(x1);
		object.set(x4, x2);
		MyLinkedList object2 = new MyLinkedList();
		object2 = (MyLinkedList) object.sublist(x1, x3);
		assertEquals(object.get(x1), object2.get(x0));
		assertEquals(object.get(x2), object2.get(x1));
	}

	/**
	 * @serialData test 5
	 */
	@Test
	public void test5() {
		MyLinkedList c = new MyLinkedList();
		final int j = 3;
		for (int i = 0; i < j; i++) {
			c.add(i);
		}
		final int x0 = 0;
		final int x1 = 1;
		final int x2 = 2;
		final int x3 = 3;
		final int x4 = 4;
		final int x7 = 7;
		final int x8 = 9;
		c.add(x0, x3);
		c.add(x4, x4);
		c.set(x1, x7);
		c.set(x4, x8);
		MyLinkedList d = new MyLinkedList();
		d.add(x3);
		d.add(x7);
		d.add(x1);
		d.add(x2);
		d.add(x8);
		final int k = 5;
		for (int i = 0; i < k; i++) {
			assertEquals(c.get(i), d.get(i));
		}
	}
}
