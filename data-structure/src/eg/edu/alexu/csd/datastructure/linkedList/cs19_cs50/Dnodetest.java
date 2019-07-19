package eg.edu.alexu.csd.datastructure.linkedList.cs19_cs50;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
/**
 *
 * @author mostafa
 *
 */

public class Dnodetest {
	/**
	 * @serialData test 1
	 */
	@Test
	public void test() {
		final int x = 5;
		DNode n = new DNode(x, null, null);
		DNode s = new DNode(x, null, n);
		assertEquals(s.getnext(), n);

	}
	/**
	 * @serialData test 2
	 */
	@Test
	public void test2() {
		final int x = 5;
		DNode n = new DNode(null, null, null);
		n.setElement(x);
		DNode s = new DNode(null, n, null);
		assertEquals(s.getpre(), n);
		assertEquals(n.getElement(), x);
	}
	/**
	 * @serialData test 3
	 */
	@Test
	public void test3() {
		final char x = 'a';
		DNode n = new DNode(null, null, null);
		DNode s = new DNode(null, null, null);
		n.setpre(s);
		n.setElement(x);
		s.setnext(n);
		assertEquals(n.getpre(), s);
		assertEquals(s.getnext(), n);
		assertEquals(n.getElement(), x);
	}
	/**
	 * @serialData test 4
	 */
	@Test
	public void test4() {
		final char x = 'a';
		DNode n = new DNode(x, null, null);
		DNode s = new DNode(x, null, n);
		n.setpre(s);
		n.setElement(x);
		assertEquals(n.getElement(), x);
	}
}
