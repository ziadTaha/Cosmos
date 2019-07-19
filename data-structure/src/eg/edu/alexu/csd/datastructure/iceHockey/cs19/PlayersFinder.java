package eg.edu.alexu.csd.datastructure.iceHockey.cs19;

import java.awt.Point;
import java.util.ArrayList;
import eg.edu.alexu.csd.datastructure.iceHockey.IPlayersFinder;
/**
 * @author ZiadTaha
 */
public class PlayersFinder implements IPlayersFinder {
	/**
	 * @serialField
	 * counts squares
	 */
	private int counter = 0;
	/**
	 * @serialField
	 * center point
	 */
	private Point center = new Point(0, 0);
	/**
	 * @serialField
	 * highest point
	 */
	private Point high = new Point(0, 0);
	/**
	 * @serialField
	 * lowest point
	 */
	private Point low = new Point(0, 0);
	/**
	 * @serialField grid
	 */
	private char[][] pGrid;
	/**
	 * @serialField
	 * area of square
	 */
	final int area = 4;
/**
 * @param photo
 *       photo for players
 * @return grid
 *   checking grid
 */
	char[][] arrayToString(final String[] photo) {
		char[][] grid = new char[photo.length][photo[0].length()];
		for (int i = 0; i < photo.length; i++) {
			for (int j = 0; j < photo[0].length(); j++) {
				grid[i][j] = photo[i].charAt(j);
			}
		}
		return grid;
	}
/**
 * @serialField checking arrays
 */
	private int[][] visited;
/**
 * @serialData setting array to zeros
 * @param a  the array needing to set zeros
 */
	void setToZeros(final int[][] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				a[i][j] = 0;
			}
		}
	}
	/**
	 * @param r row
	 * @param c colomn
	 * @param x team
	 */
	void dFS(final int r, final int c, final char x) {
		if (r >= pGrid.length || c >= pGrid[0].length
				|| r < 0 || c < 0 || visited[r][c] == 1) {
			return;
		} else if (pGrid[r][c] != x) {
			return;
		} else {
			dPoints(r, c);
			visited[r][c] = 1;
			counter++;
			dFS(r + 1, c, x);
			dFS(r - 1, c, x);
			dFS(r, c + 1, x);
			dFS(r, c - 1, x);
		}
	}
	/**
	 * @param r row
	 * @param c colomn
	 */
	void dPoints(final int r, final int c) {
		if (r * 2 < low.y) {
			low.y = r * 2;

		} else if ((r + 1) * 2 > high.y) {
			high.y = (r + 1) * 2;
		}
		if (c * 2 < low.x) {
			low.x = c * 2;
		} else if ((c + 1) * 2 > high.x) {
			high.x = (c + 1) * 2;
		}
	}
/**
 * @serialField
 * arraylist to store center points
 */
	ArrayList<Point> validPoints = new ArrayList<Point>();

	@Override
	public Point[] findPlayers(final String[] photo,
			final int team, final int threshold) {
		// TODO Auto-generated method stub
		if (photo == null) {
			return null;
		} else {
			int i, j;
			pGrid = arrayToString(photo);
			visited = new int[pGrid.length][pGrid[0].length];
			setToZeros(visited);
			for (i = 0; i < pGrid.length; i++) {
				for (j = 0; j < pGrid[i].length; j++) {
					counter = 0;
if (Character.getNumericValue(pGrid[i][j]) == team) {
						low.x = j * 2;
						low.y = i * 2;
						high.x = (j + 1) * 2;
						high.y = (i + 1) * 2;
						dFS(i, j, pGrid[i][j]);
if (counter * area >= threshold) {
center.x = (high.x + low.x) / 2;
center.y = (high.y + low.y) / 2;
validPoints.add(new Point(center.x, center.y));
						}
					}
				}
			}
			Point[] pArray = new Point[validPoints.size()];
			pArray = validPoints.toArray(pArray);
			int size = pArray.length;
			Point x = null;
			for (i = 0; i < size; i++) {
				for (j = i + 1; j < size; j++) {
					if (pArray[j].x < pArray[i].x) {
						x = pArray[i];
						pArray[i] = pArray[j];
						pArray[j] = x;
					}
				}
			}
			for (i = 0; i < size; i++) {
				for (j = i + 1; j < size; j++) {
					if (pArray[j].x == pArray[i].x) {

						if (pArray[j].y < pArray[i].y) {
							x = pArray[i];
							pArray[i] = pArray[j];
							pArray[j] = x;
						}
					}
				}
			}
			validPoints.clear();
			return pArray;
		}
	}
}
