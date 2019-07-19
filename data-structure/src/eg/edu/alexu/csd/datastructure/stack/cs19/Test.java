package eg.edu.alexu.csd.datastructure.stack.cs19;
/**
 *
 * @author ziad
 *
 */
public class Test {
	/**
	 *
	 * @param args
	 *  main arg
	 */
	public static void main(final String[] args) {
		/*MyStack o = new MyStack();
		o.push(0);
		o.push(1);
		System.out.println(o.peek());

		System.out.println(o.isEmpty());
		*/
		MyExpressionEvaluator o = new MyExpressionEvaluator();
		System.out.println(o.infixToPostfix("A+B*C"));
		System.out.println(o.evaluate("24 2 / 3 - 4 2 * +"));
	}
}
