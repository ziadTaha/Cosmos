package eg.edu.alexu.csd.datastructure.linkedList.cs19_cs50;

import eg.edu.alexu.csd.datastructure.linkedList.IPolynomialSolver;

/**
 *
 * @author ziad_mostafa
 *
 */
public class MyPolynomialSolver implements IPolynomialSolver {

	/**
	 * @serialField list for poly A
	 */
	MyLinkedList listA = new MyLinkedList();
	/**
	 * @serialField list for poly B
	 */
	MyLinkedList listB = new MyLinkedList();
	/**
	 * @serialField list for poly C
	 */
	MyLinkedList listC = new MyLinkedList();
	/**
	 * @serialField list for poly R
	 */
	MyLinkedList listR = new MyLinkedList();

	@Override
	public void setPolynomial(final char poly, final int[][] terms) {
		// TODO Auto-generated method stub

		MyLinkedList pList = new MyLinkedList();
		for (int i = 0; i < terms.length; i++) {
			PolyNode temp = new PolyNode(terms[i][0], terms[i][1]);
			pList.add(temp);
		}
		for (int i = 0; i < terms.length; i++) {
			for (int j = i + 1; j < pList.size(); j++) {
				PolyNode temp = new PolyNode();
				PolyNode temp2 = new PolyNode();
				temp = (PolyNode) pList.get(i);
				temp2 = (PolyNode) pList.get(j);
				if (temp.ex == temp2.ex) {
					temp.co += temp2.co;
					pList.set(i, temp);
					pList.remove(j);
					j--;

				}

			}
		}
		for (int i = 0; i < terms.length; i++) {
			for (int j = i + 1; j < pList.size(); j++) {
				PolyNode temp = new PolyNode();
				PolyNode temp2 = new PolyNode();
				temp = (PolyNode) pList.get(i);
				temp2 = (PolyNode) pList.get(j);
				if (temp.ex < temp2.ex) {
					pList.set(i, temp2);
					pList.set(j, temp);
				}

			}
		}
		if (poly == 'A') {
			clearPolynomial('A');
			for (int i = 0; i < pList.size(); i++) {
				listA.add(pList.get(i));
			}
		} else if (poly == 'B') {
			clearPolynomial('B');
			for (int i = 0; i < pList.size(); i++) {
				listB.add(pList.get(i));
			}
		} else if (poly == 'C') {
			clearPolynomial('C');
			for (int i = 0; i < pList.size(); i++) {
				listC.add(pList.get(i));
			}
		} else if (poly == 'R') {
			clearPolynomial('R');
			for (int i = 0; i < pList.size(); i++) {
				listR.add(pList.get(i));
			}
		} else {
			throw new RuntimeException();
		}
	}

	@Override
	public String print(final char poly) {
		// TODO Auto-generated method stub
		String st = new String();	int check = 0;
		if (poly == 'A') {
			if (!listA.isEmpty()) {
				check = 1;
			}
			for (int i = 0; i < listA.size(); i++) {
PolyNode temp = new PolyNode();	temp = (PolyNode) listA.get(i);
				if (temp.co > 0 && !st.equals("")) {
					st += "+";
				}
				if (temp.ex == 0 && temp.co != 0) {
					st += temp.co;
} else if (temp.co == 1 && temp.ex != 0 && temp.co != 0) {
					if (temp.ex == 1) {
						st += "x";
					} else {
						st += "x^";	st += temp.ex;
					}
				} else if (temp.co == -1 && temp.ex != 0
						&& temp.co != 0) {
					if (temp.ex == 1) {
						st += "-x";
					} else {
						st += "-x^";	st += temp.ex;
					}
				} else if (temp.ex != 0 && temp.co != 0) {
					st += temp.co;
					if (temp.ex == 1) {
						st += "x";
					} else {
						st += "x^";	st += temp.ex;
					}
				}
			}
		}
		if (poly == 'B') {
			if (!listB.isEmpty()) {
				check = 1;
			}
			for (int i = 0; i < listB.size(); i++) {
PolyNode temp = new PolyNode();	temp = (PolyNode) listB.get(i);
				if (temp.co > 0 && !st.equals("")) {
					st += "+";
				}
				if (temp.ex == 0 && temp.co != 0) {
					st += temp.co;
				} else if (temp.co == 1 && temp.ex != 0
						&& temp.co != 0) {
					if (temp.ex == 1) {
						st += "x";
					} else {
						st += "x^";	st += temp.ex;
					}
				} else if (temp.co == -1 && temp.ex != 0
						&& temp.co != 0) {
					if (temp.ex == 1) {
						st += "-x";
					} else {
						st += "-x^";	st += temp.ex;
					}
				} else if (temp.ex != 0 && temp.co != 0) {
					st += temp.co;
					if (temp.ex == 1) {
						st += "x";
					} else {
						st += "x^";	st += temp.ex;
					}
				}
			}
		} else if (poly == 'C') {
			if (!listC.isEmpty()) {
				check = 1;
			}
			for (int i = 0; i < listC.size(); i++) {
PolyNode temp = new PolyNode();	temp = (PolyNode) listC.get(i);
				if (temp.co > 0 && !st.equals("")) {
					st += "+";
				}
				if (temp.ex == 0 && temp.co != 0) {
					st += temp.co;
				} else if (temp.co == 1 && temp.ex != 0
						&& temp.co != 0) {
					if (temp.ex == 1) {
						st += "x";
					} else {
						st += "x^";	st += temp.ex;
					}
				} else if (temp.co == -1 && temp.ex != 0
						&& temp.co != 0) {
					if (temp.ex == 1) {
						st += "-x";
					} else {
						st += "-x^";	st += temp.ex;
					}
				} else if (temp.ex != 0 && temp.co != 0) {
					st += temp.co;
					if (temp.ex == 1) {
						st += "x";
					} else {
						st += "x^";	st += temp.ex;
					}
				}
			}
		} else if (poly == 'R') {
			if (!listR.isEmpty()) {
				check = 1;
			}
			for (int i = 0; i < listR.size(); i++) {
PolyNode temp = new PolyNode();	temp = (PolyNode) listR.get(i);
				if (temp.co > 0 && !st.equals("")) {
					st += "+";
				}
				if (temp.ex == 0 && temp.co != 0) {
					st += temp.co;
				} else if (temp.co == 1 && temp.ex != 0
						&& temp.co != 0) {
					if (temp.ex == 1) {
						st += "x";
					} else {
						st += "x^";	st += temp.ex;
					}
				} else if (temp.co == -1 && temp.ex != 0
						&& temp.co != 0) {
					if (temp.ex == 1) {
						st += "-x";
					} else {
						st += "-x^"; st += temp.ex;
					}
				} else if (temp.ex != 0 && temp.co != 0) {
					st += temp.co;
					if (temp.ex == 1) {
						st += "x";
					} else {
						st += "x^"; st += temp.ex;
					}
				}
			}
		}
		if (st.equals("")) {
			if (check == 0) {
				return null;
			} else {
				return "";
			}
		} else {
			return st;
		}
	}

	@Override
	public void clearPolynomial(final char poly) {
		// TODO Auto-generated method stub
		if (poly == 'A') {
			listA.clear();
		} else if (poly == 'B') {
			listB.clear();
		} else if (poly == 'C') {
			listC.clear();
		} else if (poly == 'R') {
			listR.clear();
		} else {
			throw new RuntimeException();
		}

	}

	@Override
	public float evaluatePolynomial(final char poly,
			final float value) {
		// TODO Auto-generated method stub
		float val = 0;
		if (poly == 'A') {
			if (listA.isEmpty()) {
				throw new RuntimeException();
			}
			for (int i = 0; i < listA.size(); i++) {
				PolyNode temp = new PolyNode();
				temp = (PolyNode) listA.get(i);
				if (temp.co != 0) {
				val +=
				(temp.co * (Math.pow(value, temp.ex)));
				}
			}
		} else if (poly == 'B') {
			if (listB.isEmpty()) {
				throw new RuntimeException();
			}
			for (int i = 0; i < listB.size(); i++) {
				PolyNode temp = new PolyNode();
				temp = (PolyNode) listB.get(i);
				if (temp.co != 0) {
					val +=
				    (temp.co * (Math.pow(value, temp.ex)));
				}
			}
		} else if (poly == 'C') {
			if (listC.isEmpty()) {
				throw new RuntimeException();
			}
			for (int i = 0; i < listC.size(); i++) {
				PolyNode temp = new PolyNode();
				temp = (PolyNode) listC.get(i);
				if (temp.co != 0) {
					val +=
					(temp.co * (Math.pow(value, temp.ex)));
				}
			}
		} else if (poly == 'R') {
			if (listR.isEmpty()) {
				throw new RuntimeException();
			}
			for (int i = 0; i < listR.size(); i++) {
				PolyNode temp = new PolyNode();
				temp = (PolyNode) listR.get(i);
				if (temp.co != 0) {
					val +=
					(temp.co * (Math.pow(value, temp.ex)));
				}
			}
		}
		return val;
	}

	@Override
	public int[][] add(final char poly1, final char poly2) {
		// TODO Auto-generated method stub
		MyLinkedList pList = new MyLinkedList();
		MyLinkedList pList2 = new MyLinkedList();
		MyLinkedList pListR = new MyLinkedList();
		if (poly1 == 'A') {
			pList = listA;
		} else if (poly1 == 'B') {
			pList = listB;
		} else if (poly1 == 'C') {
			pList = listC;
		} else {
			throw new RuntimeException();
		}
		if (poly2 == 'A') {
			pList2 = listA;
		} else if (poly2 == 'B') {
			pList2 = listB;
		} else if (poly2 == 'C') {
			pList2 = listC;
		} else {
			throw new RuntimeException();
		}
		if (pList.isEmpty() || pList2.isEmpty()) {
			throw new RuntimeException();
		}
		clearPolynomial('R');
		for (int i = 0; i < pList.size(); i++) {
			PolyNode temp = new PolyNode();
			temp = (PolyNode) pList.get(i);
			if (temp.co != 0) {
				pListR.add(temp);
			}
		}
		for (int i = 0; i < pList2.size(); i++) {
			PolyNode temp = new PolyNode();
			temp = (PolyNode) pList2.get(i);
			if (temp.co != 0) {
				pListR.add(temp);
			}
		}
		if (pListR.isEmpty()) {
			PolyNode temp = new PolyNode(0, 0);
			pListR.add(temp);
		}
		for (int i = 0; i < pListR.size(); i++) {
			for (int j = i + 1; j < pListR.size(); j++) {
				PolyNode temp = new PolyNode();
				PolyNode temp2 = new PolyNode();
				PolyNode temp3 = new PolyNode();
				temp = (PolyNode) pListR.get(i);
				temp2 = (PolyNode) pListR.get(j);
				temp3.co = temp.co + temp2.co;
				temp3.ex = temp.ex;
				if (temp.ex == temp2.ex) {
					pListR.set(i, temp3);
					pListR.remove(j);
					j--;
				}

			}
		}
		for (int i = 0; i < pListR.size(); i++) {
			for (int j = i + 1; j < pListR.size(); j++) {
				PolyNode temp = new PolyNode();
				PolyNode temp2 = new PolyNode();
				temp = (PolyNode) pListR.get(i);
				temp2 = (PolyNode) pListR.get(j);
				if (temp.ex < temp2.ex) {
					pListR.set(i, temp2);
					pListR.set(j, temp);
				}

			}
		}
		int[][] result = new int[pListR.size()][2];
		for (int i = 0; i < pListR.size(); i++) {
			PolyNode temp = new PolyNode();
			temp = (PolyNode) pListR.get(i);
			result[i][0] = temp.co;
			result[i][1] = temp.ex;
		}
		setPolynomial('R', result);
		return result;
	}

	@Override
	public int[][] subtract(final char poly1,
			final char poly2) {
		// TODO Auto-generated method stub
		MyLinkedList pList = new MyLinkedList();
		MyLinkedList pList2 = new MyLinkedList();
		MyLinkedList pListR = new MyLinkedList();
		int check = 0;
		if (poly1 == 'A') {
			pList = listA;
		} else if (poly1 == 'B') {
			pList = listB;
		} else if (poly1 == 'C') {
			pList = listC;
		} else {
			throw new RuntimeException();
		}
		if (poly2 == 'A') {
			pList2 = listA;
		} else if (poly2 == 'B') {
			pList2 = listB;
		} else if (poly2 == 'C') {
			pList2 = listC;
		} else {
			throw new RuntimeException();
		}
		if (pList.isEmpty() || pList2.isEmpty()) {
			throw new RuntimeException();
		}
		clearPolynomial('R');
		for (int i = 0; i < pList.size(); i++) {
			PolyNode temp = new PolyNode();
			temp = (PolyNode) pList.get(i);
			if (temp.co != 0) {
				pListR.add(temp);
			}
		}
		for (int i = 0; i < pList2.size(); i++) {
			PolyNode temp = new PolyNode();
			temp = (PolyNode) pList2.get(i);
			PolyNode temp2 = new PolyNode();
			temp2.co = -temp.co;
			temp2.ex = temp.ex;
			if (temp.co != 0) {
				pListR.add(temp2);
			}
		}

		for (int i = 0; i < pListR.size(); i++) {
			for (int j = i + 1; j < pListR.size(); j++) {
				PolyNode temp = new PolyNode();
				PolyNode temp2 = new PolyNode();
				PolyNode temp3 = new PolyNode();
				temp = (PolyNode) pListR.get(i);
				temp2 = (PolyNode) pListR.get(j);
				temp3.co = temp.co + temp2.co;
				temp3.ex = temp.ex;
				if (temp.ex == temp2.ex) {
					pListR.set(i, temp3);
					pListR.remove(j);
					j--;
				}

			}
		}
		for (int i = 0; i < pListR.size(); i++) {
			for (int j = i + 1; j < pListR.size(); j++) {
				PolyNode temp = new PolyNode();
				PolyNode temp2 = new PolyNode();
				temp = (PolyNode) pListR.get(i);
				temp2 = (PolyNode) pListR.get(j);
				if (temp.ex < temp2.ex) {
					pListR.set(i, temp2);
					pListR.set(j, temp);
				}

			}
		}
		for (int i = 0; i < pListR.size(); i++) {
			PolyNode temp = new PolyNode();
			temp = (PolyNode) pListR.get(i);
			if (temp.co != 0) {
				check = 1;
			}

		}
		if (check == 0) {
			for (int i = 0; i < pListR.size(); i++) {
				PolyNode temp = new PolyNode();
				temp = (PolyNode) pListR.get(i);
				if (temp.co == 0) {
					temp.ex = 0;
				}

			}
		} else {
			for (int i = 0; i < pListR.size(); i++) {
				PolyNode temp = new PolyNode();
				temp = (PolyNode) pListR.get(i);
				if (temp.co == 0) {
					pListR.remove(i);
					i--;
				}

			}
		}
		for (int i = 0; i < pListR.size(); i++) {
			for (int j = i + 1; j < pListR.size(); j++) {
				PolyNode temp = new PolyNode();
				PolyNode temp2 = new PolyNode();
				PolyNode temp3 = new PolyNode();
				temp = (PolyNode) pListR.get(i);
				temp2 = (PolyNode) pListR.get(j);
				temp3.co = temp.co + temp2.co;
				temp3.ex = temp.ex;
				if (temp.ex == temp2.ex) {
					pListR.set(i, temp3);
					pListR.remove(j);
					j--;
				}

			}
		}
		if (pListR.isEmpty()) {
			PolyNode temp = new PolyNode(0, 0);
			pListR.add(temp);
		}

		int[][] result = new int[pListR.size()][2];
		for (int i = 0; i < pListR.size(); i++) {
			PolyNode temp = new PolyNode();
			temp = (PolyNode) pListR.get(i);
			result[i][0] = temp.co;
			result[i][1] = temp.ex;
		}
		setPolynomial('R', result);
		return result;
	}

	@Override
	public int[][] multiply(final char poly1,
			final char poly2) {
		// TODO Auto-generated method stub
		MyLinkedList pList = new MyLinkedList();
		MyLinkedList pList2 = new MyLinkedList();
		MyLinkedList pListR = new MyLinkedList();
		if (poly1 == 'A') {
			pList = listA;
		} else if (poly1 == 'B') {
			pList = listB;
		} else if (poly1 == 'C') {
			pList = listC;
		} else {
			throw new RuntimeException();
		}
		if (poly2 == 'A') {
			pList2 = listA;
		} else if (poly2 == 'B') {
			pList2 = listB;
		} else if (poly2 == 'C') {
			pList2 = listC;
		} else {
			throw new RuntimeException();
		}
		if (pList.isEmpty() || pList2.isEmpty()) {
			throw new RuntimeException();
		}
		clearPolynomial('R');
		for (int i = 0; i < pList.size(); i++) {
			for (int j = 0; j < pList2.size(); j++) {
				PolyNode temp = new PolyNode();
				PolyNode temp2 = new PolyNode();
				PolyNode temp3 = new PolyNode();
				temp = (PolyNode) pList.get(i);
				temp2 = (PolyNode) pList2.get(j);
				if (temp.co == 0) {
					break;
				}
				temp3.co = (temp.co * temp2.co);
				temp3.ex = temp.ex + temp2.ex;
				pListR.add(temp3);
			}

		}
		if (pListR.isEmpty()) {
			PolyNode temp = new PolyNode(0, 0);
			pListR.add(temp);
		}
		for (int i = 0; i < pListR.size(); i++) {
			for (int j = i + 1; j < pListR.size(); j++) {
				PolyNode temp = new PolyNode();
				PolyNode temp2 = new PolyNode();
				PolyNode temp3 = new PolyNode();
				temp = (PolyNode) pListR.get(i);
				temp2 = (PolyNode) pListR.get(j);
				temp3.co = temp.co + temp2.co;
				temp3.ex = temp.ex;
				if (temp.ex == temp2.ex) {
					pListR.set(i, temp3);
					pListR.remove(j);
					j--;
				}

			}
		}
		for (int i = 0; i < pListR.size(); i++) {
			for (int j = i + 1; j < pListR.size(); j++) {
				PolyNode temp = new PolyNode();
				PolyNode temp2 = new PolyNode();
				temp = (PolyNode) pListR.get(i);
				temp2 = (PolyNode) pListR.get(j);
				if (temp.ex < temp2.ex) {
					pListR.set(i, temp2);
					pListR.set(j, temp);
				}

			}
		}
		int[][] result = new int[pListR.size()][2];
		for (int i = 0; i < pListR.size(); i++) {
			PolyNode temp = new PolyNode();
			temp = (PolyNode) pListR.get(i);
			result[i][0] = temp.co;
			result[i][1] = temp.ex;
		}
		setPolynomial('R', result);
		return result;
	}

}
