package eg.edu.alexu.csd.datastructure.linkedList.cs19_cs50;

/**
 *
 * @author ziad_mostafa
 *
 */
public class PolyNode {
	/**
	 * @serialField coofecient
	 */
	int co;
	/**
	 * @serialField exponent
	 */
	int ex;
/**
 *
 * @param c
 * Coefficient set
 * @param e
 * exponent set
 */
	public PolyNode(final int c, final int e) {
		co = c;
		ex = e;
	}
/**
 * @serialData  empty constructor
 */
	public PolyNode() {

	}
}
