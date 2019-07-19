package eg.edu.alexu.csd.datastructure.linkedList.cs19_cs50;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
/**
 *
 * @author ziad & mostafa
 *
 */

public class Nodetest {

	/**
	 * @serialData test 1
	 */
	@Test
	public void test() {
		final int x = 5;
		Node n = new Node(x, null);
		Node s = new Node(x, n);
		assertEquals(s.getNext(), n);
	}

	/**
	 * @serialData test 2
	 */
	@Test
	public void test2() {
		final int x = 5;
		Node n = new Node(null, null);
		Node s = new Node(x, n);
		n.setNext(s);
		assertEquals(n.getNext(), s);
		assertEquals(n.getNext().getElement(), x);
	}
	/**
	 * @serialData test 3
	 */
	@Test
	public void test3() {
		final int x = 5;
		Node n = new Node(null, null);
		n.setElement(x);
		assertEquals(n.getElement(), x);
	}

}
