package eg.edu.alexu.csd.datastructure.stack.cs19;

import eg.edu.alexu.csd.datastructure.stack.IExpressionEvaluator;
/**
 *
 * @author ziad
 *
 */
public class MyExpressionEvaluator implements IExpressionEvaluator {

	@Override
	public String infixToPostfix(final String expression) {
		// TODO Auto-generated method stub
		StringBuilder postfix = new StringBuilder();
		MyStack op = new MyStack();
		int operandsC = 0;
		int operationsC = 0;
		for (int i = 0; i < expression.length(); i++) {
			if (expression.charAt(i) == ' ') {
				continue;
			}
			if (Character.isDigit(expression.charAt(i))) {
				while (i < expression.length()
 && Character.isDigit(expression.charAt(i))) {
					postfix.append(expression.charAt(i));
					i++;
				}
				postfix.append(" ");
				operandsC++;
				i--;
				continue;
			} else if (expression.charAt(i) != '+'
					&& expression.charAt(i) != '-'
					&& expression.charAt(i) != '*'
					&& expression.charAt(i) != '/'
					&& expression.charAt(i) != '('
					&& expression.charAt(i) != ')') {
				postfix.append(expression.charAt(i) + " ");
				operandsC++;
			} else if (expression.charAt(i) == '(') {
				op.push(expression.charAt(i));
			} else if (expression.charAt(i) == ')') {
				while ((char) op.peek() != '(') {
					if (op.isEmpty()) {
						throw new RuntimeException();
					}
					postfix.append(op.pop() + " ");
				}
				op.pop();
			} else if (expression.charAt(i) == '+'
					|| expression.charAt(i) == '-') {

				while (!op.isEmpty()
						&& (char) op.peek() != '(') {
					postfix.append(op.pop() + " ");
				}
				op.push(expression.charAt(i));
				operationsC++;
			} else if (expression.charAt(i) == '/'
					|| expression.charAt(i) == '*') {
				while (!op.isEmpty() && (char) op.peek() != '+'
						&& (char) op.peek() != '-'
						&& (char) op.peek() != '(') {
					postfix.append(op.pop() + " ");
				}
				op.push(expression.charAt(i));
				operationsC++;
			}

		}
		while (!op.isEmpty()) {
			postfix.append((char) op.pop());
			if (!op.isEmpty()) {
				postfix.append(" ");
			}
		}
		if (operationsC != operandsC - 1) {
			throw new RuntimeException();
		}
		if (postfix.equals("")) {
			throw new RuntimeException();
		}
		return postfix.toString();
	}

	@Override
	public int evaluate(final String expression) {
		// TODO Auto-generated method stub
		MyStack valS = new MyStack();
		float x, y, value;

		for (int i = 0; i < expression.length(); i++) {
			if (expression.charAt(i) == ' ') {
				continue;
			} else if (Character.isDigit(expression.charAt(i))) {
				String con = new String();
				while (i < expression.length()
 && Character.isDigit(expression.charAt(i))) {
					con += expression.charAt(i);
					i++;
				}
				valS.push(Float.parseFloat(con));
				i--;
				continue;
			} else if (expression.charAt(i) == '+') {
				x = (float) valS.pop();
				y = (float) valS.pop();
				value = y + x;
				valS.push(value);
			} else if (expression.charAt(i) == '-') {
				x = (float) valS.pop();
				y = (float) valS.pop();
				value = y - x;
				valS.push(value);
			} else if (expression.charAt(i) == '*') {
				x = (float) valS.pop();
				y = (float) valS.pop();
				value = y * x;
				valS.push(value);
			} else if (expression.charAt(i) == '/') {
				x = (float) valS.pop();
				y = (float) valS.pop();
				value = y / x;
				valS.push(value);
			} else {
				throw new RuntimeException();
			}
		}
		if (valS.isEmpty()) {
			throw new RuntimeException();
		}
		value = (float) valS.pop();
		return (int) value;
	}

}
